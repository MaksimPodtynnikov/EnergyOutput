package com.mpodtynnikov.energyoutput;

public class PeopleState {

    int count;
    String title;
    String  age;
    String sex;
    String imb;
    double o2;
    double co2;
    double energy;
    public PeopleState()
    {

    }
    public PeopleState(People people)
    {
        this.title = people.getTitle();
        count = people.count;
        age = people.age.birthday.toString();
        sex = people.sex.name();
        imb = people.getImb().name();
        o2 = people.getO2(true);
        co2 = people.getCO2(true);
        energy = people.getWork(true);
    }

}
