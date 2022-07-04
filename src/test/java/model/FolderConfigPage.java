package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderConfigPage extends BasePage {

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[@name='_.description']")
    private WebElement folderDescription;

    @FindBy(xpath = "//a[@class='textarea-show-preview']")
    private WebElement folderDescriptionPreview;

    @FindBy(xpath = "//div[@class='textarea-preview']")
    private WebElement folderDescriptionPreviewText;

    @FindBy(id = "jenkins")
    private WebElement configurePage;

    @FindBy(xpath = "//div[@class='jenkins-config-widgets']//div[text()='General']")
    private WebElement generalTabBar;

    public FolderConfigPage(WebDriver driver) {

        super(driver);
    }

    public FolderConfigPage setFolderDescription (String text) {
        folderDescription.sendKeys(text);

        return this;
    }

    public FolderConfigPage clickFolderDescriptionPreview() {
        folderDescriptionPreview.click();

        return new FolderConfigPage(getDriver());
    }

    public String getFolderDescriptionPreviewText() {

        return folderDescriptionPreviewText.getText();
    }

    public FolderPage saveConfigAndGoToFolderPage() {
        saveButton.click();

        return new FolderPage(getDriver());
    }

    public FolderConfigPage waitLoadingFolderConfigurePage(){
        getWait5().until(ExpectedConditions.visibilityOf(configurePage));

        return this;
    }

    public String getGeneralTabName(){

        return generalTabBar.getText();
    }
}