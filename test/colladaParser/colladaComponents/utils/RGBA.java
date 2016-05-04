package colladaParser.colladaComponents.utils;

/**
 * Created by RaJU on 3/25/2016.
 */
public final class RGBA extends RGB {

    public float alpha;

    public RGBA(byte red, byte green, byte blue, byte alpha) {
        super(red, green, blue);
        this.alpha = alpha;
    }

    public RGBA(String rgba) {
        super(rgba);
        String[] split = rgba.split(" ");
        if (split.length < 4) System.out.println("WARNING!! RGBA VALUE :" + rgba + " IS INVALID");
        alpha = Float.valueOf(split[3]);
    }

    //TODO clamp
    //TODO normalize

    @Override
    public String toString() {
        return "RGBA{" +
                "alpha=" + alpha +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
