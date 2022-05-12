import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.JuliaSabConstantsUtils;

public class JuliaSabDropDownListTest extends BaseTest {

    public void setBirthDay(String day, String month, String year) {
        getDriver().get(JuliaSabConstantsUtils.MAIL_REGISTRATION);

        getDriver().findElement(By.xpath(JuliaSabConstantsUtils.BIRTH_DAY_XPATH)).click();
        getDriver().findElement(By.xpath(String.format(JuliaSabConstantsUtils.XPATH_FOR_INPUT_TEXT, day))).click();

        getDriver().findElement(By.xpath(JuliaSabConstantsUtils.MONTH_XPATH)).click();
        getDriver().findElement(By.xpath(String.format(JuliaSabConstantsUtils.XPATH_FOR_INPUT_TEXT, month))).click();

        getDriver().findElement(By.xpath(JuliaSabConstantsUtils.YEAR_XPATH)).click();
        getDriver().findElement(By.xpath(String.format(JuliaSabConstantsUtils.XPATH_FOR_INPUT_TEXT, year))).click();
    }

    @Test
    public void inputBirthday() {
        setBirthDay("27", "Январь", "1997");
    }
}
