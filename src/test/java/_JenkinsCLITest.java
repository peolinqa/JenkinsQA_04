import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _JenkinsCLITest extends BaseTest {

    private static final String[] COMMANDNAMES = {"add-job-to-view", "build", "cancel-quiet-down", "clear-queue",
            "connect-node", "console", "copy-job", "create-credentials-by-xml",
            "create-credentials-domain-by-xml", "create-job", "create-node",
            "create-view", "declarative-linter", "delete-builds",
            "delete-credentials", "delete-credentials-domain", "delete-job", "delete-node", "delete-view",
            "disable-job", "disable-plugin", "disconnect-node", "enable-job", "enable-plugin",
            "get-credentials-as-xml", "get-credentials-domain-as-xml", "get-gradle", "get-job",
            "get-node", "get-view", "groovy", "groovysh", "help", "import-credentials-as-xml",
            "install-plugin", "keep-build", "list-changes", "list-credentials", "list-credentials-as-xml",
            "list-credentials-context-resolvers", "list-credentials-providers", "list-jobs", "list-plugins",
            "mail", "offline-node", "online-node", "quiet-down", "reload-configuration", "reload-job", "remove-job-from-view",
            "replay-pipeline", "restart", "restart-from-stage", "safe-restart", "safe-shutdown",
            "session-id", "set-build-description", "set-build-display-name", "shutdown", "stop-builds",
            "update-credentials-by-xml", "update-credentials-domain-by-xml", "update-job", "update-node", "update-view",
            "version", "wait-node-offline", "wait-node-online", "who-am-i"};

    private static final String[] COMMANDDESCRIPTIONS = {"Adds jobs to view.",
            "Builds a job, and optionally waits until its completion.",
            "Cancel the effect of the \"quiet-down\" command.",
            "Clears the build queue.", "Reconnect to a node(s)", "Retrieves console output of a build.",
            "Copies a job.", "Create Credential by XML", "Create Credentials Domain by XML",
            "Creates a new job by reading stdin as a configuration XML file.",
            "Creates a new node by reading stdin as a XML configuration.",
            "Creates a new view by reading stdin as a XML configuration.",
            "Validate a Jenkinsfile containing a Declarative Pipeline",
            "Deletes build record(s).", "Delete a Credential", "Delete a Credentials Domain",
            "Deletes job(s).", "Deletes node(s)", "Deletes view(s).", "Disables a job.",
            "Disable one or more installed plugins.", "Disconnects from a node.",
            "Enables a job.", "Enables one or more installed plugins transitively.",
            "Get a Credentials as XML (secrets redacted)", "Get a Credentials Domain as XML",
            "List available gradle installations", "Dumps the job definition XML to stdout.",
            "Dumps the node definition XML to stdout.", "Dumps the view definition XML to stdout.",
            "Executes the specified Groovy script.", "Runs an interactive groovy shell.",
            "Lists all the available commands or a detailed description of single command.",
            "Import credentials as XML. The output of \"list-credentials-as-xml\" can be used as input here as is, the only needed change is to set the actual Secrets which are redacted in the output.",
            "Installs a plugin either from a file, an URL, or from update center.",
            "Mark the build to keep the build forever.",
            "Dumps the changelog for the specified build(s).",
            "Lists the Credentials in a specific Store",
            "Export credentials as XML. The output of this command can be used as input for \"import-credentials-as-xml\" as is, the only needed change is to set the actual Secrets which are redacted in the output.",
            "List Credentials Context Resolvers",
            "List Credentials Providers", "Lists all jobs in a specific view or item group.",
            "Outputs a list of installed plugins.", "Reads stdin and sends that out as an e-mail.",
            "Stop using a node for performing builds temporarily, until the next \"online-node\" command.",
            "Resume using a node for performing builds, to cancel out the earlier \"offline-node\" command.",
            "Quiet down Jenkins, in preparation for a restart. Don’t start any builds.",
            "Discard all the loaded data in memory and reload everything from file system. Useful when you modified config files directly on disk.",
            "Reload job(s)", "Removes jobs from view.",
            "Replay a Pipeline build with edited script taken from standard input",
            "Restart Jenkins.", "Restart a completed Declarative Pipeline build from a given stage.",
            "Safely restart Jenkins.",
            "Puts Jenkins into the quiet mode, wait for existing builds to be completed, and then shut down Jenkins.",
            "Outputs the session ID, which changes every time Jenkins restarts.",
            "Sets the description of a build.", "Sets the displayName of a build.",
            "Immediately shuts down Jenkins server.", "Stop all running builds for job(s)",
            "Update Credentials by XML", "Update Credentials Domain by XML",
            "Updates the job definition XML from stdin. The opposite of the get-job command.",
            "Updates the node definition XML from stdin. The opposite of the get-node command.",
            "Updates the view definition XML from stdin. The opposite of the get-view command.",
            "Outputs the current version.", "Wait for a node to become offline.",
            "Wait for a node to become online.", "Reports your credential and permissions."};

    private int getNumberOfCommands() {
        int number = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickJenkinsCLI()
                .getNumberOfCommands();
        return number;
    }

    @Test
    public void checkCommandNameTest() {
        for (int i = 0; i < getNumberOfCommands(); i++) {
            String commandName = new HomePage(getDriver())
                    .getSideMenu()
                    .clickManageJenkins()
                    .clickJenkinsCLI()
                    .getCommandName(i);

            Assert.assertEquals(commandName, COMMANDNAMES[i]);
        }
    }

    @Test
    public void checkCommandDescriptionTest() {
        for (int i = 0; i < getNumberOfCommands(); i++) {
            String commandDescription = new HomePage(getDriver())
                    .getSideMenu()
                    .clickManageJenkins()
                    .clickJenkinsCLI()
                    .getCommandDescription(i);

            Assert.assertEquals(commandDescription, COMMANDDESCRIPTIONS[i]);
        }
    }

    @Test
    public void checkCommandExample() {
        for (int i = 0; i < getNumberOfCommands(); i++) {
            boolean isAddJobToViewExample = new HomePage(getDriver())
                    .getSideMenu()
                    .clickManageJenkins()
                    .clickJenkinsCLI()
                    .clickCommandElement(i)
                    .getCommandExample(COMMANDNAMES[i]);

            Assert.assertTrue(isAddJobToViewExample);
        }
    }

    @Test(dependsOnMethods = {"checkCommandNameTest", "checkCommandDescriptionTest", "checkCommandExample"})
    public void checkSortByName() {
        String firstCommandName = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickJenkinsCLI()
                .clickNameCaption()
                .getCommandName(0);

        Assert.assertEquals(firstCommandName, COMMANDNAMES[COMMANDNAMES.length - 1]);
    }

    @Test(dependsOnMethods = {"checkCommandNameTest", "checkCommandDescriptionTest", "checkCommandExample"})
    public void checkSortByDescription() {
        String firstDescription = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickJenkinsCLI()
                .clickDescriptionCaption()
                .getCommandDescription(0);

        Assert.assertEquals(firstDescription, COMMANDDESCRIPTIONS[COMMANDDESCRIPTIONS.length - 2]);
    }
}
