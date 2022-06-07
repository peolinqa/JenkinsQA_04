import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VZverevNamePipelineTest extends BaseTest {

    @DataProvider(name = "errorMessageData")
    public Object[][] errorData() {
        return new Object[][]{
                {"!", "» ‘!’"}, {"@", "» ‘@’"}, {"#", "» ‘#’"}, {"$", "» ‘$’"},
                {"%", "» ‘%’"}, {"^", "» ‘^’"}, {"&", "» ‘&’"}, {"*", "» ‘*’"},
                {":", "» ‘:’"}, {";", "» ‘;’"}, {"<", "» ‘<’"}, {">", "» ‘>’"},
                {"?", "» ‘?’"}, {"/", "» ‘/’"}, {"\\\\", "» ‘\\’"}
        };
    }

    @Test(dataProvider = "errorMessageData")
    public void testInvalidName(String name, String expectedMessage) {

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        WebElement errorMessage = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals(errorMessage.getText(), expectedMessage + " is an unsafe character");
    }
}
