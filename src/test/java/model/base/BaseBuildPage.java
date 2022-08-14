package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseBuildPage<Self extends BaseBuildPage<?, ?>, SideMenu> extends BaseSideMenuPage<Self, SideMenu> {

    @FindBy(css = "span.jenkins-icon-adjacent")
    private WebElement buildHeader;

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement textBuildDescription;

    public BaseBuildPage(WebDriver driver) {
        super(driver);
    }

    public String getBuildHeaderText() {
        return buildHeader.getText();
    }

    public String getBuildHeaderName() {
        String[] textBuildHeader = buildHeader.getText().split(" ");

        return textBuildHeader[1];
    }

    public String getBuildDescriptionText() {
        return textBuildDescription.getText();
    }
}
