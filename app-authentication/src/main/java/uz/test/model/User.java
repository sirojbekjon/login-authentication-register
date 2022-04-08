package uz.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String password;
}
