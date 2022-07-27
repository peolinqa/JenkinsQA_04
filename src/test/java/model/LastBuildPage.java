package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LastBuildPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[@title='Edit Build Information']")
    private WebElement editBuildInfoButton;

    @FindBy(className = "jenkins-input")
    private WebElement displayNameField;

    @FindBy(className = "setting-input")
    private WebElement descriptionField;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveButton;

    @FindBy(className = "jenkins-icon-adjacent")
    private WebElement buildName;

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement buildDescription;

    @FindBy(xpath = "//span[text()='Back to Project']")
    private WebElement backToProjectButton;

    private String testBuildName;

    public LastBuildPage(WebDriver driver) {
        super(driver);
    }

    public LastBuildPage clickEditBuildInfoButton(){
        editBuildInfoButton.click();

        return this;
    }

    public LastBuildPage enterBuildName(String name){
        displayNameField.sendKeys(name);
        testBuildName = name;

        return this;
    }

    public LastBuildPage enterBuildDescription(String description){
        descriptionField.sendKeys(description);

        return this;
    }

    public LastBuildPage clickSaveButton(){
        saveButton.click();

        return this;
    }

    public ProjectPage clickBackToProjectButton(){
        backToProjectButton.click();

        return new ProjectPage(getDriver());
    }

    public String getBuildName(){

        return buildName.getText().substring("Build ".length(),testBuildName.length()+6);
    }

    public String getBuildDescription(){

        return buildDescription.getText();
    }
}
