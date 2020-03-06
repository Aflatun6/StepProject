package main;

import dao.DAO;
import person.Person;
import person.PersonDAO;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        DAO<Person> people = new PersonDAO("people.txt");
        people.add(new Person("Aflatun", "Velibeyli"));
        people.add(new Person("Afo", "Velibeyli"));
        people.add(new Person("Alik", "Veliev"));
        people.add(new Person("Eflatun", "Veliev"));

        Collection<Person> all = people.getAll();
        all.forEach(p -> System.out.println(p));

    }
}
