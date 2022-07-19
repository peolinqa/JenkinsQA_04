package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BaseDashboardPage {

    private final JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(tagName = "h1")
    private List<WebElement> h1;

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

    @FindBy(id = "systemmessage")
    private WebElement systemMessage;

    @FindBy(xpath = "//a[@class='jenkins-table__link model-link inside']")
    private List<WebElement> listAllActualProjectNameHomePage;

    @FindBy(xpath = "//a[@rel='noopener noreferrer']")
    private WebElement linkToJenkinsIO;

    @FindBy (xpath = "//a[@href='/legend']")
    private WebElement linkIconLegend;

    @FindBy(xpath = "//a[contains(text(), 'AchViewName')]")
    private WebElement myViewName;

    @FindBy(xpath = "//a[@class='yuimenuitemlabel']//span[text()='Configure']")
    private WebElement configureFromDropdownMenu;

    private final static String PROJECT_LINK_XPATH = "//a[text()='%s']";
    private final static String PROJECT_ICON_XPATH = "parent::td/parent::tr//img";
    private final static String PROJECT_LINK_ID_XPATH = "//tr[@id='job_%s']";

    @FindBy(xpath = "//table[@id='projectstatus']//tbody//td[3]")
    private List<WebElement> itemsNames;

    @FindBy(id = "search-box")
    private WebElement searchBox;

   public HomePage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage<Object> clickNewItem() {
        newItem.click();

        return new NewItemPage<>(getDriver());
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
        List<String> textFolderNames = TestUtils.getTextFromList(getDriver(),
                By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]"));

        return textFolderNames;
    }

    public List<String> getActualDashboardProject() {
        return listAllActualProjectNameHomePage.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean checkProjectAfterDelete(String projectName) {
        boolean result = false;

        List<WebElement> actual = h1;

        if (actual.size() == 0) {
            for (String webElement : getActualDashboardProject()) {
                if (webElement.contains(projectName)) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        } else {
            for (WebElement element : h1) {
                if (element.getText().contains("Welcome to Jenkins!")) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean checkProjectNameIsPresent(String projectName) {
        return getActualDashboardProject().contains(projectName);
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
        getDriver().findElement(By.xpath(String.format("//li/a[contains(@href, '%s')]", name ))).click();

        return new MyViewPage(getDriver());
    }

    public MyViewPage clickNameOfViewOnTabBar() {
        viewNamesOnTabBar.get(0).click();

        return new MyViewPage(getDriver());
    }

    public MyViewPage clickNameOfViewOnTabBar(String name) {
        getDriver().findElement(By.xpath(String.format("//div/a[contains(text(), '%s')]", name))).click();

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

    public BuildHistoryPage clickAndGoToBuildHistoryPage() {
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

    public boolean isItemPresent(String name) {
      boolean isPresent = false;

      List<WebElement> projectsOnDashboard = itemsNames;
      for (WebElement jobs : projectsOnDashboard) {
        if (jobs.getText().contains(name)) {
          isPresent = true;
        }
      }

      return isPresent;
    }

    public MultiConfigurationProjectPage clickMultiConfigurationProjectName(String name) {
        ProjectUtils.openProject(getDriver(), name);

        return new MultiConfigurationProjectPage(getDriver());
    }

    public MultibranchPipelinePage clickMultibranchPipelineName(String name) {
        ProjectUtils.openProject(getDriver(), name);

        return new MultibranchPipelinePage(getDriver());
    }

    public String getSystemMessageText() {
        return getWait5().until(ExpectedConditions.visibilityOf(systemMessage)).getText();
    }

    public WebElement getProjectLinkByName(String name) {
       return getDriver().findElement(By.xpath(String.format(PROJECT_LINK_XPATH, name)));
    }

    public WebElement getProjectIconByName(String name) {
        return getProjectLinkByName(name).findElement(By.xpath(PROJECT_ICON_XPATH));
    }

    public int getSizeOfProjectLinkByName(String name) {
        return getDriver().findElements(By.xpath(String.format(PROJECT_LINK_ID_XPATH, name))).size();
    }

    public String getJenkinsIOPageTitle(){
        String oldTab = getDriver().getWindowHandle();
        linkToJenkinsIO.click();
        ArrayList<String> newTab = new ArrayList<>(getDriver().getWindowHandles());
        newTab.remove(oldTab);
        String title = getDriver().switchTo().window(newTab.get(0)).getTitle();

        return title;
    }

    public boolean isVisibleIconLegend(){
        return linkIconLegend.isDisplayed();
    }

    public boolean isEnabledIconLegend(){
        return linkIconLegend.isEnabled();
    }

    public LegendPage clickLinkIconLegend(){
        linkIconLegend.click();

        return new LegendPage(getDriver());
    }

    public HomePage projectMenuSelector(String name) {
        for (WebElement s : listAllActualProjectNameHomePage){
           if(s.getText().contains(name)){
               getActions().moveToElement(s).build().perform();
           }
        }
        menuSelector.click();

        return this;
    }

    public HomePage clickDeleteProjectMenuSelector() {
        ProjectUtils.Dashboard.Pipeline.DeletePipeline.click(getDriver());
        getDriver().switchTo().alert().accept();

        return this;
    }

    public MyViewPage clickMyViewNameButton(){
        myViewName.click();

        return new MyViewPage(getDriver());
    }

    public SearchPage sendTextSearchPanel(String text){
       searchBox.clear();
       searchBox.sendKeys(text + Keys.ENTER);

       return new SearchPage(getDriver());
    }

    public MultiConfigurationConfigPage clickConfigureFromDropdownMenuAndGoToMultiConfigurationConfig() {
       configureFromDropdownMenu.click();

       return new MultiConfigurationConfigPage(getDriver());
    }
}
