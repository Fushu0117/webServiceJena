package models;

import java.util.ArrayList;
import java.util.List;

public class Video {
    private String artifactName;
    private String artifactLocation;
    private String artifactFormat;
    private List<String> artifactTags;
    private String isMadeBy;
    private String hasUsedIn;
    private String hasTaggedBy;
    private String isUsedBy;

    public Video(String artifactName, String artifactLocation, String artifactFormat, List<String> artifactTags, String isMadeBy, String hasUsedIn, String hasTaggedBy, String isUsedBy){
        this.artifactName = artifactName;
        this.artifactLocation = artifactLocation;
        this.artifactFormat = artifactFormat;
        this.artifactTags = artifactTags;
        this.isMadeBy = isMadeBy;
        this.hasUsedIn = hasUsedIn;
        this.hasTaggedBy = hasTaggedBy;
        this.isUsedBy = isUsedBy;
    }
    public Video(){
        this.artifactName = "";
        this.artifactLocation = "";
        this.artifactFormat = "";
        this.artifactTags = new ArrayList<String>();
        this.isMadeBy = "";
        this.hasUsedIn = "";
        this.hasTaggedBy = "";
        this.isUsedBy = "";
    }

    public void setArtifactName(String artifactName){
        this.artifactName = artifactName;
    }

    public void setArtifactLocation(String artifactLocation){
        this.artifactLocation = artifactLocation;
    }

    public void setArtifactFormat(String artifactFormat){
        this.artifactFormat = artifactFormat;
    }

    public void setArtifactTags(List<String> artifactTags){
        this.artifactTags = artifactTags;
    }

    public void setIsMadeBy(String isMadeBy){
        this.isMadeBy = isMadeBy;
    }

    public void setHasUsedIn(String hasUsedIn){
        this.hasUsedIn = hasUsedIn;
    }

    public void setHasTaggedBy(String hasTaggedBy){
        this.hasTaggedBy = hasTaggedBy;
    }

    public void setIsUsedBy(String isUsedBy){
        this.isUsedBy = isUsedBy;
    }

    public String getArtifactName(){
        return this.artifactName;
    }

    public String getArtifactLocation(){
        return this.artifactLocation;
    }

    public String getArtifactFormat(){
        return this.artifactFormat;
    }

    public List<String> getArtifactTags(){
        return this.artifactTags;
    }

    public String getIsMadeBy(){
        return this.isMadeBy;
    }

    public String getHasUsedIn(){
        return this.hasUsedIn;
    }

    public String getHasTaggedBy(){
        return this.hasTaggedBy;
    }

    public String getIsUsedBy(){
        return this.isUsedBy;
    }
}
