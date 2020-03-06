package race;

import dao.DAO;

import java.io.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class RaceDAO implements DAO<Race> {
    public final String filename;

    public RaceDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public Optional<Race> get(int id) {
        return getAll().stream()
                .filter(r -> r.id == id).findFirst();
    }

    @Override
    public Collection<Race> getAll() {
        try {
            return new BufferedReader(new FileReader(new File(filename)))
                    .lines().map(r -> Race.parse(r)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void add(Race newItem) {
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
                    filter(r -> r.id != id)
                    .forEach(r -> {
                        try {
                            bw.write(r.represent());
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
