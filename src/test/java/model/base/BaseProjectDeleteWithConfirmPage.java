package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectDeleteWithConfirmPage<Self extends BaseProjectDeleteWithConfirmPage<?, SideMenu>, SideMenu> extends BaseProjectPage<Self, SideMenu> {

    @FindBy(id = "yui-gen1-button")
    protected WebElement yesButton;

    public BaseProjectDeleteWithConfirmPage(WebDriver driver) {
        super(driver);
    }

    public BaseProjectDeleteWithConfirmPage clickDeleteProject() {
        deleteButton.click();

        return this;
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
