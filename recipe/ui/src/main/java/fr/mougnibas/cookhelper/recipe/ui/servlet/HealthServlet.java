package fr.mougnibas.cookhelper.recipe.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/health")
public class HealthServlet extends HttpServlet {
  
  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = 1432990186646825568L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Set the content type and encoding
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    // Write health
    PrintWriter writer = response.getWriter();
    writer.println("I'm alive!");

    // Flush the response
    response.flushBuffer();
  }
}
