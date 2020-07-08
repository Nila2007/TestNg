package org.example;

//import com.sun.org.apache.bcel.internal.generic.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//demo.nopcommerce webside Registration..
public class  Register  {
    //Create a class and implement Webdriver
    static WebDriver driver;
    //write a reusable Method
    public static void TypeText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }// click method with  Two parameter, one for time
    public static void clickOnElement(By by,int time){
        driver.findElement(by).click();
    } //time stamp method
    public static long timestamp(){
        return (System.currentTimeMillis());
    } // below method is for text
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }//select method for select for drop down list
    public static void selectFromDropDownByVisiableText(By by, String text){
         Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }// Syncronization mecanisum
    public static void waitUntilElementToBeClickeble(By by) {
        //WebDriverWait wait = new WebDriverWait(driver,TimeUnit);
       // wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    @BeforeMethod
    public static void InitialiseMethode(){
        // set the cromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Soft\\chromedriver.exe");
        // object for chrome driver
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        System.setProperty("webdriver.chrome.driver","C:\\Soft\\chromedriver.exe ");
        //open the browser
        driver = new ChromeDriver();
        // to maximize window
        driver.manage().window().maximize();
        //synchronizetion micanizum
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // type the url
        driver.get("https://demo.nopcommerce.com/ ");
    }
    @Test // method for Registration page
    public static void Registration(){
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        /* open Ragistretion page*/
        driver.findElement(By.linkText("Register")).click();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
         // click on radio button male, female
        clickOnElement(By.xpath("//input[@id=\"gender-female\"]"),40);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        TypeText(By.xpath("//input[@id='FirstName']"),"Nila");
        TypeText(By.xpath("//input[@id='LastName']"),"Patel");
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        selectFromDropDownByVisiableText(By.xpath("//select[@name='DateOfBirthDay']"),"5");
        // select method to select frome drop down
        Select drpMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        drpMonth.selectByVisibleText("May");
       selectFromDropDownByVisiableText(By.xpath("//select[@name='DateOfBirthYear']"),"1912");

        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        TypeText(By.xpath("//input[@id='Email']"),"nila+"+timestamp()+"@gmail.com");
        TypeText(By.xpath("//input[@id='Company']"),"neevABC");
        driver.findElement(By.xpath("//input[@id='Newsletter']")).click();
        TypeText(By.id("Password"),"123neev");
        TypeText(By.id("ConfirmPassword"),"123neev");
        clickOnElement(By.id("register-button"),20);
        String expectedText = "Your registration completed";
        String actualText = getTextFromElement(By.xpath("//div[@class='result']"));
        // assert
        Assert.assertEquals(actualText,expectedText);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
       clickOnElement(By.xpath("//input[@name=\"register-continue\"]"),20);
       //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }
    @Test(priority = 2) // this method for Book catgory
    public static void BookCategory(){
        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        // Navigate to the boook category
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"),40);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        // add book in shoping cart
        clickOnElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[1]"),30);
        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        clickOnElement(By.xpath("//a[contains(text(),'First Prize Pies')]"),30);
        // add another Book in shoping cart
        clickOnElement(By.xpath("//input[@id='add-to-cart-button-38']"),30);
       //clickOnElement(By.xpath("//div[@class='name']//a[contains(text(),'First Prize Pies')]"),30);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"),30);
        // pass the Srting method for print book name
        String expectedText="Fahrenheit 451 by Ray Bradbury";
        // check Excepted and actual Result
        String actualText = getTextFromElement(By.xpath("//a[@class='product-name']" +
                "[contains(text(),'Fahrenheit 451 by Ray Bradbury')]"));
        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        Assert.assertEquals(actualText,expectedText);
        String expectedText1="First Prize Pies";
        String acutualText1= getTextFromElement(By.xpath("//a[@class='product-name']" +
                "[contains(text(),'First Prize Pies')]"));
        Assert.assertEquals(acutualText1,expectedText1);
    }
     @Test(priority = 1) //set the test case priority
    public static void  EmailAFriend() { //This method for email a friend
         clickOnElement(By.linkText("Computers"), 40);
         driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
         clickOnElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[1]//a[1]//img[1]"),40);
         //clickOnElement(By.xpath("//h2[@class='title']//a[contains(text(),'Desktops')]"), 40);
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         clickOnElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"),30);
         driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
         clickOnElement(By.xpath("//input[@class='button-2 email-a-friend-button valid']"),60);
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         //clickOnElement(By.xpath("\"//input[@value=\\"Email a friend\\"]\""),20);
         driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
         TypeText(By.xpath("//input[@id='YourEmailAddress']"),"neev123@gmail.com");
         TypeText(By.xpath("//textarea[@id='PersonalMessage']"),"Hello duck");
         clickOnElement(By.xpath("//input[@name='send-email']"),30);
         String expectedText="Your message has been sent";
         driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
         String actualText = getTextFromElement(By.xpath("//div[@class='result']"));
         //clickOnElement(By.xpath("//input[@name='send-email']"),30);
         Assert.assertEquals(actualText,expectedText);

     }
    }

