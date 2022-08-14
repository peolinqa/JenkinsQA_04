package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class BuildHistoryPage extends BaseBuildPage<BuildHistoryPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    @FindBy(xpath = "//table[@id='projectStatus']/tbody")
    private WebElement tableOfProjects;

    @FindBy(css = "tr:nth-child(1) td a:nth-child(2)")
    private WebElement linkBuildLastCreated;

    @FindBy(id = "menuSelector")
    private WebElement dropDownMenuSelector;

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
    }

    public List<String> collectListBuildHistory(String name) {
        return getDriver()
                .findElements(By.xpath(String.format("//table[@id='projectStatus']/tbody//td[2]//a[@href='/job/%s/']", name)))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isProjectOnBoardBuildHistory(String projectName) {
        return tableOfProjects.getText().contains(projectName);
    }

    public boolean isBuildOnBoardBuildHistory(String buildName) {
        return tableOfProjects.getText().contains(buildName);
    }

    public BuildHistoryPageSelectorMenuFrame clickBuildDropDownMenu(String projectName, String buildName) {
        getActions().moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[@href='/job/%s/%s/']", projectName, buildName)))).perform();
        dropDownMenuSelector.click();

        return new BuildHistoryPageSelectorMenuFrame(getDriver());
    }

    public FreestyleBuildPage clickLinkBuildLastCreated() {
        linkBuildLastCreated.click();

        return new FreestyleBuildPage(getDriver());
    }
}
