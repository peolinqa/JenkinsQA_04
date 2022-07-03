package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

public class HomePage extends BasePage {

    @FindBy(linkText = "New Item")
    private WebElement newItem;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(id = "jenkins-head-icon")
    private WebElement headerIcon;

    @FindBy(id = "header")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[text()='folder1']")
    private WebElement folderFolder1OnDashboard;

    @FindBy(xpath = "//td[@class='jenkins-table__cell--tight']")
    private List<WebElement> listBuildButtons;

    @FindBy(xpath = "//td/a[contains(@href, 'job/')]")
    private List<WebElement> listJobNameButtons;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage clickNewItem() {
        newItem.click();

        return new NewItemPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkins() {
        manageJenkins.click();

        return new ManageJenkinsPage(getDriver());
    }

    public String getLogoIconTagName() {
        return headerIcon.getTagName();
    }

    public String getLogoIconAttribute(String attribute) {
        return headerIcon.getAttribute(attribute);
    }

    public WebElement getHeaderIcon() {
        return headerIcon;
    }

    public String getPageHeaderLocation() {
      return pageHeader.getLocation().toString();
    }

    public WebElement getPageHeader() {
        return pageHeader;
    }

    public String getPageHeaderCssValue(String value) {
        return pageHeader.getCssValue(value);
    }

    public OrganizationFolderProjectPage clickOrganizationFolderName(String name) {
        ProjectUtils.openProject(getDriver(), name);
        return new OrganizationFolderProjectPage(getDriver());
    }

    public List <String> getTextFolderNamesOnDashboard(){
        List <String> textFolderNames = TestUtils.getTextFromList(getDriver(), By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]"));

        return textFolderNames;
    }

    public FolderPage clickFolderName(String name) {
        ProjectUtils.openProject(getDriver(), name);

        return new FolderPage(getDriver());
    }

    public ProjectPage clickProjectName(String name) {
        ProjectUtils.openProject(getDriver(), name);

        return new ProjectPage(getDriver());
    }

    public HomePage buildSelectPipeline(String pipelineName) {
        System.out.println(listBuildButtons);
        System.out.println(listJobNameButtons);
        for (WebElement el : listBuildButtons) {
            if (el.getText().contains(pipelineName)) {
                el.click();
            }
        }
        return this;
    }

    public HomePage clickRefreshPage() {
        getDriver().navigate().refresh();
        return this;
    }
}
