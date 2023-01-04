package shpp.com.ua.example.model;

import shpp.com.ua.example.validator.IpnValidator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity //mark class as an Entity
@Table //defining class name as Table name
public class Person {
    @Id //mark id as primary key
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @IpnValidator(message = "IPN is not valid! Please check it!")
    private String ipn;
    @Column
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, message = "Name must contain at least two characters!")
    private String name;
    @Column
    @Min(value = 1, message = "Age must be greater than 0!")
    private int age;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name should not be empty!")
    private String lastName;

    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getIpn() {
        return ipn;
    }

    public Person setIpn(String ipn) {
        this.ipn = ipn;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", ipn='" + ipn + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
