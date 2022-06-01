package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import javax.lang.model.element.Element;
import java.util.concurrent.TimeUnit;

@Ignore
public class GroupDreamTeamJavaTest extends BaseTest {

    @Test
    public void testFelix_IX() {

        getDriver().get("https://habr.com/ru/all/");

        getDriver().findElement(By.xpath("//a[@class='tm-header-user-menu__item tm-header-user-menu__search']"))
                .click();

        getDriver().findElement(By.className("tm-input-text-decorated__input"))
                .sendKeys("XPath\n");

        ((JavascriptExecutor) getDriver()).executeScript("scroll(0, 2000);");
        getDriver().findElement(By.xpath("//span[contains(text(),'Вот почему мы всегда пишем селекторы на ')]"))
                .click();

        WebElement actualResult = getDriver().findElement(By.xpath("//h1[@class='tm-article-snippet__title tm-article-snippet__title_h1']"));
        Assert.assertEquals(actualResult.getText(), "Вот почему мы всегда пишем селекторы на XPath");
    }

    @Test
    public void findAuto_AliaksandrDTest() {
        getDriver().get("https://av.by");
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        WebElement autoBrand = getDriver().findElement(By.xpath("//button[@name='p-6-0-2-brand']"));
        autoBrand.click();

        WebElement searchAutoBrand = getDriver().findElement(By.xpath("//input[@placeholder='Поиск']"));
        searchAutoBrand.sendKeys("Audi" + Keys.ENTER);

        WebElement model = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='p-6-0-3-model']")));
        model.click();

        WebElement buttonShow = getDriver().findElement(By.xpath("//a[@class='button button--secondary button--block']"));
        String temp = buttonShow.getText();

        WebElement searchModel = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-item-label='80']")));
        searchModel.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(buttonShow, temp)));

        buttonShow.click();
        String[] title = getDriver().getTitle().split(" ");
        Assert.assertEquals(title[1] + " " + title[2], "Audi 80");
    }

    private final void openTransportMenu(){
        try {getDriver().get("https://av.by");
            Actions actionProvider = new Actions(getDriver());
            actionProvider.moveToElement(getDriver().findElement(By.xpath("//a[@href='https://cars.av.by']"))).build().perform();
        } catch (Exception e){
            System.out.println("Menu is not opened");
        }
    }

    @Test
    public void checkNewAutoFromTransportMenuTest() {
        openTransportMenu();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://salon.av.by']/span")).getText(), "Новые автомобили");
    }

    @Test
    public void checkCarsAutoFromTransportMenuTest() {
        openTransportMenu();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://cars.av.by' and @class='nav__dropdown-link']/span")).getText(), "Автомобили с пробегом");
    }

    @Test
    public void checkTracksAutoFromTransportMenuTest() {
        openTransportMenu();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://truck.av.by']/span")).getText(), "Грузовой транспорт");
    }

    @Test
    public void checkMotoAutoFromTransportMenuTest() {
        openTransportMenu();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://moto.av.by']/span")).getText(), "Мототехника");
    }
    @Test
    public void checkAgroAutoFromTransportMenuTest() {
        openTransportMenu();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://agro.av.by']/span")).getText(), "Сельхозтехника");
    }

    @Test
    public void swivlMariaShyTest() {
        getDriver().get("https://cloud.swivl.com/login");
        getDriver().manage().deleteAllCookies();

        getDriver().findElement(By.id("username")).sendKeys("123@123");
        getDriver().findElement(By.id("password")).sendKeys("123123");
        getDriver().findElement(By.id("_submit")).click();

        WebElement invalidToken = getDriver().findElement(
                By.xpath("/html/body/div/div[2]/div[2]/div[2]/div/form/p"));
        Assert.assertEquals(invalidToken.getText(), "Invalid CSRF token.");
    }

    @Test
    public void testDinarGizSearch() throws InterruptedException {
        getDriver().get("https://stepik.org/catalog");
        getDriver().findElement(By.xpath("//div/input[@class='search-form__input ']")).sendKeys("Java");

        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//div/button[@class = 'button_with-loader search-form__submit']")).click();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://stepik.org/catalog");

    }

}