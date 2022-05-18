import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VPGroupBugHuntersTest extends BaseTest {

    private WebDriverWait wait;

    @BeforeMethod
    public void setUpWait() {
        wait = new WebDriverWait(getDriver(), 10);
    }

    @DataProvider(name = "language")
    public static Object[][] languageData() {

        return new Object[][]{
                {"английский", "Hello World!", "en"},
                {"немецкий", "Hallo Welt!", "de"}
        };
    }

    @Ignore //Видимо Гугл защищается и здесь, работает с перебоями
    @Parameters({"language"})
    @Test(dataProvider = "language")
    public void testViktorPodgornov(String language, String result, String lang) {

        getDriver().get("https://translate.google.ru");

        getDriver().findElement(By.xpath("//textarea[@aria-label='Исходный текст']"))
                .sendKeys("Привет, Мир!");

        getDriver().findElement(By.xpath("//div[5]/button/div[3]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div[2]/input")));

        WebElement searchBox = getDriver().findElement(By.xpath("//div[2]/div/div[2]/input"));
        searchBox.sendKeys(language);
        searchBox.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(String.format("//span[@lang='%s']", lang)), result));

        Assert.assertEquals(getDriver().findElement(By.xpath(String.format("//span[@lang='%s']", lang))).getText(),
                result);
    }

    @Test
    public void testViktorPodgornovNetCost() {

        getDriver().get("https://lunch.netcostmarket.com/");

        getDriver().findElement(By.xpath("//input[@role='combobox']"))
                .sendKeys("meat" + Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.xpath("//input[@role='combobox']"))
                .getAttribute("value"), "meat");
    }

}