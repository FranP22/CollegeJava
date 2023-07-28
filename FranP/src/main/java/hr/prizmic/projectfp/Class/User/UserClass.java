package hr.prizmic.projectfp.Class.User;

import java.time.LocalDate;

public class UserClass {
    private final Long ID;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final LocalDate DOB;

    public UserClass(Long id, String firstName, String lastName, String username, String password, LocalDate DOB) {
        ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.DOB = DOB;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public Long getID() {
        return ID;
    }
}
