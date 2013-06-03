package net.oddpoet.cucumber.sample.component;

import net.oddpoet.cucumber.sample.model.InputData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:06
 * To change this template use File | Settings | File Templates.
 */

@Component
public class TeamMaker {
    public Set<Set<Integer>> findAllTeams(InputData data) {

        return findTeamsFor(data.getPlayers(), data);
    }

    private Set<Set<Integer>> findTeamsFor(Set<Integer> players, InputData data) {
        Set<Set<Integer>> teams = new HashSet<>();
        for (Integer player : players) {
            Set<Integer> favorPlayers = new HashSet<>(players);
            favorPlayers.remove(player);
            favorPlayers.removeAll(data.getExclusivePlayers(player));
            if (favorPlayers.size() > 0) {
                for (Set<Integer> team : findTeamsFor(favorPlayers, data)) {
                    team.add(player);
                    teams.add(team);
                }
            }

            Set<Integer> oneManTeam = new HashSet<>();
            oneManTeam.add(player);
            teams.add(oneManTeam);

        }
        return teams;
    }
}
