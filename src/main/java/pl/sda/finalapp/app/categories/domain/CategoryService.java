package pl.sda.finalapp.app.categories.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;
import pl.sda.finalapp.app.categories.persistence.CategoryFromFileDTO;
import pl.sda.finalapp.app.categories.persistence.CategoryDAO;
import pl.sda.finalapp.app.categories.persistence.CategoryRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryDAO categoryDAO = CategoryDAO.getInstance();
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryTreeDTO> findCategories(String searchText) {
        final List<CategoryTreeDTO> dtos = categoryDAO.getCategoryList().stream()
                .map(c -> c.toTreeDTO())
                .collect(Collectors.toList());

        if (searchText == null || searchText.isBlank()) {
            return dtos;
        }

        dtos.stream()
                .filter(c -> c.getText().trim().toLowerCase().contains(searchText.trim().toLowerCase()))
                .forEach(c -> {
                    c.setSelected(true);
                    openAllParents(c, dtos);
                });

        return dtos;
    }

    public void addCategory(String categoryName, Integer parentId) {
        final List<CategoryFromFileDTO> categoryFromFileDTOList = categoryDAO.getCategoryList();
        CategoryFromFileDTO newCategoryFromFileDTO = CategoryFromFileDTO.applyFromCategory(categoryName);
        newCategoryFromFileDTO.setParentId(parentId);
        categoryFromFileDTOList.add(newCategoryFromFileDTO);
    }

    public List<CategoryDTO> findAll() {
        return categoryDAO.getCategoryList().stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
    }

    public Optional<String> findCategoryNameById(Integer id) {
        return categoryDAO.getCategoryList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> c.getCategoryName());
    }

    private void openAllParents(CategoryTreeDTO child, List<CategoryTreeDTO> parents) {
        if (child.getParent().equals(CategoryTreeDTO.NO_PARENT_VALUE)) {
            return;
        }

        final Integer parentId = Integer.valueOf(child.getParent());

        parents.stream()
                .filter(p -> p.getId().equals(parentId))
                .findFirst()
                .map(p -> {
                    p.setOpened(true);
                    openAllParents(p, parents);
                    return p;
                });
    }

    @PostConstruct
    void initializeCategories() {
        if (categoryRepository.count() == 0) {
            CategoryDAO categoryDAO = CategoryDAO.getInstance();
            Map<Integer, Integer> oldChildAndOldParentIdsMap = new HashMap<>();
            Map<Integer, Integer> newIdToOldIdMap = new HashMap<>();
            final List<CategoryFromFileDTO> categoryDTOList = categoryDAO.getCategoryList();

            for (CategoryFromFileDTO dto : categoryDTOList) {
                oldChildAndOldParentIdsMap.put(dto.getId(), dto.getParentId());
                final Category category = new Category(dto.getCategoryName());
                final Category savedCategory = categoryRepository.save(category);
                newIdToOldIdMap.put(savedCategory.getId(), dto.getId());
            }
            for (Integer newId : newIdToOldIdMap.keySet()) {
                final Category category = categoryRepository.findById(newId).orElseThrow(() -> new RuntimeException("Kategoria o id " + newId + " nie znaleziona."));
                final Integer oldId = newIdToOldIdMap.get(newId);
                final Integer oldParentId = oldChildAndOldParentIdsMap.get(oldId);
                final Integer newParentId = newIdToOldIdMap.entrySet().stream()
                        .filter(e -> e.getValue().equals(oldParentId))
                        .map(e -> e.getKey())
                        .findFirst()
                        .orElse(null);
                categoryRepository.save(category.applyParentId(newParentId));
            }
        }
    }

}
