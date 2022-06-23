import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.Random;

public class EditBuildRRenameBuildCheckBuildInTimelineAndBuildHistoryTest extends BaseTest {

    private String jobNameRes = "";
    private String newNameJob = "newNmaeJob";
    private final By descriptionSignOfTheProj = By.xpath("//div[@id='description']/div[1]");
    private final By projNameSign = By.xpath("//div[@id='main-panel']//h1[contains(text(),'Project " + newNameJob + "')]");

    public void clickNewItem() {
        getDriver().findElement(By.cssSelector("a[title='New Item']")).click();
    }

    public void inputItemName(String text) {
        getDriver().findElement(By.id("name")).sendKeys(text);
    }

    public void clickFreestyleProject() {
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
    }

    public void clickOkButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    public void clickSaveButton() {
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void clickBackToDashboardLink() {
        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();
    }

    public void clickToJenkinsLogo() {
        getDriver().findElement(By.id("jenkins-name-icon")).click();
    }

    public void clickOnRenameLink() {
        getDriver().findElement(By.xpath("//a[@title='Rename']")).click();
    }

    public void inputNewNameClear() {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
    }

    public void inputNewNameSendK(String text) {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).sendKeys(text);
    }

    public void clickRenameButton() {
        getDriver().findElement(By.id("yui-gen1")).click();
    }

    public void clickDescriptionLink() {
        getDriver().findElement(By.id("description-link")).click();
    }

    public void inputDescriptionTextArea(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(text);
    }

    public void saveButtonClick() {
        getDriver().findElement(By.xpath("//button[@type='submit' and @id='yui-gen2-button']")).click();
    }

    public void buildNowButtonClick() {
        getDriver().findElement(By.xpath("//a[@title='Build Now']")).click();
    }

    public void trendClick() {
        getDriver().findElement(By.xpath("//a[text()='trend']")).click();
    }


    @Test(description = "US_032 Rename Build")
    public void testRenameBuild() {
        jobNameRes = checkJobName("project1");
        createNewFreeStyleProject();
        renameJobAndAddDescriptionSaveIt();
        String elementDescriptionSignOfTheProj = getDriver().findElement(descriptionSignOfTheProj).getText();
        WebElement projNameSignActualRes = getDriver().findElement(projNameSign);

        Assert.assertTrue(elementDescriptionSignOfTheProj.contains("Maven + Selenium project = TESTNG"));
        Assert.assertTrue(projNameSignActualRes.isDisplayed());

        buildNowButtonClick();
        trendClick();
        WebElement jobNameInTimeline = getDriver().findElement(By.xpath("//div[@id='timeline-band-1']//div//div//div//div[contains(text(),'" + newNameJob + "')]"));

        Assert.assertTrue(jobNameInTimeline.isDisplayed());

        WebElement jobSignInBuildHistory = getDriver().findElement(By.xpath("//a[@href='/job/" + newNameJob + "/1/'][1]"));

        Assert.assertTrue(jobSignInBuildHistory.isDisplayed());


    }

    public void renameJobAndAddDescriptionSaveIt() {
        clickOnRenameLink();
        inputNewNameClear();
        inputNewNameSendK(newNameJob);
        clickRenameButton();
        clickDescriptionLink();
        inputDescriptionTextArea("Maven + Selenium project = TESTNG +  Rest Assured + Jackson = project");
        saveButtonClick();
    }

    public void createNewFreeStyleProject() {
        clickNewItem();
        inputItemName(jobNameRes);
        clickFreestyleProject();
        clickOkButton();
        WebElement saveButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        getWait5().until(ExpectedConditions.elementToBeClickable(saveButton));
        clickSaveButton();
    }

    public boolean isJobExists() {
        clickToJenkinsLogo();
        List<WebElement> listOOfJobs = getDriver().findElements(By.xpath("//table[@id='projectstatus']//tbody//tr//td[3]//a"));
        if (listOOfJobs.size() == 0) {
            return false;
        }
        return true;
    }

    public String checkJobName(String jobName) {
        Random random = new Random();
        if (isJobExists()) {
            String result = jobName + random.nextInt();
            return result;
        } else {
            return jobName;
        }
    }
}
