package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class NewItemPage<ConfigPage> extends BaseHeaderFooterPage {

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

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleItem;
    @FindBy(className = "org_jenkinsci_plugins_workflow_job_WorkflowJob")
    private WebElement pipelineItem;
    @FindBy(className = "hudson_matrix_MatrixProject")
    private WebElement multiConfigurationItem;
    @FindBy(className = "com_cloudbees_hudson_plugins_folder_Folder")
    private WebElement folderItem;
    @FindBy(className = "org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")
    private WebElement multiBranchPipelineItem;
    @FindBy(className = "jenkins_branch_OrganizationFolder")
    private WebElement organizationFolderItem;

    private final ConfigPage configPage;

    public NewItemPage(WebDriver driver) {
        this(driver, null);
    }

    private NewItemPage(WebDriver driver, ConfigPage configPage) {
        super(driver);
        this.configPage = configPage;
    }

    public NewItemPage<ConfigPage> setProjectName(String text) {
        nameText.sendKeys(text);

        return this;
    }

    public NewItemPage<FreestyleConfigPage> setProjectTypeFreestyle() {
        freestyleItem.click();

        return new NewItemPage<>(getDriver(), new FreestyleConfigPage(getDriver()));
    }

    public NewItemPage<PipelineConfigPage> setProjectTypePipeline() {
        pipelineItem.click();

        return new NewItemPage<>(getDriver(), new PipelineConfigPage(getDriver()));
    }

    public NewItemPage<FreestyleConfigPage> setProjectTypeMultiConfiguratio() {
        multiConfigurationItem.click();

        return new NewItemPage<>(getDriver(), new FreestyleConfigPage(getDriver()));
    }

    public NewItemPage<FolderConfigPage> setProjectTypeFolder() {
        folderItem.click();

        return new NewItemPage<>(getDriver(), new FolderConfigPage(getDriver()));
    }

    public NewItemPage<MultibranchPipelineConfigPage> setProjectTypeMultiBranchPipeline() {
        multiBranchPipelineItem.click();

        return new NewItemPage<>(getDriver(), new MultibranchPipelineConfigPage(getDriver()));
    }

    public NewItemPage<OrganizationFolderConfigPage> setProjectTypeOrganizationFolder() {
        organizationFolderItem.click();

        return new NewItemPage<>(getDriver(), new OrganizationFolderConfigPage(getDriver()));
    }

    public ConfigPage clickOkAndGoToConfig() {
        if (configPage == null) {
            throw new RuntimeException("Select project type");
        }

        okButton.click();

        return configPage;
    }

    public NewItemPage<ConfigPage> setCopyFromName(String name) {
        copyFromInputName.sendKeys(name);

        return this;
    }

    public NewItemPage<ConfigPage> clickCreateButton() {
        getActions().moveToElement(okButton).click().perform();

        return this;
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

    public NewItemPage<ConfigPage> checkErrorMessage(String extectedMessage) {
        Assert.assertEquals(errorInvalidName.getText(), extectedMessage);

        return this;
    }

    public String getErrorMessage() {
        return errorInvalidName.getText();
    }

    public ErrorPage createAndGoToErrorPage() {
        okButton.click();

        return new ErrorPage(getDriver());
    }

    public NewItemPage<ConfigPage> clickToMoveMousePointer() {
        h3Header.click();

        return this;
    }

    public NewItemPage<ConfigPage> clearNameText() {
        nameText.clear();

        return this;
    }

    public NewItemPage<ConfigPage> waitWarningMessage(char invalidSymbol, String text){
        getWait5().until(ExpectedConditions.textToBePresentInElement(
                getNameError(),
                "» ‘" + invalidSymbol + text));

        return this;
    }

    public NewItemPage<ConfigPage> waitDotWarningMessage(){
        getWait5().until(ExpectedConditions.textToBePresentInElement(
                getNameError(), "» “.” is not an allowed name"));

        return this;
    }
}