package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MultibranchPipelineConsolePage extends BasePage {

    @FindBy(xpath = "//pre")
    private WebElement consoleText;

    public MultibranchPipelineConsolePage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleText() {
        return consoleText.getText();
    }
}