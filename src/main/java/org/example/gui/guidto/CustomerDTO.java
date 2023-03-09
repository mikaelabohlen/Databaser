package org.example.gui.guidto;

import java.time.LocalDate;

public class CustomerDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phone;
    private AdressDTO adress;
    private UserDTO user;

    public CustomerDTO() {

    }

    public CustomerDTO(String firstName, String lastName, LocalDate birthdate, String phone, AdressDTO adress, UserDTO user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phone = phone;
        this.adress = adress;
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AdressDTO getAdress() {
        return adress;
    }

    public void setAdress(AdressDTO adress) {
        this.adress = adress;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
