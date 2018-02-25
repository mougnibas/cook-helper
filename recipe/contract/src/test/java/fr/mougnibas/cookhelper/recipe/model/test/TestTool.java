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

import fr.mougnibas.cookhelper.recipe.contract.model.Tool;

import org.junit.Test;

/**
 * Unit tests of enumeration class.
 * 
 * @author Yoann
 */
public class TestTool {
  
  @Test
  public void testStirAssist() {
    String expected = "StirAssist";
    String actual = Tool.STIR_ASSIST.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void testMultiBlade() {
    String expected = "MultiBlade";
    String actual = Tool.MULTI_BLADE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testMiniMultiBlade() {
    String expected = "Mini MultiBlade";
    String actual = Tool.MINI_MULTI_BLADE.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void tesFouetAOeufs() {
    String expected = "Fouet à oeufs";
    String actual = Tool.FOUET_A_OEUFS.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPlatsVapeur() {
    String expected = "Plats vapeur";
    String actual = Tool.PLATS_VAPEUR.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPanierVapeur() {
    String expected = "Panier vapeur";
    String actual = Tool.PANIER_VAPEUR.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testLameDePetrissage() {
    String expected = "Lame de pétrissage";
    String actual = Tool.LAME_DE_PETRISSAGE.getName();
    assertEquals(expected, actual);
  }
}
