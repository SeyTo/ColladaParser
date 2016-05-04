package colladaParser.colladaComponents.nodeComponent;

import colladaParser.XMLCollector;
import colladaParser.colladaComponents.cCamera;
import colladaParser.colladaComponents.cComponent;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class cInstance_Camera extends cInstance_Node {

    public cCamera camera;

    public void collect(Element element, Namespace ns, List<? extends cComponent> cameras) {
        url = element.getAttributeValue("url").substring(1);
        camera = (cCamera) XMLCollector.getComponent(cameras, url);
    }

}
