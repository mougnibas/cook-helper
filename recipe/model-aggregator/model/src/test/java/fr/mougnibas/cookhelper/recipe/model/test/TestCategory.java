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

import org.junit.Test;

import fr.mougnibas.cookhelper.recipe.model.Category;

/**
 * Unit tests of Category enumeration.
 * 
 * @author Yoann
 */
public class TestCategory {

  @Test
  public void testSoupe() {
    String expected = "Soupe";
    String actual = Category.SOUPE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testBouchees() {
    String expected = "Bouchées";
    String actual = Category.BOUCHEES.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testEntree() {
    String expected = "Entrée";
    String actual = Category.ENTREE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPetitDejeuner() {
    String expected = "Petit-déjeuner";
    String actual = Category.PETIT_DEJEUNER.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testDessert() {
    String expected = "Dessert";
    String actual = Category.DESSERT.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPlatDeResistance() {
    String expected = "Plat de résistance";
    String actual = Category.PLAT_DE_RESISTANCE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSauce() {
    String expected = "Sauce";
    String actual = Category.SAUCE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAccompagnement() {
    String expected = "Accompagnement";
    String actual = Category.ACCOMPAGNEMENT.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testBebe() {
    String expected = "Bébé";
    String actual = Category.BEBE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPate() {
    String expected = "Pâte";
    String actual = Category.PATE.getName();
    assertEquals(expected, actual);
  }
}
