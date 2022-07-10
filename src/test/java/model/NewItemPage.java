package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runner.ProjectUtils;
import java.util.List;

public class NewItemPage extends BaseHeaderFooterPage {

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(id = "itemname-invalid")
    private WebElement errorInvalidName;

    @FindBy(id = "itemname-required")
    private WebElement errorNameRequired;

    @FindBy(id = "from")
    private WebElement copyFromInputName;

    @FindBy(xpath = "//li/label")
    private List<WebElement> projectTypeLabels;

    @FindBy(xpath = "//div[@class='desc']")
    private List<WebElement> description;

    @FindBy(xpath = "//div[@class='icon']/img")
    private List<WebElement> projectTypeImage;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li")
    private List<WebElement> breadCrumbs;

    @FindBy(xpath = "//label[@for='name']")
    private WebElement h3Header;

    @FindBy(id = "jenkins-head-icon")
    private WebElement headerIcon;

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage setProjectName(String text) {
        nameText.sendKeys(text);

        return this;
    }

    public NewItemPage setProjectType(ProjectUtils.ProjectType projectType) {
        projectType.click(getDriver());

        return this;
    }

    public ItemConfigPage createAndGoToConfig() {
        okButton.click();

        return new ItemConfigPage(getDriver());
    }

    public HomePage createAndGoHome() {
        okButton.click();
        headerIcon.click();

        return new HomePage(getDriver());
    }

    public NewItemPage setCopyFromName(String name) {
        copyFromInputName.sendKeys(name);

        return this;
    }

    public PipelineConfigPage createAndGoToPipelineConfigure() {
        okButton.click();

        return new PipelineConfigPage(getDriver());
    }

    public NewItemPage clickCreateButton() {
        getActions().moveToElement(okButton).click().perform();

        return this;
    }

    public OrganizationFolderConfigPage createAndGoToOrganizationFolderConfigure() {
        okButton.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public FreestyleConfigPage clickOkGoToConfig() {
        okButton.click();

        return new FreestyleConfigPage(getDriver());
    }

    public String getNameErrorText() {

        return errorInvalidName.getText();
    }

    public String getNameErrorScc(String sccValue) {

        return errorInvalidName.getCssValue(sccValue);
    }

    public String getErrorNameRequiredText() {
        return errorNameRequired.getText();
    }

    public WebElement getNameError() {
        return errorInvalidName;
    }

    public boolean isDisplayedNameError() {

        return errorInvalidName.isDisplayed();
    }

    public List<WebElement> getProjectTypeLabels() {
        return projectTypeLabels;
    }

    public List<WebElement> getDescriptionStyle() {
        return description;
    }

    public List<WebElement> getProjectTypeImage() {
        return projectTypeImage;
    }

    public String getBreadCrumbs(int index) {
        return breadCrumbs.get(index).getText();
    }

    public FolderConfigPage createAndGoToFolderConfigPage() {
        okButton.click();

        return new FolderConfigPage(getDriver());
    }

    public NewItemPage checkErrorMessage(String extectedMessage) {
        Assert.assertEquals(errorInvalidName.getText(), extectedMessage);

        return this;
    }

    public String getErrorMessage() {
        return errorInvalidName.getText();
    }

    public NewItemPage checkPresenceErrorMessageAndAssert(String name) {
        Assert.assertEquals(errorInvalidName.getText(),"» A job already exists with the name ‘" + name + "’");

        return this;
    }

    public ErrorPage createAndGoToErrorPage() {
        okButton.click();

        return new ErrorPage(getDriver());
    }

    public NewItemPage clickToMoveMousePointer() {
        h3Header.click();

        return new NewItemPage(getDriver());
    }

    public NewItemPage clearNameText(){
        nameText.clear();

        return this;
    }

    public NewItemPage waitWarningMessage(char invalidSymbol, String text){
        getWait5().until(ExpectedConditions.textToBePresentInElement(
                getNameError(),
                "» ‘" + invalidSymbol + text));

        return this;
    }

    public NewItemPage waitDotWarningMessage(){
        getWait5().until(ExpectedConditions.textToBePresentInElement(
                getNameError(), "» “.” is not an allowed name"));

        return this;
    }

    public MultibranchPipelineConfigPage clickOkGoMultibranchPipelineConfig() {
        okButton.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }
}