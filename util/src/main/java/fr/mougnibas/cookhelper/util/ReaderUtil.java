package fr.mougnibas.cookhelper.util;

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
}
