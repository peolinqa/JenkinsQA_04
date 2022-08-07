package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public final class BuildParametersPage extends BaseBuildPage<BuildParametersPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    public BuildParametersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
    }

    @FindBy(xpath = "//div[contains(@class, 'jenkins-form-item tr ')]")
    private List<WebElement> choiceAndDescriptionParameterName;

    public List<String> collectChoiceAndDescriptionParameterName() {
        List<String> actualChoiceAndDescriptionParameterName = new ArrayList<>();

        for (WebElement el : choiceAndDescriptionParameterName) {
            actualChoiceAndDescriptionParameterName.add(el.getText());
        }

        return actualChoiceAndDescriptionParameterName;
    }
}