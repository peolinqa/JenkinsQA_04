package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;

public final class JenkinsCLIPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//a")
    private List<WebElement> listAvailableCommandNames;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr//td[2]")
    private List<WebElement> listCommandsDescription;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr")
    private static List<WebElement> listAvailableCommands;

    @FindBy(xpath = "//th[1]/a")
    WebElement labelName;

    @FindBy(xpath = "//th[2]/a")
    WebElement labelDescription;

    public JenkinsCLIPage(WebDriver driver) {
        super(driver);
    }

    public String getCommandName(int i) {
        return listAvailableCommandNames.get(i).getText();
    }

    public String getCommandDescription(int i) {
        return listCommandsDescription.get(i).getText();
    }

    public JenkinsCLIExamplesPage clickCommandElement(int i) {
        listAvailableCommandNames.get(i).click();

        return new JenkinsCLIExamplesPage(getDriver());
    }

    public JenkinsCLIPage clickLabelName() {
        labelName.click();

        return this;
    }
    public List<String> getTextNamesCommands() {
        return TestUtils.getTextFromListWebElements(listAvailableCommandNames);
    }

    public List<String> getTextCommandsDescription() {
        return TestUtils.getTextFromListWebElements(listCommandsDescription);
    }

    public JenkinsCLIPage clickLabelDescription() {
        labelDescription.click();

        return this;
    }
}
