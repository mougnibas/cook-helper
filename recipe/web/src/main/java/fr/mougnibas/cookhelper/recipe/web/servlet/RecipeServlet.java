/*
  © Copyright 2017 Yoann MOUGNIBAS
  
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

import fr.mougnibas.cookhelper.recipe.contract.RecipeManager;
import fr.mougnibas.cookhelper.recipe.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.recipe.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.model.Step;
import fr.mougnibas.cookhelper.recipe.model.Tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to show a given recipe.
 */
@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {

  /**
   * Generated Serial version.
   */
  private static final long serialVersionUID = 5098307142291333233L;

  /**
   * Recipe manager.
   */
  @Inject
  private RecipeManager manager;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Get the recipe name parameter
    String recipeName = request.getParameter("name");

    // Send a 404 error if the parameter is not found
    if (recipeName == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.flushBuffer();
      return;
    }

    // Get the given recipe
    Recipe recipe = manager.getByName(recipeName);

    // Send a 404 error if the recipe is not found
    if (recipe == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.flushBuffer();
      return;
    }

    // Set the content type and encoding
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    // Get the response writer
    PrintWriter writer = response.getWriter();

    // ------------------------
    // Write the recipe name and
    // ------------------------
    writer.print(recipe.getName());
    writer.print(" (");
    writer.print(recipe.getCategory().getName());
    writer.print(')');
    writer.print(" (");
    writer.print(recipe.getCookMode().getName());
    writer.println(')');
    writer.println();

    // ------------------------
    // Write the recipe headers
    // ------------------------

    // Persons
    writer.print("Pour : ");
    writer.print(recipe.getNumberOfPerson());
    writer.println(" personne(s)");

    // Time to prepare
    writer.print("Préparation : ");
    writer.print(recipe.getTimeToPrepare());
    writer.println(" min");

    // Time to prepare
    writer.print("Temps de cuisson : ");
    writer.print(recipe.getTimeToCook());
    writer.println(" min");
    writer.println();

    // ------------------------
    // Write the recipe tools
    // ------------------------
    writer.println("Outil(s) :");
    for (Tool tool : recipe.getTools()) {
      writer.print("    ");
      writer.println(tool.getName());
    }
    writer.println();

    // ------------------------
    // Write the recipe materials
    // ------------------------
    writer.println("Ingrédient(s) :");
    for (RawMaterial material : recipe.getMaterials()) {
      writer.print("    ");
      writer.print(material.getFoodNumber());
      writer.print(" ");
      writer.print(material.getFoodUnit().getName());
      writer.print(" ");
      writer.print(material.getName());
      writer.println();
    }
    writer.println();

    // ------------------------
    // Write the preparation
    // ------------------------
    writer.println("Préparation :");
    writer.println();
    for (RefinedMaterial refinedMaterial : recipe.getRefinedMaterials()) {
      writer.print("    ");
      writer.println(refinedMaterial.getProcessInstructions());
    }
    writer.println();

    // ------------------------
    // Write the recipe steps
    // ------------------------
    writer.println("Etape(s) :");

    Iterator<Step> iterator = Arrays.asList(recipe.getSteps()).iterator();
    do {

      Step step = iterator.next();

      writer.println();

      // Program and step
      if (step.getProgramNumber() != null) {
        writer.print("    ");
        writer.print("Sélectionner ");
        writer.print(recipe.getCookMode().getName());
        writer.print(' ');
        writer.print('P');
        writer.print(step.getProgramNumber());
        writer.println();
      }
      if (step.getProgramStepNumber() != null) {
        writer.print("    ");
        writer.print("Etape ");
        writer.println(step.getProgramStepNumber());
      }

      // Stub closed ?
      writer.print("    ");
      if (step.isStubClosed()) {
        writer.println("Bouchon fermé");
      } else {
        writer.println("Bouchon ouvert");
      }
      
      // Tool
      writer.print("    ");
      writer.print("Outil : ");
      writer.println(step.getTool().getName());

      // Materials
      writer.print("    ");
      writer.println("Mettre dans le bol :");
      for (RawMaterial material : step.getRawMaterials()) {
        writer.print("    - ");
        writer.println(material.getName());
      }
      for (RefinedMaterial material : step.getRefinedMaterials()) {
        writer.print("    - ");
        writer.println(material.getName());
      }

      // Instruction
      if (step.getInstruction() != null) {
        writer.print("    ");
        writer.println(step.getInstruction());
      }

      // Next step (if any)
      if (iterator.hasNext() && step.getProgramStepNumber() != null) {
        writer.print("    ");
        writer.println("Validez pour passer à l'étape suivante");
      }
    } while (iterator.hasNext());
    
    // Add new line, because it's more cool that way
    writer.println();

    // Flush the response
    response.flushBuffer();
  }

}
