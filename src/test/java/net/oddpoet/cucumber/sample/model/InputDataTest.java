package net.oddpoet.cucumber.sample.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:42
 * To change this template use File | Settings | File Templates.
 */
public class InputDataTest {


    @Test
    public void 선수1이_2를_싫어하면_선수2_기준으로도_1은_공존할수_없는_선수다() {
        // Given
        InputData data = new InputData(5);

        // When
        data.addPlayerInfo(1, 2); // 1 disklike 2

        // Then
        assertThat(data.getExclusivePlayers(2), hasItem(1));
    }
}
