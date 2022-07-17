package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;

public class OrganizationFolderProjectPage extends BaseDashboardPage {

    @FindBy(xpath = "//h1")
    private WebElement projectName;

    @FindBy(xpath = "//span[contains(text(),'Rename')]")
    private WebElement renameButton;

    @FindBy(xpath = "//span[contains(text(),'Delete Organization Folder')]")
    private WebElement deleteButton;

    @FindBy(className = "warning")
    private WebElement warningMessage;

    public OrganizationFolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return projectName.getText().trim();
    }

    public RenamePage renameOrganizationFolder() {
        renameButton.click();

        return new RenamePage(getDriver());
    }

    public DeleteOrganizationFolderPage deleteOrganizationFolder() {
        deleteButton.click();

        return new DeleteOrganizationFolderPage(getDriver());
    }

    public HashMap<String, String> getDisabledProjectWarningMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Warning Message", warningMessage.getText().substring(0, warningMessage.getText().indexOf(" \n")));
        hashMap.put("Message Color", warningMessage.getCssValue("color"));

        return hashMap;
    }
}