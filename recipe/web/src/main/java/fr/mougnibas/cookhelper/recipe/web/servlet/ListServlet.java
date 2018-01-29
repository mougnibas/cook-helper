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

package fr.mougnibas.cookhelper.recipe.web.servlet;

import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.model.Recipe;

import java.io.IOException;
import java.io.PrintWriter;

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
   * Recipe manager.
   */
  @Inject
  private RecipeManager manager;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Set the content type and encoding
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    // Write the recipes
    PrintWriter writer = response.getWriter();
    writer.println("Recettes :");

    for (String recipeName : manager.listAllRecipeNames()) {
      Recipe currentRecipe = manager.getByName(recipeName);
      writer.println(currentRecipe.getName());
    }
    
    // Flush the response
    response.flushBuffer();
  }

}
