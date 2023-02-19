package com.mpodtynnikov.energyoutput.Model;

import com.mpodtynnikov.energyoutput.IMB;
import com.mpodtynnikov.energyoutput.Sex;

public interface Dao {
    void getDBConnection();
    double getMidMass(double age, Sex sex, IMB imb);
    void addMidMass(int age,Sex sex,IMB imb, double midMass);
}
