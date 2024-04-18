import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        PlantUMLRunner.setPathJarUml("plantuml-1.2024.4.jar");
        Person person= peopleList.get(3);
        //String data =person.toPlantUmlWihParents(str -> str);
        String data = person.toPlantUmlWihParents(
                str ->String.format(Locale.ENGLISH,"#Lime"));
        PlantUMLRunner.generateDiagram(data,"uml","color");
   }
}