package data_access;

import entity.User;
import entity.UserFactory;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


// TODO 19: Make sure it extends ClearUserDataAccessInterface now too
public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password, ldt);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    // TODO 20: So I could not figure out how to get this.clearall() to work, it kept on giving me access modifier erros so I gave up and it's all public and under one function now
    // TODO 20: Implement the only piece of code that's actually doing work and deleting data
    @Override
    public ArrayList<String> clearall() {
        ArrayList<String> deletedUsers = new ArrayList<String>(); // ArrayList of names which we need to return
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(csvFile));  // TODO 20.1: Read the file and extract the first names
            String line;
            // Skip Headers
            line = reader.readLine();

            // Read names to be deleted
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values[0]);
                deletedUsers.add(values[0]);
            }
            reader.close();

            // Open file for writing then just writer headers + close to erase the other data
            writer = new BufferedWriter(new FileWriter(csvFile));  // TODO 20.2: Read the file and just write the column names
            writer.write("username,password,creation_time\n");  // TODO 20.2: For reference in Java (and also every programming language I know) the default writing to a file is actually like an overwrite. So if you opened a file with Writer, did nothing, and closed, it would wipe everything.
            writer.close();

        } catch (IOException e) {
            new RuntimeException(e);
        }
        return deletedUsers;
    }

    // TODO 20.5: Remember your roots - head back to ClearInteractor, the thing that calls this thing

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}
