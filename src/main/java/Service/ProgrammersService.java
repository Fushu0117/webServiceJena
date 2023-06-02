package Service;

import models.Programmer;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;

import org.apache.jena.util.FileManager;
import utils.CONSTANTS;
import utils.getStringFromOntology;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ProgrammersService {
    public ArrayList<Programmer> getProgrammersList(){
        getStringFromOntology getStringFromOntology = new getStringFromOntology();

        ArrayList<Programmer> programmersList = new ArrayList<>();

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        model.read(CONSTANTS.ONTOLOGY_PATH.getValue());

        String queryString = "SELECT ?programmer ?name ?email WHERE {\n" +
                "\t?programmer a <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#Programmer> .\n" +
                "\t?programmer <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#userName> ?name .\n" +
                "\t?programmer <http://www.semanticweb.org/jose/ontologies/2019/4/untitled-ontology-24#userEmail> ?email .\n" +
                "}";

        Query query = QueryFactory.create(queryString);
        try (QueryExecution exec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = exec.execSelect();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Programmer programmer = new Programmer();
                programmer.setName(getStringFromOntology.getString(solution.get("name")));
                programmer.setEmail(getStringFromOntology.getString(solution.get("email")));
                programmersList.add(programmer);
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return programmersList;
    }

    public void insertProgrammer(Programmer programmer) {
        CONSTANTS ONTOLOGY_PATH = CONSTANTS.ONTOLOGY_PATH;
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);

        // Cargar la ontología existente desde el archivo
        try {
            InputStream in = FileManager.get().open(ONTOLOGY_PATH.getValue());
            model.read(in, null);
        } catch (Exception e) {
            System.out.println("Error al cargar la ontología: " + e.getMessage());
        }

        // Crear un nuevo individuo de Programador y agregar sus propiedades
        String ns = CONSTANTS.ONTOLOGY_NAMESPACE.getValue();
        Individual programmerIndividual = model.createIndividual(ns + programmer.getName(), model.getResource(ns + "Programmer"));
        programmerIndividual.addProperty(model.getProperty(ns + "userName"), programmer.getName());
        programmerIndividual.addProperty(model.getProperty(ns + "userEmail"), programmer.getEmail());

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
