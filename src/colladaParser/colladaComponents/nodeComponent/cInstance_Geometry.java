package colladaParser.colladaComponents.nodeComponent;

import colladaParser.XMLCollector;
import colladaParser.colladaComponents.cComponent;
import colladaParser.colladaComponents.cGeometry;
import colladaParser.colladaComponents.cMaterial;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RaJU on 4/2/2016.
 */
public final class cInstance_Geometry extends cInstance_Node {

    public cGeometry geometry;

    public List<Instance_Material> boundedMaterials;

    public cInstance_Geometry() {
        boundedMaterials = new ArrayList<>();
    }

    public Instance_Material getInstanceMaterial() {
        return new Instance_Material();
    }


    public void collect(Element element, Namespace ns, List<? extends cComponent> geometries) {
        url = element.getAttributeValue("url").substring(1);
        geometry = (cGeometry) XMLCollector.getComponent(geometries, url);
    }

    public class Instance_Material {
        public cMaterial material;
        public String symbol;
        public String target;
        public String bindVertexInput_InputSemantic;
        public String bindVertexInput_Semantic;

        public void collect(Element element, Namespace ns, List<cMaterial> materials) {
            symbol = element.getAttributeValue("symbol");
            target = element.getAttributeValue("target").substring(1);
            material = (cMaterial) XMLCollector.getComponent(materials, target);
            bindVertexInput_Semantic = element.getChild("bind_vertex_input",ns).getAttributeValue("semantic");
            bindVertexInput_InputSemantic = element.getChild("bind_vertex_input",ns).getAttributeValue("input_semantic");
        }

        @Override
        public String toString() {
            return "Instance_Material{" +
                    "symbol='" + symbol + '\'' +
                    ", target='" + target + '\'' +
                    ", bindVertexInput_InputSemantic='" + bindVertexInput_InputSemantic + '\'' +
                    ", bindVertexInput_Semantic='" + bindVertexInput_Semantic + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        String out = "cInstance_Geometry => url = " + url + "\n";
        for (Instance_Material material : boundedMaterials) {
            out += material.toString()+"\n";
        }
        return out;
    }
}
