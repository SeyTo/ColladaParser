package parser;

import parser.colladaComponents.*;
import parser.colladaComponents.nodeComponent.Instance_Camera;
import parser.colladaComponents.nodeComponent.Instance_Geometry;
import parser.colladaComponents.nodeComponent.Instance_Light;
import parser.colladaComponents.nodeComponent.Node;
import parser.colladaComponents.Profile;
import parser.colladaComponents.Technique;
import parser.colladaComponents.utils.NewParam;
import parser.colladaComponents.utils.RGBA;
import parser.colladaComponents.utils.RGB;
import parser.colladaComponents.utils.Texture;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static parser.colladaComponents.utils.Collection.collectFloatArrays;
import static parser.colladaComponents.utils.Collection.collectIntArrays;

/**
 * <p><code>init()</code> call to read and ready a .dae file. Then each library component can be read individually.</p>
 * Created by rj on 3/23/2016.
 */
    //TODO type conversion where required
//TODO collada version check
    //TODO light collada test
    //TODO more tests
public final class ColladaCollector {

    private static Element root;
    private static Namespace ns;

    //TODO normal reduction
    public static void init(File file) {

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

    public static Asset collectAssets() {
        Element eAsset = root.getChild("asset",ns);
        Asset asset = new Asset();
        //<contributor>
            List<Element> eContributors = eAsset.getChildren("contributor",ns);
            for (Element eContributor : eContributors) {
                Asset.Contributor contributor = asset.getContributorInstance();
                contributor.authour = eContributor.getChildText("author", ns);
                contributor.authoring_tool = eContributor.getChildText("authoring_tool", ns);
                asset.contributors.add(contributor);
            }
        //<created>
            asset.created = eAsset.getChildText("created",ns);
        //<modified>
            asset.modified = eAsset.getChildText("modified", ns);
        //<unit>
            asset.unit_name = eAsset.getChild("unit", ns).getAttributeValue("name");
            asset.unit_meter = eAsset.getChild("unit", ns).getAttributeValue("meter");
        //<up_axis>
            asset.up_axis = eAsset.getChildText("up_axis", ns);

        return asset;
    }

    public static List<Effect> collectEffects() {
        List<Element> eEffects = root.getChild("library_effects", ns).getChildren("effect", ns);
        List<Effect> effects = new ArrayList<>();

        for (Element eEffect : eEffects) {
            Effect effect = new Effect();
                effect.id = eEffect.getAttributeValue("id");
                Element eProfile = null;
                for (int i = 0; i < 6; i++) //match to one of given 6 profiles
                    if ((eProfile =
                            eEffect.getChild(Profile.values()[i].name(), ns)) != null) break;

            if (eProfile == null) {
                System.out.println("No profile found in library_effects");
                continue;
            }
                effect.profile = Profile.valueOf(eProfile.getName());
            
            //<newparam>
            List<Element> eNewParams = eProfile.getChildren("newparam", ns);
            if (eNewParams.size() != 0) {
                List<NewParam.Surface> surfaces = new ArrayList<>();
                List<NewParam.Sampler2D> sampler2Ds = new ArrayList<>();
                for (int i = 0; i < eNewParams.size(); i++) {
                    Element eNewparam;
                    NewParam param = new NewParam();
                    if ((eNewparam = eNewParams.get(i).getChild("surface", ns)) != null) {
                        NewParam.Surface surface = param.getSurfaceInstance();
                        surface.sid = eNewParams.get(i).getAttributeValue("sid");
                        surface.type = eNewparam.getAttributeValue("type");
                        surface.init_from = eNewparam.getChildText("init_from", ns);
                        surfaces.add(surface);
                    } else if ((eNewparam = eNewParams.get(i).getChild("sampler2D", ns)) != null) {
                        NewParam.Sampler2D sampler2D = param.getSampler2DInstance();
                        sampler2D.sid = eNewParams.get(i).getAttributeValue("sid");
                        sampler2D.source = eNewparam.getChildText("source", ns);
                        sampler2Ds.add(sampler2D);
                    }
                }
                effect.surfaces = surfaces;
                effect.sampler2Ds = sampler2Ds;
            }

            //<technique>
            List<Element> eTechniques = eProfile.getChild("technique", ns).getChildren();
            for (int i = 0; i < eTechniques.size(); i++) {  //lambert, Phong...
                Element eTechnique = eTechniques.get(i);
                Technique technique = new Technique();
                if (eTechnique.getName().equals("lambert")) {
                    Technique.Lambert lambert = technique.getLambertInstance();
                    List<Element> fxs = eTechnique.getChildren();
                    for (Element fx : fxs) {
                        Element innerfxs;
                        if ((innerfxs = fx.getChild("color", ns)) != null) {
                            lambert.setColor(fx.getName(), new RGBA(innerfxs.getText()));
                        } else if ((innerfxs = fx.getChild("float", ns)) != null) {
                            lambert.setFloat(fx.getName(), Float.valueOf(innerfxs.getText()));
                        } else if ((innerfxs = fx.getChild("texture", ns)) != null) {
                            lambert.setColor(fx.getName(),
                                    new Texture(innerfxs.getAttributeValue("texture"), innerfxs.getAttributeValue("texcoord")));
                        }
                    }
                    effect.technique = lambert;
                } else if (eTechnique.getName().equals("phong")) {
                    Technique.Phong phong = technique.getPhongInstance();
                    List<Element> fxs = eTechnique.getChildren();
                    for (Element fx : fxs) {
                        Element innerfxs;
                        if ((innerfxs = fx.getChild("color", ns)) != null) {
                            phong.setColor(fx.getName(), new RGBA(innerfxs.getText()));
                        } else if ((innerfxs = fx.getChild("float", ns)) != null){
                            phong.setFloat(fx.getName(), Float.valueOf(innerfxs.getText()));
                        } else if ((innerfxs = fx.getChild("texture", ns)) != null) {
                            phong.setColor(fx.getName(),
                                    new Texture(innerfxs.getAttributeValue("texture"), innerfxs.getAttributeValue("texcoord")));
                        }
                    }
                    effect.technique = phong;
                    //If you find more than one type of technique used in a single <technique> you'll need to change
                    //Effect's technique to a HashMap and then add these techniques to it.
                } else if (eTechnique.getName().equals("extra")) {
                    Element eTexture = eTechnique.getChild("technique", ns).getChild("bump", ns).getChild("texture", ns);
                    if (eTexture != null)
                        effect.technique.bump_texture = new Texture(eTexture.getAttributeValue("texture"), eTexture.getAttributeValue("texcoord"));
                }
            }
            effects.add(effect);
        }
        return effects;
    }

    public static List<Material> collectMaterials() {
        List<Element> eMaterials;
        try {
            eMaterials = root.getChild("library_materials", ns).getChildren("material", ns);
        } catch (NullPointerException e) {
            return null;
        }
        List<Material> materials = new ArrayList<>();
        for (Element eMaterial : eMaterials) {
            Material material = new Material();
                material.id = eMaterial.getAttributeValue("id");
                material.name = eMaterial.getAttributeValue("name");
                material.url = eMaterial.getChild("instance_effect", ns).getAttributeValue("url");
            materials.add(material);
        }
        return materials;
    }

    public static List<Geometry> collectGeometry() {
        List<Element> eGeometries = root.getChild("library_geometries", ns).getChildren("geometry", ns);
        List<Geometry> geometries = new ArrayList<>();

        //<Geometry>
        for (Element eGeometry : eGeometries) {
            Geometry geometry = new Geometry();
                geometry.id = eGeometry.getAttributeValue("id");
                geometry.name = eGeometry.getAttributeValue("name");
                geometry.meshes = new ArrayList<>();
            //<mesh>
            Element eMesh = eGeometry.getChild("mesh", ns);
            //<source>
            List<Element> eSources = eMesh.getChildren("source", ns);

            Mesh mesh = new Mesh();
                mesh.sourceMap = new HashMap<>();   //if eSource is not null
                mesh.polyListMap = new HashMap<>();

            for (Element source : eSources) {
                Mesh.Source sourceInt = mesh.getSourceInstance();
                    sourceInt.sourceId = source.getAttributeValue("id");
                    sourceInt.floatArrays = new HashMap<>();

                List<Element> eFloatArray = source.getChildren("float_array", ns);
                Element eAccessor = source.getChild("technique_common", ns).getChild("accessor", ns);

                //<float_array>
                for (Element floatArray : eFloatArray) {
                    String floatText = floatArray.getText();
                    if (floatArray.getAttributeValue("id").equals(eAccessor.getAttributeValue("source").substring(1))) {
                        sourceInt.floatArrays.put(
                                floatArray.getAttributeValue("id"),
                                collectFloatArrays(floatText,
                                            Integer.parseInt(eAccessor.getAttributeValue("count")),
                                            Integer.parseInt(eAccessor.getAttributeValue("stride")))
                        );
                    }
                }
                mesh.sourceMap.put(sourceInt.sourceId, sourceInt);
            }

            //<polylist>
            List<Element> ePolylist = eMesh.getChildren("polylist", ns);
            for (Element polylist : ePolylist) {
                Mesh.PolyList mPolyList = mesh.getPolyListInstance();
                    mPolyList.semantics = new ArrayList<>();
                List<Element> eInput = polylist.getChildren("input", ns);
                //<input>
                for (Element input : eInput) {
                    Properties properties = new Properties();
                        properties.put("semantic", input.getAttributeValue("semantic"));
                        properties.put("source", input.getAttributeValue("source").substring(1));
                        properties.put("offset", input.getAttributeValue("offset"));
                    mPolyList.semantics.add(properties);
                }
                //<vcount>
                mPolyList.vcount = collectIntArrays(polylist.getChild("vcount", ns).getText());
                //<p>
                mPolyList.polygonIndices = collectIntArrays(polylist.getChild("p",ns).getText());
                mesh.polyListMap.put(polylist.getAttributeValue("material"), mPolyList);
                }
            geometry.meshes.add(mesh);
            geometry.verticesSource = (eMesh.getChild("vertices", ns).getChild("input", ns).getAttributeValue("source").substring(1));
            geometries.add(geometry);
            }
        return geometries;
    }

    public static List<Scene> collectScene() {
        List<Element> eScenes = root.getChild("library_visual_scenes", ns).getChildren("visual_scene", ns);   //ASSUME only one scene is used
        List<Scene> scenes = new ArrayList<>();

        for (Element eScene : eScenes) {    //<visual_scene>..
            Scene scene = new Scene();
                scene.id = eScene.getAttributeValue("id");
                scene.name = eScene.getAttributeValue("name");

            List<Element> eNodes = eScene.getChildren("node", ns);

                for (Element eNode : eNodes) {  //<node>..
                    Node node = new Node();
                        node.id = eNode.getAttributeValue("id");
                        node.name = eNode.getAttributeValue("name");
                        node.type =  Node.getType(eNode.getAttributeValue("type"));
                        node.matrixTransform = collectFloatArrays(eNode.getChild("matrix",ns).getText(), 4, 4);

                    List<Element> eInstance_Nodes;
                    if ((eInstance_Nodes = eNode.getChildren("instance_light", ns)).size() != 0) {  //<instance_..>..
                        for (Element eInstLight : eInstance_Nodes) {
                            Instance_Light instance_light = new Instance_Light();
                                instance_light.url = eInstLight.getAttributeValue("url").substring(1);
                            node.instance_nodes.add(instance_light);
                        }
                    } else if ((eInstance_Nodes = eNode.getChildren("instance_geometry", ns)).size() != 0) {
                        for (Element eInstGeo : eInstance_Nodes) {
                            Instance_Geometry instance_geometry = new Instance_Geometry();
                                instance_geometry.url = eInstGeo.getAttributeValue("url").substring(1);
                            Element eBind_Material;
                            if ((eBind_Material = eInstGeo.getChild("bind_material", ns)) != null) {
                                List<Element> instance_materials = eBind_Material.getChild("technique_common",ns).getChildren("instance_material",ns);
                                for (Element eInstMaterial : instance_materials) {
                                    Instance_Geometry.Instance_Material instance_material = instance_geometry.getInstanceMaterial();
                                        instance_material.symbol = eInstMaterial.getAttributeValue("symbol");
                                        instance_material.target = eInstMaterial.getAttributeValue("target").substring(1);
                                        instance_material.bindVertexInput_Semantic = eInstMaterial.getChild("bind_vertex_input",ns).getAttributeValue("semantic");
                                        instance_material.bindVertexInput_InputSemantic = eInstMaterial.getChild("bind_vertex_input",ns).getAttributeValue("input_semantic");
                                        instance_geometry.boundedMaterials.add(instance_material);
                                }
                            }
                            node.instance_nodes.add(instance_geometry);
                        }
                    } else if ((eInstance_Nodes = eNode.getChildren("instance_camera", ns)).size() != 0) {
                        for (Element eInstCam : eInstance_Nodes) {
                            Instance_Camera instance_camera = new Instance_Camera();
                                instance_camera.url = eInstCam.getAttributeValue("url").substring(1);
                            node.instance_nodes.add(instance_camera);
                        }
                    }
                    if (node.instance_nodes.size() != 0)
                        scene.nodes.add(node);
                }
            scenes.add(scene);
        }
    return scenes;
    }

    public static Camera collectCameras() {
        Element eCamera = root.getChild("library_cameras", ns).getChild("camera", ns);
        Camera camera = new Camera();
            camera.id = eCamera.getAttributeValue("id");
            camera.name = eCamera.getAttributeValue("name");

            Element eView = eCamera.getChild("optics", ns).getChild("technique_common", ns);
            Element nView;
            if ((nView = eView.getChild("perspective", ns)) != null){
                Camera.Perspective perspective = camera.getPerspectiveInstance();
                    perspective.xfov = nView.getChildText("xfov", ns);
                    perspective.aspectRatio = nView.getChildText("aspect_ratio", ns);
                    perspective.zNear = nView.getChildText("znear", ns);
                    perspective.zFar = nView.getChildText("zfar", ns);
                camera.optic = perspective;
            } else if ((nView = eView.getChild("orthographic", ns)) != null) {
                Camera.Orthographic orthographic = camera.getOrthographicInstance();
                    orthographic.xmag = Float.valueOf(nView.getChildText("xmag", ns));
                    orthographic.aspect_ratio = Float.valueOf(nView.getChildText("aspect_ratio", ns));
                    orthographic.znear = Float.valueOf(nView.getChildText("znear", ns));
                    orthographic.zfar = Float.valueOf(nView.getChildText("zfar", ns));
                camera.optic = orthographic;
            }

        return camera;
    }

    public static List<Light> collectLights() {
        List<Element> eLights;
        try {
            eLights = root.getChild("library_lights", ns).getChildren("light", ns);
        }catch (NullPointerException e) {
            return null;
        }
        List<Light> lights = new ArrayList<>();
        for (Element eLight : eLights) {
            Light light = new Light();
                light.id = eLight.getAttributeValue("id");
                light.name = eLight.getAttributeValue("name");
            List<Element> eLightTypes = eLight.getChild("technique_common", ns).getChildren();

            for (Element eLightType : eLightTypes) {
                if (eLightType.getName().equals("point")) {
                    Light.Point pointLight = light.getPointInstance();
                        pointLight.color = new RGB(eLightType.getChildText("color",ns)); //no account for sid taken
                        pointLight.constant_attenuation = Float.valueOf(eLightType.getChildText("constant_attenuation",ns));
                        pointLight.linear_attenuation = Float.valueOf(eLightType.getChildText("linear_attenuation",ns));
                        pointLight.quadratic_attenuation = Float.valueOf(eLightType.getChildText("quadratic_attenuation",ns));
                    light.pointLights.add(pointLight);
                } else if (eLightType.getName().equals("directional")) {
                    Light.Directional directional = light.getDirectionalInstance();
                        directional.color = new RGB(eLightType.getChildText("color", ns));
                    light.directionalLights.add(directional);
                } else if (eLightType.getName().equals("spot")) {
                    Light.Spot spot = light.getSpotInstance();
                        spot.color = new RGB(eLightType.getChildText("color",ns));
                        spot.constant_attenuation = Float.valueOf(eLightType.getChildText("constant_attenuation",ns));
                        spot.linear_attenuation = Float.valueOf(eLightType.getChildText("linear_attenuation",ns));
                        spot.quadratic_attenuation = Float.valueOf(eLightType.getChildText("quadratic_attenuation",ns));
                        spot.fallOffAngle = Float.valueOf(eLightType.getChildText("falloff_angle", ns));
                        spot.fallOfExponent = Float.valueOf(eLightType.getChildText("falloff_exponent", ns));
                    light.spotLights.add(spot);
                } else if (eLightType.getName().equals("ambient")) {
                    Light.Ambient ambient = light.getAmbientInstance();
                        ambient.color = new RGB(eLightType.getChildText("color", ns));
                    light.ambientLights.add(ambient);
                }
            }
            lights.add(light);
        }
        return lights;
    }

    public static List<Image> collectImages() {
        List<Element> eImages;
        try {
            eImages = root.getChild("library_images", ns).getChildren("image", ns);
        } catch (NullPointerException e) {
            return null;
        }

        List<Image> images = new ArrayList<>();

        for (Element eImage : eImages) {
            Image image = new Image();
            image.id = eImage.getAttributeValue("id");
            image.name = eImage.getAttributeValue("name");
            image.image = eImage.getChildText("init_from", ns);
            images.add(image);
        }

        return images;
    }

}
