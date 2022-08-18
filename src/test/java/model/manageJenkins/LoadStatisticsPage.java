package model.manageJenkins;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class LoadStatisticsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[1]")
    private WebElement linkShortTimespan;

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[2]")
    private WebElement linkMediumTimespan;

    @FindBy(xpath = "//div[contains(text(), 'Timespan')]/child::*[3]")
    private WebElement linkLongTimespan;

    public LoadStatisticsPage(WebDriver driver) {
        super(driver);
    }

    public LoadStatisticsPage clickLinkShortTimespan() {
        linkShortTimespan.click();

        return this;
    }

    public LoadStatisticsPage clickLinkMediumTimespan() {
        linkMediumTimespan.click();

        return this;
    }

    public LoadStatisticsPage clickLinkLongTimespan() {
        linkLongTimespan.click();

        return this;
    }

    public String getShortTooltip() {
        return linkShortTimespan.getAttribute("tooltip");
    }

    public String getMediumTooltip() {
        return linkMediumTimespan.getAttribute("tooltip");
    }

    public String getLongTooltip() {
        return linkLongTimespan.getAttribute("tooltip");
    }

    public String getShortTagName() {
        return linkShortTimespan.getTagName();
    }

    public String getMediumTagName() {
        return linkMediumTimespan.getTagName();
    }

    public String getLongTagName() {
        return linkLongTimespan.getTagName();
    }
}
