import colladaParser.ColladaCollector;
import colladaParser.colladaComponents.cGeometry;
import colladaParser.colladaComponents.cScene;
import colladaParser.colladaComponents.nodeComponent.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by rj on 3/23/2016.
 */
public class Caller {

    public static void main(String[] args) {

        try {
            ColladaCollector collector = new ColladaCollector(new File(Caller.class.getResource("testCycles2.xml").toURI()));
            List<cScene> sceneList = collector.collectScenes();
            for (cScene scene : sceneList) {
                for (cNode node : scene.nodes) {
                        if (node.instanceNode instanceof cInstance_Camera) {

                        } else if (node.instanceNode instanceof cInstance_Light) {

                        } else if (node.instanceNode instanceof cInstance_Geometry) {
                            //get url and then get geometry related to it
                            cInstance_Geometry instance_geometry = (cInstance_Geometry) node.instanceNode;
                            String url = instance_geometry.url;
                            List<cGeometry> geometries = collector.collectGeometry();
                            for (int i = 0; i < geometries.size(); i++) {
                                if (geometries.get(i).id.equals(url)) {

                                }
                            }
                            //store transform to apply
                        }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
