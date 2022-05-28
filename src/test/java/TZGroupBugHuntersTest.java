
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class TZGroupBugHuntersTest extends BaseTest {
    @Ignore
    @Test

    public void testTatsianaZarMoto() {
        getDriver().get("https://rifle.com/Default.aspx");


        WebElement fairings = getDriver().findElement(By.xpath("//a[@href='Motorcycle-Fairings/Rifle-Fairings.aspx']"));
        fairings.click();

        WebElement rifleRavenFairing = getDriver().findElement(By.xpath("//a[@href='Rifle-Raven-Fairing.aspx']"));
        rifleRavenFairing.click();

        WebElement chooseOption = getDriver().findElement(
                By.xpath("//a[@href='Honda/Raven-Fairing-Honda-Valkyrie-VTX1800-C-R.aspx']"));
        chooseOption.click();


        Assert.assertEquals(getDriver().getCurrentUrl(),
                "https://rifle.com/Motorcycle-Fairings/Honda/Raven-Fairing-Honda-Valkyrie-VTX1800-C-R.aspx");

    }

    @Test
    public void testTatsianaZarContact() {
        getDriver().get("https://rifle.com/Default.aspx");
        WebElement contactInformation = getDriver().findElement(
                By.xpath("//a[@id='ctl00_ctl00_NestedMaster_PageHeader_StoreHeaderRifle_H_BootNavContactLink']")
        );
        contactInformation.click();
        WebElement customerService = getDriver().findElement(
                By.xpath("//a[@href='/Rifle-Customer-Service-Email.aspx?s=cs']")
        );
        customerService.click();

        Assert.assertEquals(getDriver().getCurrentUrl(),
                "https://rifle.com/Rifle-Customer-Service-Email.aspx?s=cs");

    }

}



