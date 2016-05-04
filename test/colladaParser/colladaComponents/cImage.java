package colladaParser.colladaComponents;

import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by RaJU on 4/4/2016.
 */
public final class cImage extends cComponent{

    public String name;
    public String image;

    public void collect(Element element, Namespace ns) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
        image = element.getChildText("init_from", ns);
    }

    @Override
    public String toString() {
        return "cImage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
