package com.ndn.enums;

import java.util.ArrayList;
import java.util.List;

public enum Gender {
    Male, Female, Other;
    public static List<Gender> getGenders() {
        List<Gender> genders = new ArrayList<>();
        genders.add(Male);
        genders.add(Female);
        genders.add(Other);
        return genders;
    }
}
