package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class PipelineConfigPage extends BaseDashboardPage {

    public PipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(css = ".samples")
    private WebElement dropDownMenuPipelineTab;

    @FindBy(xpath = "//div[1][@class='samples']//select/option")
    private List<WebElement> dropDownMenuInTabPipeline;

    @FindBy(xpath = "//button[@id='yui-gen4-button']")
    private WebElement advancedButton;

    @FindBy(css = "a[tooltip$='Display Name']")
    private WebElement displayNameInTabAdvancedProjectOptions;

    @FindBy(css = "[href='https://plugins.jenkins.io/workflow-job']")
    private WebElement urlAttribute;

    @FindBy(className = "config-section-activator")
    private List<WebElement> itemConfigurationMenu;

    @FindBy(xpath = "//div[@class='jenkins-form-item config_pipeline active']//select")
    private WebElement definitionDropDownMenuPipelineScript;

    @FindBy(xpath = "//div[@class='jenkins-form-item has-help']//select")
    private WebElement definitionDropDownMenuPipelineScriptScm;

    @FindBy(xpath = "//button[@id='yui-gen15-button']")
    private WebElement credentialsAddButton;

    @FindBy(id = "yui-gen17")
    private WebElement jenkinsProviderButton;

    @FindBy(tagName = "h2")
    private WebElement titleOfJenkinsCredentialsProviderWindow;

    @FindBy(xpath = "//a[@href='pipeline-syntax']")
    private WebElement pipelineSyntaxLink;

    @FindBy(xpath = "//input[@name='_.sandbox']")
    private WebElement useGroovySandboxCheckbox;

    @FindBy(xpath = "//label[text()='This project is parameterized']")
    private WebElement checkboxProjectParameterized;

    @FindBy(id = "yui-gen1-button")
    private WebElement parameterAddProject;

    @FindBy(id = "yui-gen8")
    private WebElement booleanParameterButton;

    @FindBy(id = "yui-gen9")
    private WebElement choiceParameterButton;

    @FindBy(xpath = "//label[text()='Do not allow concurrent builds']")
    private WebElement checkboxDoNotAllowConcurrentBuilds;

    @FindBy(xpath = "//b[text()='Choice Parameter']")
    private WebElement menuChoiceParameter;

    @FindBy(xpath = "//b[text()='Boolean Parameter']")
    private WebElement menuBooleanParameter;

    @FindBy(xpath = "//div[@class='dd-handle']/b")
    private List<WebElement> parametersLocation;

    @FindBy(id = "cb2")
    private WebElement checkBoxDiscardOldBuilds;

    @FindBy(xpath = "//input[@name='_.numToKeepStr']")
    private WebElement maxNumberOldItemsToKeep;

    @FindBy(xpath = "//input[@name='_.daysToKeepStr']")
    private WebElement daysToKeepOldItems;

    @FindBy(xpath = "//div[contains(@hashelp, 'true')]//label")
    private List<WebElement> listOfCheckBoxWithHelps;

    @FindBy(xpath = "//div[contains(@hashelp, 'true')]//a")
    private List<WebElement> checkBoxHelpsText;

    @FindBy(name = "parameter.name")
    private WebElement parameterName;

    @FindBy(name = "parameter.choices")
    private WebElement parameterChoices;

    @FindBy(name = "parameter.description")
    private WebElement parameterDescription;

    @FindBy(css = "#yui-gen5-button")
    private WebElement applyButton;

    @FindBy(css = "#notification-bar")
    private WebElement notification;

    @FindBy(xpath = "//input[@name='hasCustomQuietPeriod']")
    private WebElement checkBoxQuietPeriod;

    @FindBy(name = "quiet_period")
    private WebElement lineQuietPeriod;

    @FindBy(xpath = "//input[@name='quiet_period']/../following-sibling::div[contains(@class, 'validation-error-area')]" +
            "/div/div[@class='error']")
    private WebElement periodErrorMessage;

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new ProjectPage(getDriver());
    }

    public PipelineConfigPage applyButtonClick() {
        applyButton.click();

        return this;
    }

    public String notification() {
        return getWait5().until(ExpectedConditions.visibilityOf(notification)).getText();
    }

    public PipelineConfigPage jsDropDownMenuPipelineTab() {
        TestUtils.scrollToElement(getDriver(), dropDownMenuPipelineTab);

        return this;
    }

    public PipelineConfigPage jsCheckboxProjectParameterized() {
        TestUtils.scrollToElement(getDriver(), checkboxProjectParameterized);

        return this;
    }

    public PipelineConfigPage jsCheckboxDoNotAllowConcurrentBuilds() {
        TestUtils.scrollToElement(getDriver(), checkboxDoNotAllowConcurrentBuilds);

        return this;
    }

    public int collectDropDownMenu() {
        return dropDownMenuInTabPipeline.size();
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        TestUtils.scrollToElement(getDriver(), advancedButton);
        advancedButton.click();

        return this;
    }

    public PipelineConfigPage clickHelpForFeatureDisplayName() {
        displayNameInTabAdvancedProjectOptions.click();

        return this;
    }

    public PipelineConfigPage selectConfigurationMenuDefinition(String name) {
        for (WebElement el : itemConfigurationMenu) {
            if (el.getText().equals(name)) {
                el.click();
            }
        }

        return new PipelineConfigPage(getDriver());
    }

    public PipelineConfigPage collectPipelineScriptDropDownMenu() {
        new Select(definitionDropDownMenuPipelineScript).selectByIndex(1);

        return this;
    }

    public PipelineConfigPage collectPipelineScriptScmDropDownMenu() {
        new Select(definitionDropDownMenuPipelineScriptScm).selectByIndex(1);

        return this;
    }

    public PipelineConfigPage clickCredentialsAddButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(credentialsAddButton)).click();

        return this;
    }

    public PipelineConfigPage clickJenkinsProviderButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(jenkinsProviderButton)).click();

        return this;
    }

    public PipelineSyntaxPage getHrefAndGoToPipelineSyntaxPage() {
        String pipelineSyntaxAtt = pipelineSyntaxLink.getAttribute("href");
        getDriver().get(pipelineSyntaxAtt);

        return new PipelineSyntaxPage(getDriver());
    }

    public String openJenkinsCredentialsProviderWindow() {
        String windowTitleText = titleOfJenkinsCredentialsProviderWindow.getText();

        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();

        return windowTitleText;
    }

    public String getUseGroovySandBoxCheckboxAtt() {
        return useGroovySandboxCheckbox.getAttribute("checked");
    }

    public PipelinePluginPage transitionToCorrectPage() {
        getDriver().navigate().to(urlAttribute.getAttribute("href"));

        return new PipelinePluginPage(getDriver());
    }

    public PipelineConfigPage clickCheckboxProjectParameterized() {
        checkboxProjectParameterized.click();

        return this;
    }

    public PipelineConfigPage clickAddParameterOfBuildButton() {
        parameterAddProject.click();

        return this;
    }

    public PipelineConfigPage clickBooleanParameterButton() {
        booleanParameterButton.click();

        return this;
    }

    public PipelineConfigPage clickChoiceParameterButton() {
        choiceParameterButton.click();

        return this;
    }

    public PipelineConfigPage menuChoiceParameterDragAndDrop() {
        getActions()
                .clickAndHold(menuChoiceParameter)
                .moveToElement(menuBooleanParameter)
                .release(menuBooleanParameter)
                .perform();

        return this;
    }

    public List<String> collectLocationProjectParameterized() {
        List<String> actualLocationProjectParameterized = new ArrayList<>();

        for (WebElement element : parametersLocation) {
            actualLocationProjectParameterized.add(element.getText());
        }
        saveConfigAndGoToProject();

        return actualLocationProjectParameterized;
    }

    public PipelineConfigPage clickDropDownMenuPipelineTab() {
        getActions()
                .moveToElement(dropDownMenuPipelineTab)
                .click()
                .sendKeys(Keys.ARROW_DOWN)
                .click()
                .perform();

        return this;
    }

    public PipelineConfigPage clickCheckBoxDiscardOldBuilds() {
        checkBoxDiscardOldBuilds.click();

        return this;
    }

    public PipelineConfigPage enteringParametersToDisplayLatestBuilds(String displayedQuantity, String storageDays) {
        maxNumberOldItemsToKeep.sendKeys(displayedQuantity);
        daysToKeepOldItems.sendKeys(storageDays);

        return this;
    }

    public boolean checkHelpTooltipsTextCheckBoxHelpText() {

        if (listOfCheckBoxWithHelps == null || checkBoxHelpsText == null) {
            return false;
        }
        boolean result = false;
        for (int i = 0; i < listOfCheckBoxWithHelps.size(); i++) {

            result = (checkBoxHelpsText.get(i).getAttribute("title")
                    .replace("Help for feature: ", "").equals(
                            listOfCheckBoxWithHelps.get(i).getText()));
        }

        return result;
    }

    public String getTitleConfigPage() {
        return getDriver().getTitle();
    }

    public PipelineConfigPage enteringParametersIntoProject() {
        getActions()
                .moveToElement(parameterName)
                .click()
                .sendKeys("Checking Name Display")
                .moveToElement(parameterChoices)
                .click()
                .sendKeys("This Is The Default Value" + Keys.ENTER)
                .sendKeys("2" + Keys.ENTER)
                .sendKeys("3" + Keys.ENTER)
                .moveToElement(parameterDescription)
                .click()
                .sendKeys("Checking Description Display")
                .perform();

        return this;
    }

    public PipelineConfigPage enteringDataIntoLineQuietPeriod() {
        getActions()
                .moveToElement(checkBoxQuietPeriod)
                .click()
                .moveToElement(lineQuietPeriod)
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("-1")
                .sendKeys(Keys.TAB)
                .perform();

        return this;
    }

    public String verificationPeriodErrorMessage() {
        String errorMessageText = periodErrorMessage.getText();
        saveConfigAndGoToProject();

        return errorMessageText;
    }
}