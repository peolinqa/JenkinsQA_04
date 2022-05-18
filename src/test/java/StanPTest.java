import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class StanPTest extends BaseTest {

    @Test
    public void testHeaderSearch() {
        getDriver().get("https://www.homedepot.com");

        WebElement searchField = getDriver().findElement(By.id("headerSearch"));
        searchField.sendKeys("tube clamp");
        WebElement searchButton = getDriver().findElement(By.xpath("//button[@id='headerSearchButton']"));
        searchButton.click();

        WebElement header = getDriver().findElement(By.xpath("//h1[@class='results-header__keyword']"));
        Assert.assertEquals(header.getText(), "Tube Clamp");
    }

    @Test
    public void testRedirectionToSignIn() {
        getDriver().get("https://www.homedepot.com");

        WebElement accountIcon = getDriver().findElement(By.xpath("//a[@id='headerMyAccount']"));
        accountIcon.click();

        WebElement signInIcon = getDriver().findElement(By.xpath("//span[text()=' Sign in ']"));
        signInIcon.click();

        Assert.assertEquals(getDriver().getTitle(), "The Home Depot: sign in, create or secure your account");
    }

}
