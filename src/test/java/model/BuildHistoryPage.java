package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BuildHistoryPage extends HeaderFooterPage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td/a[contains(@href, 'job/')][1]")
    private List<WebElement> listBuildHistory;

    @FindBy(xpath = "//table[@id='projectStatus']/tbody")
    private WebElement tableOfProjects;

    @FindBy(linkText = "Changes")
    private WebElement changesButton;

    @FindBy(linkText = "Console Output")
    private WebElement consoleButton;

    public BuildHistoryPage collectListBuildHistoryAndAssert(List<String> namesBuilds) {

        for (int i = 0; i < namesBuilds.size(); i++) {

            Assert.assertEquals(listBuildHistory.get(i).getText(), namesBuilds.get(i));
        }
        return this;
    }

    public boolean checkProjectOnBoard(String projectName) {
        return tableOfProjects.getText().contains(projectName);
    }

    public BuildHistoryPage clickBuildSpanMenu(String projectName, String buildName) {
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//a[@href='/job/" + projectName + "/" + buildName + "/']/button"))
        ).click().perform();

        return this;
    }

    public BuildChanges clickChangesAndGoToBuildPage() {
        changesButton.click();

        return new BuildChanges(getDriver());
    }

    public BuildConsole clickConsoleAndGoToBuildPage() {
        consoleButton.click();

        return new BuildConsole(getDriver());
    }
}
