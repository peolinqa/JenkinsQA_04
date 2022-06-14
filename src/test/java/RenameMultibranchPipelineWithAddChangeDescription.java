import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RenameMultibranchPipelineWithAddChangeDescription extends BaseTest {

    private Actions action;
    private final String baseSymbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String invalidSymbols = "!@#$%^&*:;\\/?№\"";
    private final String MULTI_PIPELINE_NAME_OLD = RandomStringUtils.random(10, baseSymbols);
    private final String MULTI_PIPELINE_NAME_NEW = RandomStringUtils.random(10, baseSymbols);
    private final String MULTI_PIPELINE_NAME_INVALID = RandomStringUtils.random(10, invalidSymbols);
    private final String MULTI_PIPELINE_DESCRIPTION = RandomStringUtils.random(10, baseSymbols);
    private final String MULTI_PIPELINE_JOB_RENAME = "Rename";
    private final String MULTI_PIPELINE_JOB_DELETE = "Delete Multibranch Pipeline";

    private void goHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private void clickMenu(String jobName, String jobMenu) {
        goHome();

        action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement
                (By.xpath("//a[@href = 'job/".concat(jobName).concat("/']")))).perform();

        action.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']")));
        action.click().build().perform();

        action.sendKeys(Keys.ARROW_DOWN);
        getDriver().findElement
                        (By.xpath("//span[text() = '" + jobMenu + "']"))
                .click();
    }

    private void createMultibranchPipeline(String jobName) {
        goHome();

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(jobName);
        getDriver().findElement
                (By.className("org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.id("yui-gen8-button")).click();
    }

    private void renameMultibranchPipeline(String jobNameOld, String jobNameNew) {
        goHome();

        clickMenu(jobNameOld, MULTI_PIPELINE_JOB_RENAME);

        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(jobNameNew);
        getDriver().findElement(By.id("yui-gen1-button")).click();

    }

    private void deleteMultibranchPipeline(String jobName) {
        clickMenu(jobName, MULTI_PIPELINE_JOB_DELETE);
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void addMultibranchPipelineDescription(String jobName) {
        goHome();

        getDriver().findElement
                (By.xpath("//a[@href = 'job/".concat(jobName).concat("/']"))).click();

        getDriver().findElement(By.xpath("//span[text()='Configure']")).click();
        getDriver().findElement(By.name("_.description")).sendKeys(MULTI_PIPELINE_DESCRIPTION);
        getDriver().findElement(By.id("yui-gen8-button")).click();
    }

    private void changeMultibranchPipelineDescription(String jobName) {
        goHome();

        getDriver().findElement
                (By.xpath("//a[@href = 'job/".concat(jobName).concat("/']"))).click();

        getDriver().findElement(By.xpath("//span[text()='Configure']")).click();
        getDriver().findElement(By.name("_.description")).clear();
        getDriver().findElement(By.name("_.description")).sendKeys(MULTI_PIPELINE_DESCRIPTION);
        getDriver().findElement(By.id("yui-gen8-button")).click();
    }

    private boolean isMultibranchPipelineExists(String jobName) {
        goHome();
        return getDriver().findElement
                (By.xpath("//a[@href = 'job/".concat(jobName).concat("/']"))).isDisplayed();
    }

    private boolean isMultibranchPipelineDescriptionExists(String jobName) {
        goHome();
        getDriver().findElement
                (By.xpath("//a[@href = 'job/".concat(jobName).concat("/']"))).click();
        return getDriver().findElement
                (By.id("view-message")).isDisplayed();
    }

    @Test(description = "TC_050.001 | Multibranch Pipeline > Configure: Change name")
    public void testRenameMultiPipeline() {
        createMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);

        renameMultibranchPipeline(MULTI_PIPELINE_NAME_OLD, MULTI_PIPELINE_NAME_NEW);
        Assert.assertTrue(isMultibranchPipelineExists(MULTI_PIPELINE_NAME_NEW));

        deleteMultibranchPipeline(MULTI_PIPELINE_NAME_NEW);
    }

    @Test(description = "TC_050.001 | Multibranch Pipeline > Configure: Change name to same name")
    public void testRenameMultiPipelineSameName() {
        createMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);

        renameMultibranchPipeline(MULTI_PIPELINE_NAME_OLD, MULTI_PIPELINE_NAME_OLD);

        String messageWarning = "The new name is the same as the current name.";
        Assert.assertEquals(getDriver().findElement
                (By.xpath("//div[@id='main-panel']/p")).getText(), messageWarning);

        deleteMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);
    }

    @Test(description = "TC_050.001 | Multibranch Pipeline > Configure: Change name to name with erroneous characters")
    public void testRenameMultiPipelineWrongCharacters() {
        createMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);

        renameMultibranchPipeline(MULTI_PIPELINE_NAME_OLD, MULTI_PIPELINE_NAME_INVALID);

        String messageWarning = "is an unsafe character";
        Assert.assertTrue(getDriver().findElement
                (By.xpath("//div[@id='main-panel']/p")).getText().contains(messageWarning));

        deleteMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);
    }

    @Test(description = "TC_050.001 | Multibranch Pipeline > Configure: Add description")
    public void testAddDescriptionMultiPipeline() {
        createMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);

        if (!getDriver().findElement(By.id("view-message")).isDisplayed()) {
            addMultibranchPipelineDescription(MULTI_PIPELINE_NAME_OLD);
            Assert.assertTrue(isMultibranchPipelineDescriptionExists(MULTI_PIPELINE_NAME_OLD));
            Assert.assertEquals(getDriver().findElement
                    (By.id("view-message")).getText(), MULTI_PIPELINE_DESCRIPTION);
        }

        deleteMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);
    }

    @Test(description = "TC_050.001 | Multibranch Pipeline > Configure: Change description")
    public void testChangeDescriptionMultiPipeline() {
        createMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);

        addMultibranchPipelineDescription(MULTI_PIPELINE_NAME_OLD);

        if (getDriver().findElement(By.id("view-message")).isDisplayed()) {
            changeMultibranchPipelineDescription(MULTI_PIPELINE_NAME_OLD);
            Assert.assertTrue(isMultibranchPipelineDescriptionExists(MULTI_PIPELINE_NAME_OLD));
            Assert.assertEquals(getDriver().findElement
                    (By.id("view-message")).getText(), MULTI_PIPELINE_DESCRIPTION);
        }

        deleteMultibranchPipeline(MULTI_PIPELINE_NAME_OLD);
    }
}