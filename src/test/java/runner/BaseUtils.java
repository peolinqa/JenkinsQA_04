package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class BaseUtils {

    private static final String CHROME_OPTIONS = "CHROME_OPTIONS";
    private static final String CHROME_OPTIONS_PROP = "default." + CHROME_OPTIONS.toLowerCase();

    private static Properties properties;

    private static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun()) {
                 properties.setProperty(CHROME_OPTIONS_PROP, System.getenv(CHROME_OPTIONS));
            } else {
                try {
                    InputStream inputStream = BaseUtils.class.getClassLoader().getResourceAsStream("local.properties");
                    if (inputStream == null) {
                        System.out.println("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                        System.out.println("You need to create it from local.properties.TEMPLATE file.");
                        System.exit(1);
                    }
                    properties.load(inputStream);
                } catch (IOException ignore) {}
            }
        }
    }


    private static final ChromeOptions chromeOptions;
    static {
        initProperties();

        chromeOptions = new ChromeOptions();
        String options = properties.getProperty(CHROME_OPTIONS_PROP);
        if (options != null) {
            for (String argument : options.split(";")) {
                chromeOptions.addArguments(argument);
            }
        }

        WebDriverManager.chromedriver().setup();
    }

    static boolean isServerRun() {
        return System.getenv("CI_RUN") != null;
    }

    static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}