package parser.colladaComponents;

import parser.colladaComponents.utils.RGB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 4/3/2016.
 */
public final class Light {

    public String id;
    public String name;
    public List<Point> pointLights = null;
    public List<Directional> directionalLights = null;
    public List<Spot> spotLights = null;
    public List<Ambient> ambientLights = null;


    public Point getPointInstance() {
        if (pointLights == null) pointLights = new ArrayList<>();   //TODO do this in all instance creator
        return new Point();
    }

    public Directional getDirectionalInstance() {
        if (directionalLights == null) directionalLights = new ArrayList<>();
        return new Directional();
    }

    public Spot getSpotInstance() {
        if (spotLights == null) spotLights = new ArrayList<>();
        return new Spot();
    }

    public Ambient getAmbientInstance() {
        if (ambientLights == null) ambientLights = new ArrayList<>();
        return new Ambient();
    }

    @Override
    public String toString() {
        return "Light{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pointLights : " + pointLights + '}';
    }

    public final class Point {
        public RGB color;
        public float constant_attenuation;
        public float linear_attenuation;
        public float quadratic_attenuation;

        @Override
        public String toString() {
            return "Point{" +
                    "color=" + color +
                    ", constant_attenuation=" + constant_attenuation +
                    ", linear_attenuation=" + linear_attenuation +
                    ", quadratic_attenuation=" + quadratic_attenuation +
                    '}';
        }
    }

    public final class Directional {
        public RGB color;

        @Override
        public String toString() {
            return "Directional{" +
                    "color=" + color +
                    '}';
        }
    }

    public final class Spot {
        public RGB color;
        public float constant_attenuation;
        public float linear_attenuation;
        public float quadratic_attenuation;
        public float fallOffAngle;
        public float fallOfExponent;

        @Override
        public String toString() {
            return "Spot{" +
                    "color=" + color +
                    ", constant_attenuation=" + constant_attenuation +
                    ", linear_attenuation=" + linear_attenuation +
                    ", quadratic_attenuation=" + quadratic_attenuation +
                    ", fallOffAngle=" + fallOffAngle +
                    ", fallOfExponent=" + fallOfExponent +
                    '}';
        }
    }

    public final class Ambient {
        public RGB color;

        @Override
        public String toString() {
            return "Ambient{" + "color=" + color + '}';
        }
    }
}
