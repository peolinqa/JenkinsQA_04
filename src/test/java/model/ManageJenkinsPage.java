package model;

import model.base.BaseDashboardPage;
import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class ManageJenkinsPage extends BaseDashboardPage {

    @FindBy(xpath = "//dt[text()='Configure Global Security']")
    private WebElement configureGlobalSecurity;

    @FindBy(xpath = "//dt[text()='Manage Nodes and Clouds']")
    private WebElement manageNodesAndClouds;

    @FindBy(xpath = "//dt[text()='Manage Users']")
    private WebElement manageUsers;

    @FindBy(xpath = "//dt[text()='Load Statistics']")
    private WebElement loadStatistics;

    @FindBy(xpath = "//div[@class='jenkins-section__item']/a[@href='cli']/dl/dt")
    private WebElement jenkinsCLI;

    @FindBy(xpath = "//dt[text()='About Jenkins']")
    private WebElement aboutJenkins;

    @FindBy(xpath = "//dt[text()='Manage Credentials']")
    private WebElement manageCredentials;

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

    public ManageNodesAndCloudsPage clickManageNodesAndClouds() {
        manageNodesAndClouds.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }

    public JenkinsCLIPage clickJenkinsCLI() {
        jenkinsCLI.click();

        return new JenkinsCLIPage(getDriver());
    }

    public ConfigureSystemPage clickConfigureSystem() {
        ProjectUtils.ManageJenkins.ConfigureSystem.click(getDriver());

        return new ConfigureSystemPage(getDriver());
    }

    public AboutJenkinsPage clickAboutJenkins(){
        aboutJenkins.click();

        return new AboutJenkinsPage(getDriver());
    }

    public ManageCredentialsPage clickManageCredentials(){
        ProjectUtils.ManageJenkins.ManageCredentials.click(getDriver());

        return new ManageCredentialsPage(getDriver());
    }

}
