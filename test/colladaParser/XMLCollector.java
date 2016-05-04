package colladaParser;

import colladaParser.colladaComponents.*;
import colladaParser.colladaComponents.utils.NewParam;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rj on 4/25/2016.
 */
public abstract class XMLCollector {

    protected Element root;
    protected Namespace ns;

    public XMLCollector(File file) {
        final SAXBuilder builder = new SAXBuilder();
        Document document = null;
        try {
            document = builder.build(file);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root = document.getRootElement();
        ns = root.getNamespace();
    }

    public abstract List<cScene> collectScenes();

    public abstract cAsset collectAssets();

    public abstract List<cEffect> collectEffects();

    public abstract List<cMaterial> collectMaterials();

    public abstract List<cGeometry> collectGeometry();

    public abstract List<cCamera> collectCameras();

    public abstract List<cLight> collectLights();

    public abstract List<cImage> collectImages();

    //Utils

    /**
     * Get cComponent matching url
     * @param component
     * @param url the url to match with the list of cameras
     * @return
     */
    public static cComponent getComponent(List<? extends cComponent> component, String url) {
        for (cComponent comp : component)
            if (comp.id == url)
                return comp;
        return null;
    }

    public static NewParam.Surface getSurface(List<NewParam.Surface> surfaces, String url) {
        for (NewParam.Surface comp : surfaces)
            if (comp.sid == url)
                return comp;
        return null;
    }

}
