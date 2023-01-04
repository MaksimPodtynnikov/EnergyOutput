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
                return switch (imb) {
                    case LOW -> 7.7;
                    case LOWER -> 8.2;
                    case MIDDLE -> 9.6;
                    case HIGHER -> 11.5;
                    case HIGH -> 13.3;
                };
            else if (age.getYear() < 3) return switch (imb) {
                case LOW -> 9.7;
                case LOWER -> 11.6;
                case MIDDLE -> 12.2;
                case HIGHER -> 15.5;
                case HIGH -> 17.1;
            };
            else if (age.getYear() < 4) return switch (imb) {
                case LOW -> 11.3;
                case LOWER -> 12.4;
                case MIDDLE -> 14.3;
                case HIGHER -> 17.4;
                case HIGH -> 20.7;
            };
            else if (age.getYear() < 5) return switch (imb) {
                case LOW -> 12.7;
                case LOWER -> 14.2;
                case MIDDLE -> 16.3;
                case HIGHER -> 20.5;
                case HIGH -> 24.2;
            };
            else if (age.getYear() < 6) return switch (imb) {
                case LOW -> 14.1;
                case LOWER -> 16.3;
                case MIDDLE -> 18.3;
                case HIGHER -> 23.3;
                case HIGH -> 27.9;
            };
            else if (age.getYear() < 7) return switch (imb) {
                case LOW -> 15.9;
                case LOWER -> 17;
                case MIDDLE -> 20.5;
                case HIGHER -> 25;
                case HIGH -> 31.5;
            };
            else if (age.getYear() < 8) return switch (imb) {
                case LOW -> 17.7;
                case LOWER -> 19.7;
                case MIDDLE -> 22.9;
                case HIGHER -> 30;
                case HIGH -> 36.1;
            };
            else if (age.getYear() < 9) return switch (imb) {
                case LOW -> 19.5;
                case LOWER -> 22;
                case MIDDLE -> 25.4;
                case HIGHER -> 32;
                case HIGH -> 41.5;
            };
            else if (age.getYear() < 10) return switch (imb) {
                case LOW -> 21.3;
                case LOWER -> 24;
                case MIDDLE -> 28.1;
                case HIGHER -> 38;
                case HIGH -> 48.2;
            };
            else if (age.getYear() < 11) return switch (imb) {
                case LOW -> 23.2;
                case LOWER -> 26;
                case MIDDLE -> 31.2;
                case HIGHER -> 45;
                case HIGH -> 56.4;
            };
            else if (age.getYear() < 12) return switch (imb) {
                case LOW -> 26;
                case LOWER -> 28;
                case MIDDLE -> 35;
                case HIGHER -> 46;
                case HIGH -> 51.5;
            };
            else if (age.getYear() < 13) return switch (imb) {
                case LOW -> 28.2;
                case LOWER -> 30.7;
                case MIDDLE -> 40;
                case HIGHER -> 49;
                case HIGH -> 58.7;
            };
            else if (age.getYear() < 14) return switch (imb) {
                case LOW -> 30.9;
                case LOWER -> 33.8;
                case MIDDLE -> 44;
                case HIGHER -> 55;
                case HIGH -> 66;
            };
            else if (age.getYear() < 15) return switch (imb) {
                case LOW -> 34.3;
                case LOWER -> 38;
                case MIDDLE -> 51;
                case HIGHER -> 62;
                case HIGH -> 73.2;
            };
            else if (age.getYear() < 16) return switch (imb) {
                case LOW -> 38.7;
                case LOWER -> 43;
                case MIDDLE -> 54;
                case HIGHER -> 65;
                case HIGH -> 80.1;
            };
            else return switch (imb) {
                case LOW -> 44;
                case LOWER -> 48.3;
                case MIDDLE -> 61;
                case HIGHER -> 72;
                case HIGH -> 84.7;
            };
        }
        else if(sex == Sex.WOMEN)
        {
            if (age.getYear() < 2)
                return switch (imb) {
                    case LOW -> 7.0;
                    case LOWER -> 8.0;
                    case MIDDLE -> 8.9;
                    case HIGHER -> 11.5;
                    case HIGH -> 13.1;
                };
            else if (age.getYear() < 3) return switch (imb) {
                case LOW -> 9.0;
                case LOWER -> 10;
                case MIDDLE -> 11.5;
                case HIGHER -> 14.5;
                case HIGH -> 17;
            };
            else if (age.getYear() < 4) return switch (imb) {
                case LOW -> 10.8;
                case LOWER -> 11.4;
                case MIDDLE -> 13.9;
                case HIGHER -> 17.4;
                case HIGH -> 20.9;
            };
            else if (age.getYear() < 5) return switch (imb) {
                case LOW -> 12.3;
                case LOWER -> 14.1;
                case MIDDLE -> 16.1;
                case HIGHER -> 20.5;
                case HIGH -> 25.2;
            };
            else if (age.getYear() < 6) return switch (imb) {
                case LOW -> 13.7;
                case LOWER -> 15.3;
                case MIDDLE -> 18.2;
                case HIGHER -> 22.3;
                case HIGH -> 29.5;
            };
            else if (age.getYear() < 7) return switch (imb) {
                case LOW -> 15.3;
                case LOWER -> 17;
                case MIDDLE -> 20.2;
                case HIGHER -> 25;
                case HIGH -> 33.4;
            };
            else if (age.getYear() < 8) return switch (imb) {
                case LOW -> 16.8;
                case LOWER -> 19;
                case MIDDLE -> 22.4;
                case HIGHER -> 30;
                case HIGH -> 38.3;
            };
            else if (age.getYear() < 9) return switch (imb) {
                case LOW -> 18.6;
                case LOWER -> 21;
                case MIDDLE -> 25;
                case HIGHER -> 34;
                case HIGH -> 44.1;
            };
            else if (age.getYear() < 10) return switch (imb) {
                case LOW -> 20.8;
                case LOWER -> 24;
                case MIDDLE -> 28.2;
                case HIGHER -> 44;
                case HIGH -> 51.1;
            };
            else if (age.getYear() < 11) return switch (imb) {
                case LOW -> 23.2;
                case LOWER -> 27;
                case MIDDLE -> 31.9;
                case HIGHER -> 45;
                case HIGH -> 59.2;
            };
            else if (age.getYear() < 12) return switch (imb) {
                case LOW -> 24.9;
                case LOWER -> 27.8;
                case MIDDLE -> 34;
                case HIGHER -> 42;
                case HIGH -> 55.2;
            };
            else if (age.getYear() < 13) return switch (imb) {
                case LOW -> 27.8;
                case LOWER -> 31.8;
                case MIDDLE -> 41;
                case HIGHER -> 52;
                case HIGH -> 63.4;
            };
            else if (age.getYear() < 14) return switch (imb) {
                case LOW -> 32;
                case LOWER -> 38.7;
                case MIDDLE -> 47;
                case HIGHER -> 59;
                case HIGH -> 69;
            };
            else if (age.getYear() < 15) return switch (imb) {
                case LOW -> 37.6;
                case LOWER -> 43.8;
                case MIDDLE -> 53;
                case HIGHER -> 62;
                case HIGH -> 72.2;
            };
            else if (age.getYear() < 16) return switch (imb) {
                case LOW -> 42;
                case LOWER -> 46.8;
                case MIDDLE -> 55.2;
                case HIGHER -> 64;
                case HIGH -> 74.9;
            };
            else return switch (imb) {
                    case LOW -> 45.2;
                    case LOWER -> 48.4;
                    case MIDDLE -> 56.2;
                    case HIGHER -> 65;
                    case HIGH -> 75.6;
                };
        }
        return 0;
    }
}
