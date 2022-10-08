package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.*;
import model.home.HomePage;
import model.projects.folder.FolderConfigPage;
import model.projects.folder.FolderProjectPage;
import model.projects.freestyle.FreestyleConfigPage;
import model.projects.freestyle.FreestyleProjectPage;
import model.projects.multibranch.MultibranchPipelineConfigPage;
import model.projects.orgFolder.OrganizationFolderConfigPage;
import model.projects.pipeline.PipelineConfigPage;
import org.testng.Assert;
import runner.CucumberDriver;
import runner.ProjectUtils;


public class FreestyleTest {

    private HomePage homePage;
    private NewItemPage<?> newItemPage;

    private FreestyleConfigPage freestyleConfigPage;
    private PipelineConfigPage pipelineConfigPage;
    private FolderConfigPage folderConfigPage;
    private MultibranchPipelineConfigPage multibranchPipelineConfigPage;
    private model.projects.orgFolder.OrganizationFolderConfigPage OrganizationFolderConfigPage;

    private FreestyleProjectPage freestylePage;


    private FolderProjectPage folderPage;

    @When("Go to NewItem")
    public void goToNewItem() {
        newItemPage = new HomePage(CucumberDriver.getDriver())
                .getSideMenu()
                .clickMenuNewItem();
    }

    @And("Type project name {string}")
    public void setProjectName(String name) {
        newItemPage.setProjectName(name);
    }

    @And("Choose project type as {string}")
    public void setProjectType(String projectType) {
        if ("Freestyle".equals(projectType)) {
            newItemPage = newItemPage.setFreestyleProjectType();
        } else if ("Pipeline".equals(projectType)) {
            newItemPage = newItemPage.setPipelineProjectType();
        } else if ("Folder".equals(projectType)) {
            newItemPage = newItemPage.setFolderProjectType();
        } else if ("MultibranchPipeline".equals(projectType)) {
            newItemPage = newItemPage.setMultiBranchPipelineProjectType();
        } else if ("OrganizationFolder".equals(projectType)) {
            newItemPage = newItemPage.setOrganizationFolderProjectType();
        } else {
            throw new RuntimeException(String.format("Project type '%s' unknown", projectType));
        }
    }

    @And("Choose project type as Freestyle")
    public void setProjectTypeAsFreestyle() {
        newItemPage = newItemPage.setFreestyleProjectType();
    }

    @And("Choose project type as Folder")
    public void setProjectTypeAsFolder() {
        newItemPage = newItemPage.setFolderProjectType();
    }

    @And("Click Ok and go to config")
    public void clickOkAndGoToConfig() {
        Object config = newItemPage.clickOkAndGoToConfig();

        if (FreestyleConfigPage.class.equals(config.getClass())) {
            freestyleConfigPage = (FreestyleConfigPage) config;
        } else if (PipelineConfigPage.class.equals(config.getClass())) {
            pipelineConfigPage = (PipelineConfigPage) config;
        } else if (FolderConfigPage.class.equals(config.getClass())) {
            folderConfigPage = (FolderConfigPage) config;
        } else if (MultibranchPipelineConfigPage.class.equals(config.getClass())) {
            multibranchPipelineConfigPage = (MultibranchPipelineConfigPage) config;
        } else if (OrganizationFolderConfigPage.class.equals(config.getClass())) {
            OrganizationFolderConfigPage = (OrganizationFolderConfigPage) config;
        }
    }

    @And("Go home")
    public void goHome() {
        ProjectUtils.get(CucumberDriver.getDriver());
        homePage = new HomePage(CucumberDriver.getDriver());
    }

    @And("Project with name {string} is exists")
    public void checkProjectName(String projectName) {
        Assert.assertTrue(homePage.getProjectsOnDashboardList().contains(projectName));
    }

    @And("Save config and go to Freestyle project")
    public void saveConfigAndGoToFreestyleProject() {
        freestylePage = freestyleConfigPage.saveProjectConfiguration();
    }

    @And("Save config and go to Folder project")
    public void saveConfigAndGoToFolderProject() {
        folderPage = folderConfigPage.saveProjectConfiguration();
    }

    @Then("Freestyle project name is {string}")
    public void assertFreestyleProjectName(String projectName) {
        Assert.assertEquals(freestylePage.getProjectNameText(), projectName);
    }

    @Then("Folder project name is {string}")
    public void assertFolderProjectName(String projectName) {
        Assert.assertEquals(folderPage.getProjectNameText(), projectName);
    }

    @When("Click Freestyle project {string}")
    public void clickFreestyleProject(String projectName) {
        freestylePage = new HomePage(CucumberDriver.getDriver()).clickFreestyleName(projectName);
    }

    @And("Click Freestyle configure")
    public void clickFreestyleConfigure() {
        freestyleConfigPage = freestylePage.getSideMenu().clickMenuConfigure();
    }

    @And("Type Freestyle project description as {string}")
    public void setFreestyleProjectDescription(String projectDescription) {
        freestyleConfigPage.setDescription(projectDescription);
    }

    @Then("Project description is {string}")
    public void assertFreestyleProjectDescription(String projectDescription) {
        Assert.assertEquals(freestylePage.getDescriptionText(), projectDescription);
    }
}
