package colladaParser.colladaComponents.techniques;


import colladaParser.colladaComponents.cTechnique;
import colladaParser.colladaComponents.utils.Color;

/**
 * Created by rj on 4/26/2016.
 */
public class Lambert extends cTechnique {

    public Color emission;
    public Color ambient;
    public Color diffuse;
    public Color specular;
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
        }
    }

    public void setFloat(String name, float value) {
        switch (name) {
            case "index_of_refraction" :
                index_of_refraction = value; break;
        }
    }

    @Override
    public String toString() {
        return "Lambert{" +
                "emission=" + emission +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", index_of_refraction=" + index_of_refraction +
                ", bump texture=" + bump_texture +
                '}';
    }
}
