package colladaParser.colladaComponents;

import colladaParser.XMLCollector;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cMaterial extends cComponent{

    public String name;
    public String url;
    public cEffect effect;

    public void collect(Element element, Namespace ns, List<cEffect> effects) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
        url = element.getChild("instance_effect", ns).getAttributeValue("url").substring(1);
        effect = (cEffect) XMLCollector.getComponent(effects, url);
    }

    @Override
    public String toString() {
        return "cMaterial{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
