package com.miro.whiteboard.steps;

import com.miro.whiteboard.pages.HomePage;
import com.miro.whiteboard.utils.CommonUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HomePageSteps {

    private CommonUtil commonUtil = new CommonUtil();
    private final HomePage page = new HomePage();
    private String shareUrl;

    @Given("I navigate to the e-shopping cart home page")
    public void navigateToHomePage() {
        String url = Setup.baseUrl;
        commonUtil.navigateToUrl(Setup.baseUrl);
        assertEquals(url, HomePage.driver.getCurrentUrl());
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Given("I add {string} different products to my wish list")
    public void addProductsToWishList(String noOfProduct) throws InterruptedException {
        System.out.println("noOfProduct = " + noOfProduct);
        page.addProductToWishList();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I view my wishlist table")
    public void wishListTable() throws InterruptedException {
        page.wishListTable();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I find total four selected items in my wishlist")
    public void wishListCount() throws InterruptedException {
        page.wishListCount();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I search for lowest price product")
    public void searchLowestPrice() throws InterruptedException {
        page.searchLowestPrice();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I am able to add the lowest price item to my cart")
    public void addLowestToCart() throws InterruptedException {
        page.addLowestToCart();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I am able to verify the item in my cart")
    public void verifyItemInCart() throws InterruptedException {
        page.verifyItemInCart();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }
}
