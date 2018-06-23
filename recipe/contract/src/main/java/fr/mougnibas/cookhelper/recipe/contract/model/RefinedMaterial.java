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

/**
 * A refined material, based on a raw material.
 * 
 * @author Yoann
 */
public class RefinedMaterial implements Serializable {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = -4819299732943145931L;

  /**
   * The name of the refined material.
   */
  private String name;

  /**
   * The instructions to process a raw material into a refined one.
   */
  private String processInstructions;

  /**
   * The raw material to process.
   */
  private RawMaterial toProcess;

  /**
   * Used only by jaxb.
   */
  @Deprecated
  protected RefinedMaterial() {

  }

  /**
   * Initialize the refined material.
   * 
   * @param name
   *          The name of the refined material.
   * @param processInstructions
   *          The instructions to process a raw material into a refined one.
   * @param toProcess
   *          The raw material to process.
   */
  public RefinedMaterial(String name, String processInstructions, RawMaterial toProcess) {
    this.name = name;
    this.processInstructions = processInstructions;
    this.toProcess = toProcess;
  }

  /**
   * Get the name of the refined material.
   * 
   * @return The name of the refined material.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the instructions to process a raw material into a refined one.
   * 
   * @return The instructions to process a raw material into a refined one.
   */
  public String getProcessInstructions() {
    return processInstructions;
  }

  /**
   * Get the raw material to process.
   * 
   * @return The raw material to process.
   */
  public RawMaterial getToProcess() {
    return toProcess;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((processInstructions == null) ? 0 : processInstructions.hashCode());
    result = prime * result + ((toProcess == null) ? 0 : toProcess.hashCode());
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
    RefinedMaterial other = (RefinedMaterial) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (processInstructions == null) {
      if (other.processInstructions != null) {
        return false;
      }
    } else if (!processInstructions.equals(other.processInstructions)) {
      return false;
    }
    if (toProcess == null) {
      if (other.toProcess != null) {
        return false;
      }
    } else if (!toProcess.equals(other.toProcess)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RefinedMaterial [name=");
    builder.append(name);
    builder.append(", processInstructions=");
    builder.append(processInstructions);
    builder.append(", toProcess=");
    builder.append(toProcess);
    builder.append("]");
    return builder.toString();
  }
}
