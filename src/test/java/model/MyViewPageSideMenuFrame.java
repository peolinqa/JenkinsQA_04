package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class MyViewPageSideMenuFrame extends BaseModel<MyViewPageSideMenuFrame> {

    @FindBy(linkText = "Edit View")
    private WebElement menuEditView;

    @FindBy(linkText = "Delete View")
    private WebElement menuDeleteView;

    public MyViewPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }
    public ViewConfigPage clickMenuEditView() {
        menuEditView.click();

        return new ViewConfigPage(getDriver());
    }

    public MyViewPage clickMenuDeleteView() {
        menuDeleteView.click();

        return new MyViewPage(getDriver());
    }
}
