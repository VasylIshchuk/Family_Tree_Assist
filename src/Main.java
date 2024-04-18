import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        List<Person> people=Person.sortedDeadPersonByAge(peopleList);
        for(Person person  : people)
            System.out.println(person);
   }
}