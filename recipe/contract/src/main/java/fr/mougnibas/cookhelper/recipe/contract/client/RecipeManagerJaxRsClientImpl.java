package fr.mougnibas.cookhelper.recipe.contract.client;

import fr.mougnibas.cookhelper.recipe.contract.exception.FetchException;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS client implementation of the service.
 * 
 * @author Yoann
 */
public class RecipeManagerJaxRsClientImpl implements RecipeManager {

  /**
   * Microservice URL.
   */
  private String serviceUri;

  /**
   * Initialize the client with the default URI.
   */
  public RecipeManagerJaxRsClientImpl() {
    this("http://cook-helper-recipe-microprofile:8080");
  }

  /**
   * Initialize the client with a custom URI.
   * 
   * @param uri
   *          custom URI.
   */
  public RecipeManagerJaxRsClientImpl(String uri) {
    serviceUri = uri;
  }

  @Override
  public Recipe[] get() {

    // The recipes to return
    Recipe[] recipes;

    try {

      // Get the recipe
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(serviceUri).path("/");
      byte[] actualBytes = target.request(MediaType.APPLICATION_OCTET_STREAM).get(byte[].class);
      recipes = (Recipe[]) new ObjectInputStream(new ByteArrayInputStream(actualBytes))
          .readObject();

    } catch (Exception ex) {

      // Wrap the exception, and throw it
      String msg = "Exception while trying to call recipe microservice";
      throw new FetchException(msg, ex);
    }

    // Return the recipes
    return recipes;
  }

  @Override
  public Recipe get(String recipeName) {

    // The recipe to return
    Recipe recipe;

    try {

      // Get the recipe
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(serviceUri).path(recipeName);
      byte[] actualBytes = target.request(MediaType.APPLICATION_OCTET_STREAM).get(byte[].class);
      recipe = (Recipe) new ObjectInputStream(new ByteArrayInputStream(actualBytes)).readObject();

    } catch (Exception ex) {

      // Wrap the exception, and throw it
      String msg = "Exception while trying to call recipe microservice";
      throw new FetchException(msg, ex);
    }

    // Return the recipe
    return recipe;
  }

}
