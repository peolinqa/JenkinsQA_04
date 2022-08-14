package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class FolderProjectPage extends BaseProjectPage<FolderProjectPage, FolderProjectPageSideMenuFrame> {

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement viewMessage;

    @FindBy(xpath = "//span[normalize-space(.)='Create a job']")
    private WebElement linkCreateJob;

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

    public String getViewMessageText() {
        if (systemMessage.getText().length() > 0) {
            return viewMessage.getText().substring(systemMessage.getText().length() + "\n".length());
        }

        return viewMessage.getText();
    }

    public NewItemPage<Object> createJobInsideFolder() {
        linkCreateJob.click();

        return new NewItemPage<>(getDriver());
    }

    public String getJobNameText() {
        return jobName.getText();
    }
}
