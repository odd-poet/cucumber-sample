package net.oddpoet.cucumber.sample.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:14
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ResultWriter  {
    @Autowired
    @Qualifier("outputWriter")
    PrintWriter outputWriter;

    public void write(Set<Set<Integer>> optimalTeams) {
        outputWriter.println("팀 구성 결과");

        for (Set<Integer> team : optimalTeams) {
            outputWriter.println(teamToString(team));
        }
    }

    private String teamToString(Set<Integer> team) {
        return StringUtils.collectionToDelimitedString(team, " ");
    }
}
