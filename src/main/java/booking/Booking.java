package booking;

import person.Person;
import race.Race;

public class Booking {
    private static int ids = 0;
    public final int id;
    private Person person;
    private Race race;

    public Booking(Person person, Race race) {
        this.id = ++ids;
        this.person = person;
        this.race = race;
    }
}
