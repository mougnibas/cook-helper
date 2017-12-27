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

/**
 * A cook mode.
 * 
 * @author Yoann
 */
public enum CookMode {
  
  /**
   * Bouillir mode.
   */
  BOUILLIR("Bouillir"),
  
  /**
   * Frire mode.
   */
  FRIRE("Frire"),
  
  /**
   * Mijoter mode.
   */
  MIJOTER("Mijoter"),
  
  /**
   * Vapeur mode.
   */
  VAPEUR("Vapeur"),
  
  /**
   * Purée mode.
   */
  PUREE("Purée"),
  
  /**
   * Pâte mode.
   */
  PATE("Pâte"),
  
  /**
   * Manuel mode.
   */
  MANUEL("Manuel");

  /**
   * The name of the cook mode.
   */
  private String name;

  /**
   * Initialize the cook mode.
   * 
   * @param name
   *          The name of the cook mode.
   */
  CookMode(String name) {
    this.name = name;
  }

  /**
   * Get the name of the cook mode.
   * 
   * @return The name of the cook mode.
   */
  public String getName() {
    return name;
  }
}
