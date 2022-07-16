package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class BuildWithParametersPage extends BaseBuildPage {

    public BuildWithParametersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@name='value']//option")
    private List<WebElement> dropDownMenuList;

    @FindBy(id = "yui-gen1-button")
    private WebElement buildButton;

    public List<String> collectDropDownMenu() {
        List<String> actualCollectDropDownMenu = new ArrayList<>();

        for (WebElement element : dropDownMenuList) {
            actualCollectDropDownMenu.add(element.getText());
        }

        return actualCollectDropDownMenu;
    }

    public  ProjectPage clickBuildButton() {
        buildButton.click();

        return new ProjectPage(getDriver());
    }
}