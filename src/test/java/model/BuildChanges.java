package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildChanges extends BasePage{

    @FindBy(css = "span.jenkins-icon-adjacent")
    private WebElement changesHeader;

    public BuildChanges(WebDriver driver) {
        super(driver);
    }

    public String getChangesPageHeader() {
        return changesHeader.getText();
    }
}
