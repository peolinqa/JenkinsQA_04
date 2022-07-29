package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;


public class HomePageSideMenuFrame extends BaseModel<HomePageSideMenuFrame> {

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

    public HomePageSideMenuFrame(WebDriver driver) {
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

    public NewViewPage clickNewView() {
        newView.click();

        return new NewViewPage(getDriver());
    }

    public MyViewPage clickMyView() {
        myViews.click();

        return new MyViewPage(getDriver());
    }

    public BuildHistoryPage clickBuildHistory() {
        buildHistory.click();

        return new BuildHistoryPage(getDriver());
    }
}
