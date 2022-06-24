import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.List;

public class _ManageNodesAndCloudsTest extends BaseTest {

    private static final String COMPUTER_NAME = "first test node 456";
    private static final By XPATH_MENU_SELECTOR_BTN = By.xpath("//div[@id='menuSelector']");

    private WebElement findBuildQueueBtn() {
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/toggleCollapse?paneId=buildQueue']")));
    }

    private String getTitleValueBuildQueueBtn() {
        return findBuildQueueBtn().getAttribute("title");
    }

    private WebElement findBuildExecutorStatusBtn() {
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/toggleCollapse?paneId=executors']")));
    }

    private String getTitleValueBuildExecutorStatusBtn() {
        return findBuildExecutorStatusBtn().getAttribute("title");
    }

    @Test
    public void testCheckBuildQueue() {
        if (getDriver().findElements(By.xpath("//div[@id='buildQueue']//table")).size() > 0) {
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "collapse");

            findBuildQueueBtn().click();
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "expand");
        } else {
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "expand");

            findBuildQueueBtn().click();
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "collapse");
        }
    }

    @Test
    public void testCheckBuildExecutorStatus() {
        if (getDriver().findElements(By.xpath("//div[@id='executors']//table")).size() > 0) {
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "collapse");

            findBuildExecutorStatusBtn().click();
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "expand");
        } else {
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "expand");

            findBuildExecutorStatusBtn().click();
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "collapse");
        }
    }

    @Test
    public void testCheckManageJenkinsAndNavigation() {
        final String expectedResultURL = getDriver().getCurrentUrl();

        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manage"));

        String headerActualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(headerActualResult, "Manage Jenkins");

        getDriver().navigate().back();
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultURL);
    }

    @Test
    public void testCreateNewNodeWithValidName() {
        ProjectUtils.goOnManageNodesAndCloudsPage(getDriver());

        getDriver().findElement(By.xpath("//span[text()='New Node']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(COMPUTER_NAME);
        getDriver().findElement(By.xpath("//label[text()='Permanent Agent']")).click();
        getDriver().findElement(By.xpath("//input[@id='ok']")).click();
        getDriver().findElement(By.xpath("//button[@id='yui-gen7-button']")).click();

        List<WebElement> listComputerNames = ProjectUtils.getComputerNames(getDriver());
        var actualName = listComputerNames.stream()
                .filter(element -> element.getText().equals(COMPUTER_NAME))
                .findFirst();

        Assert.assertTrue(actualName.isPresent());
    }

    @Test(dependsOnMethods = "testCreateNewNodeWithValidName")
    public void testCheckDeleteNode() {
        ProjectUtils.goOnManageNodesAndCloudsPage(getDriver());
        List<WebElement> listComputerNames = ProjectUtils.getComputerNames(getDriver());

        for (WebElement computerName : listComputerNames) {
            if (computerName.getText().equals(COMPUTER_NAME)) {
                Actions action = new Actions(getDriver());
                action.moveToElement(computerName).build().perform();

                WebElement selectorButton = getWait5().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@id='menuSelector']")));
                action.moveToElement(selectorButton).click().build().perform();

                getWait20().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//span[text()='Delete Agent']"))).click();
                getDriver().findElement(By.xpath("//button[@id='yui-gen1-button']")).click();
                break;
            }
        }

        List<WebElement> listComputerNamesAfterDelete = ProjectUtils.getComputerNames(getDriver());
        Assert.assertFalse(listComputerNamesAfterDelete.contains(COMPUTER_NAME));
    }
}
