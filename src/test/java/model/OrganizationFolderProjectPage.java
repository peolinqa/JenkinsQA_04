package model;

import model.base.BaseProjectDeleteWithConfirmPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;

public class OrganizationFolderProjectPage extends BaseProjectDeleteWithConfirmPage {

    @FindBy(className = "warning")
    private WebElement warningMessage;

    @FindBy(xpath = "//span[text()='Configure the project']")
    private WebElement configureProjectButton;

    public OrganizationFolderProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getProjectName() {
        return projectName.getText().trim();
    }

    public RenamePage<OrganizationFolderProjectPage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new OrganizationFolderProjectPage(getDriver()));
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
}