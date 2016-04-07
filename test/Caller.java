import parser.ColladaCollector;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by rj on 3/23/2016.
 */
public class Caller {

    public static void main(String[] args) {

        try {
            ColladaCollector.init(new File(Caller.class.getResource("testCycles2.xml").toURI()));
            System.out.println(ColladaCollector.collectScene());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
