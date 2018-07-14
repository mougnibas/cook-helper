package fr.mougnibas.cookhelper.shoplist.microprofile.client;

import fr.mougnibas.cookhelper.recipe.contract.client.RecipeManagerJaxRsClientImpl;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RecipeManagerProducer {

  @Produces
  public RecipeManager make() {
    return new RecipeManagerJaxRsClientImpl();
  }
}
