import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private LocalDate dateBirth;
    private LocalDate dateDeath;

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public LocalDate getDateDeath() {
        return dateDeath;
    }

    public static Person fromCsvLine(String csvLine){
        Person person = new Person();
        String[] dataPerson = csvLine.split(",");
        person.name = dataPerson[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        person.dateBirth = person.dateBirth.parse(dataPerson[1],formatter);
        if(!dataPerson[2].isEmpty())  person.dateDeath = person.dateDeath.parse(dataPerson[2],formatter);
        return person;
    }
    public static List<Person> fromCsv(String path){
        List<Person> peopleList = new ArrayList<>();
        String csvLine;
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            bufferedReader.readLine();
            while((csvLine = bufferedReader.readLine()) != null) {
                Person person = fromCsvLine(csvLine);
                person.validateLifespan();
                peopleList.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NegativeLifespanException e) {
            System.err.println(e.getMessage());
        }
        return peopleList;
    }
    public void validateLifespan() throws NegativeLifespanException {
        if(dateDeath != null && dateDeath.isBefore(dateBirth)){
            throw new NegativeLifespanException(this);
        }
    }

    @Override
    public String toString() {
        return "Person { " +
                "name = \'" + name + '\'' +
                ", birthDate = " + dateBirth +
                ", deathDate = " + dateDeath +
                //", parents=" + parents +
                " }";
    }
}
