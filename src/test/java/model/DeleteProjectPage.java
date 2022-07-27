package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteProjectPage extends BaseHeaderFooterPage {

    @FindBy(id = "yui-gen1-button")
    protected WebElement yesButton;

    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
