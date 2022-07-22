package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage extends BaseDashboardPage{

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    @FindBy(css = "h1")
    private WebElement projectName;

    @FindBy(linkText = "Configure")
    private WebElement configureButton;

    public void clickRenameButton() {
        renameButton.click();
    }

    public void clickConfigureButton() {
        configureButton.click();
    }

    public String getProjectName() {
        return projectName.getText();
    }
}
