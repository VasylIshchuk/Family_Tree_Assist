import java.util.List;
public class Main {
    public static void main(String[] args) {
        //List<Person> peopleList = Person.fromCsv("family.csv");
        PlantUMLRunner.setPathJarUml("plantuml-1.2024.4.jar");
        String data = "@startuml\n" +
                "object JanKowalski\n" +
                "object AnnaKowalska\n" +
                "\n" +
                "JanKowalski --> AnnaKowalska\n" +
                "@enduml";
        PlantUMLRunner.generateDiagram(data,"uml","test.txt");
   }
}