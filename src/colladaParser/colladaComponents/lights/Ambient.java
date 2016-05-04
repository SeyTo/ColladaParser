package colladaParser.colladaComponents.lights;

import colladaParser.colladaComponents.cLight;
import colladaParser.colladaComponents.utils.RGB;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by rj on 4/27/2016.
 */
public class Ambient extends cLight {

    public RGB color;

    @Override
    public void collect(Element element, Namespace ns) {
        color = new RGB(element.getChildText("color", ns));
    }

    @Override
    public String toString() {
        return "Ambient{" + "color=" + color + '}';
    }
}
