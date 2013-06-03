package net.oddpoet.cucumber.sample.component;

import net.oddpoet.cucumber.sample.model.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 27.
 * Time: 오후 4:19
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TeamCreateService {

    @Autowired
    private InputDataReader inputDataReader;

    @Autowired
    private TeamMaker teamMaker;

    @Autowired
    private OptimalTeamSelector optimalTeamSelector;

    @Autowired
    private ResultWriter resultWriter;

    public void start() {
        InputData data = inputDataReader.readInput();
        Set<Set<Integer>> possibleTeams = teamMaker.findAllTeams(data);
        Set<Set<Integer>> optimalTeams = optimalTeamSelector.findOptimalTeams(possibleTeams);
        resultWriter.write(optimalTeams);
    }
}
