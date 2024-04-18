import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        Optional<Person> person=Person.olderLifePerson(peopleList);
        System.out.println(person);
   }
}