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

package fr.mougnibas.cookhelper.recipe.example;

import fr.mougnibas.cookhelper.recipe.model.Recipe;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Entry point class to unmarshall xml recipe to pojo.
 * 
 * @author Yoann
 */
public class EntryPointUnmarshall {

  /**
   * The entry point of the class.
   * 
   * @param args
   *          Not used
   * @throws Exception
   *           If there is something wrong.
   */
  public static void main(String[] args) throws Exception {

    // Create a technical object (JAXB Context)
    JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);

    // Create a technical object (Unmarshaller)
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

    // Unmarshal the recipe
    InputStream recipeIs = EntryPointUnmarshall.class.getClassLoader()
        .getResourceAsStream("risotto.xml");
    Recipe risottoUnmarshalled = (Recipe) jaxbUnmarshaller.unmarshal(recipeIs);

    // Print the toString version of the recipe
    System.out.println(risottoUnmarshalled);
  }
}
