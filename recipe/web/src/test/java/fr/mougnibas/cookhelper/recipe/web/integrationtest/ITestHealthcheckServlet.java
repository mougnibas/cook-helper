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

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mougnibas.cookhelper.recipe.web.servlet.HealthcheckServlet;
import fr.mougnibas.cookhelper.util.ReaderUtil;

import org.junit.Assert;

/**
 * Test the Healthcheck servlet.
 * 
 * @author Yoann
 */
@RunWith(Arquillian.class)
public class ITestHealthcheckServlet {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {

    // Create a jar
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);

    // Add servlet to test
    jar.addClass(HealthcheckServlet.class);
    
    // Add the dependencies
    jar.addClass(ReaderUtil.class);

    // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class, "cook-helper-recipe-web.war");

    // Add the jar as library
    war.addAsLibrary(jar);

    // Return the deployment
    return war;
  }

  @Test
  public void testIamAlive() throws MalformedURLException {

    URL urlForActual = new URL("http://localhost:8080/cook-helper-recipe-web/healthcheck");

    String expected = "I'm alive\r\n";
    String actual = ReaderUtil.readResourceAsUtf8(urlForActual);
    Assert.assertEquals(expected, actual);
  }
}
