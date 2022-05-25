package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KsuTitova99BottlesOfBeerHW12Tests5and6 extends BaseTest {

    /**
     * TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
     */

    @Test
    public void testLanguagesNamesStartWithNumbers() {
        int expectedResult = 10;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        String[] actualResult = new String[10];
        for (int i = 0; i < actualResult.length; i++) {
            actualResult[i] = getDriver().findElement(
                    By.xpath("//tbody/tr[@onmouseover][" + (i + 1) + "]")
            ).getText();
        }

        Assert.assertEquals(actualResult.length, expectedResult);
    }
}