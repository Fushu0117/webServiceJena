package utils;

public enum CONSTANTS {
    ONTOLOGY_PATH("C:/Users/Francisco Antonio/Desktop/OntologiaVideos.owl"),
//    ONTOLOGY_PATH("C:/OntologiaVideos.owl"),
    ONTOLOGY_NAMESPACE("http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#"),
    // Classes
    ONTOLOGY_CLASS_PROGRAMMER("Programmer"),
    // Data properties
    ONTOLOGY_CLASS_USER_NAME("userName"),
    ONTOLOGY_CLASS_USER_EMAIL("userEmail");
    private String value;
    private CONSTANTS(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
