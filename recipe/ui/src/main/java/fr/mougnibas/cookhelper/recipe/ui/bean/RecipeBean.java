package fr.mougnibas.cookhelper.recipe.ui.bean;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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

  @Inject
  private RecipeManager recipeManager;

  private String recipeName;

  /**
   * Loaded recipe.
   */
  private Recipe recipe;

  /**
   * Initialize the recipe bean.
   * 
   * @throws IOException If there is something wrong sending 404 error responses.
   */
  public void init() throws IOException {

    // Recipe name is required
    if (recipeName == null || recipeName.isEmpty()) {
      FacesContext.getCurrentInstance().getExternalContext().responseSendError(404,
          "Recipe's name must be set.");
      return;
    }

    // Try to get the recipe
    recipe = recipeManager.get(recipeName);

    // A recipe is required to show the page content.
    if (recipe == null) {
      FacesContext.getCurrentInstance().getExternalContext().responseSendError(404,
          "The following recipe was not found : " + recipeName);
    }
  }

  /**
   * Get the loaded recipe.
   * 
   * @return the loaded recipe.
   */
  public Recipe getRecipe() {
    return recipe;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }
}
