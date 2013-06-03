package features.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.oddpoet.cucumber.sample.component.TeamCreateService;
import net.oddpoet.cucumber.sample.testsupport.LoggedWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 27.
 * Time: 오후 1:50
 * To change this template use File | Settings | File Templates.
 */
public class InteractiveStepDef {
    @Autowired
    TeamCreateService teamCreateService;


    @Autowired
    @Qualifier("inputWriter")
    PrintWriter inputWriter;

    @Autowired
    @Qualifier("outputWriter")
    LoggedWriter outputLog;

    @Given("^시스템 출력: '(.+)'$")
    public void 시스템_출력_(String output) throws Throwable {
        String systemOut = outputLog.getLoggedLine();
        assertThat(systemOut, is(output));
    }

    @When("^사용자 입력: '(.+)'$")
    public void 사용자_입력_(String input) throws Throwable {
        inputWriter.println(input);
    }

    Thread thread;

    @Then("^팀 구성 결과를 출력한다$")
    public void 팀_구성_결과를_출력한다() throws Throwable {
         assertThat(outputLog.getLoggedLine(), is("팀 구성 결과"));
    }

    @Before
    public void setUp() {

        SystemRunner runner = new SystemRunner(teamCreateService);
        thread = new Thread(runner);
        thread.start();
    }

    @After
    public void tearDown() throws IOException {
        thread.stop();
        outputLog.clearLogs();
    }

    class SystemRunner implements Runnable {
        private TeamCreateService service;

        public SystemRunner(TeamCreateService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.start();
        }
    }

}

