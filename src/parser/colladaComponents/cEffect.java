package parser.colladaComponents;

import parser.colladaComponents.utils.NewParam;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cEffect {

    public String id;
    public cProfile profile = cProfile.profile_COMMON;
    public List<NewParam.Surface> surfaces;
    public List<NewParam.Sampler2D> sampler2Ds;
    public cTechnique technique = null;          //so far only one technique per effect has been discovered

    @Override
    public String toString() {
        return "cEffect{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", surfaces=" + surfaces +
                ", sampler2Ds=" + sampler2Ds +
                ", technique=" + technique + '\'' +
                '}';
    }
}