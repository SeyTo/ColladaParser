package colladaParser.colladaComponents;

import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by rj on 4/27/2016.
 */
public abstract class cLight extends cComponent{

    public String name;

    public abstract void collect(Element element, Namespace ns);

}
