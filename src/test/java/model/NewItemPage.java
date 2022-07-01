package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

public class NewItemPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(id = "itemname-invalid")
    private WebElement nameError;

    @FindBy(id = "from")
    private WebElement copyFromInputName;


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

    public OrganizationFolderConfigPage createAndGoToOrganizationFolderConfigure() {
        okButton.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public String getNameErrorText() {

        return nameError.getText();
    }

    public String getNameErrorScc(String sccValue) {

       return nameError.getCssValue(sccValue);
    }

    public WebElement getNameError() {
        return nameError;
    }
}
