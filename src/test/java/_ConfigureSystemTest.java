import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

public class _ConfigureSystemTest extends BaseTest {

    private static final By SYSTEM_MESSAGE_FORM = By.name("system_message");

    @Test
    public void testConfigureSystemMessagePositive() {

        final String randomSystemMessage = TestUtils.getRandomStr();

        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ConfigureSystem.click(getDriver());

        TestUtils.clearAndSend(getDriver(), SYSTEM_MESSAGE_FORM, randomSystemMessage);
        getWait20().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']"))).click();

        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());

        Assert.assertEquals(getDriver().findElement(By.id("systemmessage")).getText(), randomSystemMessage);
    }


    @Test
    public void testConfigureSystemMessagePreviewPositive() {

        final String randomSystemMessage = TestUtils.getRandomStr();

        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ConfigureSystem.click(getDriver());

        TestUtils.clearAndSend(getDriver(), SYSTEM_MESSAGE_FORM, randomSystemMessage);

        getDriver().findElement(By.xpath("//a[normalize-space(.)='Preview']")).click();

        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), randomSystemMessage);

        ProjectUtils.clickSaveButton(getDriver());
    }
}