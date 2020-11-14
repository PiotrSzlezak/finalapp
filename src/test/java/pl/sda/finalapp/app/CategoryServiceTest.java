package pl.sda.finalapp.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CategoryServiceTest {

    @Test
    public void shouldMarkSelectedAndOpened(){
        String searchText = "lek ";
        final CategoryService categoryService = new CategoryService();

        final List<CategoryDTO> categories = categoryService.findCategories(searchText);
        final CategoryDTO category1 = getCategory(categories, 1);
        final CategoryDTO category4 = getCategory(categories, 4);

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

        final List<CategoryDTO> categories = categoryService.findCategories(searchText3);
        final CategoryDTO category1 = getCategory(categories, 1);
        final CategoryDTO category4 = getCategory(categories, 4);

        Assertions.assertEquals(false, category1.getState().isOpened());
        Assertions.assertEquals(false, category4.getState().isSelected());
        Assertions.assertEquals(false, category1.getState().isSelected());
        Assertions.assertEquals(false, category4.getState().isOpened());
    }

    private CategoryDTO getCategory(List<CategoryDTO> categories, Integer catId) {
        return categories.stream()
                .filter(c -> c.getId().equals(catId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID = "+catId));
    }

}