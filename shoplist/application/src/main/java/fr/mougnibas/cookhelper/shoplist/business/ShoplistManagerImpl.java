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

package fr.mougnibas.cookhelper.shoplist.business;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.RecipeForShoplist;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeClientQualifier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Shop list manager implementation.
 * 
 * @author Yoann
 */
@Stateless
public class ShoplistManagerImpl implements ShoplistManager {
  
  /**
   * JaxRS client based recipe manager.
   */
  @JaxrsRecipeClientQualifier
  @Inject
  private RecipeManager recipeManager;

  @Override
  public RecipeForShoplist[] getAllRecipes() {
    
    // List of recipe for shop list to return
    List<RecipeForShoplist> list = new ArrayList<>();
    
    // Get the list from the recipe service, and transform it to a local recipe
    String[] recipeNames = recipeManager.listAllRecipeNames();
    for (String recipeName : recipeNames) {
      Recipe recipe = recipeManager.getByName(recipeName);
      RecipeForShoplist transform = new RecipeForShoplist(recipe);
      list.add(transform);
    }
    
    // Return the list as array
    return list.toArray(new RecipeForShoplist[list.size()]);
  }

  @Override
  public Shoplist makeShopList(String... recipesName) {
    // TODO Auto-generated method stub
    return null;
  }

}
