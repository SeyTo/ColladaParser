package parser.colladaComponents.nodeComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class cInstance_Geometry extends cInstance_Node {

    public List<Instance_Material> boundedMaterials;

    public cInstance_Geometry() {
        boundedMaterials = new ArrayList<>();
    }

    public Instance_Material getInstanceMaterial() {
        return new Instance_Material();
    }

    public class Instance_Material {
        public String symbol;
        public String target;
        public String bindVertexInput_InputSemantic;
        public String bindVertexInput_Semantic;

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
