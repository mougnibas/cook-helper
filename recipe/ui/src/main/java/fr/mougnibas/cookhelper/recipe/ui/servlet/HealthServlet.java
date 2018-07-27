package fr.mougnibas.cookhelper.recipe.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Mimic Eclipse MicroProfile HealthCheck 1.0 specification.
 * 
 * @author Yoann MOUGNIBAS
 */
@WebServlet("/health")
public class HealthServlet extends HttpServlet {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = 1432990186646825568L;

  /**
   * Class logger.
   */
  private static final Logger LOGGER = Logger.getLogger(HealthServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException {

    // Set the content type and encoding
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    // Try to write health
    try {

      PrintWriter writer = response.getWriter();
      writer.println("{\"checks\":[{\"data\":{},\"name\":\"successful-check\",\"state\":\"UP\"}],"
          + "\"outcome\":\"UP\"}");
      response.flushBuffer();

    } catch (IOException ex) {

      // Log the exception
      String msg = "response write exception";
      LOGGER.log(Level.SEVERE, msg, ex);

      // Send a http 500 code
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }
}
