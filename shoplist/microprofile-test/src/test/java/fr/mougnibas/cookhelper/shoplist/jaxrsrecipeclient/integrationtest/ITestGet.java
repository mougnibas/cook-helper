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

package fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.integrationtest;

import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.client.ShoplistManagerJaxRsClientImpl;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.shoplist.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.shoplist.contract.model.Material;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test about "RecipeGet" RestFull WebService.
 * 
 * @author Yoann
 */
public class ITestGet {

  /**
   * JAX-RS client of shoplist manager.
   */
  private ShoplistManager shoplistManager;

  @Before
  public void init() {
    shoplistManager = new ShoplistManagerJaxRsClientImpl("http://localhost:8100/");
  }

  @Test
  public void testGetRisotto() throws Exception {

    // Expected
    Shoplist expected = makeRisottoShoplist();

    // Actual
    Shoplist actual = shoplistManager.getShoplist("Risotto");

    // Compare
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetMinestrone() throws Exception {

    // Expected
    Shoplist expected = makeMinestroneShoplist();

    // Actual
    Shoplist actual = shoplistManager.getShoplist("Minestrone");

    // Compare
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetRisottoAndMinestrone() throws Exception {

    // Expected
    Shoplist expected = makeRisottoAndMinestroneShoplist();

    // Actual
    Shoplist actual = shoplistManager.getShoplist("Risotto", "Minestrone");

    // Compare
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetMinestroneAndRisotto() throws Exception {

    // Expected
    Shoplist expected = makeRisottoAndMinestroneShoplist();

    // Actual
    Shoplist actual = shoplistManager.getShoplist("Minestrone", "Risotto");

    // Compare
    Assert.assertEquals(expected, actual);
  }

  /**
   * Make a shoplist with only a Risotto recipe.
   * 
   * @return a shoplist with only a Risotto recipe.
   */
  private Shoplist makeRisottoShoplist() {

    return new Shoplist(new Material("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
        new Material("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500),
        new Material("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 40),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40),
        new Material("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90),
        new Material("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300),
        new Material("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150));
  }

  /**
   * Make a shoplist with only a Minestrone recipe.
   * 
   * @return a shoplist with only a Minestrone recipe.
   */
  private Shoplist makeMinestroneShoplist() {

    return new Shoplist(new Material("Carotte", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Courgette", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Cube de bouillon de légume", FoodFamily.BOUILLON, FoodUnit.VOID, 2),
        new Material("Céleri vert", FoodFamily.VEGETABLE, FoodUnit.BRANCH, 2),
        new Material("Dès de lard fumé", FoodFamily.MEAT, FoodUnit.GRAMME, 100),
        new Material("Dès de tomates (en conserve)", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 200),
        new Material("Eau", FoodFamily.WATER, FoodUnit.L, 1),
        new Material("Feuille de laurier", FoodFamily.MISC, FoodUnit.VOID, 1),
        new Material("Gousse d'ail", FoodFamily.VEGETABLE, FoodUnit.VOID, 1),
        new Material("Haricot blanc", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 100),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.CS, 2),
        new Material("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material(
            "Petites pâtes en forme de coquillage, non cuite "
                + "(pâte avec une durée de cuisson de 9 min.)",
            FoodFamily.PATE, FoodUnit.GRAMME, 100),
        new Material("Pomme de terre", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120));
  }

  /**
   * Make a shoplist with a Minestrone and a Risotto recipe.
   * 
   * @return a shoplist with a Minestrone and a Risotto recipe.
   */
  private Shoplist makeRisottoAndMinestroneShoplist() {

    return new Shoplist(new Material("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
        new Material("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500),
        new Material("Carotte", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Céleri vert", FoodFamily.VEGETABLE, FoodUnit.BRANCH, 2),
        new Material("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 40),
        new Material("Courgette", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Cube de bouillon de légume", FoodFamily.BOUILLON, FoodUnit.VOID, 2),
        new Material("Dès de lard fumé", FoodFamily.MEAT, FoodUnit.GRAMME, 100),
        new Material("Dès de tomates (en conserve)", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 200),
        new Material("Eau", FoodFamily.WATER, FoodUnit.L, 1),
        new Material("Feuille de laurier", FoodFamily.MISC, FoodUnit.VOID, 1),
        new Material("Gousse d'ail", FoodFamily.VEGETABLE, FoodUnit.VOID, 1),
        new Material("Haricot blanc", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 100),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40),
        new Material("Huile d'olive", FoodFamily.OIL, FoodUnit.CS, 2),
        new Material("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 210),
        new Material(
            "Petites pâtes en forme de coquillage, non cuite "
                + "(pâte avec une durée de cuisson de 9 min.)",
            FoodFamily.PATE, FoodUnit.GRAMME, 100),
        new Material("Pomme de terre", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 120),
        new Material("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300),
        new Material("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150));
  }
}
