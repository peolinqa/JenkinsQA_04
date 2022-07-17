package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class JenkinsCLIPage extends BasePage {

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//a")
    private List<WebElement> commandName;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//td[2]")
    private List<WebElement> commandDescription;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr")
    private static List<WebElement> availableCommands;

    public JenkinsCLIPage(WebDriver driver) {
        super(driver);
    }

    public String getCommandName(int i) {
        return commandName.get(i).getText();
    }

    public String getCommandDescription(int i) {
        return commandDescription.get(i).getText();
    }

    public JenkinsCLIExamplesPage clickCommandElement(int i) {
        commandName.get(i).click();

        return new JenkinsCLIExamplesPage(getDriver());
    }

    public int getNumberOfCommands(){
        return availableCommands.size();
    }
}