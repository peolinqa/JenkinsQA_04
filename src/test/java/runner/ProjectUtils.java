package runner;

import model.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

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
                .sendUser(getProperties().getProperty(PROP_ADMIN_USERNAME))
                .sendPassword(getProperties().getProperty(PROP_ADMIN_PAS))
                .login();
    }

    static void logout(WebDriver driver) {
        get(driver);

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
    }

    public static void clickOKButton(WebDriver driver) {
        driver.findElement(By.id("ok-button")).click();
    }

    public static String createProject(WebDriver driver, ProjectType itemType) {
        String name = TestUtils.getRandomStr();
        createProject(driver, itemType, name);

        return name;
    }

    public static void createProject(WebDriver driver, ProjectType itemType, String name) {
        Dashboard.Main.NewItem.click(driver);
        driver.findElement(By.id("name")).sendKeys(name);
        itemType.click(driver);
        clickOKButton(driver);
    }

    public static void openProject(WebDriver driver, String name) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", name))).click();
    }

    public static void clickSaveButton(WebDriver driver) {
        driver.findElement(By.xpath("//button[@type='submit' and contains(text(), 'Save')]")).click();
    }

    public static void clickDisableProject(WebDriver driver) {
        driver.findElement(By.xpath("//button[@type='submit' and contains(text(), 'Disable')]")).click();
    }

    public static void clickEnableProject(WebDriver driver) {
        driver.findElement(By.xpath("//button[@type='submit' and contains(text(), 'Enable')]")).click();
    }

    public static void deleteJobsWithPrefix(WebDriver driver, String prefix) {
        ProjectUtils.Dashboard.Header.Dashboard.click(driver);

        List<String> jobsNames = driver.findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        jobsNames
                .forEach(jobsName -> {
                    if (jobsName.startsWith(prefix)) {
                        String jobWithPercent = jobsName.replace(" ", "%20");
                        driver.findElement(By.xpath("//a[@href='job/" + jobWithPercent + "/']")).click();
                        driver.findElement(
                                By.xpath("//a[@data-url='/job/" + jobWithPercent + "/doDelete']")).click();
                        driver.switchTo().alert().accept();
                    }
                });
    }

    public static List<WebElement> selectSpecificJobFromListOfJobs(WebDriver driver, String name) {
        return driver.findElements(By.xpath(String.format("//a[contains(@href, 'job/%s')]", name)));
    }

    public static class Dashboard {

        public enum Header {
            Dashboard(By.linkText("Dashboard")),
            Builds(By.linkText("Builds")),
            Configure(By.linkText("Configure")),
            MyViews(By.linkText("My Views"));

            private final By locator;

            Header(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Main {
            NewItem(By.linkText("New Item")),
            People(By.linkText("People")),
            BuildHistory(By.linkText("Build History")),
            ManageJenkins(By.linkText("Manage Jenkins")),
            MyViews(By.linkText("My Views")),
            LockableResources(By.linkText("Lockable Resources")),
            NewView(By.linkText("New View"));

            private final By locator;

            Main(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Project {
            BackToDashboard(By.linkText("Back to Dashboard")),
            Status(By.linkText("Status")),
            Changes(By.linkText("Changes")),
            Workspace(By.linkText("Workspace")),
            BuildNow(By.linkText("Build Now")),
            Configure(By.linkText("Configure")),
            DeleteProject(By.xpath("//span[text()='Delete Project']")),
            DeleteMultiConfigurationProject(By.xpath("//span[text()='Delete Multi-configuration project']")),
            Move(By.linkText("Move")),
            Rename(By.linkText("Rename"));

            private final By locator;

            Project(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Build {
            ConsoleOutput(By.linkText("Console Output")),
            Changes(By.linkText("Changes"));

            private final By locator;

            Build(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum JenkinsOwnUserDatabase {
            BackToDashboard(By.linkText("Back to Dashboard")),
            ManageJenkins(By.linkText("Manage Jenkins")),
            Users(By.linkText("Users")),
            CreateUser(By.linkText("Create User"));

            private final By locator;

            JenkinsOwnUserDatabase(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Folder {
            Up(By.linkText("Up")),
            Status(By.linkText("Status")),
            Configure(By.linkText("Configure")),
            NewItem(By.linkText("New Item")),
            DeleteFolder(By.linkText("Delete Folder")),
            People(By.linkText("People")),
            BuildHistory(By.linkText("Build History")),
            Move(By.linkText("Move")),
            Rename(By.linkText("Rename")),
            Credentials(By.linkText("Credentials")),
            NewView(By.linkText("NewView"));

            private final By locator;

            Folder(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum View {
            NewItem(By.linkText("New Item")),
            People(By.linkText("People")),
            BuildHistory(By.linkText("Build History")),
            EditView(By.linkText("Edit View")),
            DeleteView(By.linkText("Delete View")),
            NewView(By.linkText("New View"));

            private final By locator;

            View(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Pipeline {
            BackToDashboard(By.linkText("Back to Dashboard")),
            Status(By.linkText("Status")),
            Changes(By.linkText("Changes")),
            BuildNow(By.linkText("Build Now")),
            Configure(By.linkText("Configure")),
            DeletePipeline(By.xpath("//span[text()='Delete Pipeline']")),
            Move(By.linkText("Move")),
            FullStageView(By.linkText("Full Stage View")),
            Rename(By.linkText("Rename")),
            PipelineSyntax(By.linkText("Pipeine Syntax"));

            private final By locator;

            Pipeline(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }
    }

    public enum ManageJenkins {

        ConfigureSystem(By.xpath("//dt[text()='Configure System']")),
        GlobalToolConfiguration(By.xpath("//dt[text()='Global Tool Configuration']")),
        ManagePlugins(By.xpath("//dt[text()='Manage Plugins']")),
        ManageNodesAndClouds(By.xpath("//dt[text()='Manage Nodes and Clouds']")),
        ConfigureGlobalSecurity(By.xpath("//dt[text()='Configure Global Security']")),
        ManageCredentials(By.xpath("//dt[text()='Manage Credentials']")),
        ConfigureCredentialProviders(By.xpath("//dt[text()='Configure Credential Providers']")),
        ManageUsers(By.xpath("//dt[text()='Manage Users']")),
        SystemInformation(By.xpath("//dt[text()='System Information']")),
        CSystemLog(By.xpath("//dt[text()='System Log']")),
        LoadStatistics(By.xpath("//dt[text()='Load Statistics']")),
        AboutJenkins(By.xpath("//dt[text()='About Jenkins']")),
        ManageOldData(By.xpath("//dt[text()='Manage Old Data']")),
        ReloadConfigurationFromDisk(By.xpath("//dt[text()='Reload Configuration from Disk']")),
        JenkinsCLI(By.xpath("//dt[text()='Jenkins CLI']")),
        ScriptConsole(By.xpath("//dt[text()='Script Console']")),
        PrepareForShutdown(By.xpath("//dt[text()='Prepare for Shutdown']"));

        private final By locator;

        ManageJenkins(By locator) {
            this.locator = locator;
        }

        public void click(WebDriver driver) {
            driver.findElement(locator).click();
        }
    }

    public enum ProjectType {
        Freestyle(By.className("hudson_model_FreeStyleProject")),
        Pipeline(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")),
        MultiConfigurationProject(By.className("hudson_matrix_MatrixProject")),
        Folder(By.className("com_cloudbees_hudson_plugins_folder_Folder")),
        MultiBranchPipeline(By.className("org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")),
        OrganizationFolder(By.className("jenkins_branch_OrganizationFolder"));

        private final By locator;

        ProjectType(By locator) {
            this.locator = locator;
        }

        public void click(WebDriver driver) {
            driver.findElement(locator).click();
        }
    }

}