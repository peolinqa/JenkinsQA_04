package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

@Ignore
public class ViktorPodgornov99BottlesOfBeerTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String BROWSE_LANGUAGES_MENU_XPATH = "//ul[@id='menu']//a[text()='Browse Languages']";

    public WebElement findElement(String xpath) {

        return getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getBrowseLanguagesSubmenuXpath(String submenuElement) {

        return findElement(String.format("//ul[@id='submenu']//a[text()='%s']", submenuElement));
    }

    public List<WebElement> getListOfWebElementsDependentOnXpath(String yourXPath) {

        return getDriver().findElements(By.xpath(yourXPath));
    }

    @Test
    public void testUserCanSeeDescriptionOfPageWhereAllLanguageBeginJ() {

        getDriver().get(BASE_URL);
        findElement(BROWSE_LANGUAGES_MENU_XPATH).click();
        getBrowseLanguagesSubmenuXpath("J").click();

        Assert.assertEquals(findElement("//p[contains(text(),'All languages starting with the letter')]")
                        .getText(),
                "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void testConfirmThatInBrowserLanguagesMenuSubmenuMLastLanguageIsMySql() {

        getDriver().get(BASE_URL);
        findElement(BROWSE_LANGUAGES_MENU_XPATH).click();
        getBrowseLanguagesSubmenuXpath("M").click();

        Assert.assertEquals(findElement("//tr[last()]//a[text()='MySQL']").getText(), "MySQL");
    }

    @Test
    public void testConfirmThatTableExistsWithCorrectNamesInBrowserLanguageMenu() {

        getDriver().get(BASE_URL);
        findElement(BROWSE_LANGUAGES_MENU_XPATH).click();

        List<String> tableHeadersNamesActual = new ArrayList<>();
        getListOfWebElementsDependentOnXpath("//table[@id='category']//th").stream().map(WebElement::getText)
                .forEach(tableHeadersNamesActual::add);

        List<String> tableHeadersNamesExpected = new ArrayList<>(Arrays.asList(
                "Language", "Author", "Date", "Comments", "Rate"));

        Assert.assertEquals(tableHeadersNamesActual.toString(), tableHeadersNamesExpected.toString());
    }

    @Test
    public void testConfirmThatCreatorOfMathematicaIsBrentonBostickDateUpdatedIsCorrectHasOneComment() {

        getDriver().get(BASE_URL);
        findElement(BROWSE_LANGUAGES_MENU_XPATH).click();
        getBrowseLanguagesSubmenuXpath("M").click();

        Assert.assertEquals(findElement("//a[text()='Mathematica']//ancestor::tr").getText(),
                "Mathematica Brenton Bostick 03/16/06 1");
    }

    @Test
    public void testConfirmThatSiteHasTenLanguagesWhoseNamesBeginWithNumbers() {

        getDriver().get(BASE_URL);
        findElement(BROWSE_LANGUAGES_MENU_XPATH).click();
        getBrowseLanguagesSubmenuXpath("0-9").click();
        List<WebElement> languagesWhoseNamesBeginWithNumbers = getListOfWebElementsDependentOnXpath(
                "//table[@id='category']//a[contains(@href, 'language')]");

        int countLanguagesNamesBeginWithNumbersActual = 0;
        for (WebElement languagesWhoseNamesBeginWithNumber : languagesWhoseNamesBeginWithNumbers) {
            if (Character.isDigit(languagesWhoseNamesBeginWithNumber.getText().charAt(0))) {
                countLanguagesNamesBeginWithNumbersActual++;
            }
        }
        Assert.assertEquals(countLanguagesNamesBeginWithNumbersActual, 10);
    }

    @Test
    public void testConfirmThatUserCanSeeErrorMessageOnPageGuestbookWhenSecurityCodeHasThreeDigits() {

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        findElement("//input[@name='name']").sendKeys("Viktor");
        findElement("//input[@name='location']").sendKeys("New York");
        findElement("//input[@name='email']").sendKeys("emailfortest@gmail.com");
        findElement("//input[@name='homepage']").sendKeys("http:/sitefortest.com");
        findElement("//input[@name='captcha']")
                .sendKeys(Integer.toString(100 + (int) (Math.random() * 900)));
        findElement("//textarea[@name='comment']").sendKeys("test test test");
        findElement("//input[@name='submit']").click();

        Assert.assertEquals(findElement("//p[contains(@style, 'border: 1px solid red')]").getText(),
                "Error: Error: Invalid security code.");
    }
}
