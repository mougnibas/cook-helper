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

package fr.mougnibas.cookhelper.recipe.contract.service;

import fr.mougnibas.cookhelper.recipe.model.Recipe;

import java.io.Serializable;

import javax.ejb.Local;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * Recipe manage.
 * 
 * @author Yoann
 */
@Local
public interface RecipeManager extends Serializable {

  /**
   * List all of the recipe names.
   * 
   * @return all of the recipe names.
   */
  @Transactional(value = TxType.NOT_SUPPORTED)
  String[] listAllRecipeNames();

  /**
   * Get a recipe by his name.
   * 
   * @param recipeName
   *          The name of the recipe.
   * @return a recipe.
   */
  @Transactional(value = TxType.NOT_SUPPORTED)
  Recipe getByName(String recipeName);
}
