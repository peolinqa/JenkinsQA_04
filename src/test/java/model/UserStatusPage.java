package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserStatusPage extends BasePage {

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement userName;

    @FindBy(id = "description-link")
    private WebElement editDescriptionButton;

    @FindBy(name = "description")
    private WebElement descriptionTextArea;

    @FindBy(id = "yui-gen1-button")
    private WebElement descriptionSaveButton;

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement userDescription;

    @FindBy(xpath = "//div[@id='main-panel']/div[2]")
    private WebElement jenkinsUserID;

    public UserStatusPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return userName.getText();
    }

    public UserStatusPage clickEditDescriptionButton(){
        editDescriptionButton.click();

        return this;
    }

    public UserStatusPage clearDescriptionTextArea() {
        descriptionTextArea.clear();

        return this;
    }

    public UserStatusPage clickDescriptionSaveButton() {
        descriptionSaveButton.click();

        return this;
    }

    public UserStatusPage addUserDescription(String newUserDescription){
        descriptionTextArea.sendKeys(newUserDescription);

        return this;
    }

    public String getUserDescriptionText() {
        return userDescription.getText();
    }

    public String getJenkinsUserIDLine() {
        return jenkinsUserID.getText();
    }
}