package ls.cookbook.controller;

import ls.cookbook.domain.Recipe;
import ls.cookbook.domain.RecipeCategory;
import ls.cookbook.repository.CookbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CookbookController {

    private CookbookRepository cookbookRepository;

    @Autowired
    public CookbookController(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    @GetMapping("/")
    String showRecipes(@RequestParam(required = false, name = "kategoria") RecipeCategory category, Model model,
                       @RequestParam(required = false) String searchText) {

        List<Recipe> result;

        if (searchText != null) {
            result = cookbookRepository.findByTitleContains(searchText);
        } else {
            if (category != null) {
                result = cookbookRepository.findByCategory(category);
            } else {
                result = cookbookRepository.findAll();
            }
        }

        model.addAttribute("recipe", result);
        return "home";

    }

    @GetMapping("/recipe")
    String showAnimal(@RequestParam(name = "title") String recipeTitle, Model model) {
        Recipe recipe = cookbookRepository.getRecipe(recipeTitle);

        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/save")
    String save(Recipe recipe) {
        cookbookRepository.saveRecipe(recipe);
        return "redirect:/recipe?title=" + recipe.getRecipeTitle();
    }

    @GetMapping("/add")
    String addRecipe(Model model) {
        model.addAttribute("newRecipe", new Recipe());
        model.addAttribute("mode", "dodaj");
        return "formRecipe";
    }

    @GetMapping("/edit")
    String editForm(Model model, @RequestParam String title) {
        Recipe recipe = cookbookRepository.getRecipe(title);
        model.addAttribute("newRecipe", recipe);
        model.addAttribute("mode", "edit");
        return "formRecipe";
    }

    @PostMapping("/edytuj")
    String edit(Recipe recipe) {
        cookbookRepository.update(recipe);
        return "redirect:/recipe?title=" + recipe.getRecipeTitle();
    }
}
