package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;


public class HomePageSideMenuFrame extends BaseModel<HomePageSideMenuFrame> {

    @FindBy(linkText = "New Item")
    private WebElement menuNewItem;

    @FindBy(linkText = "People")
    private WebElement menuPeople;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement menuManageJenkins;

    @FindBy(linkText = "New View")
    private WebElement menuNewView;

    @FindBy(linkText = "My Views")
    private WebElement menuMyViews;

    @FindBy(linkText = "Build History")
    private WebElement menuBuildHistory;

    public HomePageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public NewItemPage<Object> clickMenuNewItem() {
        menuNewItem.click();

        return new NewItemPage<>(getDriver());
    }

    public PeoplePage clickMenuPeople() {
        menuPeople.click();

        return new PeoplePage(getDriver());
    }

    public ManageJenkinsPage clickMenuManageJenkins() {
        menuManageJenkins.click();

        return new ManageJenkinsPage(getDriver());
    }

    public NewViewPage clickMenuNewView() {
        menuNewView.click();

        return new NewViewPage(getDriver());
    }

    public MyViewPage clickMenuMyView() {
        menuMyViews.click();

        return new MyViewPage(getDriver());
    }

    public BuildHistoryPage clickMenuBuildHistory() {
        menuBuildHistory.click();

        return new BuildHistoryPage(getDriver());
    }
}
