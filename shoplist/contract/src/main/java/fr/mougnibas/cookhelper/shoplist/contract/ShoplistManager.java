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

package fr.mougnibas.cookhelper.shoplist.contract;

import fr.mougnibas.cookhelper.shoplist.model.RecipeForShoplist;
import fr.mougnibas.cookhelper.shoplist.model.Shoplist;

import javax.ejb.Local;

/**
 * Shoplist manager.
 * 
 * @author Yoann
 */
@Local
public interface ShoplistManager {

  /**
   * Get all recipes.
   * 
   * @return all recipes.
   */
  RecipeForShoplist[] getAllRecipes();

  /**
   * Compile a shoplist from the given recipes.
   * 
   * @param recipesName
   *          The list of recipe name to add to the shop list.
   * 
   * @return The shop list.
   */
  Shoplist makeShopList(String... recipesName);
}
