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

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.shoplist.CommonPackaging;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeClientQualifier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
    return CommonPackaging.createDeployment();
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
