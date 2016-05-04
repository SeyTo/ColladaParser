package colladaParser.colladaComponents.utils;

/**
 * Created by RaJU on 4/2/2016.
 */
public class Printer {

    public static String toString(int[] arr) {
        String val= "[";
        for (int i = 0; i < arr.length; i++) {
            val += arr[i] + " ";
        }
        val += "]";
        return val;
    }

    public static String toString(float[][] arr) {
        String val= "[";
        for (int i = 0; i < arr.length; i++) {
            val += "<";
            for (int j = 0; j < arr[0].length; j++) {
                val += arr[i][j] + ",";
            }
            val += ">,";
        }
        val += "]";
        return val;
    }

    public static String toString(float[] arr) {
        String val= "[";
        for (int i = 0; i < arr.length; i++) {
            val += "<";
                val += arr[i] + ",";
            val += ">,";
        }
        val += "]";
        return val;
    }

    public static String toString(float[] arr, int size) {
        String val= "[";
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (j == 0)
                val += "<";

            val += arr[i] + ",";

            if (++j == size) {
                val += ">,";
                j = 0;
            }
        }
        val += "]";
        return val;
    }

    public static String toString(int[] arr, int size) {
        String val= "[";
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (j == 0)
                val += "<";

            val += arr[i] + ",";

            if (++j == size) {
                val += ">,";
                j = 0;
            }
        }
        val += "]";
        return val;
    }
}
