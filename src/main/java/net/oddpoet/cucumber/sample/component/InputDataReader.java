package net.oddpoet.cucumber.sample.component;

import net.oddpoet.cucumber.sample.model.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 10:03
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InputDataReader implements MessageSourceAware {
    @Autowired
    @Qualifier("inputReader")
    private BufferedReader inputReader;

    @Qualifier("outputWriter")
    @Autowired
    private PrintWriter outputWriter;

    @Autowired
    private InputValidator inputValidator;

    private MessageSource message;

    public InputData readInput() {
        int numberOfPlayers = readNumberOfPlayers();
        InputData data = new InputData(numberOfPlayers);
        for (int playerId = 1; playerId <= numberOfPlayers; playerId++) {
            Integer[] dislikePlayers = readPlayerInfoFor(playerId, numberOfPlayers);
            data.addPlayerInfo(playerId, dislikePlayers);
        }

        return data;
    }

    private Integer[] readPlayerInfoFor(int playerId, int numberOfPlayers) {
        boolean failed = false;
        while (true) {
            if (failed) {
                outputWriter.println(getMessage("promptPlayerInfoAgain", playerId));
            } else {
                outputWriter.println(getMessage("promptPlayerInfo", playerId));
            }
            String line = readLine();
            if (line != null && inputValidator.isValidPlayerInfo(numberOfPlayers, playerId, line)) {
                String[] tokens = line.split("\\s+");
                Integer[] dislikes = new Integer[tokens.length - 2];
                for (int i = 2; i < tokens.length; i++) {
                    dislikes[i - 2] = Integer.valueOf(tokens[i]);
                }
                return dislikes;
            } else {
                failed = true;
            }
        }
    }

    private int readNumberOfPlayers() {
        boolean failed = false;
        while (true) {
            if (failed) {
                outputWriter.println(getMessage("promptPlayerCountAgain"));
            } else {
                outputWriter.println(getMessage("promptPlayerCount"));
            }
            String line = readLine();
            if (line != null && inputValidator.isValidCount(line)) {
                return Integer.valueOf(line);
            } else {
                failed = true;
            }
        }
    }


    private String readLine() {
        try {
            String aLine = inputReader.readLine();
            if (aLine != null) {
                return aLine.trim();
            }
        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }


    private String getMessage(String code, Object... args) {
        return message.getMessage(code, args, Locale.getDefault());
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.message = messageSource;
    }
}
