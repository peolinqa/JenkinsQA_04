package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage<Self extends BaseProjectPage<?, SideMenu>, SideMenu> extends BaseSideMenuPage<Self, SideMenu> {

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    protected WebElement projectName;

    @FindBy(linkText = "Configure")
    private WebElement configureButton;

    @FindBy(css = ".icon-edit-delete")
    protected WebElement deleteButton;

    public void clickConfigureButton() {
        configureButton.click();
    }

    //todo: переименовать под общее название!
    public String getProjectName() {
        return projectName.getText();
    }
}
