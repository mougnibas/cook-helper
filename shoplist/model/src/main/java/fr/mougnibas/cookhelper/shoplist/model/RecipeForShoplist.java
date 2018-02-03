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

package fr.mougnibas.cookhelper.shoplist.model;

import java.io.Serializable;

import fr.mougnibas.cookhelper.recipe.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.model.Recipe;

/**
 * A recipe, with only a name, a number of person and the raw materials.
 * 
 * @author Yoann
 */
public class RecipeForShoplist implements Serializable, Comparable<RecipeForShoplist> {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = 1671553610500539885L;

  /**
   * The original recipe.
   */
  private Recipe recipe;

  /**
   * Initialize the recipe.
   * 
   * @param recipe
   *          The original recipe.
   */
  public RecipeForShoplist(Recipe recipe) {
    this.recipe = recipe;
  }

  /**
   * Get the name of the recipe.
   * 
   * @return the name of the recipe.
   */
  public String getName() {
    return recipe.getName();
  }

  /**
   * Get the number of person for this recipe.
   * 
   * @return The number of person for this recipe.
   */
  public Integer getNumberOfPerson() {
    return recipe.getNumberOfPerson();
  }

  /**
   * Get the materials of the recipe (including those of the refined ones).
   * 
   * @return the materials of the recipe.
   */
  public RawMaterial[] getMaterials() {

    return recipe.getMaterials();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RecipeForShoplist other = (RecipeForShoplist) obj;
    if (recipe == null) {
      if (other.recipe != null)
        return false;
    } else if (!recipe.equals(other.recipe))
      return false;
    return true;
  }

  @Override
  public int compareTo(RecipeForShoplist other) {
    return recipe.compareTo(other.recipe);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RecipeForShoplist [recipe=");
    builder.append(recipe);
    builder.append("]");
    return builder.toString();
  }

}
