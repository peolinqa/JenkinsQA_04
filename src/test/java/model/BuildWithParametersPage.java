package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public final class BuildWithParametersPage extends BaseBuildPage<BuildWithParametersPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    public BuildWithParametersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
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

    public PipelineProjectPage clickBuildButton() {
        buildButton.click();

        return new PipelineProjectPage(getDriver());
    }
}
