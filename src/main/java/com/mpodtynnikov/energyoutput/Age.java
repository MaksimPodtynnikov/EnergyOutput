package com.mpodtynnikov.energyoutput;

import java.time.LocalDate;

public class Age {
    LocalDate age;
    LocalDate birthday;
    public Age(int year, int month, int dayOfMonth)
    {
        birthday=LocalDate.of(year,month,dayOfMonth);
        age = LocalDate.of(LocalDate.now().getYear() - year,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()).minusMonths(month);
    }
    public Age(int year)
    {
        age = LocalDate.of(year,1,1);
        birthday = LocalDate.now();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public double getMultiplier(Sex sex) {
        if(sex == Sex.MAN) {
            if (age.getYear() < 3) return 0.249;
            else if (age.getYear() < 10) return 0.095;
            else if (age.getYear() < 17) return 0.074;
        }
        else if(sex == Sex.WOMEN)
        {
            if (age.getYear() < 3) return 0.244;
            else if (age.getYear() < 10) return 0.085;
            else if (age.getYear() < 17) return 0.056;
        }
        return 0;
    }
    public static Age valueOf(String value)
    {
        LocalDate date = LocalDate.parse(value);
        return new Age(date.getYear(), date.getMonthValue(),date.getDayOfMonth());
    }

    public double getSummand(Sex sex) {
        if(sex == Sex.MAN) {
            if (age.getYear() < 3) return -0.249;
            else if (age.getYear() < 10) return 0.095;
            else if (age.getYear() < 17) return 0.074;
        }
        else if(sex == Sex.WOMEN)
        {
            if (age.getYear() < 3) return -0.244;
            else if (age.getYear() < 10) return 0.085;
            else if (age.getYear() < 17) return 0.056;
        }
        return 0;
    }
}
