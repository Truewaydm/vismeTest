package ui.extensions;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.*;

@Slf4j
public class TestLoggingListener implements BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {

    public static String separator = "*" + StringUtils.repeat("*", 160);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        log.info("\n" + separator + "\nStarting tests \'" + context.getRequiredTestClass() + "\'.\n" + separator);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        log.info("\n" + separator + "\nStarting \'" + context.getDisplayName() + "\' tests.\n" + separator);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        log.info("\n" + separator + "\nFinished tests \'" + context.getRequiredTestClass() + "\'.\n" + separator);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        log.info("\n" + separator + "\nFinnishing \'" + context.getDisplayName() + "\' tests.\n" + separator);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
    }
}
