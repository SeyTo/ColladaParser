package parser.colladaComponents;

/**
 * Created by rj on 3/25/2016.
 */
public final class Material {

    public String id;
    public String name;
    public String url;

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
