package parser.colladaComponents.nodeComponent;

import parser.colladaComponents.utils.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class Node {

    public enum TYPE {
        NODE, JOINT;
    }

    public String id;
    public String name;
    public TYPE type = TYPE.NODE;

    public float[][] matrixTransform;

    public List<Instance_Node> instance_nodes;  //TODO put all instance nodes inside this node, rewire

    public static TYPE getType(String value) {
        switch (value) {
            case "NODE":
                return TYPE.NODE;
            case "JOINT" :
                return TYPE.JOINT;
        }
        return null;
    }

    public static Instance_Node getNode(String name) {
        Instance_Node node;
        switch (name) {
            case "instance_camera":
                return node = new Instance_Camera();
            case "instance_light" :
                return node = new Instance_Light();
            case "instance_geometry" :
                return node = new Instance_Geometry();
            default:
                return null;
        }
    }

    public Node() {
        instance_nodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String out = "Node => id = " + id + ", name = " + name + ", type = " + type.name() + "\nMatrixTransform : ";
        out += Printer.toString(matrixTransform) + "\n";
        for (Instance_Node node : instance_nodes) {
            out += node.toString() + "\n";
        }
        return out;
    }
}
