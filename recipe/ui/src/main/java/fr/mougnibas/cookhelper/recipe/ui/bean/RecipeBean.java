package fr.mougnibas.cookhelper.recipe.ui.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;

/**
 * Application bean, connected to recipe microservices.
 * 
 * @author Yoann
 */
@Named
@RequestScoped
public class RecipeBean implements Serializable {

  /**
   * Serialization version number.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Loaded recipe.
   */
  private Recipe recipe;

  /**
   * Get the loaded recipe.
   * 
   * @return the loaded recipe.
   */
  public Recipe getRecipe() {
    return recipe;
  }

  /**
   * Set the recipe.
   * 
   * @param recipe The recipe.
   */
  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }
}
