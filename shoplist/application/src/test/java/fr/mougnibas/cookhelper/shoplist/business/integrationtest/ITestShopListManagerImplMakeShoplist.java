package fr.mougnibas.cookhelper.shoplist.business.integrationtest;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
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
