package ro.itschool.controller.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ro.itschool.entity.MyUser;

@Data
public class MyUserModel {

    @CsvBindByPosition(position = 1)
    private Long id;

    @CsvBindByPosition(position = 0)
    private String username;

    @CsvBindByPosition(position = 2)
    private String firstName;

    @CsvBindByPosition(position = 3)
    private String lastName;

    public MyUser toEntity() {
        MyUser myUser = new MyUser();
        myUser.setId(this.id);
        myUser.setUsername(this.username);
        myUser.setFirstName(this.firstName);
        myUser.setLastName(this.lastName);
        return myUser;
    }
}
