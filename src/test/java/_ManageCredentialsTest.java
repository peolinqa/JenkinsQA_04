import model.HomePage;

import model.ManageCredentialsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.TestUtils;

public class _ManageCredentialsTest extends BaseTest {

    @Test
    public void testManageCredentialsChekMenu() {
        final String newUsername = TestUtils.getRandomStr(8);
        final String newPassword = TestUtils.getRandomStr(9);

        String createCredentials = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickGlobalCredentials()
                .clickAddCredentials()
                .createUserCredentials(newUsername, newPassword)
                .getTableText();

        Assert.assertTrue(createCredentials.contains(newUsername));
    }

    @Test
    public void testIconSizeChangePositive() {
        final String[] icon_style = new String[]{"icon-credentials-system-store icon-sm",
                "icon-credentials-system-store icon-md", "icon-credentials-system-store icon-lg"};

        ManageCredentialsPage iconSizeChange = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(iconSizeChange.clickSmallSizeIcon().getAttributeClass(), icon_style[0]);
        asserts.assertEquals(iconSizeChange.clickMediumSizeIcon().getAttributeClass(), icon_style[1]);
        asserts.assertEquals(iconSizeChange.clickLargeSizeIcon().getAttributeClass(), icon_style[2]);
        asserts.assertAll();
    }

    @Test
    public void testIconSizeCSSChangePositive() {
        final String grey = "rgba(248, 248, 248, 1)";
        final String transparent = "rgba(0, 0, 0, 0)";
        final String[] buttonSPressed = new String[]{grey, transparent, transparent};
        final String[] buttonMPressed = new String[]{transparent, grey, transparent};
        final String[] buttonLPressed = new String[]{transparent, transparent, grey};

        ManageCredentialsPage iconSizeChange = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials();

        String[] actualResultSPressed = iconSizeChange.clickSmallSizeIcon().getChangeIconButtonsBGColors();
        String[] actualResultMPressed = iconSizeChange.clickMediumSizeIcon().getChangeIconButtonsBGColors();
        String[] actualResultLPressed = iconSizeChange.clickLargeSizeIcon().getChangeIconButtonsBGColors();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(actualResultSPressed, buttonSPressed);
        asserts.assertEquals(actualResultMPressed, buttonMPressed);
        asserts.assertEquals(actualResultLPressed, buttonLPressed);
        asserts.assertAll();
    }

    @Test
    public void testCheckDropDownMenuAddDomain() {
        String domainName = TestUtils.getRandomStr(8);

        String addDomain = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickCredentialsStoreSystemMenu()
                .clickMenuSelector()
                .clickAddDomain()
                .createNewDomain(domainName)
                .getDomainHeader();

        Assert.assertEquals(addDomain, domainName);
    }

    @Test
    public void testValidateIconSize() {

        boolean expected = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickIconButtonAndGetSize();

         Assert.assertFalse(expected);
    }
}