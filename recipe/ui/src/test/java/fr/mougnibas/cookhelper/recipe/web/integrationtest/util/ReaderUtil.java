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

package fr.mougnibas.cookhelper.recipe.web.integrationtest.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * An utility class to read resources.
 * 
 * @author Yoann
 */
public class ReaderUtil {

  /**
   * Read an UTF8 encoded text resource from an URL.
   * 
   * @param url
   *          The URL.
   * @return The resource, as String.
   */
  public static String readResourceAsUtf8(URL url) {

    String text;

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(url.openStream(), "UTF8"))) {
      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
      }
      text = sb.toString();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    return text;
  }

  /**
   * Utility class don't need a public constructor.
   */
  private ReaderUtil() {

  }
}
