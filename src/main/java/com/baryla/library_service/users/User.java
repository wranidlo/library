package com.baryla.library_service.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USER", schema = "jpa_users")
public class User {
    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private AccountType accountType;

    public User() {}

    public User(String firstName, String lastName, AccountType accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName)
                && accountType == user.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, accountType);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
