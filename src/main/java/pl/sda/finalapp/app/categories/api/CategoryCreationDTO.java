package pl.sda.finalapp.app.categories.api;

public class CategoryCreationDTO {

    private Integer parentId;
    private String categoryName;

    public Integer getParentId() {
        return parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
