package fr.mougnibas.cookhelper.recipe;

import fr.mougnibas.cookhelper.recipe.business.RecipeManagerImpl;
import fr.mougnibas.cookhelper.recipe.contract.exception.InitializationException;
import fr.mougnibas.cookhelper.recipe.contract.model.Category;
import fr.mougnibas.cookhelper.recipe.contract.model.CookMode;
import fr.mougnibas.cookhelper.recipe.contract.model.FoodFamily;
import fr.mougnibas.cookhelper.recipe.contract.model.FoodUnit;
import fr.mougnibas.cookhelper.recipe.contract.model.RawMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Recipe;
import fr.mougnibas.cookhelper.recipe.contract.model.RefinedMaterial;
import fr.mougnibas.cookhelper.recipe.contract.model.Step;
import fr.mougnibas.cookhelper.recipe.contract.model.Tool;
import fr.mougnibas.cookhelper.recipe.contract.service.RecipeManager;
import fr.mougnibas.cookhelper.recipe.contract.util.EndpointsReader;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeApplication;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeGet;
import fr.mougnibas.cookhelper.recipe.jaxrs.RecipeList;
import fr.mougnibas.cookhelper.recipe.web.servlet.HealthcheckServlet;
import fr.mougnibas.cookhelper.recipe.web.servlet.ListServlet;
import fr.mougnibas.cookhelper.recipe.web.servlet.RecipeServlet;
import fr.mougnibas.cookhelper.util.ReaderUtil;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class CommonPackaging {

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {

    // Add contract dependency
    JavaArchive contractJar = ShrinkWrap.create(JavaArchive.class);
    contractJar.addClass(InitializationException.class);
    contractJar.addClass(Category.class);
    contractJar.addClass(CookMode.class);
    contractJar.addClass(FoodFamily.class);
    contractJar.addClass(FoodUnit.class);
    contractJar.addClass(RawMaterial.class);
    contractJar.addClass(Recipe.class);
    contractJar.addClass(RefinedMaterial.class);
    contractJar.addClass(Step.class);
    contractJar.addClass(Tool.class);
    contractJar.addClass(RecipeManager.class);
    contractJar.addClass(EndpointsReader.class);
    contractJar.addAsResource("endpoints.properties");

    // Add util dependency
    JavaArchive utilJar = ShrinkWrap.create(JavaArchive.class);
    utilJar.addClass(ReaderUtil.class);

    // Add current project content
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
    jar.addClass(RecipeManagerImpl.class);
    jar.addClass(RecipeApplication.class);
    jar.addClass(RecipeGet.class);
    jar.addClass(RecipeList.class);
    jar.addClass(HealthcheckServlet.class);
    jar.addClass(ListServlet.class);
    jar.addClass(RecipeServlet.class);
    jar.addAsResource("index.txt");
    jar.addAsResource("minestrone.xml");
    jar.addAsResource("risotto.xml");

    // Ass current project test content
    jar.addAsResource("recipe-minestrone.txt");
    jar.addAsResource("recipe-minestrone.xml");
    jar.addAsResource("recipe-risotto.txt");
    jar.addAsResource("recipe-risotto.xml");
    jar.addAsResource("recipes-list-name.json");
    jar.addAsResource("recipes-list-name.txt");

    // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class, "cook-helper-recipe.war");

    // Create the war
    war.addAsLibrary(contractJar);
    war.addAsLibrary(utilJar);
    war.addAsLibrary(jar);

    // Return the deployment
    return war;
  }
}
