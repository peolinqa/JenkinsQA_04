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
    public void searchMercedesVinAndDecodeIt() {
        // This method search inventory of the Pick-n-Pull vehicle yard for Mercedes CLK vehicles
        // Get first vehicle found, open it page to get unique VIN (Vehicle Identification Number)
        // Decode VIN number using another website with database of VIN numbers
        // Verify that decoded data contains that VIN belongs to CLK model

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "/Users/oldmang/Workspace/Utils/chromedriver";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        //TO_DO
        // need to automate submiting search form. For now use hardcoded url with search for Mercedes
        driver.get("https://www.picknpull.com/check-inventory/vehicle-search?make=182&model=3611&distance=25&zip=95123&year=");

        // wait to have first test result, then open it
        WebElement firstSearchResult = new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*/div[@class='fixed-table-body']/table//img[contains(@alt, 'Mercedes')][1]")));
        firstSearchResult.click();

        // Expecting URL has VIN number at the end it is always 17 char long. Get the VIN and assign to variable
        String vinNumber = driver.getCurrentUrl().substring(driver.getCurrentUrl().length() - 17);

        //TO_DO make selection of brand from main page
        // Lets decode VIN and get some info about vehicle it belongs to, assuming that vin belogs to Mercedes brand
        driver.get("http://www.elcats.ru/mercedes/");

        driver.findElement(By.id("cphMasterPage_txbVIN")).sendKeys(vinNumber);
        driver.findElement(By.id("cphMasterPage_btnFindByVIN")).click();

        List<WebElement> vehicleInfoRow = driver.findElements(By.xpath("//table[@id='cphMasterPage_tblComplectation']//tr[2]/td"));

        // Lets make not assertion but verification that after decoding VIN we got Mercedes CLK model, not other
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
