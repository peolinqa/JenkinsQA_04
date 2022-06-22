import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _ManageNodesAndCloudsTest extends BaseTest {

    private static final By XPATH_MANAGE_JENKINS = By.xpath("//span[text()='Manage Jenkins']");
    private static final By XPATH_MANAGE_NODES_AND_CLOUDS = By.xpath("//dt[text()='Manage Nodes and Clouds']");
    private static final By XPATH_NEW_NODE = By.xpath("//span[text()='New Node']");
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

    private void deleteNode(String name) {
        List<WebElement> listComputerNames = getComputerNames();

        for (WebElement computerName : listComputerNames) {
            if (computerName.getText().equals(name)) {

                Actions action = new Actions(getDriver());
                action.moveToElement(computerName).build().perform();

                getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_MENU_SELECTOR_BTN));
                action.moveToElement(getDriver().findElement(XPATH_MENU_SELECTOR_BTN)).click().build().perform();

                getWait20().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//span[text()='Delete Agent']"))).click();
                getDriver().findElement(By.xpath("//button[@id='yui-gen1-button']")).click();
                break;
            }
        }
    }

    private List<WebElement> getComputerNames() {
        getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']")).click();
        getDriver().findElement(XPATH_MANAGE_JENKINS).click();
        getDriver().findElement(XPATH_MANAGE_NODES_AND_CLOUDS).click();
        return getDriver().findElements(By.xpath("//table[@id='computers']/tbody/*/td[2]"));
    }

    private void goOnNewNodePage() {
        getDriver().findElement(XPATH_MANAGE_JENKINS).click();
        getDriver().findElement(XPATH_MANAGE_NODES_AND_CLOUDS).click();
        getDriver().findElement(XPATH_NEW_NODE).click();
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
    public void testCheckManageJenkins() {
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
        goOnNewNodePage();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("first test node");
        getDriver().findElement(By.xpath("//label[text()='Permanent Agent']")).click();
        getDriver().findElement(By.xpath("//input[@id='ok']")).click();
        getDriver().findElement(By.xpath("//button[@id='yui-gen7-button']")).click();
        getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']")).click();

        List<WebElement> listComputerNames = getComputerNames();
        var actualName = listComputerNames.stream()
                .filter(element -> element.getText().equals("first test node"))
                .findFirst();
        Assert.assertTrue(actualName.isPresent());

        deleteNode("first test node");
    }
}
