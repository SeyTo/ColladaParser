package colladaParser.colladaComponents.nodeComponent;

import colladaParser.XMLCollector;
import colladaParser.colladaComponents.cComponent;
import colladaParser.colladaComponents.cLight;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class cInstance_Light extends cInstance_Node {

    public cLight light;

    public void collect(Element element, Namespace ns, List<? extends cComponent> lights) {
        url = element.getAttributeValue("url").substring(1);
        light = (cLight) XMLCollector.getComponent(lights, url);
    }

    @Override
    public String toString() {
        String out = "cInstance_Light => url = " + url + "\n";
        return out;
    }

}
