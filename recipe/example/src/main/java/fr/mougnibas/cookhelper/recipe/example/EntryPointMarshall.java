/*
  © Copyright 2017 Yoann MOUGNIBAS
  
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

package fr.mougnibas.cookhelper.recipe.example;

import fr.mougnibas.cookhelper.recipe.model.Category;
import fr.mougnibas.cookhelper.recipe.model.CookMode;
import fr.mougnibas.cookhelper.recipe.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.model.Recipe;
import fr.mougnibas.cookhelper.recipe.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.model.Step;
import fr.mougnibas.cookhelper.recipe.model.Tool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Entry point class to marshall pojo to XML.
 * 
 * @author Yoann
 */
public class EntryPointMarshall {

  /**
   * The entry point of the class.
   * 
   * @param args
   *          Not used
   * @throws Exception
   *           If there is something wrong.
   */
  public static void main(String[] args) throws Exception {

    // Create a recipe
    Recipe risotto = makeRisotto();

    // Create a technical object (JAXB Context)
    JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);

    // Create a technical object (Marshaller)
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    // Marshal the recipe
    jaxbMarshaller.marshal(risotto, System.out);
  }

  /**
   * Make a risotto recipe.
   * 
   * @return a risotto recipe.
   */
  private static Recipe makeRisotto() {

    // Content of the recipe
    String name = "Risotto";
    Integer timeToPrepare = 2;
    Integer timeToCook = 30;
    Integer numberOfPerson = 4;
    Category category = Category.PLAT_DE_RESISTANCE;

    Step step1 = new Step(Tool.MULTI_BLADE, null,
        new RefinedMaterial[] {
            new RefinedMaterial("Oignon coupé en 4", "Pelez et coupez l'oignon en 4",
                new RawMaterial("Oignon", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 90)) },
        true, "Actionner pendant 5 secondes le bouton impulsion 'pulse', ouvrez le couvercle "
            + "et raclez les parois du bol.",
        null, null);

    Step step2 = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] { new RawMaterial("Huile d'olive", FoodFamily.OIL, FoodUnit.GRAMME, 40) },
        null, false, null, 9, 1);

    Step step3 = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Riz Carnaroli", FoodFamily.VEGETABLE, FoodUnit.GRAMME, 300) },
        null, false, null, null, 2);

    Step step4 = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Vin blanc sec", FoodFamily.ALCOHOL, FoodUnit.GRAMME, 150) },
        null, false, null, null, 3);

    Step step5 = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] {
            new RawMaterial("Bouillon de légume", FoodFamily.BOUILLON, FoodUnit.ML, 500) },
        null, false, null, null, 4);

    Step step6 = new Step(Tool.STIR_ASSIST,
        new RawMaterial[] { new RawMaterial("Beurre", FoodFamily.MISC, FoodUnit.GRAMME, 20),
            new RawMaterial("Copeaux de parmesan", FoodFamily.CHEESE, FoodUnit.GRAMME, 80) },
        null, false, "Appuyez sur annuler pour désactiver la fonction de maintien au chaud. "
            + "Réglez sur vitesse 1 et laisser tourner pendant une minute.",
        null, 5);

    Step[] steps = new Step[] { step1, step2, step3, step4, step5, step6 };
    CookMode cookMode = CookMode.MIJOTER;

    // Instantiate the recipe
    Recipe risotto = new Recipe(name, timeToPrepare, timeToCook, numberOfPerson, category, steps,
        cookMode);

    // Return it
    return risotto;
  }

}
