package model;

import model.base.BaseProjectDeleteWithConfirmPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;

public class OrganizationFolderProjectPage extends BaseProjectDeleteWithConfirmPage<OrganizationFolderProjectPage, OrganizationFolderProjectPageSideMenuFrame> {

    @FindBy(className = "warning")
    private WebElement warningMessage;

    @FindBy(xpath = "//span[text()='Configure the project']")
    private WebElement configureProjectButton;

    @FindBy(css = "#yui-gen1 > span > button[type='submit']")
    private WebElement disableButton;

    @FindBy(css = "#view-message")
    private WebElement textDescription;

    public OrganizationFolderProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderProjectPageSideMenuFrame getSideMenu() {
        return new OrganizationFolderProjectPageSideMenuFrame(getDriver());
    }

    @Override
    public String getProjectName() {
        return projectName.getText().trim();
    }

    public HashMap<String, String> getDisabledProjectWarningMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Warning Message", warningMessage.getText().substring(0, warningMessage.getText().indexOf(" \n")));
        hashMap.put("Message Color", warningMessage.getCssValue("color"));

        return hashMap;
    }

    public OrganizationFolderConfigPage clickConfigureProjectButton() {
        configureProjectButton.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public OrganizationFolderProjectPage clickDisableButton() {
        disableButton.click();

        return this;
    }

    public String getDescriptionText() {
        String[] text = textDescription.getText().split("\n");

        return text[text.length - 1];
    }
}