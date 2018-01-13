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

import java.io.Serializable;

/**
 * The tools.
 * 
 * @author Yoann.
 */
public enum Tool implements Serializable {
  
  /**
   * The StirAssist tool.
   */
  STIR_ASSIST("StirAssist"),

  /**
   * The MultiBlade tool.
   */
  MULTI_BLADE("MultiBlade"),
  
  /**
   * The Mini MultiBlade tool.
   */
  MINI_MULTI_BLADE("Mini MultiBlade"),
  
  /**
   * The "Fouet à oeufs" tool.
   */
  FOUET_A_OEUFS("Fouet à oeufs"),
  
  /**
   * The "Plats vapeur" tool.
   */
  PLATS_VAPEUR("Plats vapeur"),
  
  /**
   * The "Panier vapeur" tool.
   */
  PANIER_VAPEUR("Panier vapeur"),
  
  /**
   * The "Lame de pétrissage" tool.
   */
  LAME_DE_PETRISSAGE("Lame de pétrissage");

  /**
   * The name of the tool.
   */
  private String name;

  /**
   * Initialize the tool.
   * 
   * @param name
   *          The name of the tool.
   */
  Tool(String name) {
    this.name = name;
  }

  /**
   * Get the name of the tool.
   * 
   * @return The name of the tool.
   */
  public String getName() {
    return name;
  }
}
