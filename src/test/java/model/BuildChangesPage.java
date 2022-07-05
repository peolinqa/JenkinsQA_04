package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildChangesPage extends BasePage{

    @FindBy(css = "span.jenkins-icon-adjacent")
    private WebElement changesHeader;

    public BuildChangesPage(WebDriver driver) {
        super(driver);
    }

    public String getChangesPageHeader() {
        return changesHeader.getText();
    }
}
