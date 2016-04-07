package parser.colladaComponents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaJU on 3/25/2016.
 */
public final class Asset {

    public List<Contributor> contributors;
    public String created;
    public String keyword;
    public String modified;
    public String revision;
    public String subject;
    public String title;
    public String unit_name;
    public String unit_meter;
    public String up_axis;


    public Asset() {
        contributors = new ArrayList<>();
    }

    public Contributor getContributorInstance() {
        return new Contributor();
    }

    @Override
    public String toString() {
        return "\nAsset : \n{" +
                "contributors=" + contributors +
                ", created='" + created + '\'' +
                ", keyword='" + keyword + '\'' +
                ", modified='" + modified + '\'' +
                ", revision='" + revision + '\'' +
                ", subject='" + subject + '\'' +
                ", title='" + title + '\'' +
                ", unit_name='" + unit_name + '\'' +
                ", unit_meter='" + unit_meter + '\'' +
                ", up_axis='" + up_axis + '\'' +
                '}';
    }

    public class Contributor {
        public String authour;
        public String authoring_tool;
        public String comments;
        public String copyright;
        public String source_data;

        @Override
        public String toString() {
            return "Contributor{" +
                    "authour='" + authour + '\'' +
                    ", authoring_tool='" + authoring_tool + '\'' +
                    ", comments='" + comments + '\'' +
                    ", copyright='" + copyright + '\'' +
                    ", source_data='" + source_data + '\'' +
                    '}';
        }
    }


}
