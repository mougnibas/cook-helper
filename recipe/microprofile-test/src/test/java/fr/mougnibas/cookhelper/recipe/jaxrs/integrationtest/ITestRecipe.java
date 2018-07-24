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

package fr.mougnibas.cookhelper.recipe.jaxrs.integrationtest;

import fr.mougnibas.cookhelper.recipe.contract.client.RecipeManagerJaxRsClientImpl;
import fr.mougnibas.cookhelper.recipe.contract.model.Category;
import fr.mougnibas.cookhelper.recipe.contract.model.CookMode;
import fr.mougnibas.cookhelper.recipe.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Step;
import fr.mougnibas.cookhelper.recipe.contract.model.Tool;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test about "RecipeGet" RestFull WebService.
 * 
 * @author Yoann
 */
public class ITestRecipe {

  /**
   * Recipe manager (jaxrs client implementation).
   */
  private RecipeManager recipeManager;

  @Before
  public void init() {
    recipeManager = new RecipeManagerJaxRsClientImpl("http", "localhost", 8080, "");
  }

  @Test
  public void testGetMinestrone() throws Exception {

    // Expected
    Recipe expected = makeMinestrone();

    // Actual
    Recipe actual = recipeManager.get("Minestrone");

    // // Compare them
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetRisotto() throws Exception {

    // Expected
    Recipe expected = makeRisotto();

    // Actual
    Recipe actual = recipeManager.get("Risotto");

    // // Compare them
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetAll() throws Exception {

    // Expected
    Recipe[] expected = { makeMinestrone(), makeRisotto() };

    // Actual
    Recipe[] actual = recipeManager.get();

    // // Compare them
    Assert.assertArrayEquals(expected, actual);
  }

  /**
   * Create a minestrone recipe.
   * 
   * @return a minestrone recipe.
   */
  private Recipe makeMinestrone() {

    // Recipe's steps
    Step[] steps = new Step[] {
        new Step(Tool.MULTI_BLADE, new RawMaterial[] {},
            new RefinedMaterial[] {
                new RefinedMaterial("Oignon coupé en 4", "Pelez et coupez l'oignon en 4",
                    new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120)),
                new RefinedMaterial("gousse d'ail pelé", "Pelez la gousse d'ail",
                    new RawMaterial("Gousse d'ail", FoodFamily.VEGETABLE, FoodUnit.VOID, 1)) },
            true,
            "Actionner pendant 5 secondes le bouton impulsion 'pulse', ouvrez le couvercle et "
                + "raclez les parois du bol.",
            null, null),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] { new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.CS, 2),
                new RawMaterial("Dès de lard fumé", FoodFamily.MEAT, FoodUnit.GRAMME, 100) },
            new RefinedMaterial[] {}, false, null, 3, 1),
        new Step(Tool.STIR_ASSIST, new RawMaterial[] {
            new RawMaterial("Dès de tomates (en conserve)", FoodFamily.VEGETABLE, FoodUnit.GRAMME,
                200),
            new RawMaterial("Cube de bouillon de légume", FoodFamily.BOUILLON, FoodUnit.VOID, 2),
            new RawMaterial("Feuille de laurier", FoodFamily.MISC, FoodUnit.VOID, 1),
            new RawMaterial("Eau", FoodFamily.WATER, FoodUnit.L, 1) },
            new RefinedMaterial[] {
                new RefinedMaterial("Carottes coupés en dès",
                    "Pelez et coupez en dès de 0.5 cm les carottes",
                    new RawMaterial("Carotte", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120)),
                new RefinedMaterial("Pommes de terre coupés en dès",
                    "Pelez et coupez en dès de 0.5 cm les pommes de terre",
                    new RawMaterial("Pomme de terre", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120)),
                new RefinedMaterial("Courgette coupés en dès",
                    "Pelez et coupez en dès de 0.5 cm les courgettes",
                    new RawMaterial("Courgette", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120)),
                new RefinedMaterial("Céleri coupés en dès", "Coupez en dès de 0.5 cm le céleri",
                    new RawMaterial("Céleri vert", FoodFamily.VEGETABLE, FoodUnit.BRANCH, 2)),
                new RefinedMaterial("Haricot blanc égouté", "Egouter les haricots blancs",
                    new RawMaterial("Haricot blanc", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 100)) },
            false, null, null, 2),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] { new RawMaterial(
                "Petites pâtes en forme de coquillage, non cuite "
                    + "(pâte avec une durée de cuisson de 9 min.)",
                FoodFamily.PATE, FoodUnit.GRAMME, 100) },
            new RefinedMaterial[] {}, false, null, null, 3) };

    // Instantiate the recipe
    Recipe recipe = new Recipe("Minestrone", 8, 25, 4, Category.PLAT_DE_RESISTANCE, steps,
        CookMode.BOUILLIR);

    // Return it
    return recipe;
  }

  /**
   * Create a risotto recipe.
   * 
   * @return a risotto recipe.
   */
  private Recipe makeRisotto() {

    // Recipe's steps
    Step[] steps = new Step[] {
        new Step(Tool.MULTI_BLADE, new RawMaterial[] {},
            new RefinedMaterial[] {
                new RefinedMaterial("Oignon coupé en 4", "Pelez et coupez l'oignon en 4",
                    new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90)) },
            Boolean.TRUE,
            "Actionner pendant 5 secondes le bouton impulsion 'pulse', "
                + "ouvrez le couvercle et raclez les parois du bol.",
            null, null),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] {
                new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40) },
            new RefinedMaterial[] {}, false, null, 9, 1),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] {
                new RawMaterial("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300) },
            new RefinedMaterial[] {}, false, null, null, 2),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] {
                new RawMaterial("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150) },
            new RefinedMaterial[] {}, false, null, null, 3),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] {
                new RawMaterial("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500) },
            new RefinedMaterial[] {}, false, null, null, 4),
        new Step(Tool.STIR_ASSIST,
            new RawMaterial[] { new RawMaterial("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
                new RawMaterial("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 40) },
            new RefinedMaterial[] {}, false,
            "Appuyez sur annuler pour désactiver la fonction de maintien au chaud. "
                + "Réglez sur vitesse 1 et laisser tourner pendant une minute.",
            null, 5) };

    // Instantiate the recipe
    Recipe recipe = new Recipe("Risotto", 2, 30, 4, Category.PLAT_DE_RESISTANCE, steps,
        CookMode.MIJOTER);

    // Return it
    return recipe;
  }
}
