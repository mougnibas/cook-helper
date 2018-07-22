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

package fr.mougnibas.cookhelper.recipe.web.integrationtest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/**
 * Very basic integration test of RecipeManager implementation.
 * 
 * @author Yoann
 */
public class ITestRecipe {

  @Test
  public void testGetMissingParameter() throws MalformedURLException, IOException {

    int expected = HttpURLConnection.HTTP_NOT_FOUND;

    HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8090/recipe.xhtml")
        .openConnection();
    int actual = connection.getResponseCode();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetRecipeNotFound() throws MalformedURLException, IOException {

    int expected = HttpURLConnection.HTTP_NOT_FOUND;

    HttpURLConnection connection = (HttpURLConnection) new URL(
        "http://localhost:8090/recipe.xhtml?name=fdshgfq").openConnection();
    int actual = connection.getResponseCode();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetRisotto() throws MalformedURLException {

    URL urlForExpected = getClass().getClassLoader().getResource("recipe-risotto.html");
    URL urlForActual = new URL("http://localhost:8090/recipe.xhtml?name=Risotto");

    String expected = ReaderUtil.readResourceAsUtf8(urlForExpected);
    String actual = ReaderUtil.readResourceAsUtf8(urlForActual);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetMinestrone() throws MalformedURLException {

    URL urlForExpected = getClass().getClassLoader().getResource("recipe-minestrone.html");
    URL urlForActual = new URL("http://localhost:8090/recipe.xhtml?name=Minestrone");

    String expected = ReaderUtil.readResourceAsUtf8(urlForExpected);
    String actual = ReaderUtil.readResourceAsUtf8(urlForActual);
    Assert.assertEquals(expected, actual);
  }
}
