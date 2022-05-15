import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class LetsBeTestersTest extends BaseTest {

    private void openNokiaPage() {
        getDriver().get("https://www.nokia.com/");
        getDriver().findElement(By.id("modalAcceptAllBtn")).click();
    }

    private void openDavinagazPage() {
        getDriver().get("https://davinagaz.by/");
        getDriver().findElement(By.xpath("//b[text()= ' Полесская 14']")).click();
    }

    private void openDemoQAPage() {
        getDriver().get("https://demoqa.com/");
        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();
    }

    private void openRegistrationPage() {
        getDriver().get("https://cloud.swivl.com/register/");
    }

    @Test
    public void testCountOfSectionButtons() {

        openNokiaPage();

        List<WebElement> carousel = getDriver().findElements(By.xpath("//div[contains(@id, 'tns1-item')]//h2"));

        Assert.assertEquals(carousel.size(), 9);
    }

    @Test
    public void testCheckTablet() {

        openNokiaPage();

        Actions action = new Actions(getDriver());

        List<WebElement> menu = getDriver().findElements(By.xpath("//li[contains(@class, 'dropdown-menu')]/a"));
        WebElement forConsumers = menu.stream().filter(el2 -> el2.getText().equals("For consumers")).findFirst().orElse(null);

        action.moveToElement(forConsumers).build().perform();

        getDriver().findElement(By.xpath("//li[@class='dropdown-submenu-item']/a[text()='Phones']")).click();
        getDriver().findElement(By.xpath("//a[@data-gtm-cta='tablets']")).click();
        String actualResult = getDriver().findElement(By.xpath("//li[contains(@class, 'h5')]")).getText();

        Assert.assertEquals(actualResult, "Nokia T20");
    }

    @Test
    public void testKICheckAddress() {

        openDavinagazPage();

        String address = "Полесская 14";

        WebElement actualResult = getDriver().findElement(By.cssSelector(".name-office"));

        Assert.assertTrue(actualResult.getText().contains(address));
    }

    @Test
    public void testKICheckHeader() throws InterruptedException {

        openDavinagazPage();

        String expectedResult = "Аккумулятор";

        WebElement menu = getDriver().findElement(By.xpath("//a[text()='ТО и фильтра']"));

        Actions action = new Actions(getDriver());
        action.moveToElement(menu).perform();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[text()='Аккумулятор']")).click();
        Thread.sleep(1000);

        WebElement actualResult = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(actualResult.getText(), expectedResult.toUpperCase());
    }

    @Test
    public void testElementsTextBox() {

        openDemoQAPage();

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

        openDemoQAPage();

        getDriver().findElement(By.id("item-2")).click();

        getDriver().findElement(By.xpath("//*[@for='impressiveRadio']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='text-success']")).getText(), "Impressive");
    }

    @Test
    public void testRegistration(){

        openRegistrationPage();

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
}
