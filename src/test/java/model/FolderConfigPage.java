package model;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderConfigPage extends BasePage {

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(id = "jenkins")
    private WebElement configurePage;

    @FindBy(xpath = "//div[@class='jenkins-config-widgets']//div[text()='General']")
    private WebElement generalTabBar;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
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
