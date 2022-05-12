import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.JuliaSabConstantsUtils;

public class JuliaSabScrollTest extends BaseTest {

    @Test
    public void scrollToElement(){
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        getDriver().get(JuliaSabConstantsUtils.JACKSON_WIKI_URL);
        WebElement textElement = getDriver().findElement(By.xpath(JuliaSabConstantsUtils.THEMES_ANS_GENRES_XPATH));
        WebElement linkElement = getDriver().findElement(By.xpath(JuliaSabConstantsUtils.TITO_XPATH));
        jse.executeScript(JuliaSabConstantsUtils.JS_SCROLL_SCRIPT, textElement);
        jse.executeScript(JuliaSabConstantsUtils.JS_SCROLL_SCRIPT, linkElement);
    }
}
