package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class AuthProfile {
    String publicId;
    String name;
    String lastName;
    String email;
    int house;
    String phone;
    String position;
    String street;
    String zip;
    String city;
    String companyName;
    String companyPhone;
    String companyWebsite;
    String country;
    String role;
}
