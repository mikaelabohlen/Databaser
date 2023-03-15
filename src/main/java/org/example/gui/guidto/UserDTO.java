package org.example.gui.guidto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String userName;
    private String password;
    private boolean admin;
    private AdressDTO adressDTO;
    private List<ConcertDTO> concerts;

    public UserDTO() {
        this.concerts = new ArrayList<>();
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.concerts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public AdressDTO getAdressDTO() {
        return adressDTO;
    }

    public void setAdressDTO(AdressDTO adressDTO) {
        this.adressDTO = adressDTO;
    }

    public List<ConcertDTO> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<ConcertDTO> concerts) {
        this.concerts = concerts;
    }
}
