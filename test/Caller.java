import parser.ColladaCollector;
import parser.colladaComponents.cGeometry;
import parser.colladaComponents.cScene;
import parser.colladaComponents.nodeComponent.*;

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
            List<cScene> sceneList = collector.collectScene();
            for (cScene scene : sceneList) {
                for (cNode node : scene.nodes) {
                    for (cInstance_Node instance_node : node.instance_nodes) {
                        if (instance_node instanceof cInstance_Camera) {

                        } else if (instance_node instanceof cInstance_Light) {

                        } else if (instance_node instanceof cInstance_Geometry) {
                            //get url and then get geometry related to it
                            cInstance_Geometry instance_geometry = (cInstance_Geometry) instance_node;
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
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
