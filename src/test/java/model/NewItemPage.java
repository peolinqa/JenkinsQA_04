package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

import java.util.List;

public class NewItemPage extends BasePage {

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

    public NewItemPage setCopyFromName(String name){
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

    public NoSuchJobErrorPage createAndGoToNoSuchJobError() {
        okButton.click();

        return new NoSuchJobErrorPage(getDriver());
    }

    public String getNameErrorText() {

        return errorInvalidName.getText();
    }

    public String getNameErrorScc(String sccValue) {

       return errorInvalidName.getCssValue(sccValue);
    }

    public String getErrorNameRequiredText(){
        return errorNameRequired.getText();
    }

    public WebElement getNameError() {
        return errorInvalidName;
    }

    public List<WebElement> getProjectTypeLabels(){
        return projectTypeLabels;
    }

    public List<WebElement> getDescriptionStyle(){
        return description;
    }

    public List<WebElement> getProjectTypeImage(){
        return projectTypeImage;
    }

    public String getBreadCrumbs(int index){
        return breadCrumbs.get(index).getText();
    }
}

