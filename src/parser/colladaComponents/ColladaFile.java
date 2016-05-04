package parser.colladaComponents;

import java.util.List;

/**
 * Created by rj on 3/28/2016.
 */
public final class ColladaFile {

    public String version = "1.4.1";
    public Asset assets;
    public Camera camera;
    public List<Light> lights;
    public List<Image> images;
    public List<Effect> effects;
    public List<Material> materials;
    public List<Geometry> geoms;
    public List<Scene> scenes;
    public String useScene;

}
