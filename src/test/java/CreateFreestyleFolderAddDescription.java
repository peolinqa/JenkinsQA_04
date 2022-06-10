import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import runner.BaseTest;

import java.util.PrimitiveIterator;

public class CreateFreestyleFolderAddDescription extends BaseTest {
    private final String NEW_ITEM = "New Item";
    private final String INPUT_NAME = "name";
    private final String OK_BUTTON = "ok-button";
    private final String SAVE_BUTTON = "//button[@type='submit']";
    private final String DELETE_PROJECT_BUTTON = "Delete Project";
    private final String EDIT_DESCRIPTION = "//a[@href='editDescription']";
    private final String EDIT_DESCRIPTION_TEXTAREA = "//textarea[@name='description']";
    private final String SAVE_BUTTON_DESCRIPTION = "yui-gen1-button";



    protected void deleteNewProject(String link) {
        getDriver().findElement(By.linkText(link)).click();
        getDriver().findElement(By.linkText(DELETE_PROJECT_BUTTON)).click();

        getDriver().switchTo().alert().accept();
    }

    protected void clearDescriptionBoxOfFreestyleProject() {
        getDriver().findElement(By.xpath(EDIT_DESCRIPTION)).click();
        if (getDriver().findElement(By.name(("description"))).isDisplayed()) {
            getDriver().findElement(By.name("description")).clear();
            getDriver().findElement(By.id(SAVE_BUTTON_DESCRIPTION)).click();
        }
    }

    @Test
    public void createFreestyleProject() {
        getDriver().findElement(By.linkText(NEW_ITEM)).click();
        getDriver().findElement(By.id(INPUT_NAME)).sendKeys("project 10");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul//li[1]")).click();
        getDriver().findElement(By.id(OK_BUTTON)).click();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        getDriver().findElement(By.xpath(SAVE_BUTTON)).click();

        Assert.assertEquals(getDriver().getTitle(), "project 10 [Jenkins]");

        deleteNewProject("project 10");
    }

    @Test
    public void testAddDescriptionToFreestyleProject() throws InterruptedException {
        getDriver().findElement(By.xpath(EDIT_DESCRIPTION)).click();
        getDriver().findElement(By.xpath(EDIT_DESCRIPTION_TEXTAREA)).sendKeys("new description added");
        getDriver().findElement(By.id(SAVE_BUTTON_DESCRIPTION)).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='description']/div[text()]")).getText(), "new description added");

        clearDescriptionBoxOfFreestyleProject();
    }
}

