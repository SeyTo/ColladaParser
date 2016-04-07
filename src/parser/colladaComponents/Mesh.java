package parser.colladaComponents;

import parser.colladaComponents.utils.Printer;

import java.util.*;

/**
 * Created by RaJU on 3/29/2016.
 */
public final class Mesh {

    public Map<String, Source> sourceMap;
    public Map<String, PolyList> polyListMap;

    public Source getSourceInstance() {
        return new Source();
    }

    public PolyList getPolyListInstance() {
        return new PolyList();
    }

    @Override
    public String toString() {
        String out = "\n{Mesh =>\n";
        out += "\tSourceMap => " + sourceMap.toString() + "\n";
        out += "\tPolyListMap => " + polyListMap.toString() + "\n";
        return out;

    }

    public class Source {
        public String sourceId;
        public Map<String, float[][]> floatArrays;

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
        public List<Properties> semantics;
        public int[] vcount;
        public int[] polygonIndices;

        @Override
        public String toString() {
            return "\nPolyList{" +
                    ", semantics=" + semantics +
                    ", vcount=" + Arrays.toString(vcount) +
                    ", polygonIndices=" + Arrays.toString(polygonIndices) +
                    '}';
        }
    }
}
