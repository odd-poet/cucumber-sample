package net.oddpoet.cucumber.sample.component;

import net.oddpoet.cucumber.sample.model.InputData;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 29.
 * Time: 오전 1:12
 * To change this template use File | Settings | File Templates.
 */
public class TeamMakerTest {

    TeamMaker sut = new TeamMaker();

    @Test
    public void test() {
        // Given
        InputData data = new InputData(5);
        data.addPlayerInfo(1, 2);
        data.addPlayerInfo(2, 3);
        data.addPlayerInfo(3);
        data.addPlayerInfo(4, 2, 3);
        data.addPlayerInfo(5, 4);

        // When
        Set<Set<Integer>> teams = sut.findAllTeams(data);

        // Then
        assertThat(teams, hasItem(Sets.newSet(1, 3, 5)));
        assertThat(teams, hasItem(Sets.newSet(2, 5)));
        assertThat(teams, not(hasItem(Sets.newSet(4, 5))));

    }
}
