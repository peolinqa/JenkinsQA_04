package model.base;

import model.ErrorPage;
import model.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;
import runner.ProjectUtils;
import java.util.List;

public abstract class BasePage extends BaseModel {

    @FindBy(xpath = "//h1[text() = 'Error']")
    private List<WebElement> errorText;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goHome() {
        ProjectUtils.get(getDriver());

        return new HomePage(getDriver());
    }

    public ErrorPage getErrorPageIfPresent() {
        if (errorText.size() > 0) {
            return new ErrorPage(getDriver());
        }

        return null;
    }
}