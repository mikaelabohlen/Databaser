package org.example;

import javax.persistence.*;

// With @Enumerated(EnumType.STRING), we can safely add new enum values or change our enum's order.
// However, renaming an enum value will still break the database data.

//AttributeConverter interface. Moreover, we can safely add new enum values
//or change the existing ones without breaking the already persisted data.
public enum Setting {
    INSIDE("inomhus"),
    OUTSIDE("utomhus");
    public final String label;
    private Setting(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }
}
