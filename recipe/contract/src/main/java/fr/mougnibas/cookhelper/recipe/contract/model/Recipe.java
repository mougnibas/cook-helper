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

package fr.mougnibas.cookhelper.recipe.contract.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A recipe.
 * 
 * @author Yoann
 */
public class Recipe implements Serializable, Comparable<Recipe> {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = 6577908964910923042L;

  /**
   * The name of the recipe.
   */
  private String name;

  /**
   * The time to prepare the raw materials (in minutes).
   */
  private Integer timeToPrepare;

  /**
   * The time to cook the raw materials (in minutes).
   */
  private Integer timeToCook;

  /**
   * The number of person for this recipe.
   */
  private Integer numberOfPerson;

  /**
   * The category of the recipe.
   */
  private Category category;

  /**
   * The steps of the recipe.
   */
  private List<Step> steps;

  /**
   * The cook mode of the recipe.
   */
  private CookMode cookMode;

  /**
   * Initialize a recipe.
   * 
   * @param name
   *          The name of the recipe.
   * @param timeToPrepare
   *          The time to prepare the raw materials (in minutes).
   * @param timeToCook
   *          The time to cook the raw materials (in minutes).
   * @param numberOfPerson
   *          The number of person for this recipe.
   * @param category
   *          The category of the recipe.
   * @param steps
   *          The steps of the recipe.
   * @param cookMode
   *          The cook mode of the recipe.
   */
  public Recipe(String name, Integer timeToPrepare, Integer timeToCook, Integer numberOfPerson,
      Category category, Step[] steps, CookMode cookMode) {
    this.name = name;
    this.timeToPrepare = timeToPrepare;
    this.timeToCook = timeToCook;
    this.numberOfPerson = numberOfPerson;
    this.category = category;
    this.steps = Arrays.asList(steps);
    this.cookMode = cookMode;
  }

  /**
   * Get the name of the recipe.
   * 
   * @return the name of the recipe.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the time to prepare the raw materials (in minutes).
   * 
   * @return The time to prepare the raw materials (in minutes).
   */
  public Integer getTimeToPrepare() {
    return timeToPrepare;
  }

  /**
   * Get the time to cook the raw materials (in minutes).
   * 
   * @return The time to cook the raw materials (in minutes).
   */
  public Integer getTimeToCook() {
    return timeToCook;
  }

  /**
   * Get the number of person for this recipe.
   * 
   * @return The number of person for this recipe.
   */
  public Integer getNumberOfPerson() {
    return numberOfPerson;
  }

  /**
   * Get the category of the recipe.
   * 
   * @return The category of the recipe.
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Get the steps of the recipe.
   * 
   * @return The steps of the recipe.
   */
  public Step[] getSteps() {
    return steps.toArray(new Step[steps.size()]);
  }

  /**
   * Get the tools of the recipe.
   * 
   * @return The tools of the recipe.
   */
  public Tool[] getTools() {

    Set<Tool> tools = new TreeSet<>();
    for (Step step : steps) {
      Tool stepTool = step.getTool();
      tools.add(stepTool);
    }

    return tools.toArray(new Tool[tools.size()]);
  }

  /**
   * Get the materials of the recipe (including those of the refined ones).
   * 
   * @return the materials of the recipe.
   */
  public RawMaterial[] getMaterials() {

    // The list to return
    List<RawMaterial> materials = new LinkedList<>();

    // For each step
    for (Step step : steps) {

      // For each raw material
      for (RawMaterial rawMaterial : step.getRawMaterials()) {
        materials.add(rawMaterial);
      }

      // for each refined material
      for (RefinedMaterial refinedMaterial : step.getRefinedMaterials()) {
        materials.add(refinedMaterial.getToProcess());
      }
    }

    // Return the materials
    return materials.toArray(new RawMaterial[materials.size()]);
  }

  /**
   * Get the refined material of the recipes.
   * 
   * @return the refined material of the recipes.
   */
  public RefinedMaterial[] getRefinedMaterials() {

    // The list to return
    List<RefinedMaterial> materials = new LinkedList<>();

    // For each step
    for (Step step : steps) {

      // for each refined material
      for (RefinedMaterial refinedMaterial : step.getRefinedMaterials()) {
        materials.add(refinedMaterial);
      }
    }

    // Return the materials
    return materials.toArray(new RefinedMaterial[materials.size()]);
  }

  /**
   * Get the cook mode of the recipe.
   * 
   * @return The cook mode of the recipe.
   */
  public CookMode getCookMode() {
    return cookMode;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Recipe other = (Recipe) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Recipe otherRecipe) {
    return name.compareTo(otherRecipe.name);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Recipe [name=");
    builder.append(name);
    builder.append(", timeToPrepare=");
    builder.append(timeToPrepare);
    builder.append(", timeToCook=");
    builder.append(timeToCook);
    builder.append(", numberOfPerson=");
    builder.append(numberOfPerson);
    builder.append(", category=");
    builder.append(category);
    builder.append(", steps=");
    builder.append(steps);
    builder.append(", cookMode=");
    builder.append(cookMode);
    builder.append("]");
    return builder.toString();
  }

}
