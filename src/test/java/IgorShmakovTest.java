import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class IgorShmakovTest extends BaseTest {

    @Test
    public void searchMercedesVinAndDecodeItTest() {
        getDriver().get("https://www.picknpull.com/check-inventory/vehicle-search?make=182&model=3611&distance=25&zip=95123&year=");

        WebElement firstSearchResult = new WebDriverWait(getDriver(), 10).until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*/div[@class='fixed-table-body']/table//img[contains(@alt, 'Mercedes')][1]")));
        firstSearchResult.click();

        String vinNumber = getDriver().getCurrentUrl().substring(getDriver().getCurrentUrl().length() - 17);

        getDriver().get("http://www.elcats.ru/mercedes/");

        getDriver().findElement(By.id("cphMasterPage_txbVIN")).sendKeys(vinNumber);
        getDriver().findElement(By.id("cphMasterPage_btnFindByVIN")).click();

        List<WebElement> vehicleInfoRow = getDriver().findElements(By.xpath("//table[@id='cphMasterPage_tblComplectation']//tr[2]/td"));

        boolean isMercedesCLKfound = false;
        for (WebElement webElement : vehicleInfoRow) {
            if (webElement.getText().contains("CLK")) {
                isMercedesCLKfound = true;
                break;
            }
        }

        Assert.assertTrue(isMercedesCLKfound);
    }

}
