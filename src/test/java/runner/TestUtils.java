package runner;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static List<String> getTextFromList(WebDriver driver, By locator) {
        driver.findElements(locator);
        return driver.findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public static Set<String> getOpenTabTitles(WebDriver driver){
        Set<String> actualSet = new HashSet<>();
        for (String handle : driver.getWindowHandles()) {
            actualSet.add(driver.switchTo().window(handle).getTitle());
        }

        return actualSet;
    }

    public static JavascriptExecutor scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        return js;
    }

    public static List<String> getTextFromListWebElements(List<WebElement> listWebElements) {
        List<String> listStr = new ArrayList<>();
        for (WebElement list : listWebElements) {
            listStr.add(list.getText());
        }

        return listStr;
    }
}
