package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PracticeOne {
	/*
	 * Test Scenario to Login into Sauce site and select the highest price item and add it to the cart and perform further activities.
	 * */

public static void main(String[] args) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Chrome\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Login Screen using Username and Password//
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		
		driver.findElement(By.id("login-button")).click();
		
		//Into the product page//
		ProductPage prod = new ProductPage(driver); //Used PageFactory to implement POM 
		
		//Clicking Menu Link
		prod.clickMenuButton();
		
		//clicking About link
		prod.clickAboutLink();
		
		
		//Verify user taken to https://saucelabs.com/ site
		Thread.sleep(2000);
		String url_actual = driver.getCurrentUrl();
		String url_expected = "https://saucelabs.com/";
		Assert.assertEquals(url_actual, url_expected,"User is taken to Sauce Lab site");
		
		//Click back and verify it lands on product page
		driver.navigate().back();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Product')]")));
		String product = driver.findElement(By.xpath("//span[contains(text(),'Product')]")).getText();
		System.out.println("Product: " + product);
		if(product.contains("PRODUCTS")) {
			
			System.out.println("Able to land at Product page");
		}else{
			System.out.println("Able to Not land at Product page");
		}
		
		//Selecting Product with highest price
		Thread.sleep(1000);
		WebElement sort = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select sc = new Select(sort);
		sc.selectByValue("hilo");
		
		Thread.sleep(2000);
		//Adding the highest Price item into cart
		driver.findElement(By.xpath("(//div[@class='inventory_item'])[1]//button")).click();
		
		//Clicking on cart link
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Your Cart')]")));
		
		//Click on Check Out
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		//Into Checkout: Your Information Screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Checkout: Your Information')]")));
		
		Thread.sleep(2000);
		//First name
		driver.findElement(By.id("first-name")).sendKeys("Hello");
		//Last name
		driver.findElement(By.id("last-name")).sendKeys("Java");
		//Zip Code
		driver.findElement(By.id("postal-code")).sendKeys("560037");
		
		
		//Click continue button
		driver.findElement(By.id("continue")).click();
		
		//Into Checkout: Overview Screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Checkout: Overview')]")));
		
		//verify the Price format
		String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
		System.out.println("Price: " + price);
		
		
}
}
