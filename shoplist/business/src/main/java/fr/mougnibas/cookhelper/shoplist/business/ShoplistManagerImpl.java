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

import javax.ejb.Stateless;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.model.RecipeForShoplist;
import fr.mougnibas.cookhelper.shoplist.model.Shoplist;

/**
 * Shop list manager implementation.
 * 
 * @author Yoann
 */
@Stateless
public class ShoplistManagerImpl implements ShoplistManager {

  @Override
  public RecipeForShoplist[] getAllRecipes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Shoplist makeShopList(String... recipesName) {
    // TODO Auto-generated method stub
    return null;
  }

}
