package parser.colladaComponents;

/**
 * Created by RaJU on 4/2/2016.
 */
public final class Camera {

    public String id;
    public String name;
    public Optic optic;


        public Perspective getPerspectiveInstance() {
            return new Perspective();
        }

        public Orthographic getOrthographicInstance() {
            return new Orthographic();
        }

    @Override
    public String toString() {
        return "Camera{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", optic=" + optic +
                '}';
    }

    public class Optic {

    }

    public final class Perspective extends Optic {
            public String xfov;
            public String aspectRatio;
            public String zNear;
            public String zFar;

        @Override
        public String toString() {
            return "Perspective{" +
                    "xfov='" + xfov + '\'' +
                    ", aspectRatio='" + aspectRatio + '\'' +
                    ", zNear='" + zNear + '\'' +
                    ", zFar='" + zFar + '\'' +
                    '}';
        }
    }

        public final class Orthographic extends Optic {

            public float xmag;
            public float aspect_ratio;
            public float znear;
            public float zfar;

            @Override
            public String toString() {
                return "Orthographic{" + "xmag=" + xmag + ", aspect_ratio=" + aspect_ratio +
                        ", znear=" + znear +
                        ", zfar=" + zfar +
                        '}';
            }
        }


}
