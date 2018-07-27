/*
  © Copyright 2017-2018 Yoann MOUGNIBAS
  
  This file is part of Cook-Helper.
  
  Cook-Helper is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  Cook-Helper is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with Cook-Helper. If not, see <http://www.gnu.org/licenses/>
 */

package fr.mougnibas.cookhelper.recipe.contract.util;

import fr.mougnibas.cookhelper.recipe.contract.exception.EndpointConfigurationException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

/**
 * A simple class to provide a way to get endpoints URL.
 * 
 * @author Yoann
 *
 */
public final class EndpointsReader {

  /**
   * Unique class instance.
   */
  private static final EndpointsReader INSTANCE = new EndpointsReader();

  /**
   * Get the unique class instance.
   * 
   * @return the unique class instance.
   */
  public static EndpointsReader get() {
    return INSTANCE;
  }

  /**
   * THe target endpoint URI.
   */
  private URI target;

  /**
   * Private constructor, for singleton pattern.
   */
  private EndpointsReader() {

    // Get a resource bundle
    // FIXME Rename "endpoints.properties" to "endpoints-recipe.properties"
    ResourceBundle rb = ResourceBundle.getBundle("endpoints");

    // Get server host data
    String protocol = rb.getString("PROTOCOL");
    String host = rb.getString("HOST");
    Integer port = Integer.parseInt(rb.getString("PORT"));

    // Get the web context root
    String webContextRoot = rb.getString("WEB_CONTEXT_ROOT");

    // Generate the target URL.
    try {
      target = new URI(protocol, null, host, port, webContextRoot, null, null);
    } catch (URISyntaxException ex) {
      String msg = "Can't create target endpoint URL.";
      throw new EndpointConfigurationException(msg, ex);
    }
  }

  /**
   * Get the target endpoint URL.
   * 
   * @return The target endpoint URL.
   */
  public URI getTarget() {
    return target;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("EndpointsReader [target=");
    builder.append(target);
    builder.append("]");
    return builder.toString();
  }
}
