package parser.colladaComponents;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class Geometry {

    public String id;
    public String name;
    public List<Mesh> meshes;
    public String verticesSource;

    @Override
    public String toString() {
        return "Geometry{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", verticesSource='" + verticesSource + '\'' +
                ", meshes=" + meshes +
                '}';
    }
}
