package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;

public class OrganizationFolderProjectPage extends BaseProjectPage<OrganizationFolderProjectPage, OrganizationFolderProjectPageSideMenuFrame> {

    @FindBy(id = "enable-project")
    private WebElement textWarningDisable;

    @FindBy(xpath = "//span[text()='Configure the project']")
    private WebElement linkConfigureTheProject;

    @FindBy(css = "#disable-project button")
    private WebElement btnDisableProject;

    @FindBy(css = "#view-message")
    private WebElement viewSystemMessage;

    public OrganizationFolderProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderProjectPageSideMenuFrame getSideMenu() {
        return new OrganizationFolderProjectPageSideMenuFrame(getDriver());
    }

    @Override
    public String getProjectNameText() {
        return super.getProjectNameText().trim();
    }

    public HashMap<String, String> getWarningDisableText() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Warning Message", textWarningDisable.getText().substring(0, textWarningDisable.getText().indexOf(" \n")));
        hashMap.put("Message Color", textWarningDisable.getCssValue("color"));

        return hashMap;
    }

    public OrganizationFolderConfigPage clickLinkConfigureTheProject() {
        linkConfigureTheProject.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public OrganizationFolderProjectPage clickDisableButton() {
        btnDisableProject.click();

        return this;
    }

    public String getSystemMessageText() {
        String[] text = viewSystemMessage.getText().split("\n");

        return text[text.length - 1];
    }
}
