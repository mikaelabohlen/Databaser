package org.example.gui;

import org.example.gui.guidto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuiController {

    private UserDTO demoUserDTO, demoUser2DTO, demoAdmin;
    private AdressDTO adressDTO, adressDTO2, adressDTO3, adressDTO4, adressDTO5;
    private ConcertDTO concertDTO, concertDTO2, concertDTO3, concertDTO4, concertDTO5;
    private CustomerDTO customerDTO;
    private ArenaDTO arenaDTO, arenaDTO2, arenaDTO3, arenaDTO4, arenaDTO5;

    private List<ConcertDTO> concertDTOS;
    private List<ArenaDTO> arenaDTOS;
    private List<UserDTO> userDTOS;
    public List<ConcertDTO> getConcertDTOS() {
        return concertDTOS;
    }

    public List<ArenaDTO> getArenaDTOS() {
        return arenaDTOS;
    }

    public GuiController() {
        createMockData();
    }

    public boolean validateLogin(String userName, String password) {
        for (UserDTO userDTO : userDTOS) {
            if (userDTO.isAdmin()) {
                continue;
            }
            if (userDTO.getUserName().equals(userName) && userDTO.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateLoginAdmin(String userName, String password) {
        for (UserDTO userDTO : userDTOS) {
            if (!userDTO.isAdmin()) {
                continue;
            }
            if (userDTO.getUserName().equals(userName) && userDTO.getPassword().equals(password)) {
                return true;
            }

        }
        return false;
    }

    public boolean addNewConcert(List<String> addConcertInputs) {
        try {
            ConcertDTO concertDTOnew = new ConcertDTO();

            concertDTOnew.setArtist(addConcertInputs.get(0));
            concertDTOnew.setDate(LocalDate.parse(addConcertInputs.get(1)));
            concertDTOnew.setPrice(Double.parseDouble(addConcertInputs.get(2)));
            concertDTOnew.setAgeLimit(Integer.parseInt(addConcertInputs.get(3)));

            for(int i=0; i<getArenaDTOS().size(); i++) {
                if(getArenaDTOS().get(i).getName().equals(addConcertInputs.get(4))) {
                    concertDTOnew.setArena(getArenaDTOS().get(i));
                }
            }
            getConcertDTOS().add(concertDTOnew);
            return true;

        }catch (Exception e) {
            return false;
        }

    }

    public boolean addnewArena(List<String> addArenaInputs) {
        try {
            ArenaDTO arenaDTOnew = new ArenaDTO();
            AdressDTO adressDTOnew = new AdressDTO();

            arenaDTOnew.setName(addArenaInputs.get(0));

            adressDTOnew.setStreet(addArenaInputs.get(1));
            adressDTOnew.setStreetNumber(Integer.parseInt(addArenaInputs.get(2)));
            adressDTOnew.setZipCode(Integer.parseInt(addArenaInputs.get(3)));
            adressDTOnew.setCity(addArenaInputs.get(4));

            arenaDTOnew.setAdress(adressDTOnew);

            getArenaDTOS().add(arenaDTOnew);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private void createMockData() {
        createMockUsers();
        createMockAdresses();
        createMockCustomers();
        createMockArenas();
        createMockConcerts();

        concertDTOS = new ArrayList<>();
        concertDTOS.add(concertDTO);
        concertDTOS.add(concertDTO2);
        concertDTOS.add(concertDTO3);
        concertDTOS.add(concertDTO4);
        concertDTOS.add(concertDTO5);

        arenaDTOS = new ArrayList<>();
        arenaDTOS.add(arenaDTO);
        arenaDTOS.add(arenaDTO2);
        arenaDTOS.add(arenaDTO3);
        arenaDTOS.add(arenaDTO4);
        arenaDTOS.add(arenaDTO5);

        userDTOS = new ArrayList<>();
        userDTOS.add(demoUserDTO);
        userDTOS.add(demoUser2DTO);
        userDTOS.add(demoAdmin);
    }

    private void createMockUsers() {
        demoUserDTO = new UserDTO("jim", "hej");
        demoUser2DTO = new UserDTO("jim2", "hej2");
        demoAdmin = new UserDTO("admin", "admin");
        demoAdmin.setAdmin(true);
    }

    private void createMockCustomers() {
    }

    private void createMockAdresses() {
        adressDTO = new AdressDTO("Ullevivägen",1,66666,"Göteborg");
        adressDTO2 = new AdressDTO("Globenvägen",1,55555,"Stockholm");
        adressDTO3 = new AdressDTO("Friendsvägen",1,44444,"Stockholm");
        adressDTO4 = new AdressDTO("Tele2vägen",1,33333,"Stockholm");
        adressDTO5 = new AdressDTO("Hovetvägen",1,22222,"Stockholm");
    }

    private void createMockArenas() {
        arenaDTO = new ArenaDTO("Ullevi", adressDTO,true);
        arenaDTO2 = new ArenaDTO("Globen", adressDTO2,true);
        arenaDTO3 = new ArenaDTO("Friends Arena", adressDTO3,true);
        arenaDTO4 = new ArenaDTO("Tele 2 Arena", adressDTO4,true);
        arenaDTO5 = new ArenaDTO("Hovet", adressDTO5,true);
    }

    private void createMockConcerts() {
        concertDTO = new ConcertDTO("The Hellacopters", LocalDate.of(2023,3,24), 500.00, arenaDTO,15);
        concertDTO2 = new ConcertDTO("Metallica", LocalDate.of(2023,5,11), 500.00, arenaDTO2,15);
        concertDTO3 = new ConcertDTO("Megadeth", LocalDate.of(2023,6,14), 600.00, arenaDTO3,15);
        concertDTO4 = new ConcertDTO("Bombus", LocalDate.of(2023,7,30), 700.00, arenaDTO4,15);
        concertDTO5 = new ConcertDTO("Danko Jones", LocalDate.of(2023,11,13), 2000.00, arenaDTO5,15);
    }


}

