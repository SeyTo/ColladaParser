package colladaParser.colladaComponents.lights;

import colladaParser.colladaComponents.cLight;
import colladaParser.colladaComponents.utils.RGB;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Created by rj on 4/27/2016.
 */
public class Spot extends cLight {

    public RGB color;
    public float constant_attenuation;
    public float linear_attenuation;
    public float quadratic_attenuation;
    public float fallOffAngle;
    public float fallOfExponent;

    @Override
    public void collect(Element element, Namespace ns) {
        color = new RGB(element.getChildText("color",ns));
        constant_attenuation = Float.valueOf(element.getChildText("constant_attenuation",ns));
        linear_attenuation = Float.valueOf(element.getChildText("linear_attenuation",ns));
        quadratic_attenuation = Float.valueOf(element.getChildText("quadratic_attenuation",ns));
        fallOffAngle = Float.valueOf(element.getChildText("falloff_angle", ns));
        fallOfExponent = Float.valueOf(element.getChildText("falloff_exponent", ns));
    }

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
