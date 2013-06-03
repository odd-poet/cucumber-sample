package net.oddpoet.cucumber.sample.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 27.
 * Time: 오전 3:17
 * To change this template use File | Settings | File Templates.
 */
public class InputOutputFactoryBean {

    public BufferedReader getInputReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }


    public PrintWriter getOutputWriter() {
        // 실행환경 별 (IDE or Console)
        if (System.console() != null) {
            return System.console().writer();
        } else {
            return new PrintWriter(new OutputStreamWriter(System.out));
        }
    }
}
