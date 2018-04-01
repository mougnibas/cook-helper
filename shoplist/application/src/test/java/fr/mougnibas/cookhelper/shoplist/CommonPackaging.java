package fr.mougnibas.cookhelper.shoplist;

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
import fr.mougnibas.cookhelper.shoplist.business.ShoplistManagerImpl;
import fr.mougnibas.cookhelper.shoplist.contract.ShoplistManager;
import fr.mougnibas.cookhelper.shoplist.contract.model.RecipeForShoplist;
import fr.mougnibas.cookhelper.shoplist.contract.model.Shoplist;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeClientQualifier;
import fr.mougnibas.cookhelper.shoplist.jaxrsrecipeclient.JaxrsRecipeManagerImpl;
import fr.mougnibas.cookhelper.util.ReaderUtil;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class CommonPackaging {

  /**
   * Create a JAR containing recipe contract.
   * 
   * @return a JAR containing recipe contract.
   */
  public static JavaArchive createRecipeContractJar() {

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

    return contractJar;
  }

  /**
   * Create a JAR containing recipe application.
   * 
   * @return a JAR containing recipe application.
   */
  public static JavaArchive createRecipeApplicationJar() {

    // Add application project content
    JavaArchive applicationJar = ShrinkWrap.create(JavaArchive.class);
    applicationJar.addClass(RecipeManagerImpl.class);
    applicationJar.addClass(RecipeApplication.class);
    applicationJar.addClass(RecipeGet.class);
    applicationJar.addClass(RecipeList.class);
    applicationJar.addClass(HealthcheckServlet.class);
    applicationJar.addClass(ListServlet.class);
    applicationJar.addClass(RecipeServlet.class);
    applicationJar.addAsResource("index.txt");
    applicationJar.addAsResource("minestrone.xml");
    applicationJar.addAsResource("risotto.xml");

    return applicationJar;
  }

  /**
   * Create a JAR containing util.
   * 
   * @return a JAR containing util.
   */
  public static JavaArchive createUtilJar() {

    // Add util dependency
    JavaArchive utilJar = ShrinkWrap.create(JavaArchive.class);
    utilJar.addClass(ReaderUtil.class);

    return utilJar;
  }

  /**
   * Create a deploy ready WAR.
   * 
   * @return a deploy ready WAR.
   */
  @Deployment
  public static WebArchive createDeployment() {

    // Add contract dependency
    JavaArchive contractJar = ShrinkWrap.create(JavaArchive.class);
    contractJar.addClass(ShoplistManager.class);
    contractJar.addClass(fr.mougnibas.cookhelper.shoplist.contract.model.Category.class);
    contractJar.addClass(fr.mougnibas.cookhelper.shoplist.contract.model.FoodFamily.class);
    contractJar.addClass(fr.mougnibas.cookhelper.shoplist.contract.model.FoodUnit.class);
    contractJar.addClass(fr.mougnibas.cookhelper.shoplist.contract.model.Material.class);
    contractJar.addClass(RecipeForShoplist.class);
    contractJar.addClass(Shoplist.class);

    // Add current project content
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
    jar.addClass(ShoplistManagerImpl.class);
    jar.addClass(JaxrsRecipeClientQualifier.class);
    jar.addClass(JaxrsRecipeManagerImpl.class);

    // Add current project test content
    jar.addAsResource("recipe-minestrone.xml");
    jar.addAsResource("recipe-risotto.xml");

    // Add util dependency
    JavaArchive utilJar = createUtilJar();

    // Deployment to return
    WebArchive war = ShrinkWrap.create(WebArchive.class, "cook-helper-recipe.war");

    // Create the war
    war.addAsLibrary(contractJar);
    war.addAsLibrary(utilJar);
    war.addAsLibrary(jar);
    war.addAsLibrary(createRecipeContractJar());
    war.addAsLibrary(createRecipeApplicationJar());

    // Return the deployment
    return war;
  }
}
