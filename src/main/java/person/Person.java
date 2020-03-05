package person;

public class Person {
    public final String name;
    public final String surname;
    private static int ids = 0;
    private int id;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = ++ids;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public static Person parse(String s) {
        String[] splited = s.split(":");
        return new Person(
                splited[0],
                splited[1]
        );
    }

    public String represent() {
        return String.format("%s:%s:%d", name, surname, id);
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', surname='%s', id=%d}", name, surname, id);
    }
}
