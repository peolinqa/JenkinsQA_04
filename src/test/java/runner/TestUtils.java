package runner;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestUtils {

    public static String getRandomStr() {
        return getRandomStr(20);
    }

    public static String getRandomStr(int length) {
        return RandomStringUtils.random(length,
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    public static String getRandomStrCyrillic() {
        String[] randomCyrillic = new String[9];
        for (int i = 0; i < randomCyrillic.length; i++) {
            randomCyrillic[i] = String.valueOf((char)(Math.random()*32 + 1072));
        }
        return Joiner.on("").join(randomCyrillic);
    }

    public static void clearAndSend(WebDriver driver, By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static List<WebElement> getList(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }
}
