import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFolderTest extends BaseTest {

    private static final By NAME = By.id("name");

    public void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    public void clickFolderItem() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
    }

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    public void clickJenkinsHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private void createFolder(String nameFolder) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(nameFolder);
        clickFolderItem();
        clickOKButton();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        clickJenkinsHome();
    }

    public void deleteFolder(String nameFolder) {
        clickJenkinsHome();
        getDriver().findElement(By.xpath("//a[@href='job/" + nameFolder + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Folder')]")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    /**
     * TC_009.001 Create Folder
     */
    @Test
    public void testCreateFolderPositive() {

        clickNewItem();

        getDriver().findElement(NAME).sendKeys("TestFolder_1");
        clickFolderItem();
        clickOKButton();

        getDriver().findElement(By.name("_.description")).sendKeys("Description for the new folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']")).click();

        boolean isFound;
        try {
            getDriver().findElement(By.id("job_TestFolder_1"));
            isFound = true;
        } catch (NoSuchElementException e) {
            isFound = false;
        }

        Assert.assertTrue(isFound);
    }

    /**
     * TC_009.002
     */
    @Test
    public void testCreateFolderThatStartsWithUnsafeCharacter() {

        String symbols = "!@#$%^&*:;<>?/\\.";

        clickNewItem();

        WebElement name = getDriver().findElement(NAME);
        clickFolderItem();

        for (int i = 0; i < symbols.length(); i++) {
            name.clear();
            String s = String.valueOf(symbols.charAt(i));
            name.sendKeys(s);

            String expectedResult = "";
            if (s.equals(".")) {
                expectedResult = "» “" + s + "” is not an allowed name";
            } else {
                expectedResult = "» ‘" + s + "’ is an unsafe character";
            }

            String actualResult = getDriver().findElement(By.id("itemname-invalid")).getText();

            Assert.assertEquals(actualResult, expectedResult);
        }
    }

    /**
     * TC_009.007
     */
    @Test
    public void testCreateFolderWithUnsafeCharacter() {

        String expectedErrorMessage = "» ‘@’ is an unsafe character";
        String expectedError = "Error";

        clickNewItem();

        getDriver().findElement(NAME).sendKeys("TestFolder@Jenkins");
        String actualErrorMessage1 = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(actualErrorMessage1, expectedErrorMessage);

        clickFolderItem();
        clickOKButton();

        String actualError = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        String actualErrorMessage2 = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualError, expectedError);
        Assert.assertEquals(actualErrorMessage2, expectedErrorMessage.substring(2));
    }

    /**
     * TC_009.008
     */
    @Test
    public void testCreateFolderWithTheSameName() {

        String nameFolder = "TestRomanFolder";

        createFolder(nameFolder);

        String expectedErrorMessage = "» A job already exists with the name ‘" + nameFolder + "’";
        String expectedError = "Error";

        clickNewItem();

        getDriver().findElement(NAME).sendKeys(nameFolder);
        WebElement actualErrorMessage1 = getWait20()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("itemname-invalid")));

        Assert.assertEquals(actualErrorMessage1.getText(), expectedErrorMessage);

        clickFolderItem();
        clickOKButton();

        String actualError = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        String actualErrorMessage2 = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualError, expectedError);
        Assert.assertEquals(actualErrorMessage2, expectedErrorMessage.substring(2));

        deleteFolder(nameFolder);
    }
}