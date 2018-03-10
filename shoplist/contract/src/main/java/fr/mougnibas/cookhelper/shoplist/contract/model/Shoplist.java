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

import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * A shop list.
 * 
 * @author Yoann
 */
public class Shoplist implements Serializable {

  /**
   * Generated serial version.
   */
  private static final long serialVersionUID = -6068580553196682909L;

  /**
   * The list of materials.
   */
  private RawMaterial[] materials;

  /**
   * Intialize the shoplist.
   * 
   * @param materials
   *          The materials.
   */
  public Shoplist(RawMaterial... materials) {
    TreeSet<RawMaterial> tree = new TreeSet<>(Arrays.asList(materials));
    this.materials = tree.toArray(new RawMaterial[tree.size()]);
  }

  /**
   * Return the materials.
   * 
   * @return the materials.
   */
  public RawMaterial[] getMaterials() {
    return materials.clone();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(materials);
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
    Shoplist other = (Shoplist) obj;
    if (!Arrays.equals(materials, other.materials)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Shoplist [materials=");
    builder.append(Arrays.toString(materials));
    builder.append("]");
    return builder.toString();
  }

}
