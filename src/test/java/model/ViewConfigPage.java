package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.List;

public class ViewConfigPage extends BasePage {

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(xpath = "//textarea[contains(@name, 'description')]")
    private WebElement descriptionField;

    @FindBy(id = "yui-gen13-button")
    private WebElement saveConfigViewButton;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveConfigViewButton1;

    @FindBy(xpath = "//button[contains(text(), 'Add column')]")
    private WebElement addColumnButton;

    @FindBy(xpath = "//button[contains(text(), 'Apply')]")
    private WebElement applyButton;

    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    private WebElement okButton;

    @FindBy(xpath = "//div[@descriptorid]//b")
    private List<WebElement> listExistingColumns;

    @FindBy(css = "#yui-gen4  li a")
    private List<WebElement> listColumnsWhichCanAdd;

    public ViewConfigPage(WebDriver driver) {
        super(driver);
    }

    private final JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public ViewConfigPage setName(String text) {
        nameText.clear();
        nameText.sendKeys(text);

        return new ViewConfigPage(getDriver());
    }

    public ViewConfigPage setDescription(String text) {
        descriptionField.sendKeys(text);

        return new ViewConfigPage(getDriver());
    }

    public MyViewPage saveConfigAndGoToView() {
        saveConfigViewButton.click();

        return new MyViewPage(getDriver());
    }

    public MyViewPage saveConfigAndGoToView1() {
        saveConfigViewButton1.click();

        return new MyViewPage(getDriver());
    }

    public ViewConfigPage scrollPageDown() {
        js.executeScript("window.scrollBy(0, 500)");

        return new ViewConfigPage(getDriver());
    }

    public ViewConfigPage scrollPageToAddColumnButton() {
        TestUtils.scrollToElement(getDriver(), addColumnButton);

        return new ViewConfigPage(getDriver());
    }

    public ViewConfigPage scrollAndClickJob() {
        getActions().moveToElement(getDriver().findElement(
                    By.xpath("//input[@name= 'AchJobName']")))
                .click()
                .perform();

        return new ViewConfigPage(getDriver());
    }

    public ViewConfigPage addAllUniqueColumns() {
        scrollPageToAddColumnButton();
        List<String> existingColumnsNames = TestUtils.getTextFromListWebElements(listExistingColumns);
        scrollPageToAddColumnButton();

        addColumnButton.click();
        List<String> columnsCanAddNames = TestUtils.getTextFromListWebElements(listColumnsWhichCanAdd);
        columnsCanAddNames.removeAll(existingColumnsNames);

        for (String columnsCanAddName : columnsCanAddNames) {
            getDriver().findElement(
                    By.xpath(String.format("//a[contains(text(), '%s')]", columnsCanAddName))).click();
            scrollPageToAddColumnButton();
            addColumnButton.click();
        }

        return this;
    }

    public ViewConfigPage removeAllColumns() {
        List<String> existingColumnsNames = TestUtils.getTextFromListWebElements(listExistingColumns);
        for (int i = 0; i < existingColumnsNames.size(); i++) {
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    getDriver().findElement(
                            By.xpath(String.format("//button[@id = 'yui-gen%s-button']", (i + 6)))))).click();
        }

        return this;
    }

    public MyViewPage clickApplyAndOkAndGoToMyViewPage() {
        applyButton.click();
        okButton.click();

        return new MyViewPage(getDriver());
    }
}
