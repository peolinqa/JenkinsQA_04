package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShurikDrink99BottlesOfBeerTest extends BaseTest {
    public final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void test_TC_12_01() {
        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']//a[@href='j.html']")).click();
        WebElement languages = getDriver().findElement(
                By.xpath("//div[@id='main']//p[1]"));

        Assert.assertEquals(languages.getText(),
                "All languages starting with the letter " +
                        "J are shown, sorted by Language.");
    }

    @Test
    public void test_TC_12_02() {
        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']//a[@href='m.html']")).click();
        WebElement lastLanguage = getDriver().findElement(
                By.xpath("//table[@id='category']//tr[last()]//a"));

        Assert.assertEquals(lastLanguage.getText(), "MySQL");
    }

    @Test
    public void test_TC_12_03() {
        List<String> expectedResult = new ArrayList<>(
                Arrays.asList("Language", "Author", "Date", "Comments", "Rate"));

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();
        List<WebElement> listOfTitles = getDriver().findElements(
                By.xpath("//table[@id='category']/tbody/tr[1]/th"));

        List<String> actualResult = new ArrayList<>();
        for (WebElement title : listOfTitles) {
            actualResult.add(title.getText());
        }
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_03_ByCollection() {
        List<String> expectedResult = new ArrayList<>(
                Arrays.asList("Language", "Author", "Date", "Comments", "Rate"));

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();

        List<String> actualResult = getDriver().findElements(
                        By.xpath("//table[@id='category']/tbody/tr[1]/th")).
                stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_04() {
        List<String> expectedResult = new ArrayList<>(
                Arrays.asList("Brenton Bostick", "03/16/06", "1"));

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']//a[@href='m.html']")).click();
        getDriver().findElement(
                By.xpath("//table[@id='category']//tr//a[@href=" +
                        "'language-mathematica-1090.html']")).click();

        String author = getDriver().findElement(
                By.xpath("//div[@id='main']/table//tr[2]/td[2]")).getText();
        String date = getDriver().findElement(
                By.xpath("//div[@id='main']/table//tr[1]/td[2]")).getText();
        String comments = getDriver().findElement(
                By.xpath("//div[@id='main']/table//tr[4]/td[2]")).getText();

        List<String> actualResult = new ArrayList<>(
                Arrays.asList(author, date, comments));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_05() {

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();
        List<WebElement> languagesWithNumeric = getDriver().findElements(
                By.xpath("//table[@id='category']/tbody/tr"));

        Assert.assertEquals(languagesWithNumeric.size() - 1, 10);
    }
}