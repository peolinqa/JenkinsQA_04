package model.buildsHistory;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class BuildHistoryPageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Console Output")
    private WebElement menuSelectorConsoleOutput;

    public BuildHistoryPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public BuildConsolePage clickMenuSelectorConsoleOutput() {
        menuSelectorConsoleOutput.click();

        return new BuildConsolePage(getDriver());
    }
}
