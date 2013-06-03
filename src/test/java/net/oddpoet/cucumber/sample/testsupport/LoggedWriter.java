package net.oddpoet.cucumber.sample.testsupport;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 30.
 * Time: 오전 1:40
 * To change this template use File | Settings | File Templates.
 */
public class LoggedWriter extends PrintWriter {
    private List<String> lines = Collections.synchronizedList(new ArrayList<String>());

    public LoggedWriter() {
        super(System.out);
    }

    @Override
    public void println(String line) {
        lines.add(line);
    }

    public String getLoggedLine() {
        while(true) {
            if (lines.size()>0) {
                return lines.remove(0);
            } else {
//                try {
//                    Thread.sleep(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
                continue;
            }
        }

    }

    public void clearLogs() {
        lines.clear();
    }
}
