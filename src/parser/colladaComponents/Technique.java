package parser.colladaComponents;

import parser.colladaComponents.utils.Color;

/**
 * Created by rj on 3/25/2016.
 */
public class Technique {

    public Color bump_texture;

    public Phong getPhongInstance() {
        return new Phong();
    }

    public Lambert getLambertInstance() {
        return new Lambert();
    }

    public final class Phong extends Technique{
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

    public final class Lambert extends Technique{
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


}
