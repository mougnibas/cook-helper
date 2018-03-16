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

package fr.mougnibas.cookhelper.recipe.war.integrationtest;

import fr.mougnibas.cookhelper.util.ReaderUtil;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/**
 * Integration test about "RecipeGet" RestFull WebService.
 * 
 * @author Yoann
 */
public class ITestRecipeList {

  @Test
  public void testList() throws Exception {

    URL urlForExpected = getClass().getClassLoader().getResource("recipes-list-name.json");
    URL urlForActual = new URL("http://localhost:8080/cook-helper-recipe/recipe/");

    String expected = ReaderUtil.readResourceAsUtf8(urlForExpected);
    String actual = ReaderUtil.readResourceAsUtf8(urlForActual);
    Assert.assertEquals(expected, actual);
  }
}
