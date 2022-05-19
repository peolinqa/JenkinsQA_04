import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DariaKozhTest extends BaseTest {

    @Test
    public void testDariaTitleInSubmenuNMenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//body/div[@id ='wrap']/div[@id ='navigation']/ul[@id = 'menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//body/div[@id ='wrap']/div[@id ='navigation']/ul[@id = 'submenu']/li/a[@href='n.html']")).click();

        WebElement titleInSubmenuN = getDriver().findElement(
                By.xpath("//body/div[@id ='wrap']/div[@id ='main']/h2"));
        String actualResult = titleInSubmenuN.getText();

        Assert.assertEquals(actualResult, "Category N");

        getDriver().quit();
    }
}
