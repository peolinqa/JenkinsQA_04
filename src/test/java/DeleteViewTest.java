import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class DeleteViewTest extends BaseTest {
    private static final String NAME_OF_VIEW = "My new view";

    @BeforeMethod
    public void createNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();
    }

    @Test
    public void testDeleteCreatedView() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='/view/My%20new%20view/']")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> namesView = getDriver().findElements(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']"));
        List<String> names = new ArrayList<>();
        for (WebElement name : namesView) {
            names.add(name.getText());
        }

        Assert.assertTrue(names.get(0).isEmpty());
        Assert.assertFalse(names.contains(NAME_OF_VIEW));
    }
}
