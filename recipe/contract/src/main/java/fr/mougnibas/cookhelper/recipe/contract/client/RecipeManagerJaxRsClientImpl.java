package fr.mougnibas.cookhelper.recipe.contract.client;

import fr.mougnibas.cookhelper.recipe.contract.exception.EndpointConfigurationException;
import fr.mougnibas.cookhelper.recipe.contract.exception.FetchException;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.contract.util.EndpointsReader;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;

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
  private URI serviceUri;

  /**
   * Initialize the client with the target URI defined in "endpoints.properties".
   */
  public RecipeManagerJaxRsClientImpl() {
    serviceUri = EndpointsReader.get().getTarget();
  }

  /**
   * Initialize the client with the target URI defined in parameters.
   * 
   * @param protocol       Target URI protocol.
   * @param host           Target URI host.
   * @param port           Target URI port.
   * @param webContextRoot Target URI web context root.
   */
  public RecipeManagerJaxRsClientImpl(String protocol, String host, int port,
      String webContextRoot) {

    // Generate the target URL.
    try {
      serviceUri = new URI(protocol, null, host, port, webContextRoot, null, null);
    } catch (URISyntaxException ex) {
      String msg = "Can't create target endpoint URL.";
      throw new EndpointConfigurationException(msg, ex);
    }
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
