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

package fr.mougnibas.cookhelper.shoplist.contract.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * A base food raw material.
 * 
 * @author Yoann
 */
public class Material implements Serializable, Comparable<Material> {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = -7017336723419590202L;

  /**
   * The name of the food material.
   */
  @XmlElement
  private String name;

  /**
   * The food family of this raw material.
   */
  @XmlElement
  private FoodFamily foodFamiliy;

  /**
   * The food unit.
   */
  @XmlElement
  private FoodUnit foodUnit;

  /**
   * The number of this food.
   */
  @XmlElement
  private Integer foodNumber;
  
  /**
   * Used only by jaxb.
   */
  @Deprecated
  protected Material() {
    
  }

  /**
   * Initialize the material.
   * 
   * @param name
   *          The name of the material.
   * @param foodFamiliy
   *          The food family of this raw material.
   * @param foodUnit
   *          The food family of this raw material.
   * @param foodNumber
   *          The number of this food.
   */
  public Material(String name, FoodFamily foodFamiliy, FoodUnit foodUnit,
      Integer foodNumber) {
    this.name = name;
    this.foodFamiliy = foodFamiliy;
    this.foodUnit = foodUnit;
    this.foodNumber = foodNumber;
  }

  /**
   * Get the name of the food material.
   * 
   * @return The name of the food material.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the food family of this raw material.
   * 
   * @return The food family of this raw material.
   */
  public FoodFamily getFoodFamily() {
    return foodFamiliy;
  }

  /**
   * Get the food unit.
   * 
   * @return The food unit.
   */
  public FoodUnit getFoodUnit() {
    return foodUnit;
  }

  /**
   * Get the number of this food.
   * 
   * @return The number of this food.
   */
  public Integer getFoodNumber() {
    return foodNumber;
  }

  @Override
  public int compareTo(Material o) {
    String inefficiant = o.toString();
    return toString().compareTo(inefficiant);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((foodFamiliy == null) ? 0 : foodFamiliy.hashCode());
    result = prime * result + ((foodNumber == null) ? 0 : foodNumber.hashCode());
    result = prime * result + ((foodUnit == null) ? 0 : foodUnit.hashCode());
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
    Material other = (Material) obj;
    if (foodFamiliy != other.foodFamiliy) {
      return false;
    }
    if (foodNumber == null) {
      if (other.foodNumber != null) {
        return false;
      }
    } else if (!foodNumber.equals(other.foodNumber)) {
      return false;
    }
    if (foodUnit != other.foodUnit) {
      return false;
    }
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
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RawMaterial [name=");
    builder.append(name);
    builder.append(", foodFamiliy=");
    builder.append(foodFamiliy);
    builder.append(", foodUnit=");
    builder.append(foodUnit);
    builder.append(", foodNumber=");
    builder.append(foodNumber);
    builder.append("]");
    return builder.toString();
  }
}
