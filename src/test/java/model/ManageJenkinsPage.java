package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage extends HeaderFooterPage {

    @FindBy(xpath = "//dt[text()='Configure Global Security']")
    private WebElement configureGlobalSecurity;

    @FindBy(xpath = "//dt[text()='Manage Nodes and Clouds']")
    private WebElement manageNodesAndClouds;

    @FindBy(xpath = "//dt[text()='Manage Users']")
    private WebElement manageUsers;

    @FindBy(xpath = "//dt[text()='Load Statistics']")
    private WebElement loadStatistics;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public ConfigureGlobalSecurityPage clickConfigureGlobalSecurity() {
        configureGlobalSecurity.click();

        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public ManageUsersPage clickManageUsers() {
        manageUsers.click();

        return new ManageUsersPage(getDriver());
    }

    public LoadStatisticsPage clickLoadStatistics() {
        loadStatistics.click();

        return new LoadStatisticsPage(getDriver());
    }
}
