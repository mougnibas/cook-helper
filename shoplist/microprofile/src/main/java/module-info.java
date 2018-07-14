module fr.mougnibas.cookhelper.shoplist.microprofile {

  // Nothing exported (private module)

  // Require recipe and shoplist contract
  requires fr.mougnibas.cookhelper.recipe.contract;
  requires fr.mougnibas.cookhelper.shoplist.contract;

  // Require eclipse microprofile modules
  // TODO Migrate to real modules when it will be possible
  requires javax.ws.rs.api;
  requires cdi.api;
  requires javax.inject;
  requires microprofile.health.api;
}