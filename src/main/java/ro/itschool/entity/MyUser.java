package ro.itschool.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MyUser {

    @Id
    private Long id;

    private String username;

    private String firstName;

    private String lastName;


}
