package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;

public final class Page404Page extends BasePage {

    public Page404Page(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage404() {
        return getDriver().getTitle();
    }
}