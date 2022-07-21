package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderPage extends BaseDashboardPage {

    @FindBy(linkText = "Rename")
    private WebElement renameFolder;

    @FindBy(css = "h1")
    private WebElement folderName;

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement folderDescription;

    @FindBy(xpath = "//span[text()='Delete Folder']")
    private WebElement deleteFolder;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    @FindBy(xpath = "//span[text()='Configure']")
    private WebElement clickConfigure;

    @FindBy(xpath = "//span[normalize-space(.)='Create a job']")
    private WebElement createJob;

    @FindBy(xpath = "//a[contains(@class,'jenkins-table__link model-link')]")
    private WebElement jobName;

    @FindBy(id = "systemmessage")
    private WebElement systemMessage;

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public String getFolderName() {
        return folderName.getText();
    }

    public String getFolderDescription() {
        if (folderDescription.getText().contains("\n")) {
            return folderDescription.getText().substring(systemMessage.getText().length() + "\n".length());
        }

        return folderDescription.getText();
    }

    public RenamePage clickRenameFolder() {
        renameFolder.click();

        return new RenamePage(getDriver());
    }

    public FolderPage clickDeleteFolder() {
        deleteFolder.click();

        return this;
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
        clickConfigure.click();
        return new FolderConfigPage(getDriver());
    }
}