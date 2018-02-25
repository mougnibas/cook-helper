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

package fr.mougnibas.cookhelper.shoplist.contract.model;

import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A recipe, with only a name, a number of person and the raw materials.
 * 
 * @author Yoann
 */
public class RecipeForShoplist implements Serializable, Comparable<RecipeForShoplist> {

  /**
   * Generated serial version.
   */
  private static final long serialVersionUID = 2083593326831381150L;

  /**
   * The name of the recipe.
   */
  private String name;

  /**
   * The number of person for this recipe.
   */
  private Integer numberOfPerson;

  /**
   * The materials of the recipe (including those of the refined ones).
   */
  private RawMaterial[] rawMaterials;

  /**
   * Initialize the recipe.
   * 
   * @param recipe
   *          The original recipe.
   */
  public RecipeForShoplist(Recipe recipe) {
    name = recipe.getName();
    numberOfPerson = recipe.getNumberOfPerson();
    rawMaterials = recipe.getMaterials();
  }

  /**
   * Initialize the recipe.
   * 
   * @param name
   *          The name of the recipe.
   * @param numberOfPerson
   *          The number of person for this recipe.
   * @param rawMaterials
   *          The materials of the recipe (including those of the refined ones).
   */
  public RecipeForShoplist(String name, Integer numberOfPerson, RawMaterial... rawMaterials) {
    this.name = name;
    this.numberOfPerson = numberOfPerson;
    this.rawMaterials = rawMaterials;
  }

  /**
   * Get the name of the recipe.
   * 
   * @return the name of the recipe.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the number of person for this recipe.
   * 
   * @return The number of person for this recipe.
   */
  public Integer getNumberOfPerson() {
    return numberOfPerson;
  }

  /**
   * Get the materials of the recipe (including those of the refined ones).
   * 
   * @return the materials of the recipe.
   */
  public RawMaterial[] getMaterials() {

    return rawMaterials.clone();
  }

  @Override
  public int compareTo(RecipeForShoplist o) {
    return name.compareTo(o.name);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    RecipeForShoplist other = (RecipeForShoplist) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RecipeForShoplist [name=");
    builder.append(name);
    builder.append(", numberOfPerson=");
    builder.append(numberOfPerson);
    builder.append(", rawMaterials=");
    builder.append(Arrays.toString(rawMaterials));
    builder.append("]");
    return builder.toString();
  }

}
