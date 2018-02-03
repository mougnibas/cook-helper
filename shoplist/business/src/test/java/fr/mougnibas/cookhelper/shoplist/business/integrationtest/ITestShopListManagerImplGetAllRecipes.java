package fr.mougnibas.cookhelper.shoplist.business.integrationtest;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.model.RecipeForShoplist;

/**
 * Integration test of ShoplistManager, getAllRecipes method.
 * 
 * @author Yoann
 */
public class ITestShopListManagerImplGetAllRecipes {

  // TODO write me

  @Inject
  private ShoplistManager shoplistManager;

  @Test
  public void testGetAllRecipes() {
    RecipeForShoplist[] expected = null;
    RecipeForShoplist[] actual = shoplistManager.getAllRecipes();
    Assert.assertArrayEquals(expected, actual);
  }
}
