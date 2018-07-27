package fr.mougnibas.cookhelper.healthcheck.client;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entrypoint class running an eclipse microprofile healthcheck 1.0 client.
 * 
 * @author Yoann MOUGNIBAS
 */
public class EntryPoint {

  /**
   * Class logger.
   */
  private static final Logger LOGGER = Logger.getLogger(EntryPoint.class.getName());

  /**
   * Exit code : OK, it's up.
   */
  private static final int OK_EXIT_CODE = 0;

  /**
   * Exit code : Oh no, it's down.
   */
  private static final int OOPS_EXIT_CODE = 1;

  /**
   * Check <code>http://localhost:9080/health report</code>.<br>
   * If the result is not equals to the following code, it return <code>-1</code> as exit code.<br>
   * Else, it exits as <code>0</code> exit code. Code (json random order handle) :
   * <code>{"checks":[{"data":{},"name":"successful-check","state":"UP"}],"outcome":"UP"}</code>
   * 
   * @param args (not used)
   */
  public static void main(String[] args) {

    // "global" expected
    String expected = "{\"checks\":[{\"data\":{},\"name\":\"successful-check\",\"state\":\"UP\"}],"
        + "\"outcome\":\"UP\"}";

    // Expected health check returns
    String expectedData = "\"data\":{}";
    String expectedName = "\"name\":\"successful-check\"";
    String expectedState = "\"state\":\"UP\"";
    String expectedOutcome = "\"outcome\":\"UP\"";

    // Actual health check return
    // If there is something wrong, just return a oops exit code
    String actual;
    try {

      URL url = new URL("http", "localhost", 9080, "/health");
      actual = ReaderUtil.readResourceAsUtf8(url);

    } catch (Exception ex) {

      // Log the exception, and send the exit code
      LOGGER.log(Level.SEVERE, "Can't get the resource", ex);
      System.exit(OOPS_EXIT_CODE);

      return;
    }

    // Check the expected and actual return
    boolean isDataOk = actual.contains(expectedData);
    boolean isNameOk = actual.contains(expectedName);
    boolean isStateOk = actual.contains(expectedState);
    boolean isOutcomeOk = actual.contains(expectedOutcome);
    boolean isOk = isDataOk && isNameOk && isStateOk && isOutcomeOk;
    if (isOk) {

      // Log the message, and send the exit code
      LOGGER.log(Level.INFO, "OK, actual and expected was equals");
      System.exit(OK_EXIT_CODE);

    } else {

      // Create the message
      StringBuilder msgSb = new StringBuilder();
      msgSb.append("Expected value didn't match (don't look at order). ");
      msgSb.append("Expected  '").append(expected).append("', ");
      msgSb.append("but was '").append(actual).append("'");
      String msg = msgSb.toString();

      // Log the message, and send the exit code
      LOGGER.log(Level.SEVERE, msg);
      System.exit(OOPS_EXIT_CODE);
    }
  }
}
