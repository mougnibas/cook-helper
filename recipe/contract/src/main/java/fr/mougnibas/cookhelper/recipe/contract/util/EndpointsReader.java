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

import fr.mougnibas.cookhelper.recipe.contract.exception.InitializationException;

import java.net.MalformedURLException;
import java.net.URL;
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
   * The recipe list URL.
   */
  private String recipeListUrl;

  /**
   * The recipe get URL.
   */
  private String recipeGetUrl;

  /**
   * Private constructor, for singleton pattern.
   */
  private EndpointsReader() {

    // Get a resource bundle
    ResourceBundle rb = ResourceBundle.getBundle("endpoints");

    // Get server host data
    String protocol = rb.getString("PROTOCOL");
    String host = rb.getString("HOST");
    Integer port = Integer.parseInt(rb.getString("PORT"));

    // Get the endpoints
    String recipeList = rb.getString("RECIPE_LIST");
    String recipeGet = rb.getString("RECIPE_GET");

    // Get the web context root
    String webContextRoot = rb.getString("WEB_CONTEXT_ROOT");

    // Build the recipe list URL
    try {
      recipeListUrl = new URL(protocol, host, port, webContextRoot + "/" + recipeList).toString();
    } catch (MalformedURLException ex) {
      throw new InitializationException(ex);
    }

    // Build the recipe get URL
    try {
      recipeGetUrl = new URL(protocol, host, port, webContextRoot + "/" + recipeGet).toString();
    } catch (MalformedURLException ex) {
      throw new InitializationException(ex);
    }
  }

  /**
   * Get the recipe list URL.
   * 
   * @return the recipe list URL.
   */
  public String getRecipeListUrl() {
    return recipeListUrl;
  }

  /**
   * Get the recipe get URL.
   * 
   * @return the recipe get URL.
   */
  public String getRecipeGetUrl() {
    return recipeGetUrl;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("EndpointsReader [recipeListUrl=");
    builder.append(recipeListUrl);
    builder.append(", recipeGetUrl=");
    builder.append(recipeGetUrl);
    builder.append("]");
    return builder.toString();
  }
}
