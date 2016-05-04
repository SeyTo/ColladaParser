package colladaParser;

import colladaParser.colladaComponents.*;
import colladaParser.colladaComponents.lights.Ambient;
import colladaParser.colladaComponents.lights.Directional;
import colladaParser.colladaComponents.lights.Point;
import colladaParser.colladaComponents.lights.Spot;
import colladaParser.colladaComponents.nodeComponent.*;
import colladaParser.colladaComponents.techniques.Lambert;
import colladaParser.colladaComponents.techniques.Phong;
import colladaParser.colladaComponents.utils.NewParam;
import com.sun.istack.internal.Nullable;
import org.jdom2.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><code>init()</code> call to read and ready a .dae file. Then each library component can be read individually.</p>
 * <b>VERSION Y</b>
 * Created by rj on 3/23/2016.
 */
    //TODO type conversion where required
//TODO collada version check
public class ColladaCollector extends XMLCollector {

    public ColladaCollector(File file) {
        super(file);
    }

    /**
     * Collecting scenes will also collect lights, cameras, geometries, materials. Direct Reference can be found inside each scene without
     * having to manually call collectLights, collectCameras etc.
     * @return list of scenes i.e. "&lt;visual scene>" in the .dae file.
     */
    @Override
    public List<cScene> collectScenes() {
        List<Element> eScenes = root.getChild("library_visual_scenes", ns).getChildren("visual_scene", ns);
        List<cScene> scenes = new ArrayList<>();

        List<cCamera> cameras = collectCameras();
        List<cLight> lights = collectLights();
        List<cGeometry> geometries = collectGeometry();
        List<cMaterial> materials = collectMaterials();

        for (Element eScene : eScenes) {

            cScene scene = new cScene();        //<visual_scene>..
                scene.collect(eScene, ns);

            for (Element eNode : eScene.getChildren("node", ns)) {

                cNode node = new cNode();       //<node>..
                    node.collect(eNode, ns);

                Element eInstance_Nodes;
                cInstance_Node instance_node = null;
                if ((eInstance_Nodes = eNode.getChild("instance_light", ns)) != null) {  //<instance_..>..
                    instance_node = new cInstance_Light();
                    instance_node.collect(eInstance_Nodes, ns, lights);
                } else if ((eInstance_Nodes = eNode.getChild("instance_geometry", ns)) != null) {
                    instance_node = new cInstance_Geometry();

                        Element eBind_Material;
                        if ((eBind_Material = eInstance_Nodes.getChild("bind_material", ns)) != null) {
                            List<Element> instance_materials = eBind_Material.getChild("technique_common",ns).getChildren("instance_material",ns);

                            for (Element eInstMaterial : instance_materials) {
                                cInstance_Geometry.Instance_Material instance_material = ((cInstance_Geometry)instance_node).getInstanceMaterial();
                                    instance_material.collect(eInstMaterial, ns, materials);
                                ((cInstance_Geometry)instance_node).boundedMaterials.add(instance_material);
                            }
                        }
                    instance_node.collect(eInstance_Nodes, ns, geometries);
                }
                else if ((eInstance_Nodes = eNode.getChild("instance_camera", ns)) != null) {
                    instance_node = new cInstance_Camera();
                    instance_node.collect(eInstance_Nodes, ns, cameras);
                }
                node.instanceNode = instance_node;
                scene.nodes.add(node);
            }
            scenes.add(scene);
        }

        cameras = null;
        lights = null;
        geometries = null;
        materials = null;

        return scenes;
    }

    @Override
    public List<cEffect> collectEffects(){
        List<Element> eEffects = root.getChild("library_effects", ns).getChildren("effect", ns);
        List<cEffect> effects = new ArrayList<>();

        List<cImage> images = collectImages();

        for (Element eEffect : eEffects) {
            cEffect effect = new cEffect();
                Element eProfile = effect.collect(eEffect, ns);
                if (eProfile == null) return null;
            
            //<newparam>
            List<Element> eNewParams = eProfile.getChildren("newparam", ns);
            if (eNewParams.size() != 0) {
                List<NewParam.Surface> surfaces = new ArrayList<>();
                List<NewParam.Sampler2D> sampler2Ds = new ArrayList<>();

                for (int i = 0; i < eNewParams.size();i++) {
                    Element eNewparam;
                    NewParam param = new NewParam();
                    if ((eNewparam = eNewParams.get(i).getChild("surface", ns)) != null) {
                        NewParam.Surface surface = param.getSurfaceInstance();
                            surface.collect(eNewparam, ns, images);
                        surfaces.add(surface);
                    } else if ((eNewparam = eNewParams.get(i).getChild("sampler2D", ns)) != null) {
                        NewParam.Sampler2D sampler2D = param.getSampler2DInstance();
                            sampler2D.collect(eNewparam, ns, surfaces);
                        sampler2Ds.add(sampler2D);
                    }
                }
                images = null;
                effect.sampler2Ds = sampler2Ds;
            }

            //<technique>
            List<Element> eTechniques = eProfile.getChild("technique", ns).getChildren();
            for (Element eTechnique : eTechniques) {  //lambert, Phong...
                cTechnique technique = null;
                if (eTechnique.getName().equals("phong")) {
                    technique = new Phong();
                } else if (eTechnique.getName().equals("lambert")) {
                    technique = new Lambert();
                }
                List<Element> fxs = eTechnique.getChildren();

                for (Element fx : fxs) {
                    technique.collect(fx, ns, technique);
                }

                effect.technique = technique;

                //TODO account for bump textures
               /* Element eTexture;
                if ((eTexture = eTechnique.getChild("technique", ns).getChild("bump", ns))!=null) {
                    eTexture = eTexture.getChild("texture", ns);
                }
                if (eTexture != null)
                    effect.technique.bump_texture = new Texture(eTexture.getAttributeValue("texture"), eTexture.getAttributeValue("texcoord"));*/

            }
            effects.add(effect);
        }
        return effects;
    }


    @Override
    //TODO ..but is this really required?
    public cAsset collectAssets() {
        return null;
    }

    @Nullable
    public List<cMaterial> collectMaterials() {
        List<Element> eMaterials;
        try {
            eMaterials = root.getChild("library_materials", ns).getChildren("material", ns);
        } catch (NullPointerException e) {
            return null;
        }
        List<cMaterial> materials = new ArrayList<>();
        List<cEffect> effects = collectEffects();
        for (Element eMaterial : eMaterials) {
            cMaterial material = new cMaterial();
                material.collect(eMaterial, ns, effects);
            materials.add(material);
        }
        effects = null;     //Something might go wrong
        return materials;
    }

    public List<cGeometry> collectGeometry() {
        List<Element> eGeometries = root.getChild("library_geometries", ns).getChildren("geometry", ns);
        List<cGeometry> geometries = new ArrayList<>();


        for (Element eGeometry : eGeometries) {                                 //<cGeometry>
            cGeometry geometry = new cGeometry();
                geometry.collect(eGeometry, ns);

            Element eMesh = eGeometry.getChild("mesh", ns);

            cMesh mesh = new cMesh();
            List<Element> eSources = eMesh.getChildren("source", ns);           //<source>

            for (Element source : eSources) {
                cMesh.Source sourceInt = mesh.getSourceInstance();
                    sourceInt.collect(source, ns);
                mesh.sourceMap.put(sourceInt.sourceId, sourceInt);
            }

            List<Element> ePolylist = eMesh.getChildren("polylist", ns);        //<polylist>
            for (Element polylist : ePolylist) {
                cMesh.PolyList mPolyList = mesh.getPolyListInstance();
                    mPolyList.collect(polylist, ns);

                mesh.polyListMap.put(polylist.getAttributeValue("material"), mPolyList);
            }
            mesh.verticesSource = mesh.sourceMap.get(eMesh.getChild("vertices", ns).getChild("input", ns).getAttributeValue("source").substring(1));
            geometry.meshes.add(mesh);
            geometries.add(geometry);
        }
        return geometries;
    }

    public List<cCamera> collectCameras() {
        List<Element> eCameras = root.getChild("library_cameras", ns).getChildren("camera", ns);
        List<cCamera> cameras = new ArrayList<>();

        for (Element eCamera : eCameras) {
            cCamera camera = new cCamera();

            Element eView = eCamera.getChild("optics", ns).getChild("technique_common", ns);
            Element nView;
            cCamera.Optic optic = null;
            if ((nView = eView.getChild("perspective", ns)) != null)
                optic = camera.getPerspectiveInstance();
            else if ((nView = eView.getChild("orthographic", ns)) != null)
                optic = camera.getOrthographicInstance();


            if (optic != null) optic.collect(nView, ns);
                camera.collect(eCamera, ns, optic);
            cameras.add(camera);
        }

        return cameras;
    }


    @Nullable
    public List<cLight> collectLights() {
        List<Element> eLights;
        try {
            eLights = root.getChild("library_lights", ns).getChildren("light", ns);
        }catch (NullPointerException e) {
            return null;
        }
        List<cLight> lights = new ArrayList<>();
        for (Element eLight : eLights) {
            cLight light = null;
            Element eType = eLight.getChild("technique_common",ns);
            if ((eType = eType.getChild("point",ns))!= null)
                light = new Point();
            else if ((eType = eType.getChild("spot",ns))!= null)
                light = new Spot();
            else if ((eType = eType.getChild("directional",ns))!= null)
                light = new Directional();
            else if ((eType = eType.getChild("ambient",ns))!= null)
                light = new Ambient();


            light.collect(eType, ns);
            lights.add(light);
        }
        return lights;
    }


    @Nullable
    public List<cImage> collectImages() {
        List<Element> eImages;
        try {
            eImages = root.getChild("library_images", ns).getChildren("image", ns);
        } catch (NullPointerException e) {
            return null;
        }

        List<cImage> images = new ArrayList<>();

        for (Element eImage : eImages) {
            cImage image = new cImage();
                image.collect(eImage, ns);
            images.add(image);
        }

        return images;
    }

}
