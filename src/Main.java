import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        PlantUMLRunner.setPathJarUml("plantuml-1.2024.4.jar");
        String data = Person.toPlantUmlPeople(peopleList);
        PlantUMLRunner.generateDiagram(data,"uml","people.txt");
   }
}