package parser.colladaComponents.utils;

/**
 * Created by rj on 4/5/2016.
 */
public class NewParam {

    public String sid;

    public Sampler2D getSampler2DInstance() {
        return new Sampler2D();
    }

    public Surface getSurfaceInstance() {
        return new Surface();
    }

    public final class Sampler2D extends NewParam {
        public String source;

        @Override
        public String toString() {
            return "Sampler2D{ sid=" + sid +
                    " source='" + source + '\'' +
                    '}';
        }
    }

    public final class Surface extends NewParam {
        public String type;
        public String init_from;

        @Override
        public String toString() {
            return "Surface{ sid=" + sid +
                    " type='" + type + '\'' +
                    ", init_from='" + init_from + '\'' +
                    '}';
        }
    }

}
