package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class MyViewPageSideMenuFrame extends BaseModel<MyViewPageSideMenuFrame> {

    @FindBy(linkText = "Edit View")
    private WebElement editView;

    @FindBy(linkText = "Delete View")
    private WebElement deleteView;

    public MyViewPageSideMenuFrame(WebDriver driver) {
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
}
