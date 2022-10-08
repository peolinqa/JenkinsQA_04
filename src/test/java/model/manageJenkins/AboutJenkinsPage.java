package model.manageJenkins;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public final class AboutJenkinsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[text()='AntLR Parser Generator']")
    private WebElement linkAntLrParser;

    @FindBy(linkText = "License and dependency information for plugins")
    private WebElement tabLicenseAndDependency;

    @FindBy(linkText = "Static resources")
    private WebElement tabStaticResources;

    @FindBy(linkText = "Mavenized dependencies")
    private WebElement tabMavenizedDependencies;

    public AboutJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public int countLinksInTab(String tab) {
        List<WebElement> columnName = getDriver().findElements(
                By.xpath(String.format("//h2[text()='%s']//parent::div//tbody/tr", tab)));

        return columnName.size();
    }

    public AboutJenkinsPage clickLinkAntLRParserGenerator() {
        linkAntLrParser.click();

        return this;
    }

    public AboutJenkinsPage clickTabLicenseAndDependency() {
        tabLicenseAndDependency.click();

        return this;
    }

    public AboutJenkinsPage clickTabStaticResources() {
        tabStaticResources.click();

        return this;
    }

    public AboutJenkinsPage clickTabMavenizedDependencies() {
        tabMavenizedDependencies.click();

        return this;
    }
}
