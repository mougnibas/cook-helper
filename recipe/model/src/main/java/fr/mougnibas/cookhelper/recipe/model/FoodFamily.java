/*
  © Copyright 2017 Yoann MOUGNIBAS
  
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

import java.io.Serializable;

/**
 * A raw food material family.
 * 
 * @author Yoann.
 */
public enum FoodFamily implements Serializable {

  /**
   * Meat family.
   */
  MEAT,

  /**
   * Vegetable family.
   */
  VEGETABLE,

  /**
   * Oil family.
   */
  OIL,

  /**
   * Alcohol family.
   */
  ALCOHOL,
  
  /**
   * Water family.
   */
  WATER,

  /**
   * Bouillon family.
   */
  BOUILLON,

  /**
   * Cheese family.
   */
  CHEESE,
  
  /**
   * Pâte family.
   */
  PATE,

  /**
   * All other family.
   */
  MISC;
}
