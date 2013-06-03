package net.oddpoet.cucumber.sample.component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 11:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InputValidator {

    public boolean isValidCount(String playerCount) {
        if (isNumeric(playerCount) == false) {
            return false;
        }
        int count = Integer.parseInt(playerCount);
        return count >= 2 && count <= 100;
    }

    private final static Pattern NUMERIC = Pattern.compile("\\d+");

    private boolean isNumeric(String numeric) {
        return NUMERIC.matcher(numeric).matches();
    }

    private final static Pattern NUMERIC_SEQ = Pattern.compile("[\\d ]+");

    public boolean isValidPlayerInfo(int numberOfPlayers, int player, String input) {
        if (NUMERIC_SEQ.matcher(input).matches() == false) {
            return false;
        }
        List<Integer> intArray = string2intArry(input);
        if (intArray.size() < 2) {
            return false;
        }

        int playerId = intArray.get(0);
        int dislikeCount = intArray.get(1);

        intArray.remove(1);
        Set<Integer> players = new HashSet<>(intArray);

        boolean playerIdOk = (playerId == player);
        boolean countOk = (dislikeCount == intArray.size() - 1);
        boolean noDuplication = (players.size() == dislikeCount + 1);
        boolean validIds = true;
        for (int id : players) {
            validIds = validIds && (id >= 1 && id <= numberOfPlayers);
        }
        return playerIdOk && countOk && noDuplication && validIds;
    }


    private List<Integer> string2intArry(String input) {
        String[] tokens = input.split("\\s+");
        List<Integer> integers = new ArrayList<>();
        for (String token : tokens) {
            integers.add(Integer.valueOf(token));
        }
        return integers;
    }


}
