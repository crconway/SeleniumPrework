

/**
 * Created by Washburn13 on 3/5/17.
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;


import java.util.concurrent.TimeUnit;


public class AccessSite {
    @FindBy(how= How.XPATH, using="//SPAN[@class='top-refinement-label js-top-refinement-label drop-down__title drop-down__title--alt'][text()='Brand']/self::SPAN") WebElement brandBox;

    public ChromeDriver driver;


    @Before
    public void setUpDriver() {

        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Test


    public void goToWebPage()
    {
        setUpDriver();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10,TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        driver.get("http://www.homedepot.com");
//        assertTrue(driver.getTitle().equals("The Home Depot"));
        System.out.println("Title Pass");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div [@class='MyStore__store' and text()='Midtown']"), "Kennesaw North"));

        WebElement element=driver.findElement(By.xpath("//input[@class='SearchBox__input']"));
        element.sendKeys("Hammer");
        element.sendKeys(Keys.RETURN);
        System.out.println("Search Entered");
        //driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);

        boolean displayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='top-refinement-items' and ./li[contains(.,'Brand')]]"))).isDisplayed();

        System.out.println("wait");
        brandBox.click();

        Assert.assertEquals(true, isPageLoaded());

        element=driver.findElement(By.xpath("//INPUT[@id='4']"));
        System.out.println("Search");
        element.sendKeys("Husky");
        element.sendKeys(Keys.RETURN);
        System.out.println("Brand Filtered");



        //quitDriver();

    }
    public boolean isPageLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
       WebDriverWait wait1 = new WebDriverWait(driver, 40);
        wait1.until(pageLoadCondition);

        return true;
    }

    @After
    public void quitDriver(){

       //driver.quit();

    }
}


