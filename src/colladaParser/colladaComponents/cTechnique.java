package colladaParser.colladaComponents;

import colladaParser.colladaComponents.utils.Color;
import colladaParser.colladaComponents.utils.RGBA;
import colladaParser.colladaComponents.utils.Texture;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by rj on 3/25/2016.
 */
public abstract class cTechnique {

    public Color bump_texture;

    public void collect(Element element, Namespace ns, cTechnique technique) {
        Element innerfxs;
        if ((innerfxs = element.getChild("color", ns)) != null) {
            technique.setColor(element.getName(), new RGBA(innerfxs.getText()));
        } else if ((innerfxs = element.getChild("float", ns)) != null) {
            technique.setFloat(element.getName(), Float.valueOf(innerfxs.getText()));
        } else if ((innerfxs = element.getChild("texture", ns)) != null) {
            technique.setColor(element.getName(),
                    new Texture(innerfxs.getAttributeValue("texture"), innerfxs.getAttributeValue("texcoord")));
        }
    }

    public abstract void setColor(String name, Color value);
    public abstract void setFloat(String name, float value);

}
