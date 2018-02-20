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

package fr.mougnibas.cookhelper.recipe.jaxrs.integrationtest;

import fr.mougnibas.cookhelper.recipe.business.PackagingBusiness;
import fr.mougnibas.cookhelper.recipe.contract.PackagingContract;
import fr.mougnibas.cookhelper.recipe.data.PackagingData;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeApplication;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeGet;
import fr.mougnibas.cookhelper.recipe.model.PackagingModel;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.util.PackagingUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
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
public class ITestRecipeGetMinestrone {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {
    
 // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class, "cook-helper-recipe-jaxrs.war");

    // Add the dependencies
    war.addAsLibrary(PackagingModel.makeJar());
    war.addAsLibrary(PackagingUtil.makeJar());
    war.addAsLibrary(PackagingData.makeJar());
    war.addAsLibrary(PackagingContract.makeJar());
    war.addAsLibrary(PackagingBusiness.makeJar());

    // Add the jar jaxrs dependency
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
    jar.addClass(RecipeApplication.class);
    jar.addClass(RecipeGet.class);
    jar.addAsResource("recipe-minestrone.xml");
    war.addAsLibrary(jar);

    // Return the deployment
    return war;
  }

  @Test
  public void testGet() throws Exception {

    // Expected
    JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Recipe expected = (Recipe) jaxbUnmarshaller
        .unmarshal(getClass().getClassLoader().getResource("recipe-minestrone.xml"));

    // Actual
    Client client = ClientBuilder.newClient();
    Recipe actual = client
        .target("http://localhost:8080/cook-helper-recipe-jaxrs/recipe/Minestrone")
        .request(MediaType.APPLICATION_XML).get(Recipe.class);

    // Compare
    Assert.assertEquals(expected.toString(), actual.toString());
  }
}
