package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewConfigPage extends BasePage {

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(xpath = "//textarea[contains(@name, 'description')]")
    private WebElement descriptionField;

    @FindBy(id = "yui-gen13-button")
    private WebElement saveConfigViewButton;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveConfigViewButton1;

    public ViewConfigPage(WebDriver driver) {
        super(driver);
    }

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
}
