package parser.colladaComponents.utils;

/**
 * Created by RaJU on 4/5/2016.
 */
public final class Texture extends Color{

    public String texture;
    public String texcoord;

    public Texture(String texture, String texcoord) {
        this.texture = texture;
        this.texcoord = texcoord;
    }

    @Override
    public String toString() {
        return "Texture{" +
                "texture='" + texture + '\'' +
                ", texcoord='" + texcoord + '\'' +
                '}';
    }
}
