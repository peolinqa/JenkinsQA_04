import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _MyViewTest extends BaseTest {
    private static final String JOB_NAME = "Job";
    private static final String VIEW_NAME = "Test view 123456789012345678901234567890123456789";
    private static final String VIEW_NAME_1 = "My new view";
    private static final String EDIT_VIEW_NAME = String.valueOf((int) (Math.random() * 999));

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

    private void createJob() {
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(JOB_NAME);
        getDriver().findElement(By.xpath("//li[contains(@class,'FreeStyleProject')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs li a")).click();
    }

    private void deleteJob() {
        getDriver().findElement(By.linkText(JOB_NAME)).click();
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testCreateNewView() {
        final String viewDescription = "My first Test view ;-)";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@title = 'New View']")).click();

        driver.findElement(By.id("name")).sendKeys(VIEW_NAME);
        driver.findElement(By.xpath("//label[text() = 'List View']")).click();
        driver.findElement(By.id("ok")).click();

        driver.findElement(By.name("description")).sendKeys(viewDescription);
        driver.findElement(By.id("yui-gen13-button")).click();

        Assert.assertEquals(VIEW_NAME, driver.findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='item']/a[starts-with(@href, '/view')]")).getText());
        Assert.assertEquals(viewDescription, driver.findElement(By.xpath("//div[@id='description']/div[1]")).getText());
    }

    @Test(dependsOnMethods = {"testCreateNewView"})
    public void testDeleteViewViaTabBarFrame() {

        createJob();

        getDriver().findElement(By.xpath("//div/a[contains(text(),'" + VIEW_NAME + "')]")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> listViews = getDriver().findElements(By.cssSelector("div .tab a"));
        List<String> namesOfViews = listViews.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertFalse(namesOfViews.contains(VIEW_NAME));

        deleteJob();
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME_1);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertEquals(
                VIEW_NAME_1,
                getDriver()
                        .findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]"))
                        .getText()
        );
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyView"})
    public void testCreateNewViewWithAnExistingName() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME_1);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();

        String errorMessageActual = getDriver().findElement(By.className("error")).getText();
        String errorMessageExpected = "A view already exists with the name " + '"' + VIEW_NAME_1 + '"';

        Assert.assertEquals(errorMessageActual, errorMessageExpected);
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithAnExistingName"})
    public void testEditViewChangeName() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='/view/My%20new%20view/']")).click();

        getDriver().findElement(By.linkText("Edit View")).click();

        WebElement name = getDriver().findElement(By.name("name"));
        name.clear();
        name.sendKeys(EDIT_VIEW_NAME);

        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(
                EDIT_VIEW_NAME,
                getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '"
                                + EDIT_VIEW_NAME + "')]"))
                        .getText()
        );
    }

    @Test(dependsOnMethods = {"testEditViewChangeName"})
    public void testDeleteCreatedView() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + "/view/" + EDIT_VIEW_NAME + "')]")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> namesView = getDriver().findElements(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']"));
        List<String> names = new ArrayList<>();
        for (WebElement name : namesView) {
            names.add(name.getText());
        }

        Assert.assertTrue(names.get(0).isEmpty());
        Assert.assertFalse(names.contains(EDIT_VIEW_NAME));
    }

    @Test
    public void testAddDescriptionOnMyViews() {
        dashboardMyViews().click();

        buttonEditDescription().isDisplayed();
        clearDescription();
        buttonEditDescription().click();

        textareaDescription().sendKeys(expectedResultDescription);
        buttonSave().click();

        Assert.assertEquals(actualResultDescription(), expectedResultDescription);
    }

    @Test
    public void testEditDescriptionOnMyViews() {
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
    public void testCheckButtonPreviewDescriptionOnMyViews() {
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
    public void testCheckButtonHidePreviewDescriptionOnMyViews() {
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
