package fr.mougnibas.cookhelper.shoplist.business.integrationtest;

import fr.mougnibas.cookhelper.recipe.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;
import fr.mougnibas.cookhelper.shoplist.CommonPackaging;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.RecipeForShoplist;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test of ShoplistManager, getAllRecipes method.
 * 
 * @author Yoann
 */
@RunWith(Arquillian.class)
public class ITestShopListManagerImplGetAllRecipes {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {
    return CommonPackaging.createDeployment();
  }

  @Inject
  private ShoplistManager shoplistManager;

  @Test
  public void testGetAllRecipes() {
    RecipeForShoplist[] expected = {
        new RecipeForShoplist("Minestrone", 4,
            new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
            new RawMaterial("Gousse d'ail", FoodFamily.VEGETABLE, FoodUnit.VOID, 1),
            new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.CS, 2),
            new RawMaterial("Dès de lard fumé", FoodFamily.MEAT, FoodUnit.GRAMME, 100),
            new RawMaterial("Dès de tomates (en conserve)", FoodFamily.VEGETABLE, FoodUnit.GRAMME,
                200),
            new RawMaterial("Cube de bouillon de légume", FoodFamily.BOUILLON, FoodUnit.VOID, 2),
            new RawMaterial("Feuille de laurier", FoodFamily.MISC, FoodUnit.VOID, 1),
            new RawMaterial("Eau", FoodFamily.WATER, FoodUnit.L, 1),
            new RawMaterial("Carotte", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
            new RawMaterial("Pomme de terre", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
            new RawMaterial("Courgette", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
            new RawMaterial("Céleri vert", FoodFamily.VEGETABLE, FoodUnit.BRANCH, 2),
            new RawMaterial("Haricot blanc", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 100),
            new RawMaterial(
                "Petites pâtes en forme de coquillage, non cuite "
                    + "(pâte avec une durée de cuisson de 9 min.)",
                FoodFamily.PATE, FoodUnit.GRAMME, 100)),
        new RecipeForShoplist("Risotto", 4,
            new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90),
            new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40),
            new RawMaterial("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300),
            new RawMaterial("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150),
            new RawMaterial("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500),
            new RawMaterial("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
            new RawMaterial("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 80)) };
    RecipeForShoplist[] actual = shoplistManager.getAllRecipes();
    Assert.assertArrayEquals(expected, actual);
  }
}
