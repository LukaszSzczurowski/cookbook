package ls.cookbook.domain;

public class Recipe {

    private String recipeTitle;
    private String recipeDescription;
    private RecipeCategory category;
    private String url;

    public Recipe(String recipeTitle, String recipeDescription, RecipeCategory category, String url) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.category = category;
        this.url = url;
    }

    public Recipe() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }
}
