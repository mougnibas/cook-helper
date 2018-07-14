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

package fr.mougnibas.cookhelper.shoplist.microprofile.jaxrs;

import fr.mougnibas.cookhelper.shoplist.contract.exception.RecipeNotFoundException;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;
import fr.mougnibas.cookhelper.shoplist.contract.service.ShoplistManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Get a shoplist, by recipe name(s).
 * 
 * @author Yoann
 */
@Path("/")
public class GetShoplist {

  /**
   * Recipe manager implementation.
   */
  @Inject
  private ShoplistManager shoplistManager;

  /**
   * List all of the recipe names.
   * 
   * @return all of the recipe names.
   */
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  @GET
  public byte[] getByName(@QueryParam("name") String... recipeNames) throws IOException {

    // Try to make the shoplist
    Shoplist shoplist;
    try {
      shoplist = shoplistManager.getShoplist(recipeNames);
    } catch (RecipeNotFoundException ex) {
      throw new NotFoundException(ex);
    }

    // Serialize it
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(shoplist);

    // And send it to the wire
    return baos.toByteArray();
  }
}
