package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public final class NewItemPage<ConfigPage> extends BaseHeaderFooterPage {

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "ok-button")
    private WebElement btnOk;

    @FindBy(id = "itemname-invalid")
    private WebElement errorMessageItemNameInvalid;

    @FindBy(id = "itemname-required")
    private WebElement errorMessageItemNameRequired;

    @FindBy(id = "from")
    private WebElement inputCopyFrom;

    @FindBy(xpath = "//li/label")
    private List<WebElement> listTypeProjectLabels;

    @FindBy(xpath = "//div[@class='desc']")
    private List<WebElement> listTypeProjectDescriptions;

    @FindBy(xpath = "//div[@class='icon']/img")
    private List<WebElement> listTypeProjectImages;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li")
    private List<WebElement> listBreadCrumbItems;

    @FindBy(xpath = "//label[@for='name']")
    private WebElement headerNewItemPage;

    @FindBy(className = "input-help")
    private WebElement messageInputNameDisabled;

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
        inputName.sendKeys(text);

        return this;
    }

    public NewItemPage<FreestyleConfigPage> setFreestyleProjectType() {
        freestyleItem.click();

        return new NewItemPage<>(getDriver(), new FreestyleConfigPage(getDriver()));
    }

    public NewItemPage<PipelineConfigPage> setPipelineProjectType() {
        pipelineItem.click();

        return new NewItemPage<>(getDriver(), new PipelineConfigPage(getDriver()));
    }

    public NewItemPage<MultiConfigurationConfigPage> setMultiConfigurationProjectType() {
        multiConfigurationItem.click();

        return new NewItemPage<>(getDriver(), new MultiConfigurationConfigPage(getDriver()));
    }

    public NewItemPage<FolderConfigPage> setFolderProjectType() {
        folderItem.click();

        return new NewItemPage<>(getDriver(), new FolderConfigPage(getDriver()));
    }

    public NewItemPage<MultibranchPipelineConfigPage> setMultiBranchPipelineProjectType() {
        multiBranchPipelineItem.click();

        return new NewItemPage<>(getDriver(), new MultibranchPipelineConfigPage(getDriver()));
    }

    public NewItemPage<OrganizationFolderConfigPage> setOrganizationFolderProjectType() {
        organizationFolderItem.click();

        return new NewItemPage<>(getDriver(), new OrganizationFolderConfigPage(getDriver()));
    }

    public ConfigPage clickOkAndGoToConfig() {
        if (configPage == null) {
            throw new RuntimeException("Select project type");
        }
        btnOk.click();

        return configPage;
    }

    public NewItemPage<ConfigPage> setCopyFromName(String name) {
        getActions().moveToElement(inputCopyFrom).perform();
        inputCopyFrom.sendKeys(name);

        return this;
    }

    public NewItemPage<ConfigPage> clickOkButton() {
        getActions().moveToElement(btnOk).click().perform();

        return this;
    }

    public String getErrorMsgNameInvalidText() {
        return getWait5().until(ExpectedConditions.visibilityOf(errorMessageItemNameInvalid)).getText();
    }

    public String getErrorMsgNameInvalidCss(String cssValue) {
        return errorMessageItemNameInvalid.getCssValue(cssValue);
    }

    public String getErrorMsgNameRequiredText() {
        return errorMessageItemNameRequired.getText();
    }

    public boolean isErrorMsgNameInvalidDisplayed() {
        return errorMessageItemNameInvalid.isDisplayed();
    }

    public List<String> getFontWeightForEachProjectLabel() {
        List<String> listFontWeigh = new ArrayList<>();
        for (WebElement value : listTypeProjectLabels) {
            listFontWeigh.add(value.getCssValue("font-weight"));
        }

        return listFontWeigh;
    }

    public List<String> getFontSizeForEachProjectLabel() {
        List<String> listFontSize = new ArrayList<>();
        for (WebElement value : listTypeProjectLabels) {
            listFontSize.add(value.getCssValue("font-size"));
        }

        return listFontSize;
    }

    public List<String> getColorForEachProjectLabel() {
        List<String> listColor = new ArrayList<>();
        for (WebElement value : listTypeProjectLabels) {
            listColor.add(value.getCssValue("color"));
        }

        return listColor;
    }

    public List<String> getTextForEachProjectLabel() {
        List<String> listLabelText = new ArrayList<>();
        for (WebElement labelName : listTypeProjectLabels) {
            listLabelText.add(labelName.getText());
        }

        return listLabelText;
    }

    public List<String> getFontWeightForEachDescription() {
        List<String> listFontWeigh = new ArrayList<>();
        for (WebElement value : listTypeProjectDescriptions) {
            listFontWeigh.add(value.getCssValue("font-weight"));
        }

        return listFontWeigh;
    }

    public List<String> getFontSizeForEachDescription() {
        List<String> listFontSize = new ArrayList<>();
        for (WebElement value : listTypeProjectDescriptions) {
            listFontSize.add(value.getCssValue("font-size"));
        }

        return listFontSize;
    }

    public List<String> getColorForEachDescription() {
        List<String> listColor = new ArrayList<>();
        for (WebElement value : listTypeProjectDescriptions) {
            listColor.add(value.getCssValue("color"));
        }

        return listColor;
    }

    public List<Boolean> isTypeProjectImageDisplayed() {
        List<Boolean> listImageIsDisplayed = new ArrayList<>();
        getWait20().until(ExpectedConditions.visibilityOfAllElements(listTypeProjectImages));
        for (WebElement image : listTypeProjectImages) {
            listImageIsDisplayed.add(image.isDisplayed());
        }

        return listImageIsDisplayed;
    }

    public List<Boolean> isTypeProjectImageEnabled() {
        List<Boolean> listImageIsEnabled = new ArrayList<>();
        getWait20().until(ExpectedConditions.visibilityOfAllElements(listTypeProjectImages));
        for (WebElement image : listTypeProjectImages) {
            listImageIsEnabled.add(image.isEnabled());
        }

        return listImageIsEnabled;
    }

    public String getBreadCrumbsItemText(int index) {
        return listBreadCrumbItems.get(index).getText();
    }

    public String getErrorMessageItemNameInvalid() {
        return errorMessageItemNameInvalid.getText();
    }

    public ErrorPage clickBtnOkAndGoToErrorPage() {
        btnOk.click();

        return new ErrorPage(getDriver());
    }

    public NewItemPage<ConfigPage> clickToMoveMousePointer() {
        headerNewItemPage.click();

        return this;
    }

    public NewItemPage<ConfigPage> clearNameText() {
        inputName.clear();

        return this;
    }

    public String getMessageInputNameDisabled() {
        return messageInputNameDisabled.getText();
    }

    public String getOkButtonAttribute(String attribute) {
        return btnOk.getAttribute(attribute);
    }
}
