import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;


public class MultiConfigurationProjectVKSuiteTest extends BaseTest {
    private static final Map<String, Map<String, String>> TEST_DATA_MAP = Map.of(
            "testCreateMCP", Map.of("projectName", "MCP-TC_041.007", "projectDescription", ""),
            "testCreateMCPwithDescription", Map.of("projectName", "MCP-TC_041.007wDescription", "projectDescription", "This is a description, TC_041.007"),
            "testAddDescription", Map.of("projectName", "MCP-TC_042.001", "projectDescription", "This is a description, TC_042.001, added after creation"),
            "testDeleteMCP", Map.of("projectName", "deleteMCP-TC_046.002"),
            "testRenameMCP", Map.of("projectName", "MCP-TC_043.007", "projectNewName", "renamedMCP-TC_043.007"),
            "testDisableMCP", Map.of("projectName", "MCP-TC_045.003"),
            "testBuildNowMCP", Map.of("projectName", "MCP-TC_044.002"));
    private static final Set<String> CATEGORY_TYPE_SET = Set.of
            ("Freestyle project", "Pipeline", "Multi-configuration project",
                    "Folder", "Multibranch Pipeline", "Organization Folder");

    private static final List<String> ALERT_TYPE_CONFIRMATION = List.of
            ("Freestyle project", "Pipeline", "Multi-configuration project");
    private static final List<String> FORM_TYPE_CONFIRMATION = List.of
            ("Folder", "Multibranch Pipeline", "Organization Folder");

    private static final String CATEGORY_TYPE = "Multi-configuration project";

    private WebElement targetProjectWeElement(String projectName) {
        return getDriver().findElement(By
                .cssSelector(String.format("table#projectstatus>tbody>tr[id='%s'] td>a", projectName)));
    }
    private void backToDashboardClick() {
        getDriver().findElement(By.cssSelector("a[title='Back to Dashboard']")).click();
    }

    private void applyButtonClick() {
        getDriver().findElement(By.xpath("//button[@type='button' and text()='Apply']")).click();
    }

    private void addOrEditDescriptionButtonClick() {
        getDriver().findElement(By.xpath("//a[text()='Add description']")).click();
    }

    private void saveSubmitButtonClick() {
        getDriver().findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
    }

    private void submitButtonClick() {
        getDriver().findElement(By.cssSelector("button[type=submit]")).click();
    }

    private void saveOrYesSubmitButtonClick() {
        submitButtonClick();
    }

    private void renameSubmitButtonClick() {
        submitButtonClick();
    }

    private void disableProjectSubmitButtonClick() {
        submitButtonClick();
    }

    public boolean isAlertConfirmation(String categoryType) {
        return ALERT_TYPE_CONFIRMATION.contains(categoryType);
    }

    private boolean removeJobFromList(String name) {
        try {
            List<WebElement> jobList = getDriver().findElements(By.cssSelector("table#projectstatus>tbody>tr"));
            int index = -1;
            String s = String.format("job_%s", name);
            for (int i = 0; i < jobList.size(); i++) {
                if (jobList.get(i).getAttribute("id").equals(s)) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                jobList.get(index).findElement(By.cssSelector(String.format("a[href='job/%s/']", name))).click();
                WebElement menuDeleteItem = getDriver().findElement(By
                        .xpath("//a/span[contains(text(),'Delete')]"));

                String getTypeOfItem = menuDeleteItem.getText().substring(6);
                menuDeleteItem.click();
                if (isAlertConfirmation(getTypeOfItem.trim())) {
                    Alert alert = getDriver().switchTo().alert();
                    alert.accept();
                } else {
                    saveOrYesSubmitButtonClick();
                }
                return true;
            }
            return false;

        } catch (NoSuchElementException e) {
            return false;
        }

    }

    private void deleteMenuItemClick() {
        WebElement menuDeleteItem = getDriver().findElement(By.xpath("//a/span[contains(text(),'Delete')]"));

        String getTypeOfItem = menuDeleteItem.getText().substring(6);
        menuDeleteItem.click();

        if (isAlertConfirmation(getTypeOfItem.trim())) {
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        } else {
            saveOrYesSubmitButtonClick();
        }
    }

    private void renameMenuItemClick() {
        WebElement menuRenameItem = getDriver().findElement(By
                .xpath("//a/span[contains(text(),'Rename')]"));

        menuRenameItem.click();
    }

    private void buildNowMenuItemClick() {
        WebElement menuBuildNowItem = getDriver().findElement(By
                .xpath("//a/span[contains(text(),'Build Now')]"));

        menuBuildNowItem.click();
    }

    private void createNewItem(String projectName, String categoryType) {
        try {
            WebElement newItemButton = getDriver().findElement(By.cssSelector("a[title='New Item']"));
            newItemButton.click();

            WebElement itemNameInput = getDriver().findElement(By.cssSelector("input#name"));
            itemNameInput.clear();
            itemNameInput.sendKeys(projectName);

            WebElement categoryItemInputLabel = getDriver().findElement(By
                    .xpath(String.format("//label[contains(.,'%s')]", categoryType)));
            categoryItemInputLabel.click();

            WebElement okButton = getDriver().findElement(By.id("ok-button"));
            okButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (CATEGORY_TYPE_SET.contains(categoryType)) {
                System.out.printf("!!!%s\n===>  This exception related to No such element", e);
            } else {
                System.out.printf("!!![%s]\n===> This exception maybe related to incorrect category for project '%s'!"
                        + " Provided category type '%s', available for selection categories: %s",
                        e, projectName, categoryType, CATEGORY_TYPE_SET);
            }
        }
    }

    @Test
    public void testDeleteExistentProject() {

        String projectNameToDelete = TEST_DATA_MAP.get("testDeleteMCP").get("projectName");

        createNewItem(projectNameToDelete, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        backToDashboardClick();

        WebElement projectToDelete = targetProjectWeElement(String.format("job_%s", projectNameToDelete));

        projectToDelete.click();
        deleteMenuItemClick();

        List<WebElement> listProjects = getDriver().findElements(By.cssSelector("table#projectstatus>tbody>tr"));
        Assert.assertFalse(listProjects.contains(projectToDelete));
    }

    @Test
    public void testCreateMCProject() {
        String projectName = TEST_DATA_MAP.get("testCreateMCP").get("projectName");
        boolean removalResult = removeJobFromList(projectName);
        createNewItem(projectName, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        WebElement projectNameHeadlineH1 = getDriver().findElement(By.cssSelector("#main-panel>h1"));

        Assert.assertEquals(projectNameHeadlineH1.getText(), String.format("Project %s", projectName));
    }

    @Ignore("alternative for testCreateMCProject")
    @Test
    public void testCreateMCProjectWithDescription() {

        String projectName = TEST_DATA_MAP.get("testCreateMCPwithDescription").get("projectName");
        String projectDescription = TEST_DATA_MAP.get("testCreateMCPwithDescription").get("projectDescription");

        boolean removalResult = removeJobFromList(projectName);
        createNewItem(projectName, CATEGORY_TYPE);

        WebElement generalTab = getDriver().findElement(By.xpath("//div[.='General']"));
        Assert.assertTrue(generalTab.getAttribute("class").contains("active"));

        WebElement descriptionTextArea = getDriver().findElement(By.cssSelector("textarea[name=description]"));
        descriptionTextArea.sendKeys(projectDescription);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        WebElement projectNameHeadlineH1 = getDriver().findElement(By.cssSelector("#main-panel>h1"));
        WebElement projectDescriptionDiv = getDriver().findElement(By.cssSelector("#description>div"));

        Assert.assertEquals(projectNameHeadlineH1.getText(), String.format("Project %s", projectName));
        Assert.assertEquals(projectDescriptionDiv.getText(), projectDescription);
    }

    @Test
    public void testAddDescription() {

        String projectNameToAddDescription = TEST_DATA_MAP.get("testAddDescription").get("projectName");
        String projectDescription = TEST_DATA_MAP.get("testAddDescription").get("projectDescription");

        boolean removalResult = removeJobFromList(projectNameToAddDescription);
        createNewItem(projectNameToAddDescription, CATEGORY_TYPE);

        WebElement generalTab = getDriver().findElement(By.xpath("//div[.='General']"));
        Assert.assertTrue(generalTab.getAttribute("class").contains("active"));

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        backToDashboardClick();

        WebElement projectToAddDescription = targetProjectWeElement(String.format("job_%s", projectNameToAddDescription));

        projectToAddDescription.click();

        addOrEditDescriptionButtonClick();

        WebElement descriptionTextArea = getDriver().findElement(By.cssSelector("textarea[name=description]"));
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys(projectDescription);

        saveSubmitButtonClick();

        WebElement projectNameHeadlineH1 = getDriver().findElement(By.cssSelector("#main-panel>h1"));
        WebElement projectDescriptionDiv = getDriver().findElement(By.cssSelector("#description>div"));

        Assert.assertEquals(projectNameHeadlineH1.getText(), String.format("Project %s", projectNameToAddDescription));
        Assert.assertEquals(projectDescriptionDiv.getText(), projectDescription);
    }

    @Test
    public void testRenameExistentProject() {

        String projectNameToRename = TEST_DATA_MAP.get("testRenameMCP").get("projectName");
        String projectNameToRenameNewName = TEST_DATA_MAP.get("testRenameMCP").get("projectNewName");
        boolean removalResult = removeJobFromList(projectNameToRename);
        boolean removalResult2 = removeJobFromList(projectNameToRenameNewName);
        createNewItem(projectNameToRename, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        backToDashboardClick();

        WebElement projectToRename = targetProjectWeElement(String.format("job_%s", projectNameToRename));

        projectToRename.click();
        renameMenuItemClick();

        WebElement inputNewName = getDriver().findElement(By.cssSelector("input[name='newName']"));
        inputNewName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputNewName.sendKeys(projectNameToRenameNewName);
        renameSubmitButtonClick();

        backToDashboardClick();

        List<WebElement> listProjects = getDriver().findElements(By.cssSelector("table#projectstatus>tbody>tr"));
        Assert.assertFalse(listProjects.contains(projectToRename));

    }

    @Test
    public void testDisableExistentProject() {

        String projectNameToDisable = TEST_DATA_MAP.get("testDisableMCP").get("projectName");

        boolean removalResult = removeJobFromList(projectNameToDisable);

        createNewItem(projectNameToDisable, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        backToDashboardClick();

        WebElement projectToDisable = targetProjectWeElement(String.format("job_%s", projectNameToDisable));

        projectToDisable.click();

        disableProjectSubmitButtonClick();

        String expectedText = "Enable";
        String enableButtonText = getDriver().findElement(By
                .cssSelector("form#enable-project button[type='submit']")).getText();

        Assert.assertEquals(enableButtonText, expectedText);

    }

    @Test
    public void testBuildNowExistentProject() {

        String projectNameToBuildNow = TEST_DATA_MAP.get("testBuildNowMCP").get("projectName");

        boolean removalResult = removeJobFromList(projectNameToBuildNow);

        createNewItem(projectNameToBuildNow, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        backToDashboardClick();

        WebElement projectToBuildNow = targetProjectWeElement(String.format("job_%s", projectNameToBuildNow));

        projectToBuildNow.click();

        List<WebElement> buildHistoryBefore = getDriver().findElements(By.cssSelector("table>tbody>tr[page-entry-id]"));
        int counterBeforeBuild = buildHistoryBefore.size();

        buildNowMenuItemClick();

        List<WebElement> buildHistoryAfter = getDriver().findElements(By.cssSelector("table>tbody>tr[page-entry-id]"));
        int counterAfterBuild = buildHistoryAfter.size();

        Assert.assertNotEquals(counterAfterBuild, counterBeforeBuild, "Build could not succeed!");

    }

}
