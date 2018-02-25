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

package fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.contract.util.EndpointsReader;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS implementation of recipe manager.
 * 
 * @author Yoann
 */
@Stateless
@JaxrsRecipeClientQualifier
public class JaxrsRecipeManagerImpl implements RecipeManager {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = -9122298788442430077L;
  
  /**
   * JAX-RS recipe endpoint of "list".
   */
  private String endpointRecipeList;

  /**
   * JAX-RS recipe endpoint of "get".
   */
  private String endpointRecipeGet;

  @PostConstruct
  protected void init() {
    EndpointsReader reader = EndpointsReader.get();
    endpointRecipeList = reader.getRecipeListUrl();
    endpointRecipeGet = reader.getRecipeGetUrl();
  }

  @Override
  public String[] listAllRecipeNames() {

    // The uri to call
    String uri = endpointRecipeList;

    // Make the call
    Client client = ClientBuilder.newClient();
    String[] list = client.target(uri).request(MediaType.APPLICATION_JSON).get(String[].class);

    // Return the result
    return list;
  }

  @Override
  public Recipe getByName(String recipeName) {

    // The uri to call
    String uri = endpointRecipeGet.replace("{SomeRecipeName}", recipeName);

    // Make the call
    Client client = ClientBuilder.newClient();
    Recipe recipe = client.target(uri).request(MediaType.APPLICATION_XML).get(Recipe.class);

    // Return the result
    return recipe;
  }
}
