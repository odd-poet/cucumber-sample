package net.oddpoet.cucumber.sample.model;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:06
 * To change this template use File | Settings | File Templates.
 */
public class InputData {
    private int playerCount;
    private Map<Integer, Set<Integer>> exclusive = new HashMap<>();

    public InputData(int playerCount) {
        this.playerCount = playerCount;
        for (int i = 0; i < playerCount; i++) {
            int playerId = i + 1;
            exclusive.put(playerId, new HashSet<Integer>());
        }
    }

    public void addPlayerInfo(Integer playerId, Integer... dislikePlayerIds) {
        List<Integer> dislikePlayers = Arrays.asList(dislikePlayerIds);
        exclusive.get(playerId).addAll(dislikePlayers);

        for (Integer pId : dislikePlayers) {
            if (exclusive.containsKey(pId) == false) {
                exclusive.put(pId, new HashSet<Integer>());
            }
            exclusive.get(pId).add(playerId);
        }
    }

    public Set<Integer> getPlayers() {
        return exclusive.keySet();
    }

    public Set<Integer> getExclusivePlayers(int playerId) {
        return exclusive.get(playerId);
    }

}
