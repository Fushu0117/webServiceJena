package Service;

import models.Programmer;
import models.Video;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import utils.CONSTANTS;
import utils.getStringFromOntology;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class VideosService {
    public ArrayList<Video> getVideosList(){
        getStringFromOntology getStringFromOntology = new getStringFromOntology();

        ArrayList<Video> videosList = new ArrayList<>();

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        model.read(CONSTANTS.ONTOLOGY_PATH.getValue());

        String queryString = "SELECT ?video ?artifactLocation ?artifactFormat ?isMadeBy ?hasUsedIn ?hasTaggedBy ?isUsedBy WHERE { "
                + "?video a <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#Videos> . "
                + "?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#artifactLocation> ?artifactLocation . "
                + "?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#artifactFormat> ?artifactFormat . "
                + "OPTIONAL { ?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#isMadeBy> ?isMadeBy . } "
                + "OPTIONAL { ?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#hasUsedIn> ?hasUsedIn . } "
                + "OPTIONAL { ?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#hasTaggedBy> ?hasTaggedBy . } "
                + "OPTIONAL { ?video <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#isUsedBy> ?isUsedBy . } "
                + "}";


        Query query = QueryFactory.create(queryString);
        try (QueryExecution exec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = exec.execSelect();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Video video = new Video();
                video.setArtifactLocation(getStringFromOntology.getString(solution.get("artifactLocation")));
                video.setArtifactFormat(getStringFromOntology.getString(solution.get("artifactFormat")));
                video.setIsMadeBy(getStringFromOntology.getString(solution.get("isMadeBy")));
                video.setHasUsedIn(getStringFromOntology.getString(solution.get("hasUsedIn")));
                video.setHasTaggedBy(getStringFromOntology.getString(solution.get("hasTaggedBy")));
                video.setIsUsedBy(getStringFromOntology.getString(solution.get("isUsedBy")));
                videosList.add(video);
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return videosList;
    }
    public void insertVideo(Video video) {
        CONSTANTS ONTOLOGY_PATH = CONSTANTS.ONTOLOGY_PATH;
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);

        try {
            InputStream in = FileManager.get().open(ONTOLOGY_PATH.getValue());
            model.read(in, null);
        } catch (Exception e) {
            System.out.println("Error al cargar la ontología: " + e.getMessage());
        }

        String ns = CONSTANTS.ONTOLOGY_NAMESPACE.getValue();
        Individual videoIndividual = model.createIndividual(ns + video.getArtifactName(), model.getResource(ns + "Video"));
        videoIndividual.addProperty(model.getProperty(ns + "artifactLocation"), video.getArtifactLocation());
        videoIndividual.addProperty(model.getProperty(ns + "artifactFormat"), video.getArtifactFormat());

        Resource videoIsMadeByResource = model.createResource(ns + video.getIsMadeBy());
        videoIndividual.addProperty(model.getProperty(ns + "isMadeBy"), videoIsMadeByResource);
        Resource videohasUsedInResource = model.createResource(ns + video.getHasUsedIn());
        videoIndividual.addProperty(model.getProperty(ns + "hasUsedIn"), videohasUsedInResource);
        Resource videohasTaggedByResource = model.createResource(ns + video.getHasTaggedBy());
        videoIndividual.addProperty(model.getProperty(ns + "hasTaggedBy"), videohasTaggedByResource);
        Resource videoisUsedByResource = model.createResource(ns + video.getIsUsedBy());
        videoIndividual.addProperty(model.getProperty(ns + "isUsedBy"), videoisUsedByResource);

        // Guardar la ontología de vuelta en el archivo
        try {
            FileOutputStream out = new FileOutputStream(ONTOLOGY_PATH.getValue());
            model.write(out, "RDF/XML-ABBREV");
            out.close();
        } catch (Exception e) {
            System.out.println("Error al guardar la ontología: " + e.getMessage());
        }
    }
    
}
