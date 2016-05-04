package colladaParser.colladaComponents;

import colladaParser.colladaComponents.utils.NewParam;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cEffect extends cComponent{

    public String id;
    public cProfile profile = cProfile.profile_COMMON;

    public List<NewParam.Sampler2D> sampler2Ds;
    public cTechnique technique = null;          //so far only one technique per effect has been discovered

    public Element collect(Element element, Namespace ns) {
        id = element.getAttributeValue("id");
        Element eProfile = null;
        for (cProfile profile : cProfile.values())
            if ((eProfile = element.getChild(profile.name(), ns)) != null) break;

        if (eProfile == null) {
            System.out.println("No profile found in library_effects");  //why is profile important when then r so many 3d applications?
            return null;
        }
        profile = cProfile.valueOf(eProfile.getName());
        return eProfile;
    }

    @Override
    public String toString() {
        return "cEffect{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", sampler2Ds=" + sampler2Ds +
                ", technique=" + technique + '\'' +
                '}';
    }
}