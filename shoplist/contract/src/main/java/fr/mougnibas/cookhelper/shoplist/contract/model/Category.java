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

import java.io.Serializable;

/**
 * The categories.
 * 
 * @author Yoann
 */
public enum Category implements Serializable {

  /**
   * Soupe category.
   */
  SOUPE("Soupe"),

  /**
   * Bouchées category.
   */
  BOUCHEES("Bouchées"),

  /**
   * Entrée category.
   */
  ENTREE("Entrée"),

  /**
   * Petit-déjeuner category.
   */
  PETIT_DEJEUNER("Petit-déjeuner"),

  /**
   * Dessert category.
   */
  DESSERT("Dessert"),

  /**
   * Plat de résistance category.
   */
  PLAT_DE_RESISTANCE("Plat de résistance"),

  /**
   * Sauce category.
   */
  SAUCE("Sauce"),

  /**
   * Accompagnement category.
   */
  ACCOMPAGNEMENT("Accompagnement"),

  /**
   * Bébé category.
   */
  BEBE("Bébé"),

  /**
   * Pâte category.
   */
  PATE("Pâte");

  /**
   * The name of the category.
   */
  private String name;

  /**
   * Initialize the category.
   * 
   * @param name
   *          The name of the category.
   */
  Category(String name) {
    this.name = name;
  }

  /**
   * Get the name of the category.
   * 
   * @return The name of the category.
   */
  public String getName() {
    return name;
  }
}
