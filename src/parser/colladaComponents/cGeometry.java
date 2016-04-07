package parser.colladaComponents;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cGeometry {

    public String id;
    public String name;
    public List<cMesh> meshes;

    @Override
    public String toString() {
        return "cGeometry{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", meshes=" + meshes +
                '}';
    }
}
