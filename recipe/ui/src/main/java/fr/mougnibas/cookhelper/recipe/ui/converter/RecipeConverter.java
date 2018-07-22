package fr.mougnibas.cookhelper.recipe.ui.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import fr.mougnibas.cookhelper.recipe.contract.client.RecipeManagerJaxRsClientImpl;
import fr.mougnibas.cookhelper.recipe.contract.exception.FetchException;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

/**
 * A recipe converter.
 * 
 * @author Yoann
 */
@FacesConverter(value = "recipeConverter", forClass = Recipe.class)
public class RecipeConverter implements Converter {

  /**
   * Recipe manager api.
   */
  // TODO Use @Inject when JSF 2.3 when it will be released
  private RecipeManager recipeManager = new RecipeManagerJaxRsClientImpl();

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {

    // Try to get the recipe
    Recipe recipe = recipeManager.get(value);

    // Throw an exception if the recipe is null
    if (recipe == null) {
      context.addMessage(null, new FacesMessage("The following recipe is not found : " + value));
      return null;
    }

    // Return the recipe
    return recipe;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {

    // The string to return (may be null)
    String toReturn;

    // Get the recipe name, or null
    if (value == null) {
      toReturn = null;
    } else {
      toReturn = ((Recipe) value).getName();
    }

    // Return it
    return toReturn;
  }

}
