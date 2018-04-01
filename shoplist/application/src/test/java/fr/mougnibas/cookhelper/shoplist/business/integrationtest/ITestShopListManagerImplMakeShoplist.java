package fr.mougnibas.cookhelper.shoplist.business.integrationtest;

import fr.mougnibas.cookhelper.shoplist.CommonPackaging;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.shoplist.contract.model.Material;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test of ShoplistManager, makeShopList method.
 * 
 * @author Yoann
 */
@RunWith(Arquillian.class)
public class ITestShopListManagerImplMakeShoplist {

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
  public void testMakeShopLists() {
    String[] recipesName = { "Minestrone", "Risotto" };
    Shoplist expected = new Shoplist(new Material[] {
        new Material("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 210),
        new Material("Gousse d'ail", FoodFamily.VEGETABLE, FoodUnit.VOID, 1),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.CS, 2),
        new Material("Dès de lard fumé", FoodFamily.MEAT, FoodUnit.GRAMME, 100),
        new Material("Dès de tomates (en conserve)", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 200),
        new Material("Cube de bouillon de légume", FoodFamily.BOUILLON, FoodUnit.VOID, 2),
        new Material("Feuille de laurier", FoodFamily.MISC, FoodUnit.VOID, 1),
        new Material("Eau", FoodFamily.WATER, FoodUnit.L, 1),
        new Material("Carotte", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Pomme de terre", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Courgette", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Céleri vert", FoodFamily.VEGETABLE, FoodUnit.BRANCH, 2),
        new Material("Haricot blanc", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 100),
        new Material(
            "Petites pâtes en forme de coquillage, non cuite "
                + "(pâte avec une durée de cuisson de 9 min.)",
            FoodFamily.PATE, FoodUnit.GRAMME, 100),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40),
        new Material("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300),
        new Material("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150),
        new Material("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500),
        new Material("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
        new Material("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 80) });
    Shoplist actual = shoplistManager.makeShopList(recipesName);
    Assert.assertEquals(expected, actual);
  }
}
