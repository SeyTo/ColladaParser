package colladaParser.colladaComponents;

import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cGeometry extends cComponent{

    public String id;
    public String name;
    public List<cMesh> meshes;

    public cGeometry() {
        this.meshes = new ArrayList<>();
    }

    public void collect(Element element, Namespace ns) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
    }

    @Override
    public String toString() {
        return "cGeometry{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", meshes=" + meshes +
                '}';
    }
}
