package features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;
import net.oddpoet.cucumber.sample.component.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 30.
 * Time: 오전 2:28
 * To change this template use File | Settings | File Templates.
 */
public class ValidationStepDef {

    @Autowired
    InputValidator inputValidator;

    String numOfPlayersInput;
    int numOfPlayers;
    String playerInfoInput;
    int playerId;

    @Given("^총원으로 (.+)가 입력된다$")
    public void 총원으로_playerCount_가_입력된다(String count) throws Throwable {
        this.numOfPlayersInput = count;
    }

    @Then("^입력은 (.+)하다$")
    public void 입력은_valid_하다(String valid) throws Throwable {
        boolean valid2boolean = valid.equals("valid");
        boolean result = inputValidator.isValidCount(numOfPlayersInput);

        assertThat(result, is(valid2boolean));
    }

    @Given("^총원이 '(\\d+)'이고$")
    public void 총원이_이고(int count) throws Throwable {
        this.numOfPlayers = count;
    }

    @When("^(\\d+)번 선수정보가 (.+)로 입력된다$")
    public void 선수정보가_playerInfo_로_입력된다(int playerId, String input) throws Throwable {
        this.playerId = playerId;
        this.playerInfoInput = input;
    }

    @Then("^그 입력 선수정보는 (.+)하다$")
    public void 그_입력_선수정보는_valid_하다(String valid) throws Throwable {
        boolean valid2boolean = valid.equals("valid");
        boolean result = inputValidator.isValidPlayerInfo(numOfPlayers, playerId, playerInfoInput);
        assertThat(result, is(valid2boolean));
    }
}
