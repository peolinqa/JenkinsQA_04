package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MyViewPage extends BasePage {


    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='item']")
    private List<WebElement> viewNamesOnBreadcrumbs;

    @FindBy(css = "div .tab a")
    private List<WebElement> viewNamesOnTabBar;

    @FindBy(linkText = "Edit View")
    private WebElement editView;

    @FindBy(linkText = "Delete View")
    private WebElement deleteView;

    @FindBy(id = "yui-gen1-button")
    private WebElement submitDeleteViewButton;

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

    public MyViewPage(WebDriver driver) {
        super(driver);
    }

    public ViewConfigPage clickEditView() {
        editView.click();

        return new ViewConfigPage(getDriver());
    }

    public MyViewPage clickDeleteView() {
        deleteView.click();

        return new MyViewPage(getDriver());
    }

    public HomePage clickSubmitDeleteViewAndGoHome() {
        submitDeleteViewButton.click();

        return new HomePage(getDriver());
    }


    public List<String> getNamesOfViewsOnBreadcrumbs() {

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

    public WebElement getButtonPreview() {
        return buttonPreview;
    }

    public MyViewPage clickButtonPreview() {
        buttonPreview.click();

        return this;
    }

    public String getTextFromTextareaPreview() {
        return textareaPreview.getText();
    }

    public WebElement getTextareaPreview() {
        return textareaPreview;
    }

    public WebElement getButtonHidePreview() {
        return buttonHidePreview;
    }

    public MyViewPage clickButtonHidePreview() {
        buttonHidePreview.click();

        return this;
    }

}
