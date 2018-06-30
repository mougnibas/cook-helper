package fr.mougnibas.cookhelper.shoplist.business;

import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ShoplistManagerProducer {
  
  @Inject
  private RecipeManager recipeManager;

  @Produces
  public ShoplistManager make() {
    return new ShoplistManagerImpl(recipeManager);
  }
}
