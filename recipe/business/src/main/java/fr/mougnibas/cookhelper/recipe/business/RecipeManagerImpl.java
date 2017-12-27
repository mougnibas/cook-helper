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

package fr.mougnibas.cookhelper.recipe.business;

import fr.mougnibas.cookhelper.recipe.contract.RecipeManager;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.util.ReaderUtil;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Recipe manage.
 * 
 * @author Yoann
 */
@Stateless
public class RecipeManagerImpl implements RecipeManager {

  /**
   * Generated Serial Number.
   */
  private static final long serialVersionUID = -1781943570913270314L;

  /**
   * A map of recipes (recipe name to recipe object).
   */
  private Map<String, Recipe> recipes;

  /**
   * Initialize the EJB.
   */
  @PostConstruct
  protected void init() {

    // Instantiate the map
    recipes = new TreeMap<>();

    // Read the index
    String recipesName = ReaderUtil
        .readResourceAsUtf8(getClass().getClassLoader().getResource("index.txt"));
    String[] recipesNameTab = recipesName.split(System.lineSeparator());

    // For each recipe in the index
    for (String recipeName : recipesNameTab) {
      
      // Make it
      Recipe recipe = makeRecipe(recipeName);
      
      // And put it to the map
      recipes.put(recipe.getName(), recipe);
    }
  }

  /**
   * Make a recipe.
   * 
   * @param recipeName
   *          The recipe name.
   * 
   * @return a risotto recipe.
   */
  private Recipe makeRecipe(String recipeName) {

    // The recipe to return
    Recipe recipe;

    try {
      // Create and configure JAXB technical objects
      JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      // Unmarshal the recipe
      InputStream recipeIs = getClass().getClassLoader().getResourceAsStream(recipeName);
      recipe = (Recipe) jaxbUnmarshaller.unmarshal(recipeIs);

    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    // Return the result
    return recipe;
  }

  @Override
  public String[] listAllRecipeNames() {
    String[] recipeNames = recipes.keySet().toArray(new String[recipes.size()]);
    return recipeNames;
  }

  @Override
  public Recipe getByName(String recipeName) {
    Recipe recipe = recipes.get(recipeName);
    return recipe;
  }
}
