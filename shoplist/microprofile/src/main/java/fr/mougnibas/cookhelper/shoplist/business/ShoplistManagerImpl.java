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
import fr.mougnibas.cookhelper.shoplist.contract.exception.RecipeNotFoundException;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.shoplist.contract.model.Material;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Shop list manager implementation.
 * 
 * @author Yoann
 */
public class ShoplistManagerImpl implements ShoplistManager {

  /**
   * JaxRS client based recipe manager.
   */
  private RecipeManager recipeManager;

  /**
   * Initialize it with a valid recipe manager implementation.
   * 
   * @param toInject
   *          A valid recipe manager implementation.
   */
  public ShoplistManagerImpl(RecipeManager toInject) {
    recipeManager = toInject;
  }

  // TODO So much inefficiency here...
  @Override
  public Shoplist getShoplist(String... recipesName) {

    // Get all recipes concerned by the recipes list parameter
    List<Recipe> recipes = new ArrayList<>(recipesName.length);
    for (String recipeName : recipesName) {
      Recipe recipe = recipeManager.get(recipeName);
      if (recipe == null) {
        throw new RecipeNotFoundException("recipe : '" + recipeName + "' is not found");
      }
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

    // Transform the materials
    List<Material> transformedMaterials = new ArrayList<>(materials.size());
    for (RawMaterial rawMaterial : materials) {
      Material transformedMaterial = MaterialTransformer.transform(rawMaterial);
      transformedMaterials.add(transformedMaterial);
    }

    // Create and return the shoplist
    return new Shoplist(transformedMaterials.toArray(new Material[transformedMaterials.size()]));
  }

  /**
   * An utility class to transform RawMaterial (from 'recipe contract') to Material (from 'shoplist
   * builder contract').
   * 
   * @author Yoann
   */
  private static class MaterialTransformer {

    /**
     * Transform a raw material to a material (microservice contract transform).
     * 
     * @param toTransform
     *          The raw material to transform.
     * @return The transformed material.
     */
    private static Material transform(RawMaterial toTransform) {

      // Extract and/or transform informations
      String name = toTransform.getName();
      FoodFamily foodFamily = FoodFamily.valueOf(toTransform.getFoodFamily().name());
      FoodUnit foodUnit = FoodUnit.valueOf(toTransform.getFoodUnit().name());
      Integer foodNumber = toTransform.getFoodNumber();

      // Return the new transformed instance
      return new Material(name, foodFamily, foodUnit, foodNumber);
    }
  }
}
