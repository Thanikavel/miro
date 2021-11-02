package com.miro.whiteboard.pages;

import com.miro.whiteboard.utils.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    private CommonUtil commonUtil = new CommonUtil();

    String item01 = "", item02 = "" , item03 = "", item04 = "", methodToCall = "", productName = "";

    @FindBy(how = How.XPATH, using = "//*[@id=\"cc-window\"]/div[5]/a[1]")
    private WebElement popupCookie;

    @FindBy(xpath="//div//a[@href='?add_to_wishlist=14' and @data-product-id = '14']")
    private WebElement item1;

    @FindBy(xpath="//div//a[@href='?add_to_wishlist=17' and @data-product-id = '17']")
    private WebElement item2;

    @FindBy(xpath="//div//a[@href='?add_to_wishlist=20' and @data-product-id = '20']")
    private WebElement item3;

    @FindBy(xpath="//div//a[@href='?add_to_wishlist=23' and @data-product-id = '23']")
    private WebElement item4;

    @FindBy(xpath="//*[@id=\"yith-wcwl-row-14\"]/td[6]/a")
    private WebElement addToCart1;

    @FindBy(xpath="//*[@id=\"yith-wcwl-row-17\"]/td[6]/a")
    private WebElement addToCart2;

    @FindBy(xpath="//*[@id=\"yith-wcwl-row-20\"]/td[6]/a")
    private WebElement addToCart3;

    @FindBy(xpath="//*[@id=\"yith-wcwl-row-23\"]/td[6]/a")
    private WebElement addToCart4;

    @FindBy(xpath="//div[@class='header-wishlist']")
    private WebElement wishlist;

    @FindBy(xpath="//tr[@id='yith-wcwl-row-14']")
    private WebElement wishlistitem1;

    @FindBy(xpath="//tr[@id='yith-wcwl-row-17']")
    private WebElement wishlistitem2;

    @FindBy(xpath="//tr[@id='yith-wcwl-row-20']")
    private WebElement wishlistitem3;

    @FindBy(xpath="//tr[@id='yith-wcwl-row-23']")
    private WebElement wishlistitem4;

    @FindBy(xpath="//*[@id=\"yith-wcwl-form\"]/table/tbody/tr")
    private List<WebElement> wishListTotalElements;

    @FindBy(xpath="//h1[contains(text(),'Cart']")
    private WebElement cart;

    @FindBy(xpath="//*[@id=\"site-content\"]/div/div/article/div/div/div[1]/div/form/table/tbody/tr")
    private List<WebElement> cartTotalElements;


    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void gotoWishList() {
        this.wishlist.click();
    }

    public String wishListItems01() {
        return wishlistitem1.getText();
    }

    public String wishListItems02() {
        return wishlistitem2.getText();
    }

    public String wishListItems03() {
        return wishlistitem3.getText();
    }

    public String wishListItems04() {
        return wishlistitem4.getText();
    }

    public void cart(){
        try {
            driver.findElement(By.xpath("//i[@class='la la-shopping-bag']")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//i[@class='la la-shopping-bag']")).click();
        }
    }

    /**
     * to perform action for creating new board
     */
    public void addProductToWishList() throws InterruptedException {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(this.popupCookie));
        commonUtil.validateCookie(this.popupCookie, actions);
        this.item1.click();
        this.item2.click();
        this.item3.click();
        this.item4.click();
        CommonUtil.takeScreenShot(HomePage.scenario);
        Thread.sleep(5000);
    }

    /**
     * to perform action for creating new board
     */
    public void wishListTable() throws InterruptedException {
        this.gotoWishList();
        CommonUtil.takeScreenShot(HomePage.scenario);
        wait.until(ExpectedConditions.visibilityOf(this.wishlistitem1));
        item04 = this.wishListItems04();
        CommonUtil.takeScreenShot(HomePage.scenario);
        item03 = this.wishListItems03();
        CommonUtil.takeScreenShot(HomePage.scenario);
        item01 = this.wishListItems01();
        CommonUtil.takeScreenShot(HomePage.scenario);
        item02 = this.wishListItems02();
        CommonUtil.takeScreenShot(HomePage.scenario);
        Thread.sleep(1000);
        System.out.println(" item1 : " + item01 + " item2 : " + item02 + " item3 : " + item03 + " item4 : " + item04 );

        int columns_count = wishListTotalElements.size();
        System.out.println("Number of cells In Row are "+columns_count);
    }

    /**
     * to perform action for creating new board
     */
    public void wishListCount() throws InterruptedException {
        int columns_count = wishListTotalElements.size();
        System.out.println("Number of cells In Row are "+columns_count);

    }

    /**
     * to perform action for creating new board
     */
//    public void searchLowestPrice() throws InterruptedException {
//        int columns_count = wishListTotalElements.size();
//        System.out.println("Number of cells In Row are "+columns_count);
//        Integer value1 = 0, value2 = 0, tempLowestPrice = 0, lowestPrice = 0;
//        String regex = ".00";
//        // split the string object
//        String[] output = item01.split(regex);
//
////        findLowestPrice(output[0], output[1], lowestPrice, tempLowestPrice, "AddToCart1");
//        String substring1 = output[0].length() > 2 ? output[0].substring(output[0].length() - 2) : output[0];
//        String substring2 = output[1].length() > 2 ? output[1].substring(output[1].length() - 2) : output[1];
//        productName = output[0].substring(0,output[0].length() - 4);
//        value1 = Integer.parseInt(substring1);
//        value2 = Integer.parseInt(substring2);
//        lowestPrice = value1 < value2 ? value1 : value2;
//        tempLowestPrice = lowestPrice;
//        methodToCall = "AddToCart1";
//
////        regex = ".00";
//        // split the string object
//        output = item02.split(regex);
//        lowestPrice = findLowestPrice(output[0], output[1], lowestPrice, tempLowestPrice, "AddToCart2");
//
//        output = item03.split(regex);
//        lowestPrice = findLowestPrice(output[0], output[1], lowestPrice, tempLowestPrice, "AddToCart3");
//
//        output = item04.split(regex);
//        lowestPrice = findLowestPrice(output[0], output[1], lowestPrice, tempLowestPrice, "AddToCart4");
//
//        System.out.println(lowestPrice);
//        System.out.println(methodToCall);
//
//    }

    /**
     * to perform action for creating new board
     */
    public void searchLowestPrice() throws InterruptedException {
        int columns_count = wishListTotalElements.size();
        System.out.println("Number of cells In Row are "+columns_count);
        Integer value1 = 0;
        Integer value2 = 0;
        Integer tempLowestPrice = 0;
        Integer lowestPrice = 0;
        String regex = ".00";
        // split the string object
        String[] output = item01.split(regex);
        String substring1 = output[0].length() > 2 ? output[0].substring(output[0].length() - 2) : output[0];
        String substring2 = output[1].length() > 2 ? output[1].substring(output[1].length() - 2) : output[1];
        productName = output[0].substring(0,output[0].length() - 4);
        System.out.println("Name:"+output[0]);
        System.out.println("Age:"+output[1]);
        System.out.println("Country:"+output[2]);
        System.out.println("substring1:"+substring1);
        System.out.println("substring2:"+substring2);
        value1 = Integer.parseInt(substring1);
        value2 = Integer.parseInt(substring2);
        lowestPrice = value1 < value2 ? value1 : value2;
        tempLowestPrice = lowestPrice;
        methodToCall = "AddToCart1";
        System.out.println("tempLowestPrice:"+tempLowestPrice);
        System.out.println("methodToCall:"+methodToCall);
//        if(lowestPrice != tempLowestPrice){
//            methodToCall = "AddToCart2";
//            tempLowestPrice = lowestPrice;
//        }

        regex = ".00";
        // split the string object
        output = item02.split(regex);
        substring1 = output[0].length() > 2 ? output[0].substring(output[0].length() - 2) : output[0];
        substring2 = output[1].length() > 2 ? output[1].substring(output[1].length() - 2) : output[1];
        System.out.println("Name:"+output[0]);
        System.out.println("Age:"+output[1]);
        System.out.println("Country:"+output[2]);
        System.out.println("substring1:"+substring1);
        System.out.println("substring2:"+substring2);
        value1 = Integer.parseInt(substring1);
        value2 = Integer.parseInt(substring2);
        lowestPrice = value1 < lowestPrice ? value1 : lowestPrice;
        lowestPrice = value2 < lowestPrice ? value2 : lowestPrice;
        System.out.println("tempLowestPrice:"+tempLowestPrice);
        System.out.println("lowestPrice:"+lowestPrice);
        if(lowestPrice != tempLowestPrice){
            productName = output[0].substring(0,output[0].length() - 4);
            methodToCall = "AddToCart2";
            tempLowestPrice = lowestPrice;
        }


        regex = ".00";
        // split the string object
        output = item03.split(regex);
        substring1 = output[0].length() > 2 ? output[0].substring(output[0].length() - 2) : output[0];
        substring2 = output[1].length() > 2 ? output[1].substring(output[1].length() - 2) : output[1];
        System.out.println("Name:"+output[0]);
        System.out.println("Age:"+output[1]);
        System.out.println("Country:"+output[2]);
        System.out.println("substring1:"+substring1);
        System.out.println("substring2:"+substring2);
        value1 = Integer.parseInt(substring1);
        value2 = Integer.parseInt(substring2);
        lowestPrice = value1 < lowestPrice ? value1 : lowestPrice;
        lowestPrice = value2 < lowestPrice ? value2 : lowestPrice;
        System.out.println("tempLowestPrice:"+tempLowestPrice);
        System.out.println("lowestPrice:"+lowestPrice);
        if(lowestPrice != tempLowestPrice){
            productName = output[0].substring(0,output[0].length() - 4);
            methodToCall = "AddToCart3";
            tempLowestPrice = lowestPrice;
        }

        regex = ".00";
        // split the string object
        output = item04.split(regex);
        substring1 = output[0].length() > 2 ? output[0].substring(output[0].length() - 2) : output[0];
        substring2 = output[1].length() > 2 ? output[1].substring(output[1].length() - 2) : output[1];

        value1 = Integer.parseInt(substring1);
        value2 = Integer.parseInt(substring2);
        lowestPrice = value1 < lowestPrice ? value1 : lowestPrice;
        lowestPrice = value2 < lowestPrice ? value2 : lowestPrice;
        System.out.println("tempLowestPrice:"+tempLowestPrice);
        System.out.println("lowestPrice:"+lowestPrice);
        if(lowestPrice != tempLowestPrice){
            productName = output[0].substring(0,output[0].length() - 4);
            methodToCall = "AddToCart4";
            tempLowestPrice = lowestPrice;
        }

        System.out.println(lowestPrice);
        System.out.println(methodToCall);

    }



    private Integer findLowestPrice(String itemSplitValueOne, String itemSplitValueTwo, Integer lowestPrice, Integer tempLowestPrice, String methodToCall){
        Integer value1 = Integer.parseInt(itemSplitValueOne.length() > 2 ? itemSplitValueOne.substring(itemSplitValueOne.length() - 2) : itemSplitValueOne);
        Integer value2 = Integer.parseInt(itemSplitValueTwo.length() > 2 ? itemSplitValueTwo.substring(itemSplitValueTwo.length() - 2) : itemSplitValueTwo);
        lowestPrice = value1 < lowestPrice ? value1 : lowestPrice;
        lowestPrice = value2 < lowestPrice ? value2 : lowestPrice;
        if(lowestPrice <= tempLowestPrice){
            productName = itemSplitValueOne.substring(0,itemSplitValueOne.length() - 4);
            this.methodToCall = methodToCall;
            return lowestPrice;
        }
        return tempLowestPrice;
    }


    /**
     * to perform action for creating new board
     */
    public void addLowestToCart() throws InterruptedException {
        switch (methodToCall){
            case "AddToCart2" :
                this.addToCart2.click();
                break;
            case "AddToCart3" :
                this.addToCart3.click();
                break;
            case "AddToCart4" :
                this.addToCart4.click();
                break;
            default:
                this.addToCart1.click();

        }

        CommonUtil.takeScreenShot(HomePage.scenario);

    }

    /**
     * to perform action for creating new board
     */
    public void verifyItemInCart() throws InterruptedException {
        this.cart();
        CommonUtil.takeScreenShot(HomePage.scenario);
        System.out.println("thani : " + new CartSummaryPage().getProductName());
        CommonUtil.takeScreenShot(HomePage.scenario);
        int columns_count = cartTotalElements.size();
        System.out.println("Number of cells In Row are "+columns_count);
        System.out.println(productName);

    }

    public static Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

}
