
import model.HomePage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.Arrays;

public class _ManageCredentialsTest extends BaseTest {
    private static final String ICON_XPATH = "//td[@data='Jenkins Credentials Provider']";
    private static final By SMALL_SIZE_ICONS = By.xpath("//a[@href='/iconSize?16x16']");
    private static final By MEDIUM_SIZE_ICONS = By.xpath("//a[@href='/iconSize?24x24']");
    private static final By LARGE_SIZE_ICONS = By.xpath("//a[@href='/iconSize?32x32']");

    private String getAttributeClass() {

        return getDriver().findElement(By.xpath(".//td[@data='Jenkins Credentials Provider']//img"))
                .getAttribute("class");
    }

    public String[] elementBGColor() {
        String[] elementBGColor = new String[3];
        elementBGColor[0] = getDriver().findElement(By.xpath("//span[text()='mall']/.."))
                .getCssValue("background-color");
        elementBGColor[1] = getDriver().findElement(By.xpath("//span[text()='edium']/.."))
                .getCssValue("background-color");
        elementBGColor[2] = getDriver().findElement(By.xpath("//span[text()='arge']/.."))
                .getCssValue("background-color");

        return elementBGColor;
    }

    @Test
    public void testManageCredentials() {

        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ManageCredentials.click(getDriver());

        String iconButtonName = getDriver().findElement(
                By.xpath("//li[@class='jenkins-icon-size__items-item']")).getText().substring(0, 1);

        String iconSizeBefore = getDriver().findElement(
                By.xpath(ICON_XPATH)).getCssValue("height");

        if (iconButtonName.equals("L")) {
            getDriver().findElement(By.xpath("//a[@href='/iconSize?16x16']")).click();
        } else {
            getDriver().findElement(By.xpath("//a[@href='/iconSize?32x32']")).click();
        }
        String iconSizeAfter = getDriver().findElement(
                By.xpath(ICON_XPATH)).getCssValue("height");

        Assert.assertNotEquals(iconSizeAfter, iconSizeBefore);
    }

    @Test
    public void testManageCredentialsChekMenu() {

        final String newUsername = TestUtils.getRandomStr(8);
        final String newPassword = TestUtils.getRandomStr(9);

        String createCredentials = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageCredentials()
                .clickGlobalCredentials()
                .clickAddCredentials()
                .createUserCredentials(newUsername, newPassword)
                .getTableText();

        Assert.assertTrue(createCredentials.contains(newUsername));
    }

    @Test
    public void testIconSizeChangePositive() {

        final String[] expectedResult = new String[]{"icon-credentials-system-store icon-sm",
                "icon-credentials-system-store icon-md", "icon-credentials-system-store icon-lg"};
        String[] actualResult = new String[3];

        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ManageCredentials.click(getDriver());

        getDriver().findElement(SMALL_SIZE_ICONS).click();
        actualResult[0] = getAttributeClass();
        getDriver().findElement(MEDIUM_SIZE_ICONS).click();
        actualResult[1] = getAttributeClass();
        getDriver().findElement(LARGE_SIZE_ICONS).click();
        actualResult[2] = getAttributeClass();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconSizeCSSChangePositive() {

        final String grey = "rgba(248, 248, 248, 1)";
        final String transparent = "rgba(0, 0, 0, 0)";
        final String[] buttonSPressed = new String[]{grey, transparent, transparent};
        final String[] buttonMPressed = new String[]{transparent, grey, transparent};
        final String[] buttonLPressed = new String[]{transparent, transparent, grey};
        final boolean[] expectedResult = new boolean[]{true, true, true};
        final boolean[] actualResult = new boolean[]{false, false, false};

        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ManageCredentials.click(getDriver());

        getDriver().findElement(SMALL_SIZE_ICONS).click();
        String[] currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonSPressed)) {
            actualResult[0] = true;
        }

        getDriver().findElement(MEDIUM_SIZE_ICONS).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonMPressed)) {
            actualResult[1] = true;
        }

        getDriver().findElement(LARGE_SIZE_ICONS).click();
        currentButtonResult = elementBGColor();
        if (Arrays.equals(currentButtonResult, buttonLPressed)) {
            actualResult[2] = true;
        }


        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckDropDownMenuAddDomain() {

        String domainName = TestUtils.getRandomStr(8);

        String addDomain = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageCredentials()
                .clickCredentialsStoreSystemMenu()
                .clickMenuSelector()
                .clickAddDomain()
                .createNewDomain(domainName)
                .getDomainHeader();

        Assert.assertEquals(addDomain, domainName);
    }
}