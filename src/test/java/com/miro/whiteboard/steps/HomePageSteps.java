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

    @Given("I navigate to the miro page")
    public void navigateToMiro() {
        String url = Setup.baseUrl;
        commonUtil.navigateToUrl(Setup.baseUrl);
        assertEquals(url, HomePage.driver.getCurrentUrl());
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I login to the miro page with given credentials")
    public void loginPage(DataTable dataTable) throws InterruptedException {
        String userEmailId = dataTable.asMaps().get(0).get("userEmailId");
        String password = dataTable.asMaps().get(0).get("password");
        page.wait.until(ExpectedConditions.visibilityOf(page.getSignIn()));
        page.getSignIn().click();
        page.loginToMiro(userEmailId, password);
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Then("I create a new white miro board")
    public void createNewBoard() throws InterruptedException, IOException, UnsupportedFlavorException {
        page.createMiroBoard();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Then("I create sticker widget")
    public void createNewStickyNotes() throws InterruptedException, IOException, UnsupportedFlavorException {
        page.createNewStickyNotes(200, 60, false);
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @And("I share the miro board")
    public void shareMiroBoard() throws IOException, UnsupportedFlavorException {
        shareUrl = page.shareMiroBoard();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Then("I logout from the miro application")
    public void logoutMiro() throws InterruptedException {
        page.logoutMiro();
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @When("I navigate to shared url to the miro page with given credentials")
    public void loginMiroPage(DataTable dataTable) throws InterruptedException {
        String userEmailId = dataTable.asMaps().get(0).get("secondUserEmailId");
        String password = dataTable.asMaps().get(0).get("secondUserPassword");
        commonUtil.navigateToUrl(shareUrl);
        page.loginToMiro(userEmailId, password);
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Then("I create additional sticker widget")
    public void createAdditionalStickyNotes() throws IOException {
        page.createNewStickyNotes(400, 60, true);
        CommonUtil.takeScreenShot(HomePage.scenario);
    }

    @Then("I verify if the sticker widget is present and {string} validated")
    public void verifyStickerWidget(String valid) throws IOException {
        Boolean hasDiff = page.validateStickyNotes();
        if (valid.equalsIgnoreCase("valid")) {
            Assert.assertFalse("Visual differences found!", hasDiff);
        } else {
            Assert.assertTrue("Visual differences not found!", hasDiff);
        }
        CommonUtil.takeScreenShot(HomePage.scenario);
    }
}
