package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage<Self extends BaseProjectPage<?, SideMenu>, SideMenu> extends BaseSideMenuPage<Self, SideMenu> {

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    private WebElement projectName;

    public String getProjectName() {
        return projectName.getText();
    }
}
