package utils;

import org.apache.jena.rdf.model.RDFNode;

public class getStringFromOntology {
    public String getString(RDFNode value) {
        if(value == null)
            return "";

        if(value.toString().contains("^^"))
            return value.toString().split("\\^")[0];
        else
            return value.toString().split("#")[1];
    }
}
