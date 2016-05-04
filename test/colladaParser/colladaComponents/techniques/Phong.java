package colladaParser.colladaComponents.techniques;


import colladaParser.colladaComponents.cTechnique;
import colladaParser.colladaComponents.utils.Color;

/**
 * Created by rj on 4/26/2016.
 */
public class Phong extends cTechnique {

    public Color emission;
    public Color ambient;
    public Color diffuse;
    public Color specular;
    public float shininess;
    public Color reflective;
    public float reflectivity;
    public Color transparent;
    public float transparency;
    public float index_of_refraction;

    public void setColor(String name, Color value) {
        switch (name) {
            case "emission":
                emission = value; break;
            case "ambient":
                ambient = value; break;
            case "diffuse":
                diffuse = value; break;
            case "specular":
                specular = value; break;
            case "reflective":
                reflective = value; break;
            case "transparent":
                transparent = value;
        }
    }

    public void setFloat(String name, float value) {
        switch (name) {
            case "shininess" :
                shininess = value; break;
            case "reflectivity" :
                reflectivity = value; break;
            case "index_of_refraction" :
                index_of_refraction = value; break;
            case "transparency" :
                transparency = value;
        }
    }

    @Override
    public String toString() {
        return "Phong{" +
                "emission=" + emission +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", shininess=" + shininess +
                ", reflective=" + reflective +
                ", reflectivity=" + reflectivity +
                ", transparent=" + transparent +
                ", transparency=" + transparency +
                ", index_of_refraction=" + index_of_refraction +
                ", bump texture=" + bump_texture +
                '}';
    }
}
