import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        PlantUMLRunner.setPathJarUml("plantuml-1.2024.4.jar");
        String data = peopleList.get(3).plantUmlWihParents();
        PlantUMLRunner.generateDiagram(data,"uml","person.txt");
   }
}