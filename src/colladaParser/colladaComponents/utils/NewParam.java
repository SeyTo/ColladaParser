package colladaParser.colladaComponents.utils;

import colladaParser.XMLCollector;
import colladaParser.colladaComponents.cImage;
import org.jdom2.Element;
import org.jdom2.Namespace;
import java.util.List;

/**
 * Created by rj on 4/5/2016.
 */
public class NewParam {

    public String sid;

    public Sampler2D getSampler2DInstance() {
        return new Sampler2D();
    }

    public Surface getSurfaceInstance() {
        return new Surface();
    }

    public final class Sampler2D extends NewParam {
        public Surface source;

        public void collect(Element element, Namespace ns, List<Surface> surfaces) {
            sid = element.getAttributeValue("sid");
            source = XMLCollector.getSurface(surfaces, element.getChildText("source", ns));   //TEST
        }

        @Override
        public String toString() {
            return "Sampler2D{ sid=" + sid +
                    " source='" + source + '\'' +
                    '}';
        }
    }

    public final class Surface extends NewParam {
        public String type;
        public cImage init_from;

        public void collect(Element element, Namespace ns, List<cImage> images) {
            sid = element.getAttributeValue("sid");
            type = element.getAttributeValue("type");
            init_from = (cImage) XMLCollector.getComponent(images, element.getChildText("init_from", ns));
        }

        @Override
        public String toString() {
            return "Surface{ sid=" + sid +
                    " type='" + type + '\'' +
                    ", init_from='" + init_from + '\'' +
                    '}';
        }
    }

}
