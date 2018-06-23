package fr.mougnibas.cookhelper.shoplist.contract.client;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.exception.FetchException;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS client implementation of the service.
 * 
 * @author Yoann Mougnibas.
 */
public class ShoplistManagerJaxRsClientImpl implements ShoplistManager {

  /**
   * Microservice URL.
   */
  private String serviceUri;

  /**
   * Initialize the client with the default URI.
   */
  public ShoplistManagerJaxRsClientImpl() {
    this("http://cook-helper-recipe-microprofile:8100");
  }

  /**
   * Initialize the client with a custom URI.
   * 
   * @param uri
   *          custom URI.
   */
  public ShoplistManagerJaxRsClientImpl(String uri) {
    serviceUri = uri;
  }

  @Override
  public Shoplist getShoplist(String... recipesName) {

    // The shoplist to return
    Shoplist shoplist;

    try {

      // To be conform to varargs method signature...
      Object[] values = recipesName;

      // Get the recipe
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(serviceUri).path("/").queryParam("name", values);
      byte[] actualBytes = target.request(MediaType.APPLICATION_OCTET_STREAM).get(byte[].class);
      shoplist = (Shoplist) new ObjectInputStream(new ByteArrayInputStream(actualBytes))
          .readObject();

    } catch (Exception ex) {

      // Wrap the exception, and throw it
      String msg = "Exception while trying to call recipe microservice";
      throw new FetchException(msg, ex);
    }

    // Return the recipe
    return shoplist;

  }

}
