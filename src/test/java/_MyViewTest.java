import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class _MyViewTest extends BaseTest {
    private static final String VIEW_NAME = TestUtils.getRandomStr();
    private static final String EDIT_VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_DESCRIPTION = TestUtils.getRandomStr();

    private final By VIEW_NAMES_ON_TABBAR = By.cssSelector("div .tab a");
    private final By VIEW_NAMES_ON_BREADCRUMBS = By.xpath("//ul[@id='breadcrumbs']/li[@class='item']");

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

    private WebElement buttonOk1() {
        return getDriver().findElement(By.id("yui-gen1-button"));
    }

    private WebElement buttonOk2() {
        return getDriver().findElement(By.id("yui-gen2-button"));
    }

    private WebElement buttonOk13() {
        return getDriver().findElement(By.id("yui-gen13-button"));
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

    private List<String> getTextFromList(WebDriver driver, By locator) {
        driver.findElements(locator);
        return driver.findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private void fillFieldViewNameAndSelectLabelOfView(WebDriver driver, By locator) {
        driver.findElement(By.id("name")).sendKeys(VIEW_NAME);
        driver.findElement(locator).click();
    }

    private void clickNameOfViewOnBreadcrumbs() {
        getDriver().findElement(VIEW_NAMES_ON_BREADCRUMBS).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + VIEW_NAME + "')]")).click();
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());

        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        fillFieldViewNameAndSelectLabelOfView(getDriver(), By.xpath("//label[text() = 'List View']"));
        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonOk13().click();

        Assert.assertTrue(getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());
        fillFieldViewNameAndSelectLabelOfView(getDriver(), By.xpath("//label[text() = 'My View']"));
        getDriver().findElement(By.id("ok")).click();

        Assert.assertTrue(getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME));
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckTabBar() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());

        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        fillFieldViewNameAndSelectLabelOfView(getDriver(), By.xpath("//label[text() = 'List View']"));
        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonOk13().click();

        Assert.assertTrue(getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Ignore
    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs"})
    public void testCreateNewViewWithAnExistingName() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());
        fillFieldViewNameAndSelectLabelOfView(getDriver(), By.xpath("//label[text() = 'My View']"));

        Assert.assertEquals(getDriver().findElement(By.className("error")).getText(), "A view already exists with the name " + '"' + VIEW_NAME + '"');
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs"})
    public void testEditViewChangeName() {
        clickNameOfViewOnBreadcrumbs();

        ProjectUtils.Dashboard.View.EditView.click(getDriver());
        TestUtils.clearAndSend(getDriver(), By.name("name"), EDIT_VIEW_NAME);
        buttonOk2().click();

        Assert.assertEquals(EDIT_VIEW_NAME, getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).getText());
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

    @Test(dependsOnMethods = {"testEditViewChangeName"})
    public void testDeleteViewViaBreadcrumbs() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).click();

        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonOk1().click();

        Assert.assertFalse(getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs"})
    public void testDeleteViewViaTabBarFrame() {
        getDriver().findElement(By.xpath("//div/a[contains(text(),'" + VIEW_NAME + "')]")).click();
        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonOk1().click();

        Assert.assertFalse(getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
    }
}
