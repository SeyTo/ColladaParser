package parser.colladaComponents.nodeComponent;

import parser.colladaComponents.utils.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class cNode {

    public enum TYPE {
        NODE, JOINT
    }

    public String id;
    public String name;
    public TYPE type = TYPE.NODE;

    public float[][] matrixTransform;

    public List<cInstance_Node> instance_nodes;  //TODO put all instance nodes inside this node, rewire

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
        instance_nodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String out = "cNode => id = " + id + ", name = " + name + ", type = " + type.name() + "\nMatrixTransform : ";
        out += Printer.toString(matrixTransform) + "\n";
        for (cInstance_Node node : instance_nodes) {
            out += node.toString() + "\n";
        }
        return out;
    }
}
