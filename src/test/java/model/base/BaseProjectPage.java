package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage<Self extends BaseProjectPage<?>> extends BaseHeaderFooterPage<Self> {

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    @FindBy(css = "h1")
    protected WebElement projectName;

    @FindBy(linkText = "Configure")
    private WebElement configureButton;

    @FindBy(css = ".icon-edit-delete")
    protected WebElement deleteButton;

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
