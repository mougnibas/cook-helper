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

package fr.mougnibas.cookhelper.recipe.data;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * Mimic a maven jar package.
 * 
 * @author Yoann
 */
public class PackagingData {

  /**
   * Make a java archive.
   * 
   * @return a java archive.
   */
  public static JavaArchive makeJar() {

    // The JAR to return
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);

    // Add stuff to the jar
    jar.addAsResource("index.txt");
    jar.addAsResource("risotto.xml");
    jar.addAsResource("minestrone.xml");

    // Return the jar
    return jar;
  }
}
