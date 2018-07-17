package fr.mougnibas.cookhelper.recipe.ui.bean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

/**
 * Application bean, connected to recipe microservices.
 * 
 * @author Yoann
 */
@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

  /**
   * Serialization version number.
   */
  private static final long serialVersionUID = -194533274984958335L;

  /**
   * Recipe manager api.
   */
  @Inject
  private RecipeManager recipeManager;

  /**
   * Get all recipes.
   * 
   * @return all recipes.
   */
  public Recipe[] getAllRecipes() {
    return recipeManager.get();
  }

  /**
   * Get a recipe, by name.
   * 
   * @param recipeName The recipe name.
   * @return A recipe, or null if not found.
   */
  public Recipe getRecipeByName(String recipeName) {
    return recipeManager.get(recipeName);
  }
}
