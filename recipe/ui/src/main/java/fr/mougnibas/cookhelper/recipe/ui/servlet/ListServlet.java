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

package fr.mougnibas.cookhelper.recipe.ui.servlet;

import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to list the recipes.
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = -1498001046038863394L;

  /**
   * Class logger.
   */
  private static final Logger LOGGER = Logger.getLogger(ListServlet.class.getName());

  /**
   * Recipe manager.
   */
  @Inject
  private RecipeManager manager;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException {

    // Set the content type and encoding
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    // Try to write the recipes
    try {

      PrintWriter writer = response.getWriter();
      writer.println("Recettes :");

      for (Recipe currentRecipe : manager.get()) {
        writer.println(currentRecipe.getName());
      }

      // Flush the response
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
