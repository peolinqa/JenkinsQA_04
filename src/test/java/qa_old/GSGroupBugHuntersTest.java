package qa_old;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class GSGroupBugHuntersTest extends BaseTest {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Actions actions;
    private static final String VALID_EMAIL = "standard_user@gmail.com";
    private static final String VALID_PASSWORD = "automation123";
    private static final String INVALID_PASSWORD = "automation12345";

    @BeforeMethod
    private void initializeDrivers() {
        driver = getDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 15, 200);
    }

    @DataProvider(name = "items")
    public static Object[][] itemsData() {
        return new Object[][]{
                {"Faded Short Sleeve T-shirts"},
                {"Printed Summer Dress"},
                {"Printed Chiffon Dress"}
        };
    }

    private WebElement getEmailField() {
        By emailBy = By.xpath("//*[@id='email']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailBy));
        return driver.findElement(emailBy);
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.xpath("//*[@id='passwd']"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.xpath("//*[@id='SubmitLogin']"));
    }

    private WebElement getAccountPage() {
        By accountBy = By.xpath("//a[@title='Information']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountBy));
        return driver.findElement(accountBy);
    }

    private WebElement getSearchField() {
        By searchFieldBy = By.xpath("//*[@name='search_query']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldBy));
        return driver.findElement(searchFieldBy);
    }

    private WebElement getSearchButton() {
        return driver.findElement(By.xpath("//*[@name='submit_search']"));
    }

    private WebElement getDeleteButton() {
        return driver.findElement(By.xpath("//*[@title='Delete']"));
    }

    public void openLoginPage() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void loginToWebsite(String username, String password) {
        openLoginPage();
        getEmailField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }

    public boolean verifyLoginSuccessful() {
        getAccountPage().click();
        String accountEmail = getEmailField().getAttribute("value");
        return accountEmail.equals(VALID_EMAIL);
    }

    public boolean verifyLoginFailed() {
        By errorBy = By.xpath(" //div[@class='alert alert-danger']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void searchForItem(String itemName) {
        getSearchField().sendKeys(itemName);
        getSearchButton().click();
    }

    public void hoverOverProduct(String itemName) {
        By productBy = By.xpath("//div[@class='right-block']//a[@class='product-name' and @title='" + itemName + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productBy));
        actions.moveToElement(driver.findElement(productBy)).perform();
    }

    public WebElement getAddToCartButton(String itemName) {
        hoverOverProduct(itemName);
        By addToCartBy = By.xpath("//div[@class='product-container']//a[@class='product-name' and " +
                "@title='" + itemName + "']/ancestor::node()[2]//a[@title='Add to cart']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBy));
        return driver.findElement(addToCartBy);
    }

    public void openCartPage() {
        driver.findElement(By.xpath("//*[@title='View my shopping cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_title']")));
        getDeleteButton().click();
    }

    public void addItemToCart(String itemName) {
        By successBy = By.xpath("//*[@class='icon-ok']");
        getAddToCartButton(itemName).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successBy));
        driver.findElement(By.xpath("//*[@title='Close window']")).click();
    }

    public boolean verifyItemExistsInCart(String itemName) {
        By itemBy = By.linkText(itemName);
        openCartPage();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verifyCartIsEmpty() {
        By emptyMessageBy = By.xpath("//*[@class='alert alert-warning' and " +
                "contains(text(),'Your shopping cart is empty.')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emptyMessageBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Test(priority = 1)
    public void loginToWebsite_CorrectCredentials_LoginSuccess_GeorgeStolbunov() {
        openLoginPage();
        loginToWebsite(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(verifyLoginSuccessful());
    }

    @Test(priority = 2)
    public void loginToWebsite_IncorrectCredentials_LoginFailed_GeorgeStolbunov() {
        openLoginPage();
        loginToWebsite(VALID_EMAIL, INVALID_PASSWORD);
        Assert.assertTrue(verifyLoginFailed());
    }

    @Test(priority = 3, dataProvider = "items")
    public void addItemToCart_VerifyItemAdded_GeorgeStolbunov(String itemName) {
        openLoginPage();
        loginToWebsite(VALID_EMAIL, VALID_PASSWORD);
        searchForItem(itemName);
        addItemToCart(itemName);
        Assert.assertTrue(verifyItemExistsInCart(itemName));
    }

    @Test(priority = 4, dataProvider = "items")
    public void addItemToCart_DeleteItem_VerifyCartIsEmpty_GeorgeStolbunov(String itemName) {
        openLoginPage();
        loginToWebsite(VALID_EMAIL, VALID_PASSWORD);
        searchForItem(itemName);
        addItemToCart(itemName);
        openCartPage();
        getDeleteButton().click();
        Assert.assertTrue(verifyCartIsEmpty());
    }
}
