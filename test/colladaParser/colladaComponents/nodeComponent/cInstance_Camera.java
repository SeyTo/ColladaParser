package colladaParser.colladaComponents.nodeComponent;

import org.jdom2.Element;
import org.jdom2.Namespace;
import parser.colladaParser.XMLCollector;
import parser.colladaParser.colladaComponents.cCamera;
import parser.colladaParser.colladaComponents.cComponent;

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
