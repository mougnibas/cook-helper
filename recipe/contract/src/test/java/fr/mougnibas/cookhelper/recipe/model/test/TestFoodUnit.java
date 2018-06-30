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

package fr.mougnibas.cookhelper.recipe.model.test;

import static org.junit.Assert.assertEquals;

import fr.mougnibas.cookhelper.recipe.contract.model.FoodUnit;

import org.junit.Test;

/**
 * Unit tests of Category enumeration.
 * 
 * @author Yoann
 */
public class TestFoodUnit {

  @Test
  public void testGramme() {
    String expected = "g";
    String actual = FoodUnit.GRAMME.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testMillilitre() {
    String expected = "ml";
    String actual = FoodUnit.ML.getName();
    assertEquals(expected, actual);
  }
}
