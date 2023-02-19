package com.mpodtynnikov.energyoutput.JSONParser;

import com.mpodtynnikov.energyoutput.People;
import com.mpodtynnikov.energyoutput.Peoples;

import java.util.ArrayList;
import java.util.List;

public class PeoplesState {
    int count;
    String title;
    String place;
    double o2;
    double co2;
    double energy;
    List<PeopleState> peopleStates = new ArrayList<>();
    List<PeoplesState> peoplesStates = new ArrayList<>();
    public PeoplesState(Peoples peoples, boolean easyWork)
    {
        this.title = peoples.getTitle();
        count = peoples.getCount();
        place = peoples.getPlace();
        o2 = peoples.getO2(easyWork);
        co2 = peoples.getCO2(easyWork);
        energy = peoples.getWork(easyWork);
        for (People people:peoples.getPeoples()) {
            peopleStates.add(new PeopleState(people,easyWork));
        }
        for (Peoples collection:peoples.getCollections()) {
            peoplesStates.add(new PeoplesState(collection,easyWork));
        }
    }
}
