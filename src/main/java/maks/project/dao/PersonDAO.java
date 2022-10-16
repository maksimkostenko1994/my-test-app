package maks.project.dao;

import maks.project.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Maks", "Maks@mail.de", 27));
        people.add(new Person(++PEOPLE_COUNT, "Anna", "Anna@mail.de", 28));
        people.add(new Person(++PEOPLE_COUNT, "Kit", "Kit@mail.de", 10));
        people.add(new Person(++PEOPLE_COUNT, "Sam", "Sam@mail.de", 14));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int id) {
        return people
                .stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void create(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = getPerson(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void remove(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
