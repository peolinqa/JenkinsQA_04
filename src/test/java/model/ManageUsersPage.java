package model;

import model.base.BaseSideMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Set;

public class ManageUsersPage extends BaseSideMenuPage<ManageUsersPage, ManageUsersPageSideMenuFrame> {

    @FindBy(xpath = "//table[@id='people']/tbody/tr")
    private List<WebElement> allUsersList;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ManageUsersPageSideMenuFrame getSideMenu() {
        return new ManageUsersPageSideMenuFrame(getDriver());
    }

    public UserConfigurePage clickUserConfigure(String userName) {
        getDriver().findElement(By.xpath(String.format("//a[@href='user/%s/configure']", userName.toLowerCase()))).click();

        return new UserConfigurePage(getDriver());
    }

    public ManageUsersPage fillUsersList(Set<String> usersList) {
        allUsersList.stream().map(WebElement::getText).forEach(usersList::add);

        return new ManageUsersPage(getDriver());
    }

    public UserDeletePage clickUserDelete(String userName) {
        getDriver().findElement(By.xpath(String.format("//a[contains(@href, '%s/delete')]", userName))).click();

        return new UserDeletePage(getDriver());
    }
}