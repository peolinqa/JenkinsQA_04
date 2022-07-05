package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;
import java.util.stream.Collectors;
import runner.ProjectUtils;

public class HomePage extends HeaderFooterPage {

    private final JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(linkText = "New Item")
    private WebElement newItem;

    @FindBy(linkText = "People")
    private WebElement people;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(linkText = "New View")
    private WebElement newView;

    @FindBy(linkText = "My Views")
    private WebElement myViews;

    @FindBy(linkText = "Build History")
    private WebElement buildHistory;

    @FindBy(xpath = "//td[@class='jenkins-table__cell--tight']")
    private List<WebElement> listBuildButtons;

    @FindBy(xpath = "//td/a[contains(@href, 'job/')]")
    private List<WebElement> listJobNameButtons;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='children']")
    private WebElement triangleOnBreadcrumbs;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='item']")
    private List<WebElement> viewNamesOnBreadcrumbs;

    @FindBy(css = "div .tab a")
    private List<WebElement> viewNamesOnTabBar;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchForm;

    @FindBy(xpath = "//a[@href='/toggleCollapse?paneId=buildQueue']")
    private WebElement buildQueueToggleButton;

    @FindBy(xpath = "//a[@href='/toggleCollapse?paneId=executors']")
    private WebElement buildExecutorToggleButton;

    @FindBy(xpath = "//div[@id='buildQueue']//table")
    private List<WebElement> elementsBuildsInQueue;

    @FindBy(xpath = "//div[@id='executors']//table")
    private List<WebElement> elementsBuildExecutorStatus;

   public HomePage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage clickNewItem() {
        newItem.click();

        return new NewItemPage(getDriver());
    }

    public PeoplePage clickPeople() {
        people.click();

        return new PeoplePage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkins() {
        manageJenkins.click();

        return new ManageJenkinsPage(getDriver());
    }

    public OrganizationFolderProjectPage clickOrganizationFolderName(String name) {
        ProjectUtils.openProject(getDriver(), name);
        return new OrganizationFolderProjectPage(getDriver());
    }

    public List<String> getTextFolderNamesOnDashboard() {
        List<String> textFolderNames = TestUtils.getTextFromList(getDriver(), By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]"));

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

    public FreestylePage clickFreestyleName(String name) {
        ProjectUtils.openProject(getDriver(), name);

        return new FreestylePage(getDriver());
    }

    public HomePage buildSelectPipeline(String pipelineName, boolean isDoubleClick) {
        for (WebElement el : listBuildButtons) {
            if (el.getText().contains(pipelineName) && isDoubleClick) {
                getActions().moveToElement(el)
                .doubleClick()
                .perform();
            } else if (el.getText().contains(pipelineName) && !isDoubleClick) {
                el.click();
            }
        }
        return this;
    }

    public HomePage clickRefreshPage() {
        getDriver().navigate().refresh();
        return this;
    }

    public NewViewPage clickNewView() {
        newView.click();

        return new NewViewPage(getDriver());
    }

    public MyViewPage clickNameOfViewOnBreadcrumbs(String name) {
        triangleOnBreadcrumbs.click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + name + "')]")).click();

        return new MyViewPage(getDriver());
    }

    public MyViewPage clickNameOfViewOnTabBar() {
        viewNamesOnTabBar.get(0).click();

        return new MyViewPage(getDriver());
    }

    public MyViewPage clickNameOfViewOnTabBar(String name) {
       getDriver().findElement(By.xpath("//div/a[contains(text(), '" + name + "')]")).click();

        return new MyViewPage(getDriver());
    }

    public List<String> getNamesOfViewsOnBreadcrumbs() {

        return viewNamesOnBreadcrumbs.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public DeletePipelineProjectPage navigateToPreviousCreatedPipeline(String projectName) {
        List<WebElement> createdJobFromListJobs = ProjectUtils.selectSpecificJobFromListOfJobs(getDriver(), projectName);
        getDriver().navigate().to(createdJobFromListJobs.get(0).getAttribute("href"));

        return new DeletePipelineProjectPage(getDriver());
    }

    public MyViewPage clickMyView() {
        myViews.click();

        return new MyViewPage(getDriver());
    }

    public BuildHistoryPage clickAndGoToBuildHistoryPage () {
        js.executeScript("arguments[0].scrollIntoView();", newItem);
        buildHistory.click();

        return new BuildHistoryPage(getDriver());
    }

    public BuildHistoryPage clickBuildHistory() {
        buildHistory.click();

        return new BuildHistoryPage(getDriver());
    }

    public SearchPage searchText(String text) {
        searchForm.sendKeys(text, Keys.ENTER);

        return new SearchPage(getDriver());
    }

    public HomePage clickBuildQueueToggleButton() {
        buildQueueToggleButton.click();

        return this;
    }

    public HomePage clickBuildExecutorToggleButton() {
        buildExecutorToggleButton.click();

        return this;
    }

    public String getTitleBuildQueueToggleButton() {
        return buildQueueToggleButton.getAttribute("title");
    }

    public String getTitleBuildExecutorToggleButton() {
        return buildExecutorToggleButton.getAttribute("title");
    }

    public int getSizeOfListForElementsBuildsInQueue() {
        return elementsBuildsInQueue.size();
    }

    public int getSizeOfListForElementsBuildExecutorStatus() {
        return elementsBuildExecutorStatus.size();
    }
}
