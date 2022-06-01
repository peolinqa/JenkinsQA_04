import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NewItemValidateValidCharacters extends BaseTest {
    @BeforeMethod
    public void login() {
        getDriver().get("http://localhost:9000/login?from=%2Fview%2Fall%2FnewJob");

        getDriver().findElement(By.id("j_username")).sendKeys("admin");
        getDriver().findElement(By.name("j_password")).sendKeys("4fcff49ed9ce42259e4c317b5e21e5bd");
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void validateValidCharacters() {
        getDriver().get("http://localhost:9000/view/all/newJob");

        boolean isValid = true;
        String[] invalidCharacters = {
                "!", "@", "#", "$", "%", "^", "&", "*", "<", ">", "?", "/"
        };

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys("asdfasd^fasdf");
        String itemNameValue = itemName.getAttribute("value");

        for (int i = 0; i < invalidCharacters.length; i++) {
            if (itemNameValue.contains(invalidCharacters[i])) {
                isValid = false;
                break;
            }
        }

        Assert.assertEquals(isValid, true);
    }
}
