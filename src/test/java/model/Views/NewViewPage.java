package model.Views;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class NewViewPage extends BaseHeaderFooterPage {

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(xpath = "//label[text() = 'List View']")
    private WebElement listView;

    @FindBy(xpath = "//label[text() = 'My View']")
    private WebElement myView;

    @FindBy(id = "ok")
    private WebElement createButton;

    public NewViewPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage setViewName(String text) {
        nameText.sendKeys(text);

        return new NewViewPage(getDriver());
    }

    public NewViewPage selectListViewType() {
        listView.click();

        return new NewViewPage(getDriver());
    }

    public NewViewPage selectMyViewType() {
        myView.click();

        return new NewViewPage(getDriver());
    }

    public ViewConfigPage createViewAndGoConfig() {
        createButton.click();

        return new ViewConfigPage(getDriver());
    }

    public MyViewPage createViewAndGoToView() {
        createButton.click();

        return new MyViewPage(getDriver());
    }

    public String getErrorText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("error"))).getText();
    }
}