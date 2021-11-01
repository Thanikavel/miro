package com.miro.whiteboard.pages;

import com.miro.whiteboard.utils.CommonUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.comparison.PointsMarkupPolicy;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    private CommonUtil commonUtil = new CommonUtil();

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li")
    List<WebElement> allSubPages;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header-login-btn\"]/span")
    WebElement signIn;

    @FindBy(how = How.ID, using = "email")
    WebElement emailId;

    @FindBy(how = How.ID, using = "password")
    WebElement password;

    @FindBy(how = How.CLASS_NAME, using = "signup__submit")
    WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "templatesSectionContent__title-i1wV8")
    WebElement signInValidation;

    @FindBy(how = How.CLASS_NAME, using = "serviceCard__icon-3J1NI")
    WebElement newBoard;

    @FindBy(how = How.XPATH, using = "/html/body/board-will-be-shared/div/div/div/div/div[3]/button[1]")
    WebElement popupSharedBoard;

    @FindBy(how = How.XPATH, using = "//*[@id=\"react-modals-container\"]/div/div/button/div")
    WebElement popupClose;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ng-transclude/div[1]/div[1]")
    WebElement popupNewCourse1;

    @FindBy(how = How.XPATH, using = "//*[@id=\"canvasContainer\"]/div[2]/div[3]/div/div[14]/div[3]")
    WebElement stickers;

    @FindBy(how = How.XPATH, using = "//*[@id=\"canvasContainer\"]/div[2]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]")
    WebElement boardName;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ng-transclude/div[2]/div[1]/input")
    WebElement boardNameInput;

    @FindBy(how = How.XPATH, using = "//*[@id=\"canvasContainer\"]/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/div/div/div/button")
    WebElement boardShareButton;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/share-content/div/div/div[2]/div[1]/div[1]/div/div[2]/button/span")
    WebElement copyInvite;

    @FindBy(how = How.XPATH, using = "//*[@id=\"canvasContainer\"]/div[2]/div[2]/div[1]/div[1]/div[1]")
    WebElement homePage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"router-container-wrapper\"]/div/div[1]/div[4]/div[2]/div[2]/div[1]/div[1]/div[2]/user-profile/div/button/div/div")
    WebElement userSettings;

    @FindBy(how = How.XPATH, using = "//*[@id=\"router-container-wrapper\"]/div/div[1]/div[4]/div[2]/div[2]/div[1]/div[1]/div[2]/user-profile/div/div/div[1]/div[2]/button[5]/div/span")
    WebElement logout;

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignIn() {
        return signIn;
    }

    public WebElement getEmailId() {
        return emailId;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    /**
     * to perform action for creating new board
     */
    public void loginToMiro(String userEmailId, String password) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(getEmailId()));
        getEmailId().clear();
        getEmailId().sendKeys(userEmailId);
        wait.until(ExpectedConditions.visibilityOf(getPassword()));
        getPassword().sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(getSignInButton()));
        getSignInButton().click();
    }

    /**
     * to perform action for creating new board
     */
    public void createMiroBoard() throws InterruptedException {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(this.newBoard));
        validateAllPopUp(actions);
        actions.click(this.newBoard).build().perform();
        Thread.sleep(1000);
        CommonUtil.takeScreenShot(HomePage.scenario);
        validateAllPopUp(actions);
        commonUtil.validatePopUp(this.popupSharedBoard, actions);
        validateAllPopUp(actions);
        actions.moveToElement(this.boardName)
                .click()
                .build().perform();
        Thread.sleep(2000);
        actions.moveToElement(this.boardNameInput)
                .doubleClick()
                .sendKeys("Board")
                .build().perform();
        actions.moveToElement(this.boardNameInput)
                .moveByOffset(400, 60)
                .click()
                .build().perform();
        scenario.log("New white board - Board is created");
        Thread.sleep(2000);
    }

    /**
     * to perform action for creating new sticky notes
     */
    public void createNewStickyNotes(int xOffset, int yOffset, Boolean isAdditionalStickyNotes) throws IOException {
        Actions actions = new Actions(driver);
        // create new miro board sticker
        actions.click(this.stickers)
                .moveByOffset(xOffset, yOffset)
                .click()
                .sendKeys("Miro board validation")
                .build().perform();
        // perform the click outside the sticky notes
        actions.moveToElement(this.stickers)
                .moveByOffset(xOffset + 200, 60)
                .click()
                .build().perform();
        validateAllPopUp(actions);
        // create screenshots for comparison
        if (!isAdditionalStickyNotes) {
            Screenshot miroScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);
            File file = new File("build/imageBase/");
            boolean bool = file.mkdir();
            ImageIO.write(miroScreenShot.getImage(), "png",
                    new File("build/imageBase/miro.png"));
        }
    }

    /**
     * to perform action for sharing the miro board
     */
    public String shareMiroBoard() throws IOException, UnsupportedFlavorException {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.boardShareButton)
                .click()
                .build().perform();
        actions.moveToElement(this.copyInvite)
                .click()
                .build().perform();
        String shareUrl = commonUtil.getMiroBoardUrl(scenario);
        actions.moveToElement(this.copyInvite)
                .moveByOffset(400, 60)
                .click()
                .build().perform();
        return shareUrl;
    }

    /**
     * to perform validation for sticky notes created
     */
    public Boolean validateStickyNotes() throws IOException {
        Actions actions = new Actions(driver);
        validateAllPopUp(actions);
        Screenshot miroScreenShot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        File file = new File("build/imageTemp/");
        boolean bool = file.mkdir();
        ImageIO.write(miroScreenShot.getImage(), "png",
                new File("build/imageTemp/miro.png"));
        BufferedImage expectedImage = ImageIO.read(new File("build/imageBase/miro.png"));
        PointsMarkupPolicy diffMarkupPolicy = new PointsMarkupPolicy();
        diffMarkupPolicy.setDiffSizeTrigger(1600);
        ImageDiffer imgDiff = new ImageDiffer().withDiffMarkupPolicy(diffMarkupPolicy);
        ImageDiff diff = imgDiff.makeDiff(expectedImage, miroScreenShot.getImage());
        BufferedImage diffImage = diff.getMarkedImage();
        BufferedImage diffMarkedImage = diff.getDiffImage();
        ImageIO.write(diffMarkedImage, "png",
                new File("build/imageTemp/diffImage.png"));
        scenario.log("diffImage is " + diff.hasDiff());
        scenario.log("diffImage is " + diff.getDiffSize());
        byte[] bytesImage = commonUtil.toByteArray(diffMarkedImage, "png");
        scenario.attach(bytesImage, "image/png", scenario.getName());
        return diff.hasDiff();
    }

    /**
     * to perform action for creating new board
     */
    public void logoutMiro() throws InterruptedException {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(this.homePage));
        actions.click(this.homePage).build().perform();
        wait.until(ExpectedConditions.visibilityOf(this.userSettings));
        actions.click(this.userSettings).build().perform();
        wait.until(ExpectedConditions.visibilityOf(this.logout));
        actions.click(this.logout).build().perform();
    }

    public static Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

    /**
     * All the pop up are handled here
     *
     * @param actions - to pass the action reference
     */
    public void validateAllPopUp(Actions actions) {
        commonUtil.validatePopUp(this.popupClose, actions);
        commonUtil.validatePopUp(this.popupSharedBoard, actions);
        commonUtil.validatePopUp(this.popupNewCourse1, actions);
    }
}
