package colladaParser.colladaComponents;

import java.util.List;

/**
 * Created by rj on 3/28/2016.
 */
public final class ColladaFile {

    public String version = "1.4.1";
    public cAsset assets;
    public cCamera camera;
    public List<cImage> images;
    public List<cEffect> effects;
    public List<cMaterial> materials;
    public List<cGeometry> geoms;
    public List<cScene> scenes;
    public String useScene;

}
