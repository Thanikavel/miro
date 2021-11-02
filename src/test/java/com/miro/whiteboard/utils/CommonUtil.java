package com.miro.whiteboard.utils;

import com.miro.whiteboard.pages.HomePage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is the common util and it will hold commmon methods which will support
 * and provide information to all tests
 */
public class CommonUtil {

    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    public Properties readPropertiesFile(String fileName) {
        InputStream fis = null;
        Properties prop = null;

        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * to navigate to the specific url
     *
     * @param url
     */
    public void navigateToUrl(String url) {
        HomePage.driver.get(url);
        new WebDriverWait(HomePage.driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }


    /**
     * @param element present or not present is checked
     * @return Boolean
     */
    public Boolean isElementPresent(WebElement element) {
        try {
            HomePage.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * To check if the pop is present and if present action is preformed on that element
     *
     * @param element to be validated is passed as input
     * @param actions - to pass the action reference
     */
    public void validateCookie(WebElement element, Actions actions) {
        if (isElementPresent(element)) {
            actions.click(element).build().perform();
        }
    }

    /**
     * To check if the pop is present and if present action is preformed on that element
     *
     * @param element to be validated is passed as input
     * @param actions - to pass the action reference
     */
    public void validatePopUp(WebElement element, Actions actions) {
        if (isElementPresent(element)) {
            actions.click(element).build().perform();
        }
    }

    /**
     * common method to take screen shot
     *
     * @param scenario
     */
    public static void takeScreenShot(Scenario scenario) {
        scenario.attach(((TakesScreenshot) HomePage.driver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
    }

    /**
     * common method to convert BufferedImage to byte[]
     *
     * @param bufferedImage
     * @param format
     * @return
     * @throws IOException
     */
    public byte[] toByteArray(BufferedImage bufferedImage, String format)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    /**
     * @return miro board url that needs to be shared with others
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public String getMiroBoardUrl(Scenario scenario) throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String shareUrl = (String) contents.getTransferData(DataFlavor.stringFlavor);
        scenario.log("The url to be sharded with another user is " + shareUrl);
        return shareUrl;
    }

}
