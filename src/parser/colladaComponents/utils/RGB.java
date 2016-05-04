package parser.colladaComponents.utils;

/**
 * Created by RaJU on 4/4/2016.
 */
public class RGB extends Color{

    public float red = 0;
    public float green = 0;
    public float blue = 0;

    public RGB(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RGB(String rgb) {
        String[] split = rgb.split(" ");
        red = Float.valueOf(split[0]);
        green = Float.valueOf(split[1]);
        blue = Float.valueOf(split[2]);
    }

    @Override
    public String toString() {
        return "RGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
