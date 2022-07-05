package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildConsolePage extends BasePage{

    @FindBy(css = "span.jenkins-icon-adjacent")
    private WebElement consoleHeader;

    public BuildConsolePage(WebDriver driver) {
        super(driver);
    }

    public String getConsolePageHeader() {
        return consoleHeader.getText();
    }
}
