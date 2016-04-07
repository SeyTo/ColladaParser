package parser.colladaComponents;

import parser.colladaComponents.utils.Printer;

import java.util.*;

/**
 * Created by RaJU on 3/29/2016.
 */
public final class cMesh {

    /**
     * <p><code>sourceMap</code> is mapped as id(of the source) to Source.</p>
     */
    public Map<String, Source> sourceMap;

    /**
     * <p><code>polyListMap</code> is mapped as material to PolyList.</p>
     */
    public Map<String, PolyList> polyListMap;
    public String verticesSource;

    public Source getSourceInstance() {
        return new Source();
    }

    public PolyList getPolyListInstance() {
        return new PolyList();
    }

    @Override
    public String toString() {
        String out = "\n{cMesh =>\n";
        out += "\tSourceMap => " + sourceMap.toString() + "\n";
        out += "\tPolyListMap => " + polyListMap.toString() + ", verticesSource : " + verticesSource + "\n";
        return out;

    }

    public class Source {
        public String sourceId;
        /**
         * <p><code>floatArrays</code> is mapped as its id to float[][]</p>
         */
        public Map<String, float[]> floatArrays;
        public int stride;

        @Override
        public String toString() {
            String out = "\n\t\tSource {" +
                    "sourceId='" + sourceId + "\n" +
                    ", floatArrays=>";
            for (String key : floatArrays.keySet()) {
                out += key + " " + Printer.toString(floatArrays.get(key)) + "\n";
            }
            return out;
        }
    }

    public class PolyList {
        public List<Properties> inputs;
        public int[] vcount;
        public int[] polygonIndices;

        @Override
        public String toString() {
            return "\nPolyList{" +
                    ", inputs=" + inputs +
                    ", vcount=" + Arrays.toString(vcount) +
                    ", polygonIndices=" + Arrays.toString(polygonIndices) +
                    '}';
        }
    }
}
