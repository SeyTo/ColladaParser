package parser.colladaComponents;

import parser.colladaComponents.utils.NewParam;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class Effect {

    public String id;
    public Profile profile = Profile.profile_COMMON;
    public List<NewParam.Surface> surfaces;
    public List<NewParam.Sampler2D> sampler2Ds;
    public Technique technique = null;          //so far only one technique per effect has been discovered

    @Override
    public String toString() {
        return "Effect{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", surfaces=" + surfaces +
                ", sampler2Ds=" + sampler2Ds +
                ", technique=" + technique + '\'' +
                '}';
    }
}