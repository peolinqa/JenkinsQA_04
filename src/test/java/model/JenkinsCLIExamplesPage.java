package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsCLIExamplesPage extends BaseHeaderFooterPage {

    @FindBy(id = "example")
    private WebElement commandExample;

    public JenkinsCLIExamplesPage(WebDriver driver) {
        super(driver);
    }

    public boolean getCommandExample(String commandName) {
        String text = commandExample.getText();

        return text.contains("-webSocket " + commandName);
    }
}