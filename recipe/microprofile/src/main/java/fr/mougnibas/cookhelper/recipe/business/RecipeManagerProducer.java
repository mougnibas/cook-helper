package fr.mougnibas.cookhelper.recipe.business;

import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RecipeManagerProducer {

  @Produces
  public RecipeManager make() {
    return new RecipeManagerImpl();
  }
}
