package person;

import dao.DAO;

import java.io.*;
import java.util.Collection;
//import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonDAO implements DAO<Person> {
    public final String filename;

    public PersonDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public Optional<Person> get(int id) {
        return getAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
//                .orElseThrow(() -> new RuntimeException("Something went wrong"));
    }

    @Override
    public Collection<Person> getAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            return br.lines().map(Person::parse).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public boolean add(Person newItem) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true));
            bw.write(newItem.represent());
            bw.write("\n");
            bw.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true));
            getAll().stream().
                    filter(p -> p.getId() != id)
                    .forEach(p -> {
                        try {
                            bw.write(p.represent());
                            bw.write("\n");
                        } catch (IOException e) {
                            throw new RuntimeException("Something went wrong");
                        }
                    });
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
        return true;
    }
}
