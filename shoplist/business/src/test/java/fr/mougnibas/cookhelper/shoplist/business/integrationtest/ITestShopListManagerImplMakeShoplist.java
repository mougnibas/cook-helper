package fr.mougnibas.cookhelper.shoplist.business.integrationtest;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.model.Shoplist;

/**
 * Integration test of ShoplistManager, makeShopList method.
 * 
 * @author Yoann
 */
public class ITestShopListManagerImplMakeShoplist {

  // TODO write me

  @Inject
  private ShoplistManager shoplistManager;

  @Test
  public void testMakeShopLists() {
    String[] recipesName = null;
    Shoplist expected = null;
    Shoplist actual = shoplistManager.makeShopList(recipesName);
    Assert.assertEquals(expected, actual);
  }
}
