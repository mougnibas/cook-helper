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

package fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.integrationtest;

import fr.mougnibas.cookhelper.recipe.business.RecipeManagerImpl;
import fr.mougnibas.cookhelper.recipe.contract.exception.InitializationException;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.contract.util.EndpointsReader;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeApplication;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeGet;
import fr.mougnibas.cookhelper.recipe.model.Category;
import fr.mougnibas.cookhelper.recipe.model.CookMode;
import fr.mougnibas.cookhelper.recipe.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.recipe.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.model.Step;
import fr.mougnibas.cookhelper.recipe.model.Tool;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeClientQualifier;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeManagerImpl;
import fr.mougnibas.cookhelper.util.ReaderUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test about "RecipeGet" RestFull WebService.
 * 
 * @author Yoann
 */
@RunWith(Arquillian.class)
public class ITestJaxrsRecipeManagerImplGetMinestrone {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {

    // Create a jar
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);

    // Add EJB classes
    jar.addClass(RecipeManager.class);
    jar.addClass(RecipeManagerImpl.class);

    // Add endpoint reader resources
    jar.addClass(EndpointsReader.class);
    jar.addClass(InitializationException.class);
    jar.addAsResource("endpoints.properties");

    // Add models
    jar.addClass(Recipe.class);
    jar.addClass(Category.class);
    jar.addClass(CookMode.class);
    jar.addClass(FoodFamily.class);
    jar.addClass(RawMaterial.class);
    jar.addClass(RefinedMaterial.class);
    jar.addClass(FoodUnit.class);
    jar.addClass(Step.class);
    jar.addClass(Tool.class);

    // Add recipe data
    jar.addAsResource("index.txt");
    jar.addAsResource("risotto.xml");
    jar.addAsResource("minestrone.xml");

    // Add restfull webservice to test
    jar.addClass(RecipeApplication.class);
    jar.addClass(RecipeGet.class);

    // Add local EJB
    jar.addClass(JaxrsRecipeClientQualifier.class);
    jar.addClass(JaxrsRecipeManagerImpl.class);

    // Add JUnit resources
    jar.addClass(ReaderUtil.class);
    jar.addAsResource("recipe-minestrone.xml");

    // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class, "cook-helper-recipe.war");

    // Add the jar as library
    war.addAsLibrary(jar);

    // Return the deployment
    return war;
  }

  @Test
  public void testGetByName(@JaxrsRecipeClientQualifier RecipeManager recipeManager)
      throws Exception {

    // Expected
    JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Recipe expected = (Recipe) jaxbUnmarshaller
        .unmarshal(getClass().getClassLoader().getResource("recipe-minestrone.xml"));

    // Actual
    Recipe actual = recipeManager.getByName("Minestrone");

    // Compare
    Assert.assertEquals(expected.toString(), actual.toString());
  }
}
