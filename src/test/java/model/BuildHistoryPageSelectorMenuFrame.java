package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildHistoryPageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Console Output")
    private WebElement menuConsoleOutput;

    public BuildHistoryPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public BuildConsolePage clickMenuDropDownConsole() {
        menuConsoleOutput.click();

        return new BuildConsolePage(getDriver());
    }
}
