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

import fr.mougnibas.cookhelper.recipe.model.CookMode;

/**
 * Unit tests of CookMode enumeration.
 * 
 * @author Yoann
 */
public class TestCookMode {

  @Test
  public void testBouillir() {
    String expected = "Bouillir";
    String actual = CookMode.BOUILLIR.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFrire() {
    String expected = "Frire";
    String actual = CookMode.FRIRE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testMijoter() {
    String expected = "Mijoter";
    String actual = CookMode.MIJOTER.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testVapeur() {
    String expected = "Vapeur";
    String actual = CookMode.VAPEUR.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPuree() {
    String expected = "Purée";
    String actual = CookMode.PUREE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPate() {
    String expected = "Pâte";
    String actual = CookMode.PATE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testManuel() {
    String expected = "Manuel";
    String actual = CookMode.MANUEL.getName();
    assertEquals(expected, actual);
  }
}
