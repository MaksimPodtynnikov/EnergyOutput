package com.mpodtynnikov.energyoutput.JSONParser;

import com.mpodtynnikov.energyoutput.People;

public class PeopleState {

    int count;
    String title;
    String  age;
    String sex;
    String imb;
    boolean yearsMode;
    int years;
    double o2;
    double co2;
    double energy;

    public PeopleState(People people, boolean easyWork)
    {
        this.title = people.getTitle();
        count = people.getCount();
        age = people.getAge().getBirthday().toString();
        yearsMode = people.isYearsMode();
        years = people.getYears();
        sex = people.getSex().name();
        imb = people.getImb().name();
        o2 = people.getO2(easyWork);
        co2 = people.getCO2(easyWork);
        energy = people.getWork(easyWork);
    }

}
