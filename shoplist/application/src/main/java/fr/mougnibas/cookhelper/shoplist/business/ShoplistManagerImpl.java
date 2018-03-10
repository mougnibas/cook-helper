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

import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.RecipeForShoplist;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeClientQualifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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

  // TODO So much inefficiency here...
  @Override
  public Shoplist makeShopList(String... recipesName) {

    // Get all recipes concerned by the recipes list parameter
    List<Recipe> recipes = new ArrayList<>(recipesName.length);
    for (String recipeName : recipesName) {
      Recipe recipe = recipeManager.getByName(recipeName);
      recipes.add(recipe);
    }

    // We need a custom comparator, to compare on name and food unit
    Comparator<RawMaterial> customComparator = new Comparator<RawMaterial>() {
      @Override
      public int compare(RawMaterial o1, RawMaterial o2) {
        String highlyInefficiant1 = o1.getName() + o1.getFoodUnit();
        String highlyInefficiant2 = o2.getName() + o2.getFoodUnit();
        return highlyInefficiant1.compareTo(highlyInefficiant2);
      }
    };

    // Count each materials
    Map<RawMaterial, Integer> mapMaterialsCount = new TreeMap<>(customComparator);
    for (Recipe recipe : recipes) {
      for (RawMaterial material : recipe.getMaterials()) {
        if (!mapMaterialsCount.containsKey(material)) {
          mapMaterialsCount.put(material, material.getFoodNumber());
        } else {
          Integer actualCount = mapMaterialsCount.get(material);
          Integer newCount = actualCount + material.getFoodNumber();
          mapMaterialsCount.put(material, newCount);
        }
      }
    }

    // Create the materials to put on the shoplist
    List<RawMaterial> materials = new ArrayList<>(mapMaterialsCount.size());
    for (Entry<RawMaterial, Integer> currentMaterialEntry : mapMaterialsCount.entrySet()) {
      Integer foodNumber = currentMaterialEntry.getValue();
      RawMaterial currentMaterial = currentMaterialEntry.getKey();
      RawMaterial material = new RawMaterial(currentMaterial.getName(),
          currentMaterial.getFoodFamily(), currentMaterial.getFoodUnit(), foodNumber);
      materials.add(material);
    }

    // Create and return the shoplist
    Shoplist shoplist = new Shoplist(materials.toArray(new RawMaterial[materials.size()]));
    return shoplist;
  }

}
