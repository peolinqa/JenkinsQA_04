package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewConfigPage extends BasePage {

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(name = "description")
    private WebElement descriptionText;

    @FindBy(id = "yui-gen6-button")
    private WebElement saveConfigListViewButton;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveConfigMyViewButton;

    public ViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public ViewConfigPage setName(String text) {
        nameText.clear();
        nameText.sendKeys(text);

        return new ViewConfigPage(getDriver());
    }

    public ViewConfigPage setDescription(String text) {
        descriptionText.sendKeys(text);

        return new ViewConfigPage(getDriver());
    }

    public ListViewPage saveConfigAndGoToListView() {
        saveConfigListViewButton.click();

        return new ListViewPage(getDriver());
    }

    public MyViewPage saveConfigAndGoToMyView() {
        saveConfigMyViewButton.click();

        return new MyViewPage(getDriver());
    }
}
