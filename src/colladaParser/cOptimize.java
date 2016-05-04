package colladaParser;

import colladaParser.colladaComponents.utils.Printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rj on 4/8/2016.
 */
public class cOptimize {

    /**
     * <p>removes duplicate float[][] arrays and at the same time change indices to compensate it. The
     * float arrays are expected to be stored as per <code>ColladaCollector</code> format.</p>
     * @param arrays
     * arrays maybe mesh-positions or mesh-normals
     * @param indices
     * indices are the one tagged with &lt;p>
     *///TODO multiple indices also
    public static void reduceFloatArray (float[][] arrays, int[] indices, int indicesOffset, int types) {
        if (arrays.length == 0 || indices.length == 0 ) {
            System.out.println("reduceFloatArray(), data is empty for either " + arrays + " or " + indices);
            return;
        }
        List<float[]> arraysList = new ArrayList<>();
            apply(arraysList, arrays);
        List<Integer> indicesList = new ArrayList<>();
            apply(indicesList, indices, indicesOffset, types);

        System.out.println("From :");
        System.out.println("Arrays : ");
        for (int i = 0; i < arraysList.size(); i++) {
            System.out.print(Printer.toString(arraysList.get(i)));
        }
        System.out.println("\nIndices : ");
        for (int i = 0; i < indicesList.size(); i++) {
            System.out.print((indicesList.get(i) + ", "));
        }

        for (int i = 0; i < arraysList.size(); i++) {
            for (int j = i+1; j < arraysList.size(); j++) {
                if (strikes(arraysList.get(i), arraysList.get(j)) == arraysList.get(j).length) {
                    Collections.replaceAll(indicesList, j, i);
                }
            }
        }

        //DO NOT think that above loops and this loop is the can be merged. They need to be separate. To merge, some index inducing
        //& temporary array algorithm is needed. & No I didn't get lazy, definitely did a lot of tests.
        for (int i = 0; i < arraysList.size(); i++) {
            for (int j = i+1; j < arraysList.size(); j++) {
                if (strikes(arraysList.get(i), arraysList.get(j)) == arraysList.get(j).length)
                    arraysList.remove(j--);
            }
        }

        System.out.println("To : ");
        System.out.println("Arrays : ");
        for (int i = 0; i < arraysList.size(); i++) {
            System.out.print(Printer.toString(arraysList.get(i)));
        }
        System.out.println("Indices : ");
        for (int i = 0; i < indicesList.size(); i++) {
            System.out.print((indicesList.get(i) + ", "));
        }

    }

    private static int strikes(float[] left, float[] right) {
        int strike = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] == right[i]) {
                ++strike;
            }
        }
        return strike;
    }

    private static void apply(List<float[]> to, float[][] from) {
        for (int i = 0; i < from.length; i++) {
            to.add(from[i]);
        }
    }

    private static void apply(List<Integer> to, int[] from, int offset, int total) {
        for (int i = offset; i < from.length; i+= total) {
            to.add(from[i]);
        }
    }
}
