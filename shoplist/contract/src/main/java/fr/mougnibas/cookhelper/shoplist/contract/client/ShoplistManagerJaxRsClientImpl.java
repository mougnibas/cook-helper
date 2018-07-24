package fr.mougnibas.cookhelper.shoplist.contract.client;

import fr.mougnibas.cookhelper.shoplist.contract.exception.EndpointConfigurationException;
import fr.mougnibas.cookhelper.shoplist.contract.exception.FetchException;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;
import fr.mougnibas.cookhelper.shoplist.contract.service.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.util.EndpointsReader;

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
 * @author Yoann Mougnibas.
 */
public class ShoplistManagerJaxRsClientImpl implements ShoplistManager {

  /**
   * Microservice URI.
   */
  private URI serviceUri;

  /**
   * Initialize the client with the target URI defined in "endpoints.properties".
   */
  public ShoplistManagerJaxRsClientImpl() {
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
  public ShoplistManagerJaxRsClientImpl(String protocol, String host, int port,
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
