import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class _MyViewTest extends BaseTest {
    private static final String NAME_OF_VIEW = "My new view";
    private static final String EDIT_NAME_OF_VIEW = String.valueOf((int) (Math.random() * 999));

    @Test
    public void testCreateNewView() {
        final String viewName = "Test view 1234567890123456789012345678901234567890";
        final String viewDescription = "My first Test view ;-)";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@title = 'New View']")).click();

        driver.findElement(By.id("name")).sendKeys(viewName);
        driver.findElement(By.xpath("//label[text() = 'List View']")).click();
        driver.findElement(By.id("ok")).click();

        driver.findElement(By.name("description")).sendKeys(viewDescription);
        driver.findElement(By.id("yui-gen13-button")).click();

        Assert.assertEquals(viewName, driver.findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='item']/a[starts-with(@href, '/view')]")).getText());
        Assert.assertEquals(viewDescription, driver.findElement(By.xpath("//div[@id='description']/div[1]")).getText());
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertEquals(
                NAME_OF_VIEW,
                getDriver()
                        .findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]"))
                        .getText()
        );
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyView"})
    public void testCreateNewViewWithAnExistingName() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();

        String errorMessageActual = getDriver().findElement(By.className("error")).getText();
        String errorMessageExpected = "A view already exists with the name " + '"' + NAME_OF_VIEW + '"';

        Assert.assertEquals(errorMessageActual, errorMessageExpected);
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithAnExistingName"})
    public void testEditViewChangeName() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='/view/My%20new%20view/']")).click();

        getDriver().findElement(By.linkText("Edit View")).click();

        WebElement name = getDriver().findElement(By.name("name"));
        name.clear();
        name.sendKeys(EDIT_NAME_OF_VIEW);

        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(
                EDIT_NAME_OF_VIEW,
                getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '"
                                + EDIT_NAME_OF_VIEW + "')]"))
                        .getText()
        );
    }

    @Test(dependsOnMethods = {"testEditViewChangeName"})
    public void testDeleteCreatedView() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + "/view/" + EDIT_NAME_OF_VIEW + "')]")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> namesView = getDriver().findElements(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']"));
        List<String> names = new ArrayList<>();
        for (WebElement name : namesView) {
            names.add(name.getText());
        }

        Assert.assertTrue(names.get(0).isEmpty());
        Assert.assertFalse(names.contains(EDIT_NAME_OF_VIEW));
    }
}
