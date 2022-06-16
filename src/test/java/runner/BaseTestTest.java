package runner;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTestTest extends BaseTest {

    private static final String BROWSER_OPENS = "Browser opens";
    private static final String BROWSER_CLOSED = "Browser closed";
    private static final String WEB_LOGIN = "Web login";
    private static final String WEB_GET = "Web get";

    private static final String TEST_11 = "test11";
    private static final String TEST_12 = "test12";
    private static final String TEST_13 = "test13";
    private static final String TEST_21 = "test21";
    private static final String TEST_22 = "test22";
    private static final String TEST_3  = "test3";

    private static final List<List<String>> RESULTS = List.of(
            List.of(BROWSER_OPENS, WEB_LOGIN, TEST_13, WEB_GET, TEST_11 + TEST_12, WEB_GET, TEST_12 + TEST_11, BROWSER_CLOSED),
            List.of(BROWSER_OPENS, WEB_LOGIN, TEST_22, WEB_GET, TEST_21, BROWSER_CLOSED),
            List.of(BROWSER_OPENS, WEB_LOGIN, TEST_3, BROWSER_CLOSED));

    private final List<String> logs = new ArrayList<>();

    @Override
    protected void loginWeb() {
        logs.add(WEB_LOGIN);
    }

    @Override
    protected void getWeb() {
        logs.add(WEB_GET);
    }

    @Override
    protected void startDriver() {
        logs.add(BROWSER_OPENS);
    }

    @Override
    protected void stopDriver() {
        logs.add(BROWSER_CLOSED);
    }

    @Test
    public void test13() {
        logs.add(TEST_13);
    }

    @Test(dependsOnMethods = TEST_13)
    public void test11() {
        logs.add(TEST_11);
    }

    @Test
    public void test3() {
        logs.add(TEST_3);
    }

    @Test(dependsOnMethods = TEST_13)
    public void test12() {
        logs.add(TEST_12);
    }

    @Test
    public void test22() {
        logs.add(TEST_22);
    }

    @Test(dependsOnMethods = TEST_22)
    public void test21() {
        logs.add(TEST_21);
    }

    @AfterClass
    void checkResult(ITestContext testContext) {

        int index = 0;
        Set<Integer> usedChain = new HashSet<>();
        List<Integer> usingChain = null;

        for (int i = 0; i < logs.size(); i++) {
            if (usingChain == null) {
                usingChain = IntStream.range(0, RESULTS.size()).boxed().filter(n -> !usedChain.contains(n)).collect(Collectors.toList());
            }

            for (Iterator<Integer> it = usingChain.iterator(); it.hasNext();) {
                int number = it.next();
                if (RESULTS.get(number).size() <= index || !RESULTS.get(number).get(index).contains(logs.get(i))) {
                    it.remove();
                }
            }
            index++;

            if (usingChain.size() == 1 && RESULTS.get(usingChain.get(0)).size() == index) {
                usedChain.add(usingChain.get(0));
                usingChain = null;
                index = 0;
            }

            Assert.assertTrue(usingChain == null || usingChain.size() > 0);
        }

        Assert.assertEquals(usedChain.size(), RESULTS.size());
    }
}
