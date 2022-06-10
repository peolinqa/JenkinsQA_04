import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class MaksPtCreateFreestyleProjectTest extends BaseTest {

    private final By NEW_ITEM = By.linkText("New Item");
    private final By INPUT = By.xpath("//input[@name='name']");
    private final By FREESTYLE_PROJECT = By.xpath("//li[@class='hudson_model_FreeStyleProject']");
    private final By OK_BUTTON = By.id("ok-button");
    private final By DASHBOARD = By.id("jenkins-home-link");

    private void stepsCreateNewProject(String str) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT).sendKeys(str);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(DASHBOARD).click();
    }

    private boolean actualResultContain(String str) {
        List<WebElement> list = getDriver().findElements(By.xpath("//tr[@id]"));
        List<String> projects = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            projects.add(list.get(i).getAttribute("id").substring(4));
            if (projects.get(i).equals(str)) {

                return true;
            }
        }

        return false;
    }

    @Test
    public void testCreateProjectWithValidName() {
        stepsCreateNewProject("MaksPt");

        boolean resultNewProjectCreated = actualResultContain("MaksPt");

        getDriver().findElement(By.xpath("//a[@href='job/MaksPt/']")).click();
        getDriver().findElement(By.xpath("//a[@data-message]")).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(resultNewProjectCreated);
    }

    @Test
    public void testCreateProjectNameWithCharacterAndEmpty() {
        getDriver().findElement(NEW_ITEM).click();

        String[] invalidName = {"!", "@", "#", "$", ";", "%", "^", "&", "?", "*", "[", "]", "/", ":", ".", ""};
        boolean resultButtonOkDisabled = true;
        for (int i = 0; i < invalidName.length; i++) {
            getDriver().findElement(INPUT).sendKeys(invalidName[i]);
            getDriver().findElement(FREESTYLE_PROJECT).click();
            if (!getDriver().findElement(By.xpath("//button[@class]")).getAttribute("class").equals("disabled")) {
                resultButtonOkDisabled = false;
            }
            getDriver().navigate().refresh();

            Assert.assertTrue(resultButtonOkDisabled);
        }
    }

    @Test
    public void testCreateProjectNameSpace() {
        stepsCreateNewProject(" ");

        Assert.assertFalse(actualResultContain(" "));
    }
}