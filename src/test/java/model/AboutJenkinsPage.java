package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AboutJenkinsPage extends BasePage {

    @FindBy(xpath = "//a[text()='AntLR Parser Generator']")
    private WebElement antlr;

    @FindBy(linkText = "License and dependency information for plugins")
    private WebElement license;

    @FindBy(linkText = "Static resources")
    private WebElement staticResources;

    public AboutJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public int countLinksInTab(String text) {
        List<WebElement> columnName = getDriver().findElements(
                By.xpath(String.format("//h2[text()='%s']//parent::div//tbody/tr", text)));

        return columnName.size();
    }

    public AboutJenkinsPage clickLinkAntLRParserGenerator() {
        antlr.click();

        return this;
    }

    public AboutJenkinsPage clickLicenseAndDependencyInformationForPlugins() {
        license.click();

        return this;
    }

    public AboutJenkinsPage clickStaticResources() {
        staticResources.click();

        return this;
    }

}
