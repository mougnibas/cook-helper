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

package fr.mougnibas.cookhelper.recipe.model;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * Mimic a maven jar package.
 * 
 * @author Yoann
 */
public class PackagingModel {

  /**
   * Make a java archive.
   * 
   * @return a java archive.
   */
  public static JavaArchive makeJar() {

    // The JAR to return
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);

    // Add stuff to the jar
    jar.addClass(Category.class);
    jar.addClass(CookMode.class);
    jar.addClass(FoodFamily.class);
    jar.addClass(FoodUnit.class);
    jar.addClass(RawMaterial.class);
    jar.addClass(Recipe.class);
    jar.addClass(RefinedMaterial.class);
    jar.addClass(Step.class);
    jar.addClass(Tool.class);

    // Return the jar
    return jar;
  }
}
