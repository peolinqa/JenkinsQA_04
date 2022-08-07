package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class FolderProjectPage extends BaseProjectPage<FolderProjectPage, FolderProjectPageSideMenuFrame> {

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

    public FolderProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderProjectPageSideMenuFrame getSideMenu() {
        return new FolderProjectPageSideMenuFrame(getDriver());
    }

    public String getFolderDescription() {
        if (systemMessage.getText().length() > 0) {
            return folderDescription.getText().substring(systemMessage.getText().length() + "\n".length());
        }

        return folderDescription.getText();
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
}