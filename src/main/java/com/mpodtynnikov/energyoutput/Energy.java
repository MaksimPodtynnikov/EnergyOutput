package com.mpodtynnikov.energyoutput;

public interface Energy {
    double getCO2(boolean easyWork);

    double getWork(boolean easyWork);

    double getO2(boolean easyWork);
    String getTitle();
}
