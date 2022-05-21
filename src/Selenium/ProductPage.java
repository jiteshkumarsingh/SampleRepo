package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {


	WebDriver driver;
	WebDriverWait wait;
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	WebElement menuButton;

	@FindBy(id="about_sidebar_link")
	WebElement aboutLink;

	@FindBy(xpath="//span[contains(text(),'Product')]")
	WebElement product;

	public void clickMenuButton() {

		menuButton.click();

	}

	public void clickAboutLink() {

		aboutLink.click();

	}
}
