module fr.mougnibas.cookhelper.recipe.contract {

  exports fr.mougnibas.cookhelper.recipe.contract.client;
  exports fr.mougnibas.cookhelper.recipe.contract.exception;
  exports fr.mougnibas.cookhelper.recipe.contract.model;
  exports fr.mougnibas.cookhelper.recipe.contract.service;

  // TODO Migrate to a real module when Eclipse MicroProfile will get this one
  requires javax.ws.rs.api;
}