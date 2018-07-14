module fr.mougnibas.cookhelper.recipe.contract {

  exports fr.mougnibas.cookhelper.recipe.contract.client;
  exports fr.mougnibas.cookhelper.recipe.contract.exception;
  exports fr.mougnibas.cookhelper.recipe.contract.model;
  exports fr.mougnibas.cookhelper.recipe.contract.service;

  // TODO Migrate to a real module when it will be possible
  requires javax.ws.rs.api;
}