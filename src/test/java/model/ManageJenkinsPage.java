package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public final class ManageJenkinsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//dt[text()='Configure System']")
    private WebElement linkConfigureSystem;

    @FindBy(xpath = "//dt[text()='Configure Global Security']")
    private WebElement linkConfigureGlobalSecurity;

    @FindBy(xpath = "//dt[text()='Manage Nodes and Clouds']")
    private WebElement linkManageNodesAndClouds;

    @FindBy(xpath = "//dt[text()='Manage Users']")
    private WebElement linkManageUsers;

    @FindBy(xpath = "//dt[text()='Load Statistics']")
    private WebElement linkLoadStatistics;

    @FindBy(xpath = "//div[@class='jenkins-section__item']/a[@href='cli']/dl/dt")
    private WebElement linkJenkinsCLI;

    @FindBy(xpath = "//dt[text()='About Jenkins']")
    private WebElement linkAboutJenkins;

    @FindBy(xpath = "//dt[text()='Manage Credentials']")
    private WebElement linkManageCredentials;

    @FindBy(xpath = "//dt[text()='Manage Plugins']")
    private WebElement linkManagePlugins;

    @FindBy(xpath = "//a[@href='script']")
    private WebElement linkScriptConsole;

    @FindBy(xpath = "//section/h2")
    private List<WebElement> listManageJenkinsSections;

    @FindBy(xpath = "//h2[text()='Tools and Actions']/ancestor::section//dt")
    private List<WebElement> listToolsAndActionsContent;

    @FindBy(xpath = "//h2[text() = 'System Configuration']/ancestor::section//dt")
    private List<WebElement> listSystemConfigurationContent;

    @FindBy(xpath = "//h2[text() = 'Security']/ancestor::section//dt")
    private List<WebElement> listSecurityContent;

    @FindBy(xpath = "//h2[text() = 'System Configuration']/ancestor::section//dd")
    private List<WebElement> listSystemConfigurationCaptions;

    @FindBy(xpath = "//h2[text() = 'Security']/ancestor::section//dd")
    private List<WebElement> listSecurityCaptions;

    @FindBy(xpath = "//h2[text() = 'Status Information']/ancestor::section//dd")
    private List<WebElement> listStatusInformationCaptions;

    @FindBy(xpath = "//h2[text() = 'Troubleshooting']/ancestor::section//dd")
    private List<WebElement> listTroubleshootingCaptions;

    @FindBy(xpath = "//h2[text() = 'Tools and Actions']/ancestor::section//dd")
    private List<WebElement> listToolsAnsActionsCaptions;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public ConfigureGlobalSecurityPage clickConfigureGlobalSecurity() {
        linkConfigureGlobalSecurity.click();

        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public ManageUsersPage clickManageUsers() {
        linkManageUsers.click();

        return new ManageUsersPage(getDriver());
    }

    public LoadStatisticsPage clickLoadStatistics() {
        linkLoadStatistics.click();

        return new LoadStatisticsPage(getDriver());
    }

    public ManageNodesAndCloudsPage clickManageNodesAndClouds() {
        linkManageNodesAndClouds.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }

    public JenkinsCLIPage clickJenkinsCLI() {
        linkJenkinsCLI.click();

        return new JenkinsCLIPage(getDriver());
    }

    public ConfigureSystemPage clickConfigureSystem() {
        linkConfigureSystem.click();

        return new ConfigureSystemPage(getDriver());
    }

    public AboutJenkinsPage clickAboutJenkins() {
        linkAboutJenkins.click();

        return new AboutJenkinsPage(getDriver());
    }

    public ManageCredentialsPage clickManageCredentials() {
        linkManageCredentials.click();

        return new ManageCredentialsPage(getDriver());
    }

    public ManagePluginsPage clickManagePlugins() {
        linkManagePlugins.click();

        return new ManagePluginsPage(getDriver());
    }

    public ManageScriptConsolePage clickScriptConsole() {
        linkScriptConsole.click();

        return new ManageScriptConsolePage(getDriver());
    }

    public List<String> getActualManageJenkinsSectionNames() {
        List<String> textSection = new ArrayList<>();
        for (WebElement list : listManageJenkinsSections) {
            textSection.add(list.getText());
        }

        return textSection;
    }

    public List<String> getToolsAndActionsContentList() {
        List<String> textButton = new ArrayList<>();
        for (WebElement list : listToolsAndActionsContent) {
            textButton.add(list.getText());
        }

        return textButton;
    }

    public List<String> getActualSystemConfigurationContentList() {
        List<String> textSection = new ArrayList<>();
        for (WebElement elem : listSystemConfigurationContent) {
            textSection.add(elem.getText());
        }

        return textSection;
    }

    public List<String> getActualSecurityContentList() {
        List<String> textSection = new ArrayList<>();
        for (WebElement elem : listSecurityContent) {
            textSection.add(elem.getText());
        }

        return textSection;
    }

    public List<String> getSystemConfigurationCaptionList() {
        List<String> captionsArray = new ArrayList<>();
        for (WebElement el : listSystemConfigurationCaptions)
            if (!el.getText().isEmpty()) {
                captionsArray.add(el.getText());
            }

        return captionsArray;
    }

    public List<String> getSecurityCaptionsList() {
        List<String> captionsArray = new ArrayList<>();
        for (WebElement el : listSecurityCaptions) {
            if (!el.getText().isEmpty()) {
                captionsArray.add(el.getText());
            }
        }

        return captionsArray;
    }

    public List<String> getStatusInfoCaptionsList() {
        List<String> captionsArray = new ArrayList<>();
        for (WebElement el : listStatusInformationCaptions) {
            if (!el.getText().isEmpty()) {
                captionsArray.add(el.getText());
            }
        }

        return captionsArray;
    }

    public List<String> getTroubleshootingCaptionsList() {
        List<String> captionsArray = new ArrayList<>();
        for (WebElement el : listTroubleshootingCaptions) {
            if (!el.getText().isEmpty()) {
                captionsArray.add(el.getText());
            }
        }

        return captionsArray;
    }

    public List<String> getToolsAnsActionsCaptionsList() {
        List<String> captionsArray = new ArrayList<>();
        for (WebElement el : listToolsAnsActionsCaptions) {
            if (!el.getText().isEmpty()) {
                captionsArray.add(el.getText());
            }
        }

        return captionsArray;
    }
}
