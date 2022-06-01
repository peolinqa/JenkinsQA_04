package qa_old;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class JuliaSabTest extends BaseTest {

    private static final String URL_MAIL_REGISTRATION = "https://account.mail.ru/signup?from=main&rf=auth.mail.ru&app_id_mytracker=58519";
    private static final String URL_JACKSON_WIKI = "https://en.wikipedia.org/wiki/Michael_Jackson";

    private static final String XPATH_BIRTH_DAY = "//div[@data-test-id='birth-date__day']//*[@class='base-0-2-37']";
    private static final String XPATH_GET_BIRTH_DAY = "//span[@data-test-id = 'birth-date__day-value']/span";
    private static final String XPATH_MONTH_XPATH = "//div[@data-test-id='birth-date__month']//*[@class='base-0-2-37']";
    private static final String XPATH_GET_MONTH_XPATH = "//span[@data-test-id = 'birth-date__month-value']/span";
    private static final String XPATH_YEAR_XPATH = "//div[@data-test-id='birth-date__year']//*[@class='base-0-2-37']";
    private static final String XPATH_GET_YEAR_XPATH = "//span[@data-test-id = 'birth-date__year-value']/span";
    private static final String XPATH_TITO_LINK = "//p[contains(text(), 'The eighth child')]/a[@title = 'Tito Jackson']";
    private static final String XPATH_TITO_HEADING = "//h1[@id='firstHeading']";

    private static final String FORMAT_FOR_INPUT = "//span[contains(text(), '%s')]";

    @Test
    public void testConfirmInputDayMonthYear() {
        getDriver().get(URL_MAIL_REGISTRATION);
        getDriver().findElement(By.xpath(XPATH_BIRTH_DAY)).click();
        getDriver().findElement(By.xpath(String.format(FORMAT_FOR_INPUT, "27"))).click();
        String actualDayResult = getDriver()
                .findElement(By.xpath(XPATH_GET_BIRTH_DAY)).getText();
        Assert.assertEquals(actualDayResult, "27");

        getDriver().findElement(By.xpath(XPATH_MONTH_XPATH)).click();
        getDriver().findElement(By.xpath(String.format(FORMAT_FOR_INPUT, "Январь"))).click();
        String actualMonthResult = getDriver()
                .findElement(By.xpath(XPATH_GET_MONTH_XPATH)).getText();
        Assert.assertEquals(actualMonthResult, "Январь");

        getDriver().findElement(By.xpath(XPATH_YEAR_XPATH)).click();
        getDriver().findElement(By.xpath(String.format(FORMAT_FOR_INPUT, "1997"))).click();
        String actualYearResult = getDriver()
                .findElement(By.xpath(XPATH_GET_YEAR_XPATH)).getText();
        Assert.assertEquals(actualYearResult, "1997");
    }

    @Test
    public void testConfirmOpenNewWebPageTito() {
        getDriver().get(URL_JACKSON_WIKI);
        getDriver().findElement(By.xpath(XPATH_TITO_LINK)).click();
        String actualHeading = getDriver()
                .findElement(By.xpath(XPATH_TITO_HEADING)).getText();
        Assert.assertEquals(actualHeading, "Tito Jackson");
    }
}
