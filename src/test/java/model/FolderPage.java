package model;

import model.base.BaseProjectDeleteWithConfirmPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderPage extends BaseProjectDeleteWithConfirmPage {

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement folderDescription;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    @FindBy(xpath = "//span[normalize-space(.)='Create a job']")
    private WebElement createJob;

    @FindBy(xpath = "//a[contains(@class,'jenkins-table__link model-link')]")
    private WebElement jobName;

    @FindBy(id = "systemmessage")
    private WebElement systemMessage;

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public String getFolderDescription() {
        if (folderDescription.getText().contains(systemMessage.getText())) {
            return folderDescription.getText().substring(systemMessage.getText().length() + "\n".length());
        }

        return folderDescription.getText();
    }

    public RenamePage<FolderPage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new FolderPage(getDriver()));
    }

    public HomePage clickYesButton() {
        yesButton.click();

        return new HomePage(getDriver());
    }

    public NewItemPage<Object> createJobInsideFolder() {
        createJob.click();

        return new NewItemPage<>(getDriver());
    }

    public String getJobName() {
        return jobName.getText();
    }

    public FolderConfigPage clickConfigure(){
        clickConfigureButton();

        return new FolderConfigPage(getDriver());
    }
}