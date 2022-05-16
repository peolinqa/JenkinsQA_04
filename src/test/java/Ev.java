import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Ev extends BaseTest {

    @Test
    public void evPiskunova() {
        getDriver().get("https://www.tula-samovar.com/");
        WebElement documentationTextButton = getDriver().findElement(
                By.xpath("//ul[@id='sidebarSubmenu_0']/li/a[contains(text(),\"Электрические самовары\")]"));
        documentationTextButton.click();
        WebElement pageTitleWE = getDriver().findElement(By.xpath("//h1['Электрические самовары']"));
        String pageTitle = pageTitleWE.getText();
        Assert.assertEquals(pageTitle, "Электрические самовары");
    }
}
