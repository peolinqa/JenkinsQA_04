import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EvTest extends BaseTest {

    @Test
    public void evPiskunovaFirstTest() {
        getDriver().get("https://www.tula-samovar.com/");

        WebElement documentationTextButton = getDriver().findElement(
                By.xpath("//ul[@id='sidebarSubmenu_0']/li/a[contains(text(),\"Электрические самовары\")]"));
        documentationTextButton.click();

        WebElement pageTitleWE = getDriver().findElement(By.xpath("//h1['Электрические самовары']"));
        Assert.assertEquals(pageTitleWE.getText(), "Электрические самовары");
    }
}
