package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoadStatisticsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[1]")
    private WebElement shortButton;

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[2]")
    private WebElement mediumButton;

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[3]")
    private WebElement longButton;

    public LoadStatisticsPage(WebDriver driver) {
        super(driver);
    }

    public LoadStatisticsPage clickShortButton() {
        shortButton.click();

        return this;
    }

    public LoadStatisticsPage clickMediumButton() {
        mediumButton.click();

        return this;
    }

    public LoadStatisticsPage clickLongButton() {
        longButton.click();

        return this;
    }

    public String getShortTooltip() {
        return shortButton.getAttribute("tooltip");
    }

    public String getMediumTooltip() {
        return mediumButton.getAttribute("tooltip");
    }

    public String getLongTooltip() {
        return longButton.getAttribute("tooltip");
    }

    public String getShortTagName() {
        return shortButton.getTagName();
    }

    public String getMediumTagName() {
        return mediumButton.getTagName();
    }

    public String getLongTagName() {
        return longButton.getTagName();
    }
}