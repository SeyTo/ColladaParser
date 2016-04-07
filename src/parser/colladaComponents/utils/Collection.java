package parser.colladaComponents.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 4/6/2016.
 */
public class Collection {

    public static float[][] collectFloatArrays(String floatText, int xSize, int ySize) {
        float[][] arrays = new float[xSize][ySize];
        int x = 0;
        int y = 0;
        char charPoint;
        String collect = "";
        boolean started = false;
        for (int i = 0; i < floatText.length(); i++) {  //skim through characters
            charPoint = floatText.charAt(i);
            if (charPoint!='\n' && charPoint != '\t' && charPoint != ' ') {
                collect += charPoint;
                started = true;         //if found for the first time set true
            }

            if (started && (charPoint == '\n' || charPoint == '\t' || charPoint == ' ' || i==floatText.length()-1)) { //when true if found space then
                arrays[x][y] = Float.valueOf(collect);              //collect it
                //System.out.println(collect + " at x = " + x + " ; y = " + y);
                ++y;            //to next index...
                collect = "";
                started = false;
                if (y == arrays[0].length) {
                    y = 0; ++x;
                    if (x == arrays.length) break;
                }
            }
        }
        return arrays;
    }

    public static int[] collectIntArrays(String intText) {
        List<Integer> list = new ArrayList<>();
        String[] split = intText.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals(" ") && !split[i].equals("\n") && !split[i].equals("\t") && !split[i].equals("") ) {
                list.add(Integer.valueOf(split[i]));
            }
        }
        int[] integerForm = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            integerForm[i] = list.get(i);
        }

        return integerForm;
    }
}
