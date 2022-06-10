import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FabrianaDescriptionOnMyViewsTests extends BaseTest {

    private WebElement dashboardMyViews() {
        return getDriver().findElement(By.xpath("//a[contains(@href, 'me/my-views')]"));
    }

    private WebElement buttonEditDescription() {
        return getDriver().findElement(By.xpath("//a[contains(@href, 'editDescription')]"));
    }

    private WebElement textareaDescription() {
        return getDriver().findElement(By.xpath("//textarea[contains(@name, 'description')]"));
    }

    private WebElement buttonSave() {
        return getDriver().findElement(By.xpath("//button[@type='submit' and contains(text(), 'Save')]"));
    }

    private WebElement fieldDescriptionOnThePage() {
        return getDriver().findElement(By.xpath("//div[@id='description']/div[not(@class)]"));
    }

    private WebElement buttonPreview() {
        return getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']"));
    }

    private WebElement textareaPreview() {
        return getDriver().findElement(By.xpath("//div[@class='textarea-preview']"));
    }

    private WebElement buttonHidePreview() {
        return getDriver().findElement(By.xpath("//a[@class='textarea-hide-preview']"));
    }

    private String actualResultDescription() {
        return fieldDescriptionOnThePage().getText();
    }

    private String expectedResultDescription = RandomStringUtils.randomAscii(10);

    private String textareaPreviewText() {
        return textareaPreview().getText();
    }

    private void clearDescription() {
        buttonEditDescription().click();
        textareaDescription().clear();
        buttonSave().click();
    }


    @Test
    public void testAddDescriptionOnMyViews_TC_028_001() {

        dashboardMyViews().click();

        buttonEditDescription().isDisplayed();
        clearDescription();
        buttonEditDescription().click();

        textareaDescription().sendKeys(expectedResultDescription);
        buttonSave().click();

        Assert.assertEquals(actualResultDescription(), expectedResultDescription);

    }

    @Test
    public void testEditDescriptionOnMyViews_TC_028_002() {

        dashboardMyViews().click();

        buttonEditDescription().isDisplayed();
        clearDescription();
        buttonEditDescription().click();

        textareaDescription().sendKeys(expectedResultDescription);
        buttonSave().click();

        Assert.assertEquals(actualResultDescription(), expectedResultDescription);

        buttonEditDescription().click();
        expectedResultDescription = "Jenkins Test Description Two";
        textareaDescription().clear();
        textareaDescription().sendKeys(expectedResultDescription);
        buttonSave().click();

        Assert.assertEquals(actualResultDescription(), expectedResultDescription);

    }

    @Test
    public void testCheckButtonPreviewDescriptionOnMyViews_TC_028_003() {

        dashboardMyViews().click();

        buttonEditDescription().isDisplayed();
        clearDescription();
        buttonEditDescription().click();

        textareaDescription().sendKeys(expectedResultDescription);
        buttonPreview().isDisplayed();
        buttonPreview().click();

        textareaPreview().isDisplayed();
        Assert.assertEquals(textareaPreviewText(), expectedResultDescription);

    }

    @Test
    public void testCheckButtonHidePreviewDescriptionOnMyViews_TC_028_004() {

        dashboardMyViews().click();

        buttonEditDescription().isDisplayed();
        clearDescription();
        buttonEditDescription().click();

        textareaDescription().sendKeys(expectedResultDescription);
        buttonPreview().isDisplayed();
        buttonPreview().click();
        textareaPreview().isDisplayed();
        Assert.assertEquals(textareaPreviewText(), expectedResultDescription);

        buttonHidePreview().isDisplayed();
        buttonHidePreview().click();
        Assert.assertFalse(textareaPreview().isDisplayed());

    }

}
