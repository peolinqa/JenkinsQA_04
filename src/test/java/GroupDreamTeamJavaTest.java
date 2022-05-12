import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GroupDreamTeamJavaTest extends BaseTest {

    @Test
    public void testFelix_IX(){

        getDriver().get("https://habr.com/ru/all/");

        String text = "XPath";
        getDriver().findElement(By
                        .xpath("//*[@class='tm-header-user-menu__item tm-header-user-menu__search']"))
                .click();

        getDriver().findElement(By.xpath("//*[@class='tm-input-text-decorated__input']"))
                .sendKeys(text + "\n");

        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("scroll(0, 2000);");

        getDriver().findElement(By
                        .xpath("//*[contains(text(),'Вот почему мы всегда пишем селекторы на ')]"))
                .click();

        WebElement x =  getDriver().findElement(By
                .xpath("//*[@class='tm-article-snippet__title tm-article-snippet__title_h1']"));

        Assert.assertEquals(x.getText(), "Вот почему мы всегда пишем селекторы на XPath");
    }
}
