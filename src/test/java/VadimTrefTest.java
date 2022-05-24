import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class VadimTrefTest extends BaseTest {

    private static final String URL = "https://demo.applitools.com/";

    void login() {
        getDriver().findElement(By.id("username")).sendKeys("123");
        getDriver().findElement(By.id("password")).sendKeys("123");
        getDriver().findElement(By.id("log-in")).click();
    }

    @Test
    public void testCustomersNameAndRole() {

        getDriver().get(URL);

        login();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@class='logged-user-name']")).getText(),
                "Jack Gomez");
        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@class='logged-user-role']")).getText(),
                "Customer".toUpperCase());
    }

    @Test
    public void testTablesTotalAmount() {

        getDriver().get(URL);

        login();

        List<WebElement> amountColumn = getDriver().findElements(
                By.xpath("//tbody/tr/td[last()]/span"));
        String[] arrayAmountsColumn = new String[amountColumn.size()];

        double actualResultTotalAmount = 0;
        for (int i = 0; i < arrayAmountsColumn.length; i++) {
            arrayAmountsColumn[i] = getDriver().findElement(By.xpath(
                    "//tbody/tr[" + (i + 1) + "]/td[last()]/span")).getText()
                    .replace(",", "").replace(" USD", "").replace(" ", "");
            actualResultTotalAmount += Double.parseDouble(arrayAmountsColumn[i]);
        }

        Assert.assertEquals(actualResultTotalAmount, 1996.22);
    }

    @Test
    public void testIsEBayInTable() {

        getDriver().get(URL);

        login();

        List<WebElement> companiesNames = getDriver().findElements(
                By.xpath("//tbody/tr/td[3]/span"));
        boolean actualResult = false;
        for (WebElement webElement : companiesNames) {
            if (webElement.getText().trim().equals("Ebay Marketplace")) {
                actualResult = true;
                break;
            }
        }

        Assert.assertTrue(actualResult);
    }
}
