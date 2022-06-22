package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static runner.BaseUtils.PREFIX_PROP;
import static runner.BaseUtils.getProperties;

public final class ProjectUtils {

    private static final String PROP_PORT = PREFIX_PROP + "port";
    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";
    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";

    static void get(WebDriver driver) {
        driver.get(String.format("http://localhost:%s", getProperties().getProperty(PROP_PORT)));
    }

    static void login(WebDriver driver) {
        get(driver);

        WebElement name = driver.findElement(By.name("j_username"));
        name.sendKeys(getProperties().getProperty(PROP_ADMIN_USERNAME));

        WebElement password = driver.findElement(By.name("j_password"));
        password.sendKeys(getProperties().getProperty(PROP_ADMIN_PAS));

        WebElement SignIn = driver.findElement(By.name("Submit"));
        SignIn.click();
    }

    static void logout(WebDriver driver) {
        get(driver);

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
    }

    public static class Dashboard {

        public enum Main {
            NewItem(By.linkText("New Item")),
            People(By.linkText("People")),
            BuildHistory(By.linkText("Build History"));

            private final By locator;

            Main(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }

        }

        public enum Project {
            BuildNow(By.partialLinkText("Build Now"));

            private final By locator;

            Project(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }
    }
}
