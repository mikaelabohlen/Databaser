package org.example.gui.guidto;

public class ArenaDTO {
    private String name;
    private AdressDTO adress;
    private boolean inside;

    public ArenaDTO() {

    }

    public ArenaDTO(String name, AdressDTO adress, boolean inside) {
        this.name = name;
        this.adress = adress;
        this.inside = inside;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdressDTO getAdress() {
        return adress;
    }

    public void setAdress(AdressDTO adress) {
        this.adress = adress;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }
}
