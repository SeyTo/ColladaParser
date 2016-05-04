package colladaParser.colladaComponents;

import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class cCamera extends cComponent {

    public String name;
    public Optic optic;


    public void collect(Element element, Namespace ns, Optic optic) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
        this.optic = optic;
    }

        public Perspective getPerspectiveInstance() {
            return new Perspective();
        }

        public Orthographic getOrthographicInstance() {
            return new Orthographic();
        }

    public String toString() {
        return "cCamera{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", optic=" + optic +
                '}';
    }

    public abstract class Optic {

        public abstract void collect(Element element, Namespace ns);

    }

    public final class Perspective extends Optic {
            public float xfov;
            public float aspectRatio;
            public float zNear;
            public float zFar;

        @Override
        public void collect(Element element, Namespace ns) {
            xfov = Float.valueOf(element.getChildText("xfov", ns));
            aspectRatio = Float.valueOf(element.getChildText("aspect_ratio", ns));
            zNear = Float.valueOf(element.getChildText("znear", ns));
            zFar = Float.valueOf(element.getChildText("zfar", ns));
        }

        @Override
        public String toString() {
            return "Perspective{" +
                    "xfov='" + xfov + '\'' +
                    ", aspectRatio='" + aspectRatio + '\'' +
                    ", zNear='" + zNear + '\'' +
                    ", zFar='" + zFar + '\'' +
                    '}';
        }
    }

        public final class Orthographic extends Optic {

            public float xmag;
            public float aspect_ratio;
            public float znear;
            public float zfar;

            @Override
            public void collect(Element element, Namespace ns) {
                xmag = Float.valueOf(element.getChildText("xmag", ns));
                aspect_ratio = Float.valueOf(element.getChildText("aspect_ratio", ns));
                znear = Float.valueOf(element.getChildText("znear", ns));
                zfar = Float.valueOf(element.getChildText("zfar", ns));
            }

            @Override
            public String toString() {
                return "Orthographic{" + "xmag=" + xmag + ", aspect_ratio=" + aspect_ratio +
                        ", znear=" + znear +
                        ", zfar=" + zfar +
                        '}';
            }
        }


}
