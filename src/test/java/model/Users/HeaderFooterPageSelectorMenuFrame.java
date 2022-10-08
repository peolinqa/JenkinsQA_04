package model.Users;

import model.Users.UserBuildsPage;
import model.Users.UserConfigurePage;
import model.Views.MyViewPage;
import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HeaderFooterPageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Builds")
    private WebElement menuSelectorBuilds;

    @FindBy(linkText = "Configure")
    private WebElement menuSelectorConfigure;

    @FindBy(linkText = "My Views")
    private WebElement menuSelectorMyView;

    @FindBy(xpath = "//ul[@class='first-of-type']/li")
    private List<WebElement> listDashboardDropdownMenuElements;

    public HeaderFooterPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserBuildsPage clickMenuSelectorBuilds() {
        menuSelectorBuilds.click();

        return new UserBuildsPage(getDriver());
    }

    public UserConfigurePage clickMenuSelectorUserConfigure() {
        menuSelectorConfigure.click();

        return new UserConfigurePage(getDriver());
    }

    public MyViewPage clickMenuSelectorMyView() {
        menuSelectorMyView.click();

        return new MyViewPage(getDriver());
    }

    public List<String> getListOfDashboardDropdownMenuElements() {
        return listDashboardDropdownMenuElements
                .stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }
}