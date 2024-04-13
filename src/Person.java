import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private String name;
    private LocalDate dateBirth;
    private LocalDate dateDeath;
    private List<Person> parents= new ArrayList<>();
    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public LocalDate getDateDeath() {
        return dateDeath;
    }
    public void addParent(Person parent){
        this.parents.add(parent);
    }

    public static Person fromCsvLine(String csvLine){
        String[] dataPerson = csvLine.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Person person = new Person();
        person.name = dataPerson[0];
        person.dateBirth = person.dateBirth.parse(dataPerson[1],formatter);
        if(!dataPerson[2].isEmpty())  person.dateDeath = person.dateDeath.parse(dataPerson[2],formatter);
        return person;
    }

    public static List<Person> fromCsv(String path){
        List<Person> peopleList = new ArrayList<>();
        Map<String,PersonWithParentsNames> personWithParentsNamesMap = new HashMap<>();
        String csvLine;
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            bufferedReader.readLine();
            while((csvLine = bufferedReader.readLine()) != null) {
                PersonWithParentsNames personWithParentsNames = PersonWithParentsNames.fromCsvLine(csvLine);
                Person person = personWithParentsNames.getPerson();
                personWithParentsNamesMap.put(person.name,personWithParentsNames);

                person.validateLifespan();
                person.checkAmbiguousPerson(peopleList);

                peopleList.add(person);
            }
            PersonWithParentsNames.linkRelatives(personWithParentsNamesMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NegativeLifespanException e) {
            System.err.println(e.getMessage());
        } catch (AmbiguousPersonException e) {
            System.err.println(e.getMessage());
        }
        return peopleList;
    }
    public void validateLifespan() throws NegativeLifespanException {
        if(this.dateDeath != null && this.dateDeath.isBefore(this.dateBirth)){
            throw new NegativeLifespanException(this);
        }
    }
    public void checkAmbiguousPerson(List<Person> peopleList) throws AmbiguousPersonException {
        for(Person person : peopleList) {
            if (this.name.equals(person.name)){
                throw new AmbiguousPersonException(this);
            }
        }
    }
    @Override
    public String toString() {
        return "Person { " +
                "name = \'" + name + '\'' +
                ", birthDate = " + dateBirth +
                ", deathDate = " + dateDeath +
                ", parents=" + parents +
                " }";
    }
}
