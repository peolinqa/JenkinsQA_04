package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserStatusPage extends BasePage {

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement userName;

    @FindBy(id = "description-link")
    private WebElement changeDescriptionButton;

    @FindBy(xpath = "//div[@id='description']//textarea")
    private WebElement descriptionTextarea;

    @FindBy(id = "yui-gen1-button")
    private WebElement descriptionSaveButton;

    @FindBy(id="description")
    private WebElement userDescription;

    public UserStatusPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {

        return userName.getText();
    }

    public UserStatusPage clearUserDescription(){
        changeDescriptionButton.click();
        descriptionTextarea.clear();
        descriptionSaveButton.click();

        return this;
    }

    public UserStatusPage addUserDescription(String newUserDescription){
        changeDescriptionButton.click();
        descriptionTextarea.sendKeys(newUserDescription);
        descriptionSaveButton.click();

        return this;
    }

    public String getUserDescription() {
        return userDescription.getText();
    }
}
