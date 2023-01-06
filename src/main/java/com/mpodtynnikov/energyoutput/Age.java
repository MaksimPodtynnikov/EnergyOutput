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
    public double getMidMass(Sex sex,IMB imb) {
        if(sex == Sex.MAN) {
            if (age.getYear() < 2)
                switch (imb) {
                    case LOW: return 7.7;
                    case LOWER: return 8.2;
                    case MIDDLE: return 9.6;
                    case HIGHER: return 11.5;
                    case HIGH: return 13.3;
                }
            else if (age.getYear() < 3) switch (imb) {
                case LOW : return 9.7;
                case LOWER : return 11.6;
                case MIDDLE : return 12.2;
                case HIGHER : return 15.5;
                case HIGH : return 17.1;
            }
            else if (age.getYear() < 4) switch (imb) {
                case LOW : return 11.3;
                case LOWER : return 12.4;
                case MIDDLE : return 14.3;
                case HIGHER : return 17.4;
                case HIGH : return 20.7;
            }
            else if (age.getYear() < 5) switch (imb) {
                case LOW : return 12.7;
                case LOWER : return 14.2;
                case MIDDLE : return 16.3;
                case HIGHER : return 20.5;
                case HIGH : return 24.2;
            }
            else if (age.getYear() < 6) switch (imb) {
                case LOW : return 14.1;
                case LOWER : return 16.3;
                case MIDDLE : return 18.3;
                case HIGHER : return 23.3;
                case HIGH : return 27.9;
            }
            else if (age.getYear() < 7) switch (imb) {
                case LOW : return 15.9;
                case LOWER : return 17;
                case MIDDLE : return 20.5;
                case HIGHER : return 25;
                case HIGH : return 31.5;
            }
            else if (age.getYear() < 8) switch (imb) {
                case LOW : return 17.7;
                case LOWER : return 19.7;
                case MIDDLE : return 22.9;
                case HIGHER : return 30;
                case HIGH : return 36.1;
            }
            else if (age.getYear() < 9) switch (imb) {
                case LOW : return 19.5;
                case LOWER : return 22;
                case MIDDLE : return 25.4;
                case HIGHER : return 32;
                case HIGH : return 41.5;
            }
            else if (age.getYear() < 10) switch (imb) {
                case LOW : return 21.3;
                case LOWER : return 24;
                case MIDDLE : return 28.1;
                case HIGHER : return 38;
                case HIGH : return 48.2;
            }
            else if (age.getYear() < 11) switch (imb) {
                case LOW : return 23.2;
                case LOWER : return 26;
                case MIDDLE : return 31.2;
                case HIGHER : return 45;
                case HIGH : return 56.4;
            }
            else if (age.getYear() < 12) switch (imb) {
                case LOW : return 26;
                case LOWER : return 28;
                case MIDDLE : return 35;
                case HIGHER : return 46;
                case HIGH : return 51.5;
            }
            else if (age.getYear() < 13)  switch (imb) {
                case LOW : return 28.2;
                case LOWER : return 30.7;
                case MIDDLE : return 40;
                case HIGHER : return 49;
                case HIGH : return 58.7;
            }
            else if (age.getYear() < 14)  switch (imb) {
                case LOW : return 30.9;
                case LOWER : return 33.8;
                case MIDDLE : return 44;
                case HIGHER : return 55;
                case HIGH : return 66;
            }
            else if (age.getYear() < 15) switch (imb) {
                case LOW : return 34.3;
                case LOWER : return 38;
                case MIDDLE : return 51;
                case HIGHER : return 62;
                case HIGH : return 73.2;
            }
            else if (age.getYear() < 16) switch (imb) {
                case LOW : return 38.7;
                case LOWER : return 43;
                case MIDDLE : return 54;
                case HIGHER : return 65;
                case HIGH : return 80.1;
            }
            else switch (imb) {
                case LOW : return 44;
                case LOWER : return 48.3;
                case MIDDLE : return 61;
                case HIGHER : return 72;
                case HIGH : return 84.7;
            }
        }
        else if(sex == Sex.WOMEN)
        {
            if (age.getYear() < 2)
                switch (imb) {
                    case LOW : return 7.0;
                    case LOWER : return 8.0;
                    case MIDDLE : return 8.9;
                    case HIGHER : return 11.5;
                    case HIGH : return 13.1;
                }
            else if (age.getYear() < 3) switch (imb) {
                case LOW : return 9.0;
                case LOWER : return 10;
                case MIDDLE : return 11.5;
                case HIGHER : return 14.5;
                case HIGH : return 17;
            }
            else if (age.getYear() < 4) switch (imb) {
                case LOW : return 10.8;
                case LOWER : return 11.4;
                case MIDDLE : return 13.9;
                case HIGHER : return 17.4;
                case HIGH : return 20.9;
            }
            else if (age.getYear() < 5) switch (imb) {
                case LOW : return 12.3;
                case LOWER : return 14.1;
                case MIDDLE : return 16.1;
                case HIGHER : return 20.5;
                case HIGH : return 25.2;
            }
            else if (age.getYear() < 6) switch (imb) {
                case LOW : return 13.7;
                case LOWER : return 15.3;
                case MIDDLE : return 18.2;
                case HIGHER : return 22.3;
                case HIGH : return 29.5;
            }
            else if (age.getYear() < 7) switch (imb) {
                case LOW : return 15.3;
                case LOWER : return 17;
                case MIDDLE : return 20.2;
                case HIGHER : return 25;
                case HIGH : return 33.4;
            }
            else if (age.getYear() < 8) switch (imb) {
                case LOW : return 16.8;
                case LOWER : return 19;
                case MIDDLE : return 22.4;
                case HIGHER : return 30;
                case HIGH : return 38.3;
            }
            else if (age.getYear() < 9) switch (imb) {
                case LOW : return 18.6;
                case LOWER : return 21;
                case MIDDLE : return 25;
                case HIGHER : return 34;
                case HIGH : return 44.1;
            }
            else if (age.getYear() < 10) switch (imb) {
                case LOW : return 20.8;
                case LOWER : return 24;
                case MIDDLE : return 28.2;
                case HIGHER : return 44;
                case HIGH : return 51.1;
            }
            else if (age.getYear() < 11) switch (imb) {
                case LOW : return 23.2;
                case LOWER : return 27;
                case MIDDLE : return 31.9;
                case HIGHER : return 45;
                case HIGH : return 59.2;
            }
            else if (age.getYear() < 12) switch (imb) {
                case LOW : return 24.9;
                case LOWER : return 27.8;
                case MIDDLE : return 34;
                case HIGHER : return 42;
                case HIGH : return 55.2;
            }
            else if (age.getYear() < 13) switch (imb) {
                case LOW : return 27.8;
                case LOWER : return 31.8;
                case MIDDLE : return 41;
                case HIGHER : return 52;
                case HIGH : return 63.4;
            }
            else if (age.getYear() < 14) switch (imb) {
                case LOW : return 32;
                case LOWER : return 38.7;
                case MIDDLE : return 47;
                case HIGHER : return 59;
                case HIGH : return 69;
            }
            else if (age.getYear() < 15) switch (imb) {
                case LOW : return 37.6;
                case LOWER : return 43.8;
                case MIDDLE : return 53;
                case HIGHER : return 62;
                case HIGH : return 72.2;
            }
            else if (age.getYear() < 16) switch (imb) {
                case LOW : return 42;
                case LOWER : return 46.8;
                case MIDDLE : return 55.2;
                case HIGHER : return 64;
                case HIGH : return 74.9;
            }
            else switch (imb) {
                case LOW : return 45.2;
                case LOWER : return 48.4;
                case MIDDLE : return 56.2;
                case HIGHER : return 65;
                case HIGH : return 75.6;
            }
        }
        return 0;
    }
}
