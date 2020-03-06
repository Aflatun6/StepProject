package booking;

import dao.DAO;

import java.io.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

//import java.util.Collections;

public class PersonDAO implements DAO<Booking> {
    public final String filename;

    public PersonDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public Optional<Booking> get(int id) {
        return getAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
//                .orElseThrow(() -> new RuntimeException("Something went wrong"));
    }

    @Override
    public Collection<Booking> getAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            return br.lines().map(Booking::parse).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void add(Booking newItem) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true));
            bw.write(newItem.represent());
            bw.write("\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void remove(int id) {
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
    }
}
