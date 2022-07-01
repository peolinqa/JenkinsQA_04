package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(linkText = "New Item")
    private WebElement newItem;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[text()='Organization Test']")
    private WebElement folderOrganizationTestOnDashboard;

    @FindBy(xpath = "//a[text()='folder1']")
    private WebElement folderFolder1OnDashboard;

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

    public OrganizationFolderProjectPage clickFolderOrganizationTestOnDashboard() {
        folderOrganizationTestOnDashboard.click();

        return new OrganizationFolderProjectPage(getDriver());
    }

    public OrganizationFolderProjectPage clickFolder1OnDashboard() {
        folderFolder1OnDashboard.click();

        return new OrganizationFolderProjectPage(getDriver());
    }

    public WebElement getFolderFolder1OnDashboard() {
        return folderFolder1OnDashboard;
    }

    public List <String> getTextFolderNamesOnDashboard(){
        List <String> textFolderNames = TestUtils.getTextFromList(getDriver(), By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]"));

        return textFolderNames;
    }

    public HomePage clickName(String name) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        return new HomePage(getDriver());
    }
}

