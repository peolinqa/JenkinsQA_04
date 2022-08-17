package runner;

import model.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static runner.BaseUtils.PREFIX_PROP;
import static runner.BaseUtils.getProperties;

public final class ProjectUtils {

    private static final String PROP_PORT = PREFIX_PROP + "port";
    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";
    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";

    public static void get(WebDriver driver) {
        driver.get(String.format("http://localhost:%s", getProperties().getProperty(PROP_PORT)));
    }

    static void login(WebDriver driver) {
        get(driver);

        new LoginPage(driver)
                .setUsername(getProperties().getProperty(PROP_ADMIN_USERNAME))
                .setPassword(getProperties().getProperty(PROP_ADMIN_PAS))
                .clickSignInButton();
    }

    static void logout(WebDriver driver) {
        get(driver);

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
    }
}
