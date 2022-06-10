import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;


public class MultiConfigurationProjectAddDescriptionTest extends BaseTest {
    private static final Map<String, String> TEST_DATA_MAP = Map.of
            ("projectName", "MCP-TC_042.001", "projectDescription", "This is a description, TC_042.001");
    private static final Set<String> CATEGORY_TYPE_SET = Set.of
            ("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

    private static final List<String> ALERT_TYPE_CONFIRMATION = List.of
            ("Freestyle project", "Pipeline", "Multi-configuration project");
    private static final List<String> FORM_TYPE_CONFIRMATION = List.of
            ("Folder", "Multibranch Pipeline", "Organization Folder");

    private static final String CATEGORY_TYPE = "Multi-configuration project";
    private static final String PROJECT_NAME = TEST_DATA_MAP.get("projectName");
    private static final String PROJECT_DESCRIPTION = TEST_DATA_MAP.get("projectDescription");

    private void backToDashboardClick() {
        getDriver().findElement(By.cssSelector("a[title='Back to Dashboard']")).click();
    }

    private void applyButtonClick() {
        getDriver().findElement(By.xpath("//button[@type='button' and text()='Apply']")).click();
    }

    private void saveOrYesSubmitButtonClick() {
        getDriver().findElement(By.cssSelector("button[type=submit]")).click();
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
                jobList.get(index).findElement(By.tagName("a")).click();
                WebElement menuDeleteItem = getDriver().findElement(By.xpath("//a/span[contains(text(),'Delete')]"));

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

    private void deleteMenuItemClick(){
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

    private void createNewItem(String projectName, String categoryType) {
        try {
            WebElement newItemButton = getDriver().findElement(By.cssSelector("a[title='New Item']"));
            newItemButton.click();

            WebElement itemNameInput = getDriver().findElement(By.cssSelector("input#name"));
            itemNameInput.clear();
            itemNameInput.sendKeys(projectName);

            WebElement categoryItemInputLabel = getDriver().findElement
                    (By.xpath(String.format("//label[contains(.,'%s')]", categoryType)));
            categoryItemInputLabel.click();

            WebElement okButton = getDriver().findElement(By.id("ok-button"));
            okButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (CATEGORY_TYPE_SET.contains(categoryType)) {
                System.out.printf("!!!%s\n===>  This exception related to No such element", e);
            } else {
                System.out.printf("!!![%s]\n===> This exception maybe related to incorrect category for project '%s'!" +
                                " Provided category type '%s', available for selection categories: %s",
                        e, projectName, categoryType, CATEGORY_TYPE_SET);
            }
        }
    }



    @Ignore("US_046 not ready yet, did not transferred to backlog")
    @Test
    public void testDeleteExistentProject() {

        String projectName = String.format("job_%s", PROJECT_NAME);
        WebElement projectToDelete = getDriver().findElement
                (By.cssSelector(String.format("table#projectstatus>tbody>tr[id='%s'] td>a", projectName)));

        projectToDelete.click();
        deleteMenuItemClick();

        List<WebElement> listProjects = getDriver().findElements(By.cssSelector("table#projectstatus>tbody>tr"));
        Assert.assertFalse(listProjects.contains(projectToDelete));
    }

    @Ignore
    @Test
    public void testCreateMCProject() {
        boolean removalResult = removeJobFromList(PROJECT_NAME);
        createNewItem(PROJECT_NAME, CATEGORY_TYPE);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        WebElement projectNameHeadlineH1 = getDriver().findElement(By.cssSelector("#main-panel>h1"));

        Assert.assertEquals(projectNameHeadlineH1.getText(), String.format("Project %s", PROJECT_NAME));
    }

    @Test
    public void testCreateMCProjectWithDescription() {

        boolean removalResult = removeJobFromList(PROJECT_NAME);
        createNewItem(PROJECT_NAME, CATEGORY_TYPE);

        WebElement generalTab = getDriver().findElement(By.xpath("//div[.='General']"));
        Assert.assertTrue(generalTab.getAttribute("class").contains("active"));

        WebElement descriptionTextArea = getDriver().findElement(By.cssSelector("textarea[name=description]"));
        descriptionTextArea.sendKeys(PROJECT_DESCRIPTION);

        applyButtonClick();
        saveOrYesSubmitButtonClick();

        WebElement projectNameHeadlineH1 = getDriver().findElement(By.cssSelector("#main-panel>h1"));
        WebElement projectDescriptionDiv = getDriver().findElement(By.cssSelector("#description>div"));

        Assert.assertEquals(projectNameHeadlineH1.getText(), String.format("Project %s", PROJECT_NAME));
        Assert.assertEquals(projectDescriptionDiv.getText(), PROJECT_DESCRIPTION);
    }

}
