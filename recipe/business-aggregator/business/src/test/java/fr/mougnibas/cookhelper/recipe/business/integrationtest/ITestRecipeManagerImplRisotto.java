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

package fr.mougnibas.cookhelper.recipe.business.integrationtest;

import fr.mougnibas.cookhelper.recipe.business.RecipeManagerImpl;
import fr.mougnibas.cookhelper.recipe.contract.PackagingContract;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.data.PackagingData;
import fr.mougnibas.cookhelper.recipe.model.Category;
import fr.mougnibas.cookhelper.recipe.model.CookMode;
import fr.mougnibas.cookhelper.recipe.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.model.PackagingModel;
import fr.mougnibas.cookhelper.recipe.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.recipe.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.model.Step;
import fr.mougnibas.cookhelper.recipe.model.Tool;
import fr.mougnibas.cookhelper.util.PackagingUtil;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A full test of "Risotto" recipe from the Recipe Manager.
 * 
 * @author Yoann
 */
@RunWith(Arquillian.class)
public class ITestRecipeManagerImplRisotto {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {

    // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class);

    // Add the dependencies
    war.addAsLibrary(PackagingModel.makeJar());
    war.addAsLibrary(PackagingUtil.makeJar());
    war.addAsLibrary(PackagingData.makeJar());
    war.addAsLibrary(PackagingContract.makeJar());

    // Add the jar business dependency
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
    jar.addClass(RecipeManagerImpl.class);
    war.addAsLibrary(jar);

    // Return the deployment
    return war;
  }

  /**
   * The service to test.
   */
  @Inject
  private RecipeManager recipeManager;

  /**
   * The recipe to test, from the recipe manager.
   */
  private Recipe recipe;

  /**
   * Get the recipe.
   */
  @Before
  public void testGetByNameRisotto() {
    recipe = recipeManager.getByName("Risotto");
  }

  @Test
  public void testRecipeName() {
    String expected = "Risotto";
    String actual = recipe.getName();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testTimeToPrepare() {
    Integer expected = 2;
    Integer actual = recipe.getTimeToPrepare();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testTimeToCook() {
    Integer expected = 30;
    Integer actual = recipe.getTimeToCook();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testNumberOfPerson() {
    Integer expected = 4;
    Integer actual = recipe.getNumberOfPerson();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testCategory() {
    Category expected = Category.PLAT_DE_RESISTANCE;
    Category actual = recipe.getCategory();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testCookMode() {
    CookMode expected = CookMode.MIJOTER;
    CookMode actual = recipe.getCookMode();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testTools() {
    Tool[] expected = { Tool.STIR_ASSIST, Tool.MULTI_BLADE };
    Tool[] actual = recipe.getTools();
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testMaterials() {
    RawMaterial[] expected = { new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90),
        new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40),
        new RawMaterial("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300),
        new RawMaterial("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150),
        new RawMaterial("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500),
        new RawMaterial("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
        new RawMaterial("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 80) };
    RawMaterial[] actual = recipe.getMaterials();
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testRefinedMaterials() {
    RefinedMaterial[] expected = new RefinedMaterial[] {
        new RefinedMaterial("Oignon coupé en 4", "Pelez et coupez l'oignon en 4",
            new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90)) };
    RefinedMaterial[] actual = recipe.getRefinedMaterials();
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testStep1() {
    Step expected = new Step(Tool.MULTI_BLADE, null,
        new RefinedMaterial[] {
            new RefinedMaterial("Oignon coupé en 4", "Pelez et coupez l'oignon en 4",
                new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90)) },
        true, "Actionner pendant 5 secondes le bouton impulsion 'pulse', ouvrez le couvercle "
            + "et raclez les parois du bol.",
        null, null);
    Step actual = recipe.getSteps()[0];
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStep2() {
    Step expected = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] { new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40) },
        null, false, null, 9, 1);
    Step actual = recipe.getSteps()[1];
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStep3() {
    Step expected = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300) },
        null, false, null, null, 2);
    Step actual = recipe.getSteps()[2];
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStep4() {
    Step expected = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150) },
        null, false, null, null, 3);
    Step actual = recipe.getSteps()[3];
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStep5() {
    Step expected = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500) },
        null, false, null, null, 4);
    Step actual = recipe.getSteps()[4];
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStep6() {
    Step expected = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] { new RawMaterial("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
            new RawMaterial("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 80) },
        null, false, "Appuyez sur annuler pour désactiver la fonction de maintien au chaud. "
            + "Réglez sur vitesse 1 et laisser tourner pendant une minute.",
        null, 5);
    Step actual = recipe.getSteps()[5];
    Assert.assertEquals(expected, actual);
  }
}
