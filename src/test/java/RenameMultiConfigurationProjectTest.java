import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RenameMultiConfigurationProjectTest extends BaseTest {

    private static final String MCP_INITIAL_NAME = String.format("%s%s", "MCPNEWLA", 100 + (int) (Math.random() * 900));
    private static final String MCP_NEW_VALID_NAME = String.format("%s%s", "MCPNEWLA", 1000 + (int) (Math.random() * 9000));

    private static final By
            ERROR_PAGE_H1 = By.xpath("//div[@id='main-panel']/h1"),
            ERROR_PAGE_P = By.xpath("//div[@id='main-panel']/p"),
            WARNING_MESSAGE = By.xpath("//div[@class='warning']"),
            ERROR_MESSAGE = By.xpath("//div[@class='error']"),
            DASHBOARD_ITEM = By.xpath("//tr[@id='job_" + MCP_INITIAL_NAME + "']");

    private WebElement getDashBoardItem() {
        return getDriver().findElement(
                By.xpath("//tr[@id='job_" + MCP_INITIAL_NAME + "']//a[contains(@class,'jenkins-table__link')]")
        );
    }

    private void clickMenuItemByName(String menuItemName) {
        getDriver().findElement(By.xpath("//span[text()='" + menuItemName + "']/../../a")).click();
    }

    private void clickButtonByName(String buttonName) {
        getDriver().findElement(By.xpath("//button[text()='" + buttonName + "']")).click();
    }

    private void openSubMenu(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);

        WebElement menuSelector = getDriver().findElement(By.id("menuSelector"));
        actions.moveToElement(menuSelector);

        actions.click().build().perform();
    }

    private void setValueInRenameMenuField(String value) {
        getDriver().findElement(By.xpath("//div[@class='setting-main']/input[@name='newName']"))
                .sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), value);
    }

    private void createMCP(String name) {
        clickMenuItemByName("New Item");
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();
        clickButtonByName("OK");
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Description");
        clickButtonByName("Save");
        getDriver().findElement(By.xpath("//h1[text()='Project " + name + "']")).isDisplayed();
        clickMenuItemByName("Back to Dashboard");
    }

    private boolean isPresent(By xpath) {
        try {
            getDriver().findElement(xpath);
        } catch (NoSuchElementException e) {

            return false;
        }

        return true;
    }

    @Test(priority = 1, description = "TC_043.001| Multi-configuration project > rename with valid data")
    public void testRenameMCPValidName() {
        if (!isPresent(DASHBOARD_ITEM)) {
            createMCP(MCP_INITIAL_NAME);
        }

        getDashBoardItem().click();
        clickMenuItemByName("Rename");
        setValueInRenameMenuField(MCP_NEW_VALID_NAME);
        clickButtonByName("Rename");

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/h1[contains(@class, 'matrix-project-headline')]")
        ).getText(), "Project " + MCP_NEW_VALID_NAME);

        clickMenuItemByName("Back to Dashboard");

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//tr[@id='job_" + MCP_NEW_VALID_NAME + "']")
        ).isDisplayed());
    }

    @Test(priority = 2, description = "TC_043.002| Multi-configuration project > rename with the same name")
    public void testRenameMCPSameName() {
        String warningMes = "The new name is the same as the current name.";

        if (!isPresent(DASHBOARD_ITEM)) {
            createMCP(MCP_INITIAL_NAME);
        }

        openSubMenu(getDashBoardItem());
        clickMenuItemByName("Rename");

        Assert.assertEquals(getDriver().findElement(WARNING_MESSAGE).getText(), warningMes);

        clickButtonByName("Rename");

        String actualResult = String.format("%s %s", getDriver().findElement(ERROR_PAGE_H1).getText()
                , getDriver().findElement(ERROR_PAGE_P).getText());
        Assert.assertEquals(actualResult, String.format("Error %s", warningMes));
    }

    @Test(priority = 3, description = "TC_043.003| Multi-configuration project > rename with using unsafe character")
    public void testRenameMCPNameWithUnsafeCharacter() {
        String[] unsafeCharacters = {"!", "@", "#", "$", "%", "^", "*", "/", "|", "\\"};

        if (!isPresent(DASHBOARD_ITEM)) {
            createMCP(MCP_INITIAL_NAME);
        }

        getDashBoardItem().click();

        for (String unsafeCharacter :
                unsafeCharacters) {

            openSubMenu(getDriver().findElement(By.linkText(MCP_INITIAL_NAME)));
            clickMenuItemByName("Rename");
            setValueInRenameMenuField(String.format("%s%s", MCP_INITIAL_NAME, unsafeCharacter));
            getDriver().findElement(By.id("main-panel")).click();

            Assert.assertEquals(getDriver().findElement(ERROR_MESSAGE).getText()
                    , String.format("‘%s’ is an unsafe character", unsafeCharacter));

            clickButtonByName("Rename");

            String actualResult = String.format("%s %s", getDriver().findElement(ERROR_PAGE_H1).getText()
                    , getDriver().findElement(ERROR_PAGE_P).getText());
            Assert.assertEquals(actualResult, String.format("Error ‘%s’ is an unsafe character", unsafeCharacter));
        }
    }
}
