package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ManageJenkinsPage extends BaseDashboardPage {

    @FindBy(xpath = "//dt[text()='Configure System']")
    private WebElement configureSystem;

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

    @FindBy(xpath = "//dt[text()='Manage Plugins']")
    private WebElement managePlugins;

    @FindBy(xpath = "//a[@href='script']")
    private WebElement scriptConsole;

    @FindBy(xpath = "//section/h2")
    private List<WebElement> manageJenkinsSection;

    @FindBy(xpath = "//h2[text()='Tools and Actions']/ancestor::section//dt")
    private List<WebElement> manageJenkinsContentToolsAndActions;

    @FindBy(xpath = "//h2[text() = 'System Configuration']/ancestor::section//dt")
    private List<WebElement> systemConfigurationContent;

    @FindBy(xpath = "//h2[text() = 'Security']/ancestor::section//dt")
    private List<WebElement> securityContent;

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
        configureSystem.click();

        return new ConfigureSystemPage(getDriver());
    }

    public AboutJenkinsPage clickAboutJenkins() {
        aboutJenkins.click();

        return new AboutJenkinsPage(getDriver());
    }

    public ManageCredentialsPage clickManageCredentials() {
        manageCredentials.click();

        return new ManageCredentialsPage(getDriver());
    }

    public ManagePluginsPage clickManagePlugins() {
        managePlugins.click();

        return new ManagePluginsPage(getDriver());
    }

    public ManageScriptConsolePage clickScriptConsole() {
        scriptConsole.click();

        return new ManageScriptConsolePage(getDriver());
    }

    public List<String> getActualManageJenkinsSectionNames() {
        List<String> textSection = new ArrayList<>();
        for (WebElement list : manageJenkinsSection) {
            textSection.add(list.getText());
        }

        return textSection;
    }

    public List<String> getActualManageJenkinsSectionContentToolsAndActions() {
        List<String> textButton = new ArrayList<>();
        for (WebElement list : manageJenkinsContentToolsAndActions) {
            textButton.add(list.getText());
        }

        return textButton;
    }

    public List<String> getActualSystemConfigurationContent() {
        List<String> textSection = new ArrayList<>();
        for (WebElement elem : systemConfigurationContent) {
            textSection.add(elem.getText());
        }

        return textSection;
    }

    public List<String> getActualSecurityContent() {
        List<String> textSection = new ArrayList<>();
        for (WebElement elem : securityContent) {
            textSection.add(elem.getText());
        }

        return textSection;
    }
}