package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public static void clickOKButton(WebDriver driver) {
        driver.findElement(By.id("ok-button")).click();
    }

    public static void createProject(WebDriver driver, NewItemTypes itemType) {
        Dashboard.Main.NewItem.click(driver);
        driver.findElement(By.id("name")).sendKeys(TestUtils.getRandomStr());
        itemType.click(driver);
        clickOKButton(driver);
        Dashboard.Main.Dashboard.click(driver);
    }

    public static void createProject(WebDriver driver, NewItemTypes itemType, String name) {
        Dashboard.Main.NewItem.click(driver);
        driver.findElement(By.id("name")).sendKeys(name);
        itemType.click(driver);
        clickOKButton(driver);
        Dashboard.Main.Dashboard.click(driver);
    }

    public static void openProject(WebDriver driver, String name) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", name))).click();
    }

    public static void goLoadStatisticsPage(WebDriver driver) {
        Dashboard.Main.ManageJenkins.click(driver);
        ManageJenkins.LoadStatistics.click(driver);
    }

    public static void goOnManageNodesAndCloudsPage(WebDriver driver) {
        Dashboard.Main.ManageJenkins.click(driver);
        ManageJenkins.ManageNodesAndClouds.click(driver);
    }

    public static List<WebElement> getComputerNames(WebDriver driver) {
        goOnManageNodesAndCloudsPage(driver);
        return driver.findElements(By.xpath("//table[@id='computers']/tbody/*/td[2]"));
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

    public static class Dashboard {

        public enum Header {
            Dashboard(By.linkText("Dashboard"));

            private final By locator;

            Header(By locator) {
                this.locator = locator;
            }

            public void click(WebDriver driver) {
                driver.findElement(locator).click();
            }
        }

        public enum Main {
            Dashboard(By.linkText("Dashboard")),
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

    public enum NewItemTypes {
        FreestyleProject(By.className("hudson_model_FreeStyleProject")),
        Pipeline(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")),
        MultiConfigurationProject(By.className("hudson_matrix_MatrixProject")),
        Folder(By.className("com_cloudbees_hudson_plugins_folder_Folder")),
        MultiBranchPipeline(By.className("org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")),
        OrganizationFolder(By.className("jenkins_branch_OrganizationFolder"));

        private final By locator;

        NewItemTypes(By locator) {
            this.locator = locator;
        }

        public void click(WebDriver driver) {
            driver.findElement(locator).click();
        }
    }

}