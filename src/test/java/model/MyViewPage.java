package model;

import model.base.BaseProjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public final class MyViewPage extends BaseProjectPage<MyViewPage, MyViewPageSideMenuFrame> {

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='item']")
    private List<WebElement> viewNamesOnBreadcrumbs;

    @FindBy(css = "div .tab a")
    private List<WebElement> viewNamesOnTabBar;

    @FindBy(id = "yui-gen1-button")
    private WebElement submitDeleteViewButton;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[5]")
    private WebElement textareaMyView;

    @FindBy(xpath = "//a[contains(@href, 'editDescription')]")
    private WebElement addOrEditDescriptionButton;

    @FindBy(xpath = "//textarea[contains(@name, 'description')]")
    private WebElement textareaDescription;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div[not(@class)]")
    private WebElement fieldDescriptionOnThePage;

    @FindBy(xpath = "//a[@previewendpoint='/markupFormatter/previewDescription']")
    private WebElement buttonPreview;

    @FindBy(xpath = "//div[@class='textarea-preview']")
    private WebElement textareaPreview;

    @FindBy(xpath = "//a[@class='textarea-hide-preview']")
    private WebElement buttonHidePreview;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='children']")
    private WebElement triangleOnBreadcrumbs;

    @FindBy(id = "menuSelector")
    private WebElement menuSelector;

    @FindBy(xpath = "//thead/tr/th/a")
    private List<WebElement> columnsInMyView;

    @FindBy(xpath = "//a[@class='jenkins-table__link model-link inside']")
    private List<WebElement> listJobsNameMyViewPage;

    public MyViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MyViewPageSideMenuFrame getSideMenu() {
        return new MyViewPageSideMenuFrame(getDriver());
    }

    public HomePage clickSubmitDeleteViewAndGoHome() {
        submitDeleteViewButton.click();

        return new HomePage(getDriver());
    }

    public List<String> getNamesOfViewsOnBreadcrumbs() {
        triangleOnBreadcrumbs.click();

        return viewNamesOnBreadcrumbs.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getNamesOfViewsOnTabBar() {
        return viewNamesOnTabBar.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public MyViewPage clickAddOrEditDescriptionButton() {
        addOrEditDescriptionButton.click();

        return this;
    }

    public MyViewPage sendTextareaDescription(String text) {
        textareaDescription.sendKeys(text);

        return this;
    }

    public MyViewPage clearTextareaDescription() {
        textareaDescription.clear();

        return this;
    }

    public String getTextareaDescription() {
        return textareaDescription.getText();
    }

    public MyViewPage clickButtonSave() {
        saveButton.click();

        return this;
    }

    public String getTextFromFieldDescriptionOnThePage() {
        return fieldDescriptionOnThePage.getText();
    }

    public boolean isButtonPreviewDisplayed() {
        return buttonPreview.isDisplayed();
    }

    public MyViewPage clickButtonPreview() {
        buttonPreview.click();

        return this;
    }

    public String getTextFromTextareaPreview() {
        return textareaPreview.getText();
    }

    public boolean isTextareaPreviewDisplayed() {
        return textareaPreview.isDisplayed();
    }

    public boolean isButtonHidePreviewDisplayed() {
        return buttonHidePreview.isDisplayed();
    }

    public String getTextMyView() {
        return textareaMyView.getText();
    }

    public MyViewPage clickButtonHidePreview() {
        buttonHidePreview.click();

        return this;
    }

    public MyViewPage moveToProjectName(String elementName) {
        getActions()
                .moveToElement(getDriver().findElement(By.xpath(String.format("//a[.='%s']", elementName))))
                .perform();

        return this;
    }

    public MyViewPageSelectorMenuFrame clickProjectDropDownMenu() {
        menuSelector.click();

        return new MyViewPageSelectorMenuFrame(getDriver());
    }

    public int getCountOfColumns() {
        return columnsInMyView.size();
    }

    public List<String> getListJobsName() {

        return listJobsNameMyViewPage.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
