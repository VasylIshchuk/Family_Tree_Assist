import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String name;
    private LocalDate dateBirth;
    private LocalDate dateDeath;
    public Person fromCsvLine(String csvLine){
        Person person = new Person();
        String[] dataPerson = csvLine.split(",");
        person.name = dataPerson[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        person.dateBirth = person.dateBirth.parse(dataPerson[1],formatter);
        if(!dataPerson[2].isEmpty())  person.dateDeath = person.dateDeath.parse(dataPerson[2],formatter);
        return person;
    }
}
