/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SwagLabs;


import java.util.Arrays;
import java.util.List;

import org.bouncycastle.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class SwagLabsTest {
 static WebDriver driver;
    @Test(priority = 1)
   //  @Parameters({"URL"})
    
    public static void homeTest() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
         driver=new ChromeDriver();
        driver.manage().window().maximize();
        Home home= new Home(driver);
        boolean actual=home.getHome("https://www.saucedemo.com/");
        Assert.assertEquals(actual, true);
        
    }
    @Test(priority = 2)
   // @Parameters({"Username","Password"})
    public static void loginTest() throws InterruptedException{
        Login login= new Login(driver);
        boolean actual=login.loginCart("standard_user", "secret_sauce");
        Assert.assertEquals(actual, true,"login unsuccessful");
        
    }
    @DataProvider(name = "productNames")
    public static String[][] buyingProducts(){
    	
    	String[][] obj=new String[][]{ {"Sauce Labs Backpack"},{"Sauce Labs Bolt T-Shirt"}};
    	return obj;
    }
    
    @Test(priority = 3,dataProvider = "productNames")
    
    public static void addToCartTest(String product1) throws InterruptedException{
    	
        AddToCart add= new AddToCart(driver);
        boolean actual=add.addToCart(product1);
        Assert.assertEquals(actual, true,"adding product cart failed");
        
     
    }
 
    @Test(priority = 4)
    
    public static void verfifyCartTest() throws InterruptedException{
        Checkout check= new Checkout(driver);
        boolean actual= check.clickOnCart(); 
        Thread.sleep(3000);       
    	List<String>list= Arrays.asList("Sauce Labs Backpack","Sauce Labs Bolt T-Shirt");
	     actual=check.verifyCartContent(list);
		Assert.assertEquals(actual, true,"verfying cart content failed");
      
        
    }
    
    @Test(priority = 5)
 //   @Parameters({"FirstName","LastName","Pincode"})
    public static void CheckoutTest() throws InterruptedException {
    	Thread.sleep(3000);
        Checkout check= new Checkout(driver);
    	boolean actual= check.checkout(); 
    	Thread.sleep(3000);
    	 actual =check.address("dev","123","123");
         Assert.assertEquals(actual, true,"adding address failed");
         actual= check.order();         //7
         Assert.assertEquals(actual, true,"placing order unsuccessfull");
         driver.quit();
	}

   
}


