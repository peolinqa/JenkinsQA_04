package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProjectPage extends BasePage {

    @FindBy(css = "h1.page-headline")
    private WebElement projectName;

    @FindBy(css = "h1")
    private WebElement folderName;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }

    public String getFolderName() {
        return folderName.getText();
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();
        return new HomePage(getDriver());
    }

    public void assertProjectStatus(String value) {
        Assert.assertTrue(getWait20().until(ExpectedConditions.attributeToBe(
                By.cssSelector(".tobsTable-body .job"), "class", value)));
    }
}
