import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = Person.fromCsv("family.csv");
        peopleList = Person.sortedListToDateDeath(peopleList);
        for(Person person: peopleList)
               System.out.println(person);
   }
}