package net.oddpoet.cucumber.sample.component;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:10
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OptimalTeamSelector {
    public Set<Set<Integer>> findOptimalTeams(Set<Set<Integer>> possibleTeams) {
        return possibleTeams;
    }
}
