import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class LetsBeTestersTest extends BaseTest {

    private static final String URL_NOKIA = "https://www.nokia.com/";
    private static final String URL_DAVINAGAZ = "https://davinagaz.by/";
    private static final String URL_FLAGMA = "https://flagma.si/";
    private static final String POP_UP_NOKIA = "modalAcceptAllBtn";

    private WebDriverWait wait;
    private Actions action;
    private JavascriptExecutor javascriptExecutor;

    @BeforeMethod
    private void before() {
        wait = new WebDriverWait(getDriver(), 10);
        action = new Actions(getDriver());
        javascriptExecutor = (JavascriptExecutor) getDriver();
    }

    @Deprecated
    private void openWebSite(String url) {
        getDriver().get(url);
    }

    @Ignore
    @Test
    public void testCountOfSectionButtons() {

        getDriver().get(URL_NOKIA);
        getDriver().findElement(By.id(POP_UP_NOKIA)).click();

        WebElement startCarousel = getDriver().findElement(By.cssSelector("#tns1-item0"));
        action.moveToElement(startCarousel).build().perform();
        List<WebElement> carousel = getDriver().findElements(By.cssSelector("div[id*='tns1-item'] h2"));

        Assert.assertEquals(carousel.size(), 10);
    }

    @Test
    public void testCheckTablet() {

        getDriver().get(URL_NOKIA);
        getDriver().findElement(By.id(POP_UP_NOKIA)).click();

        WebElement buttonForConsumers = getDriver().findElement(By.cssSelector("li[class$='dropdown-menu'] > a[href*='nokia']"));
        action.moveToElement(buttonForConsumers).build().perform();

        getDriver().findElement(By.xpath("//li[@class='dropdown-submenu-item']/a[text()='Phones']")).click();
        getDriver().findElement(By.xpath("//a[@data-gtm-cta='tablets']")).click();
        String actualResult = getDriver().findElement(By.cssSelector("li[class*='h5']")).getText();

        Assert.assertEquals(actualResult, "Nokia T20");
    }

    @Test
    public void testKICheckAddress() {

        getDriver().get(URL_DAVINAGAZ);

        getDriver().findElement(By.xpath("//b[text()=' Полесская 14']"))
                .click();

        WebElement nameOffice = getDriver().findElement(
                By.cssSelector(".name-office"));

        Assert.assertTrue(nameOffice.getText().contains("Полесская 14"));
    }

    @Test
    public void testKICheckHeader() throws InterruptedException {

        getDriver().get(URL_DAVINAGAZ);

        getDriver().findElement(By.xpath("//b[text()=' Полесская 14']"))
                .click();

        WebElement menu = getDriver().findElement(
                By.xpath("//a[text()='ТО и фильтра']"));

        Actions action = new Actions(getDriver());
        action.moveToElement(menu).perform();

        getDriver().findElement(By.xpath("//a[text()='Аккумулятор']")).click();

        WebElement headerText = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(headerText.getText(), "АККУМУЛЯТОР");
    }
    @Ignore
    @Test
    public void testElementsTextBox() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        String[] testData = {"Maksim", "test@test.com", "Sankt-Peterburg"};

        getDriver().findElement(By.id("item-0")).click();

        getDriver().findElement(By.id("userName")).sendKeys(testData[0]);
        getDriver().findElement(By.id("userEmail")).sendKeys(testData[1]);
        getDriver().findElement(By.id("currentAddress")).sendKeys(testData[2]);

        getDriver().findElement(By.id("submit")).click();

        String[] actualData = new String[3];
        actualData[0] = getDriver().findElement(By.id("name")).getText().replace("Name:", "");
        actualData[1] = getDriver().findElement(By.id("email")).getText().replace("Email:", "");
        actualData[2] = getDriver().findElement(By.xpath("//*[@class='mb-1'][@id='currentAddress']"))
                .getText().replace("Current Address :", "");

        System.out.println(getDriver().findElement(By.id("currentAddress")).getText());

        Assert.assertEquals(actualData, testData);
    }

    @Test
    public void testElementsRadioButton() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.id("item-2")).click();

        getDriver().findElement(By.xpath("//*[@for='impressiveRadio']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='text-success']")).getText(), "Impressive");
    }

    @Test
    public void testFlagmaMainPageOpening() {

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        WebElement goodsAndServicesTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(goodsAndServicesTitle.getText(), "Товары и услуги в Словении");
    }

    @Test
    public void testOfNavigationToCoffeeSection() {

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();
        getDriver().findElement(By.xpath("//div[@class='toggle-cats']")).click();

        WebElement foodMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@Class='cat-746']")));
        action.moveToElement(foodMenuItem).perform();

        WebElement groupOfDrinksItem = getDriver().findElement(By.xpath("//span[text()='Вода, напитки, соки']"));
        action.moveToElement(groupOfDrinksItem).perform();

        WebElement coffeeTeaCacaoItem = getDriver().findElement(By.xpath("//span[text()='Чай, кофе, какао']"));
        action.moveToElement(coffeeTeaCacaoItem).perform();

        WebElement coffeeItem = getDriver().findElement(By.xpath("//a[@href='https://flagma.si/ru/products/kofe/']/span[text()='Кофе']"));
        action.moveToElement(coffeeItem).click().perform();

        WebElement titleOfCoffeeSection = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(titleOfCoffeeSection.getText(), "Кофе в Словении");
    }

    @Test
    public void testCheckSmartphoneIphoneSelection() {

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();
        getDriver().findElement(By.xpath("//div[@id='search-input']//input[@name='q']"))
                .sendKeys("iphone\n");

        WebElement searchResultTitle = getDriver().findElement(By.xpath("//div[@class='title']"));

        Assert.assertEquals(searchResultTitle.getText(), "Найдено по запросу iphone в Словении");
    }

    @Ignore
    @Test
    public void testRegistration() {

        openWebSite("https://cloud.swivl.com/register/");

        WebElement NamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_firstName"));
        WebElement LastNamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_lastName"));
        WebElement EmailPlaceholderElement = getDriver().findElement(By.id("swivl_registration_email"));
        WebElement PasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_first"));
        WebElement ConfirmPasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_second"));
        Select AgeDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_age")));
        Select CountryDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_country")));
        Select RoleDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_role_rolePreset")));

        NamePlaceholderElement.sendKeys("John");
        LastNamePlaceholderElement.sendKeys("Smith");
        EmailPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        PasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        ConfirmPasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        AgeDropDownPlaceholder.selectByIndex(2);
        CountryDropDownPlaceholder.selectByVisibleText("Japan");
        RoleDropDownPlaceholder.selectByVisibleText("IT");

        getDriver().findElement(By.xpath("//button[@id = 'formSubmit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='control__error']/a")).getText(), "Sign in");
    }

    @Test
    public void testEbayFindProduct() {

        openWebSite("https://www.ebay.com/");

        getDriver().findElement(By.xpath("//input[@class = 'gh-tb ui-autocomplete-input']")).sendKeys("Ipad");
        getDriver().findElement(By.id("gh-btn")).click();
        WebElement actualResult = getDriver().findElement(By.xpath("//h1[@class = 'srp-controls__count-heading']/span[@class = 'BOLD'][2]"));

        Assert.assertEquals(actualResult.getText(), "ipad");
    }

    @Ignore
    @Test
    public void testCheckTopMenuCategory() {
        openWebSite("http://automationpractice.com/index.php");
        WebElement GeneralPAgeTopMenuCategory = getDriver().findElement(By.cssSelector("[class*='sf-menu clearfix menu-content']>li:first-child"));
        String generalCategoryName = GeneralPAgeTopMenuCategory.getText().trim();
        GeneralPAgeTopMenuCategory.click();
        String subCategoryName = getDriver().findElement(By.className("cat-name")).getText().trim();
        Assert.assertEquals(generalCategoryName, subCategoryName);
    }

    @Ignore
    @Test
    public void testEbayFindProductByCategory() {

        openWebSite("https://www.ebay.com/");

        getDriver().findElement(By.id("gh-cat")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[.='eBay Motors']"))).click();

        getDriver().findElement(By.id("gh-btn")).click();
        getDriver().findElement(By.xpath("//li/button/span[contains(text(), 'Cars & Trucks')]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(), 'Cadillac')]")).click();
        getDriver().findElement(By.xpath("//p[.='DeVille']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[.='Cadillac DeVille Cars']")).getText(), "Cadillac DeVille Cars");
    }

    @Test
    public void testEbayCheckFooterMenu() {

        openWebSite("https://www.ebay.com/");

        List<WebElement> footerElements = getDriver().findElements(By.xpath("//h3[@class = 'gf-bttl']"));

        List<String> expectedResult = Arrays.asList("Buy", "Sell", "Tools & apps", "Stay connected", "About eBay", "Help & Contact", "Community", "eBay Sites");

        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(footerElements.get(i).getText(), expectedResult.get(i));
        }
    }
    @Ignore
    @Test
    public void testDemoQAButtons() {

        getDriver().get("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.xpath("//li[@id='item-4']")).click();

        Actions action = new Actions(getDriver());

        action.doubleClick(getDriver().findElement(By.xpath("//button[@id='doubleClickBtn']"))).build().perform();
        action.contextClick(getDriver().findElement(By.xpath("//button[@id='rightClickBtn']"))).build().perform();
        action.click(getDriver().findElement(By.xpath("//button[text() = 'Click Me']"))).build().perform();

        List<WebElement> actual = getDriver().findElements(By.xpath("//div/p"));

        List<String> expected = Arrays.asList("You have done a double click", "You have done a right click", "You have done a dynamic click");

        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i).getText(), expected.get(i));
        }
    }
    @Ignore
    @Test
    public void testDemoQAWebTables() {

        getDriver().get("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.xpath("//li[@id='item-3']")).click();

        getDriver().findElement(By.id("addNewRecordButton")).click();

        getDriver().findElement(By.id("firstName")).sendKeys("Anna");
        getDriver().findElement(By.id("lastName")).sendKeys("Ivanov");
        getDriver().findElement(By.id("userEmail")).sendKeys("test@test.com");
        getDriver().findElement(By.id("age")).sendKeys("35");
        getDriver().findElement(By.id("salary")).sendKeys("100000");
        getDriver().findElement(By.id("department")).sendKeys("QA");

        getDriver().findElement(By.id("submit")).click();

        getDriver().findElement(By.id("searchBox")).sendKeys("Anna");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[text() = 'Anna']")).getText(), "Anna");

    }
    @Ignore
    @Test
    public void testDemoQAValidImage() {

        getDriver().get("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.xpath("//li[@id='item-6']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("(//div/img)[1]")).isDisplayed());
    }
    @Ignore
    @Test
    public void testDemoQAInteractionsDroppable() throws InterruptedException {

        getDriver().get("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Interactions']")).click();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement element = getDriver().findElement(By.xpath("(//li[@id='item-3'])[4]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();

        Thread.sleep(1000);

        Actions action = new Actions(getDriver());

        action.dragAndDrop(getDriver().findElement(By.id("draggable")), getDriver().findElement(By.id("droppable"))).build().perform();

        Assert.assertEquals(getDriver().findElement(By.id("droppable")).getText(), "Dropped!");
    }
    @Ignore
    @Test
    public void testDemoQAAlertAccept() {

        getDriver().get("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']")).click();

        getDriver().findElement(By.xpath("(//li[@id='item-1'])[2]")).click();

        getDriver().findElement(By.id("confirmButton")).click();

        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        Assert.assertEquals(getDriver().findElement(By.id("confirmResult")).getText(), "You selected Ok");
    }

    @Test
    public void testAmountOfSvnCities() {

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-en']")).click();
        getDriver().findElement(By.xpath("//div[@class='toggle-regions']")).click();

        List<WebElement> listOfDisplayedSvnCities = getDriver().findElements(By.xpath("//div[@class='rlist-column']/a"));

        Assert.assertEquals(listOfDisplayedSvnCities.size(), 23);
    }

    @Test
    public void testCheckSelectedCityIsPresentAmongOtherCities() {

        final String bledCityName = "Bled";

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-en']")).click();
        getDriver().findElement(By.xpath("//div[@class='toggle-regions']")).click();

        WebElement citiesOFSvn = getDriver().findElements(By.xpath("//div[@class='rlist-column']/a"))
                .stream().filter(el -> el.getText().equals(bledCityName)).findFirst().orElse(null);

        Assert.assertNotNull(citiesOFSvn);
        Assert.assertEquals(citiesOFSvn.getText(), bledCityName);
    }

    @Test
    public void testSelectedOfAboutProjectPage() {

        getDriver().get(URL_FLAGMA);

        getDriver().findElement(By.xpath("//a[@id='uc-lang-en']")).click();

        WebElement aboutProjectBtn = getDriver().findElement(By.xpath("//a[text()='About project']"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", aboutProjectBtn);
        aboutProjectBtn.click();

        WebElement mainTitleOFAboutFlagmaBusinessPlatformPage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(mainTitleOFAboutFlagmaBusinessPlatformPage.getText(), "Flagma is an international business platform");
    }
}