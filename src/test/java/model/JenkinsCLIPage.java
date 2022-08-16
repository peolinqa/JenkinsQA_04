package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;

public final class JenkinsCLIPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//a")
    private List<WebElement> commandNames;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//td[2]")
    private List<WebElement> commandDescription;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr")
    private static List<WebElement> availableCommands;

    @FindBy(xpath = "//th[1]/a")
    WebElement nameCaption;

    @FindBy(xpath = "//th[2]/a")
    WebElement descriptionCaption;

    public JenkinsCLIPage(WebDriver driver) {
        super(driver);
    }

    public String getCommandName(int i) {
        return commandNames.get(i).getText();
    }

    public String getCommandDescription(int i) {
        return commandDescription.get(i).getText();
    }

    public JenkinsCLIExamplesPage clickCommandElement(int i) {
        commandNames.get(i).click();

        return new JenkinsCLIExamplesPage(getDriver());
    }

     public JenkinsCLIPage clickNameCaption(){
        nameCaption.click();

        return this;
    }
    public List<String> getTextNamesCommands() {
        return TestUtils.getTextFromListWebElements(commandNames);
    }

    public List<String> getTextCommandsDescription() {
        return TestUtils.getTextFromListWebElements(commandDescription);
    }

    public JenkinsCLIPage clickDescriptionCaption(){
        descriptionCaption.click();

        return this;
    }
}