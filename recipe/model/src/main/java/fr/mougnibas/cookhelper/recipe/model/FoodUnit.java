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

package fr.mougnibas.cookhelper.recipe.model;

/**
 * Food units.
 * 
 * @author Yoann
 */
public enum FoodUnit {

  /**
   * Gramme unit.
   */
  GRAMME("g"),

  /**
   * Ml unit.
   */
  ML("ml"),
  
  /**
   * l unit.
   */
  L("l"),
  
  /**
   * Cuillère à soupe unit.
   */
  CS("c. à soupe"),
  
  /**
   * Branch unit.
   */
  BRANCH("Branche"),
  
  /**
   * "Void" unit.
   */
  VOID("");

  /**
   * The name of the unit.
   */
  private String name;

  /**
   * Initialize the unit.
   * 
   * @param name
   *          The name of the unit.
   */
  FoodUnit(String name) {
    this.name = name;
  }

  /**
   * Get the name of the unit.
   * 
   * @return The name of the unit.
   */
  public String getName() {
    return name;
  }
}
