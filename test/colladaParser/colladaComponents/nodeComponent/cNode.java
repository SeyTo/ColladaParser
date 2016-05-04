package colladaParser.colladaComponents.nodeComponent;

import colladaParser.colladaComponents.utils.Printer;
import org.jdom2.Element;
import org.jdom2.Namespace;

import static colladaParser.colladaComponents.utils.Collection.collectFloatArrays;

/**
 * Created by rj on 4/2/2016.
 */
public final class cNode {

    public enum TYPE {
        NODE, JOINT
    }

    public String id;
    public String name;
    public TYPE type = TYPE.NODE;

    public float[] matrixTransform;

    //Only one instance_node per node, see documentation for instance_node
    public cInstance_Node instanceNode;  //TODO put all instance nodes inside this node, rewire

    public static TYPE getType(String value) {
        switch (value) {
            case "NODE":
                return TYPE.NODE;
            case "JOINT" :
                return TYPE.JOINT;
        }
        return null;
    }

    public static cInstance_Node getNode(String name) {
        cInstance_Node node;
        switch (name) {
            case "instance_camera":
                return node = new cInstance_Camera();
            case "instance_light" :
                return node = new cInstance_Light();
            case "instance_geometry" :
                return node = new cInstance_Geometry();
            default:
                return null;
        }
    }

    public cNode() {
    }

    public void collect(Element element, Namespace ns) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
        type =  cNode.getType(element.getAttributeValue("type"));
        matrixTransform = collectFloatArrays(element.getChild("matrix",ns).getText(), 16);
    }

    @Override
    public String toString() {
        String out = "cNode => id = " + id + ", name = " + name + ", type = " + type.name() + "\nMatrixTransform : ";
        out += Printer.toString(matrixTransform) + "\n";
        out += instanceNode.toString() + "\n";
        return out;
    }
}
