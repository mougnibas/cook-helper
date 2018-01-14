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

import javax.inject.Inject;
import javax.ws.rs.Path;

import fr.mougnibas.cookhelper.recipe.contract.RecipeManager;

/**
 * Get a list of recipe names.
 * 
 * @author Yoann
 */
@Path("list")
public class RecipeList {
  
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
  public String[] listAllRecipeNames() {
    String[] allRecipeNames = recipeManager.listAllRecipeNames();
    return allRecipeNames;
  }
}
