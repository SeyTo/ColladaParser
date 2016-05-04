package colladaParser.colladaComponents.nodeComponent;

import colladaParser.colladaComponents.cComponent;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * <p>Instance_node is either &lt;instance_camera> or &lt;instance_light> or &lt;instance_material>
 *     Instance_node is of 3 types so far. To know which class you have to promote to, you can simply
 * read the url. It contains hint for you such as "-light", "-camera", "-mesh".</p>
 * Created by rj on 4/2/2016.
 */
public abstract class cInstance_Node {

    public String url;

    public abstract void collect(Element element, Namespace ns, List<? extends cComponent> components);

}
