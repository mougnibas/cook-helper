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

package fr.mougnibas.cookhelper.recipe.jaxrs;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Get a list of recipe names.
 * 
 * @author Yoann
 */
@Path("/")
public class GetAllRecipes {
  
  /**
   * Recipe manager implementation.
   */
  @Inject
  private RecipeManager recipeManager;

  /**
   * List all of the recipe names.
   * 
   * @return all of the recipe names.
   */
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  @GET
  public byte[] listAllRecipeNames() throws Exception {
    
    Recipe[] recipes = recipeManager.get();
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(recipes);
    
    return baos.toByteArray();
  }
}
