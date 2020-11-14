package pl.sda.finalapp.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;

class CategoryServiceTest {

    @Test
    public void shouldMarkSelectedAndOpened(){
        String searchText = "lek ";
        final CategoryService categoryService = new CategoryService();

        final List<CategoryTreeDTO> categories = categoryService.findCategories(searchText);
        final CategoryTreeDTO category1 = getCategory(categories, 1);
        final CategoryTreeDTO category4 = getCategory(categories, 4);

        Assertions.assertEquals(true, category1.getState().isOpened());
        Assertions.assertEquals(true, category4.getState().isSelected());
        Assertions.assertEquals(false, category1.getState().isSelected());
        Assertions.assertEquals(false, category4.getState().isOpened());
    }

    @Test
    public void shouldMarkSelectedAndOpenedWithBlankString(){
//        String searchText1 = null; // null String
//        String searchText2 = ""; // empty String
        String searchText3 = " "; // blank String
        final CategoryService categoryService = new CategoryService();

        final List<CategoryTreeDTO> categories = categoryService.findCategories(searchText3);
        final CategoryTreeDTO category1 = getCategory(categories, 1);
        final CategoryTreeDTO category4 = getCategory(categories, 4);

        Assertions.assertEquals(false, category1.getState().isOpened());
        Assertions.assertEquals(false, category4.getState().isSelected());
        Assertions.assertEquals(false, category1.getState().isSelected());
        Assertions.assertEquals(false, category4.getState().isOpened());
    }

    private CategoryTreeDTO getCategory(List<CategoryTreeDTO> categories, Integer catId) {
        return categories.stream()
                .filter(c -> c.getId().equals(catId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID = "+catId));
    }

}