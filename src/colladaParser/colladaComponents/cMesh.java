package colladaParser.colladaComponents;

import colladaParser.colladaComponents.utils.Printer;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.*;

import static colladaParser.colladaComponents.utils.Collection.collectFloatArrays;
import static colladaParser.colladaComponents.utils.Collection.collectIntArrays;


/**
 * Created by RaJU on 3/29/2016.
 */
public final class cMesh{

    /**
     * <p><code>sourceMap</code> is mapped as id(of the source) to Source.</p>
     */
    public Map<String, Source> sourceMap;

    /**
     * <p><code>polyListMap</code> is mapped as material to PolyList.</p>
     */
    public Map<String, PolyList> polyListMap;
    public Source verticesSource;

    public cMesh() {
        sourceMap = new HashMap<>();
        polyListMap = new HashMap<>();
    }

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

        public Source() {
            floatArrays = new HashMap<>();
        }

        public void collect(Element element, Namespace ns) {
            sourceId = element.getAttributeValue("id");

            List<Element> eFloatArray = element.getChildren("float_array", ns);
            Element eAccessor = element.getChild("technique_common", ns).getChild("accessor", ns);

            //<float_array>
            for (Element floatArray : eFloatArray) {
                String floatText = floatArray.getText();
                if (floatArray.getAttributeValue("id").equals(eAccessor.getAttributeValue("source").substring(1))) {
                    stride = Integer.parseInt(eAccessor.getAttributeValue("stride"));
                    floatArrays.put(
                            floatArray.getAttributeValue("id"),
                            collectFloatArrays(floatText, Integer.parseInt(eAccessor.getAttributeValue("count")) * stride));
                }
            }
        }

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

        public PolyList() {
            inputs = new ArrayList<>();
        }

        public void collect(Element element, Namespace ns) {
            vcount = collectIntArrays(element.getChild("vcount", ns).getText());          //<vcount>
            polygonIndices = collectIntArrays(element.getChild("p",ns).getText());       //<p>

            List<Element> eInput = element.getChildren("input", ns);

            for (Element input : eInput) {                                   //<input>
                Properties properties = new Properties();
                    properties.put("semantic", input.getAttributeValue("semantic"));
                    properties.put("source", input.getAttributeValue("source").substring(1));
                    properties.put("offset", input.getAttributeValue("offset"));
                inputs.add(properties);
            }

        }

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
