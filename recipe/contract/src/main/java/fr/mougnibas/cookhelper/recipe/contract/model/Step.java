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

/**
 * A step of a recipe.
 * 
 * @author Yoann
 */
public class Step implements Serializable {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = 3303350811093614115L;

  /**
   * The tool needed by this step.
   */
  private Tool tool;

  /**
   * The materials of this step.
   */
  private List<RawMaterial> rawMaterials;

  /**
   * The refined materials of this step.
   */
  private List<RefinedMaterial> refinedMaterials;

  /**
   * True if the stub is closed, false otherwise.
   */
  private Boolean isStubClosed;

  /**
   * Instruction of this step (may be null).
   */
  private String instruction;

  /**
   * Program number (may be null).
   */
  private Integer programNumber;

  /**
   * Program step number (may be null).
   */
  private Integer programStepNumber;

  /**
   * Initialize the step.
   * 
   * @param tool
   *          The tool of the step.
   * @param materials
   *          The materials of this step.
   * @param refinedMaterials
   *          The refined materials of this step.
   * @param isCouvercleClosed
   *          True if the stub is closed, false otherwise.
   * @param instruction
   *          Instruction of this step (may be null).
   * @param programNumber
   *          Program number (may be null).
   * @param programStepNumber
   *          Program step number (may be null).
   */
  public Step(Tool tool, RawMaterial[] materials, RefinedMaterial[] refinedMaterials,
      Boolean isCouvercleClosed, String instruction, Integer programNumber,
      Integer programStepNumber) {
    this.tool = tool;
    if (materials == null) {
      this.rawMaterials = new LinkedList<>();
    } else {
      this.rawMaterials = Arrays.asList(materials);
    }
    if (refinedMaterials == null) {
      this.refinedMaterials = new LinkedList<>();
    } else {
      this.refinedMaterials = Arrays.asList(refinedMaterials);
    }

    this.isStubClosed = isCouvercleClosed;
    this.instruction = instruction;
    this.programNumber = programNumber;
    this.programStepNumber = programStepNumber;
  }

  /**
   * Get the tool needed by this step.
   * 
   * @return The tool needed by this step.
   */
  public Tool getTool() {
    return tool;
  }

  /**
   * Get the materials of this step.
   * 
   * @return The materials of this step.
   */
  public RawMaterial[] getRawMaterials() {
    return rawMaterials.toArray(new RawMaterial[rawMaterials.size()]);
  }

  /**
   * Get the refined materials of this step.
   * 
   * @return The refined materials of this step.
   */
  public RefinedMaterial[] getRefinedMaterials() {
    return refinedMaterials.toArray(new RefinedMaterial[refinedMaterials.size()]);
  }

  /**
   * True if the stub is closed, false otherwise.
   * 
   * @return True if the stub is closed, false otherwise.
   */
  public Boolean isStubClosed() {
    return isStubClosed;
  }

  /**
   * Get instruction of this step (may be null).
   * 
   * @return Instruction of this step (may be null).
   */
  public String getInstruction() {
    return instruction;
  }

  /**
   * Get program number (may be null).
   * 
   * @return Program number (may be null).
   */
  public Integer getProgramNumber() {
    return programNumber;
  }

  /**
   * Get the program step number (may be null).
   * 
   * @return Program step number (may be null).
   */
  public Integer getProgramStepNumber() {
    return programStepNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((instruction == null) ? 0 : instruction.hashCode());
    result = prime * result + ((isStubClosed == null) ? 0 : isStubClosed.hashCode());
    result = prime * result + ((programNumber == null) ? 0 : programNumber.hashCode());
    result = prime * result + ((programStepNumber == null) ? 0 : programStepNumber.hashCode());
    result = prime * result + ((rawMaterials == null) ? 0 : rawMaterials.hashCode());
    result = prime * result + ((refinedMaterials == null) ? 0 : refinedMaterials.hashCode());
    result = prime * result + ((tool == null) ? 0 : tool.hashCode());
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
    Step other = (Step) obj;
    if (instruction == null) {
      if (other.instruction != null) {
        return false;
      }
    } else if (!instruction.equals(other.instruction)) {
      return false;
    }
    if (isStubClosed == null) {
      if (other.isStubClosed != null) {
        return false;
      }
    } else if (!isStubClosed.equals(other.isStubClosed)) {
      return false;
    }
    if (programNumber == null) {
      if (other.programNumber != null) {
        return false;
      }
    } else if (!programNumber.equals(other.programNumber)) {
      return false;
    }
    if (programStepNumber == null) {
      if (other.programStepNumber != null) {
        return false;
      }
    } else if (!programStepNumber.equals(other.programStepNumber)) {
      return false;
    }
    if (rawMaterials == null) {
      if (other.rawMaterials != null) {
        return false;
      }
    } else if (!rawMaterials.equals(other.rawMaterials)) {
      return false;
    }
    if (refinedMaterials == null) {
      if (other.refinedMaterials != null) {
        return false;
      }
    } else if (!refinedMaterials.equals(other.refinedMaterials)) {
      return false;
    }
    return tool != other.tool;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Step [tool=");
    builder.append(tool);
    builder.append(", rawMaterials=");
    builder.append(rawMaterials);
    builder.append(", refinedMaterials=");
    builder.append(refinedMaterials);
    builder.append(", isStubClosed=");
    builder.append(isStubClosed);
    builder.append(", instruction=");
    builder.append(instruction);
    builder.append(", programNumber=");
    builder.append(programNumber);
    builder.append("]");
    return builder.toString();
  }

}
