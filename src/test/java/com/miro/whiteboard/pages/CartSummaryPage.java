package com.miro.whiteboard.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartSummaryPage extends BasePage {

	@FindBy(xpath="//td[contains(@class,'product-name')]//a")
	private WebElement product;

	public CartSummaryPage() {
		super();
		PageFactory.initElements(driver, this);
	}

	public String getProductName() {
		return product.getText();
		}	
}