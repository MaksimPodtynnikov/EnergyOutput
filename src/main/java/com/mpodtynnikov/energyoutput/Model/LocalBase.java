package com.mpodtynnikov.energyoutput.Model;

import com.mpodtynnikov.energyoutput.IMB;
import com.mpodtynnikov.energyoutput.Sex;

import java.sql.*;

public class LocalBase implements Dao {
    public static final String DB_URL = "jdbc:h2:/db/SchedulesDB";
    public static final String DB_Driver = "org.h2.Driver";
    public static final String TableMass="Mass";

    @Override
    public void getDBConnection() {
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL);
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, TableMass, null);
            if (!rs.next()) {
                createTableMass();
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }
    }

    private void deleteTable()
    {
        String deleteTableSQL = "DROP TABLE "+ LocalBase.TableMass;
        try (Connection dbConnection = DriverManager.getConnection(DB_URL)) {
            assert dbConnection != null;
            try (Statement statement = dbConnection.createStatement()) {
                statement.execute(deleteTableSQL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableMass() {
        String createTableSQL = "CREATE TABLE "+ TableMass+ " ("
                + "ID number NOT NULL auto_increment, "
                + "Age number NOT NULL, "
                + "IMB number NOT NULL, "
                + "MidMass number NULL, "
                + "Sex boolean NULL"
                + ")";
        try (Connection dbConnection = DriverManager.getConnection(DB_URL)) {
            assert dbConnection != null;
            try (Statement statement = dbConnection.createStatement()) {
                statement.execute(createTableSQL);
                startCollection();
            }
        } catch (SQLException ignored) {
        }
    }

    private void startCollection()
    {
        addMidMass(1, Sex.MAN, IMB.LOW,7.7);
        addMidMass(1,Sex.MAN,IMB.LOWER,8.2);
        addMidMass(1,Sex.MAN,IMB.MIDDLE,9.6);
        addMidMass(1,Sex.MAN,IMB.HIGHER,11.5);
        addMidMass(1,Sex.MAN,IMB.HIGH,13.3);

        addMidMass(2,Sex.MAN,IMB.LOW,9.7);
        addMidMass(2,Sex.MAN,IMB.LOWER,11.6);
        addMidMass(2,Sex.MAN,IMB.MIDDLE,12.2);
        addMidMass(2,Sex.MAN,IMB.HIGHER,15.5);
        addMidMass(2,Sex.MAN,IMB.HIGH,17.1);

        addMidMass(3,Sex.MAN,IMB.LOW,11.3);
        addMidMass(3,Sex.MAN,IMB.LOWER,12.4);
        addMidMass(3,Sex.MAN,IMB.MIDDLE,14.3);
        addMidMass(3,Sex.MAN,IMB.HIGHER,17.4);
        addMidMass(3,Sex.MAN,IMB.HIGH,20.7);

        addMidMass(4,Sex.MAN,IMB.LOW,12.7);
        addMidMass(4,Sex.MAN,IMB.LOWER,14.2);
        addMidMass(4,Sex.MAN,IMB.MIDDLE,16.3);
        addMidMass(4,Sex.MAN,IMB.HIGHER,20.5);
        addMidMass(4,Sex.MAN,IMB.HIGH,24.2);

        addMidMass(5,Sex.MAN,IMB.LOW,14.1);
        addMidMass(5,Sex.MAN,IMB.LOWER,16.3);
        addMidMass(5,Sex.MAN,IMB.MIDDLE,18.3);
        addMidMass(5,Sex.MAN,IMB.HIGHER,23.3);
        addMidMass(5,Sex.MAN,IMB.HIGH,27.9);

        addMidMass(6,Sex.MAN,IMB.LOW,15.9);
        addMidMass(6,Sex.MAN,IMB.LOWER,17);
        addMidMass(6,Sex.MAN,IMB.MIDDLE,20.5);
        addMidMass(6,Sex.MAN,IMB.HIGHER,25);
        addMidMass(6,Sex.MAN,IMB.HIGH,31.5);

        addMidMass(7,Sex.MAN,IMB.LOW,17.7);
        addMidMass(7,Sex.MAN,IMB.LOWER,19.7);
        addMidMass(7,Sex.MAN,IMB.MIDDLE,22.9);
        addMidMass(7,Sex.MAN,IMB.HIGHER,30);
        addMidMass(7,Sex.MAN,IMB.HIGH,36.1);

        addMidMass(8,Sex.MAN,IMB.LOW,19.5);
        addMidMass(8,Sex.MAN,IMB.LOWER,22);
        addMidMass(8,Sex.MAN,IMB.MIDDLE,25.4);
        addMidMass(8,Sex.MAN,IMB.HIGHER,32);
        addMidMass(8,Sex.MAN,IMB.HIGH,41.5);

        addMidMass(9,Sex.MAN,IMB.LOW,21.3);
        addMidMass(9,Sex.MAN,IMB.LOWER,24);
        addMidMass(9,Sex.MAN,IMB.MIDDLE,28.1);
        addMidMass(9,Sex.MAN,IMB.HIGHER,38);
        addMidMass(9,Sex.MAN,IMB.HIGH,48.2);

        addMidMass(10,Sex.MAN,IMB.LOW,23.2);
        addMidMass(10,Sex.MAN,IMB.LOWER,26);
        addMidMass(10,Sex.MAN,IMB.MIDDLE,31.2);
        addMidMass(10,Sex.MAN,IMB.HIGHER,45);
        addMidMass(10,Sex.MAN,IMB.HIGH,56.4);

        addMidMass(11,Sex.MAN,IMB.LOW,26);
        addMidMass(11,Sex.MAN,IMB.LOWER,28);
        addMidMass(11,Sex.MAN,IMB.MIDDLE,35);
        addMidMass(11,Sex.MAN,IMB.HIGHER,46);
        addMidMass(11,Sex.MAN,IMB.HIGH,51.5);

        addMidMass(12,Sex.MAN,IMB.LOW,28.2);
        addMidMass(12,Sex.MAN,IMB.LOWER,30.7);
        addMidMass(12,Sex.MAN,IMB.MIDDLE,40);
        addMidMass(12,Sex.MAN,IMB.HIGHER,49);
        addMidMass(12,Sex.MAN,IMB.HIGH,58.7);

        addMidMass(13,Sex.MAN,IMB.LOW,30.9);
        addMidMass(13,Sex.MAN,IMB.LOWER,33.8);
        addMidMass(13,Sex.MAN,IMB.MIDDLE,44);
        addMidMass(13,Sex.MAN,IMB.HIGHER,55);
        addMidMass(13,Sex.MAN,IMB.HIGH,66);

        addMidMass(14,Sex.MAN,IMB.LOW,34.3);
        addMidMass(14,Sex.MAN,IMB.LOWER,38);
        addMidMass(14,Sex.MAN,IMB.MIDDLE,51);
        addMidMass(14,Sex.MAN,IMB.HIGHER,62);
        addMidMass(14,Sex.MAN,IMB.HIGH,73.2);

        addMidMass(15,Sex.MAN,IMB.LOW,38.7);
        addMidMass(15,Sex.MAN,IMB.LOWER,43);
        addMidMass(15,Sex.MAN,IMB.MIDDLE,54);
        addMidMass(15,Sex.MAN,IMB.HIGHER,65);
        addMidMass(15,Sex.MAN,IMB.HIGH,80.1);

        addMidMass(16,Sex.MAN,IMB.LOW,44);
        addMidMass(16,Sex.MAN,IMB.LOWER,48.3);
        addMidMass(16,Sex.MAN,IMB.MIDDLE,61);
        addMidMass(16,Sex.MAN,IMB.HIGHER,72);
        addMidMass(16,Sex.MAN,IMB.HIGH,84.7);

        addMidMass(1,Sex.WOMEN,IMB.LOW,7);
        addMidMass(1,Sex.WOMEN,IMB.LOWER,8);
        addMidMass(1,Sex.WOMEN,IMB.MIDDLE,8.9);
        addMidMass(1,Sex.WOMEN,IMB.HIGHER,11.5);
        addMidMass(1,Sex.WOMEN,IMB.HIGH,13.1);

        addMidMass(2,Sex.WOMEN,IMB.LOW,9);
        addMidMass(2,Sex.WOMEN,IMB.LOWER,10);
        addMidMass(2,Sex.WOMEN,IMB.MIDDLE,11.5);
        addMidMass(2,Sex.WOMEN,IMB.HIGHER,14.5);
        addMidMass(2,Sex.WOMEN,IMB.HIGH,17);

        addMidMass(3,Sex.WOMEN,IMB.LOW,10.8);
        addMidMass(3,Sex.WOMEN,IMB.LOWER,11.4);
        addMidMass(3,Sex.WOMEN,IMB.MIDDLE,13.9);
        addMidMass(3,Sex.WOMEN,IMB.HIGHER,17.4);
        addMidMass(3,Sex.WOMEN,IMB.HIGH,20.9);

        addMidMass(4,Sex.WOMEN,IMB.LOW,12.3);
        addMidMass(4,Sex.WOMEN,IMB.LOWER,14.1);
        addMidMass(4,Sex.WOMEN,IMB.MIDDLE,16.1);
        addMidMass(4,Sex.WOMEN,IMB.HIGHER,20.5);
        addMidMass(4,Sex.WOMEN,IMB.HIGH,25.2);

        addMidMass(5,Sex.WOMEN,IMB.LOW,13.7);
        addMidMass(5,Sex.WOMEN,IMB.LOWER,15.3);
        addMidMass(5,Sex.WOMEN,IMB.MIDDLE,18.2);
        addMidMass(5,Sex.WOMEN,IMB.HIGHER,22.3);
        addMidMass(5,Sex.WOMEN,IMB.HIGH,29.5);

        addMidMass(6,Sex.WOMEN,IMB.LOW,15.3);
        addMidMass(6,Sex.WOMEN,IMB.LOWER,17);
        addMidMass(6,Sex.WOMEN,IMB.MIDDLE,20.2);
        addMidMass(6,Sex.WOMEN,IMB.HIGHER,25);
        addMidMass(6,Sex.WOMEN,IMB.HIGH,33.4);

        addMidMass(7,Sex.WOMEN,IMB.LOW,16.8);
        addMidMass(7,Sex.WOMEN,IMB.LOWER,19);
        addMidMass(7,Sex.WOMEN,IMB.MIDDLE,22.4);
        addMidMass(7,Sex.WOMEN,IMB.HIGHER,30);
        addMidMass(7,Sex.WOMEN,IMB.HIGH,38.3);

        addMidMass(8,Sex.WOMEN,IMB.LOW,18.6);
        addMidMass(8,Sex.WOMEN,IMB.LOWER,21);
        addMidMass(8,Sex.WOMEN,IMB.MIDDLE,25);
        addMidMass(8,Sex.WOMEN,IMB.HIGHER,34);
        addMidMass(8,Sex.WOMEN,IMB.HIGH,44.1);

        addMidMass(9,Sex.WOMEN,IMB.LOW,20.8);
        addMidMass(9,Sex.WOMEN,IMB.LOWER,24);
        addMidMass(9,Sex.WOMEN,IMB.MIDDLE,28.2);
        addMidMass(9,Sex.WOMEN,IMB.HIGHER,44);
        addMidMass(9,Sex.WOMEN,IMB.HIGH,51.1);

        addMidMass(10,Sex.WOMEN,IMB.LOW,23.2);
        addMidMass(10,Sex.WOMEN,IMB.LOWER,27);
        addMidMass(10,Sex.WOMEN,IMB.MIDDLE,31.9);
        addMidMass(10,Sex.WOMEN,IMB.HIGHER,45);
        addMidMass(10,Sex.WOMEN,IMB.HIGH,59.2);

        addMidMass(11,Sex.WOMEN,IMB.LOW,24.9);
        addMidMass(11,Sex.WOMEN,IMB.LOWER,27.8);
        addMidMass(11,Sex.WOMEN,IMB.MIDDLE,34);
        addMidMass(11,Sex.WOMEN,IMB.HIGHER,42);
        addMidMass(11,Sex.WOMEN,IMB.HIGH,55.2);

        addMidMass(12,Sex.WOMEN,IMB.LOW,27.8);
        addMidMass(12,Sex.WOMEN,IMB.LOWER,31.8);
        addMidMass(12,Sex.WOMEN,IMB.MIDDLE,41);
        addMidMass(12,Sex.WOMEN,IMB.HIGHER,52);
        addMidMass(12,Sex.WOMEN,IMB.HIGH,63.4);

        addMidMass(13,Sex.WOMEN,IMB.LOW,32);
        addMidMass(13,Sex.WOMEN,IMB.LOWER,38.7);
        addMidMass(13,Sex.WOMEN,IMB.MIDDLE,47);
        addMidMass(13,Sex.WOMEN,IMB.HIGHER,59);
        addMidMass(13,Sex.WOMEN,IMB.HIGH,69);

        addMidMass(14,Sex.WOMEN,IMB.LOW,37.6);
        addMidMass(14,Sex.WOMEN,IMB.LOWER,43.8);
        addMidMass(14,Sex.WOMEN,IMB.MIDDLE,53);
        addMidMass(14,Sex.WOMEN,IMB.HIGHER,62);
        addMidMass(14,Sex.WOMEN,IMB.HIGH,72.2);

        addMidMass(15,Sex.WOMEN,IMB.LOW,42);
        addMidMass(15,Sex.WOMEN,IMB.LOWER,46.8);
        addMidMass(15,Sex.WOMEN,IMB.MIDDLE,55.2);
        addMidMass(15,Sex.WOMEN,IMB.HIGHER,64);
        addMidMass(15,Sex.WOMEN,IMB.HIGH,74.9);

        addMidMass(16,Sex.WOMEN,IMB.LOW,45.2);
        addMidMass(16,Sex.WOMEN,IMB.LOWER,48.4);
        addMidMass(16,Sex.WOMEN,IMB.MIDDLE,56.2);
        addMidMass(16,Sex.WOMEN,IMB.HIGHER,65);
        addMidMass(16,Sex.WOMEN,IMB.HIGH,75.6);
    }
    @Override
    public double getMidMass(double age, Sex sex,IMB imb) {
        boolean sexData =sex.equals(Sex.MAN);
        String selection = "select * from "+ TableMass+" where Sex="+sexData+" AND Age="+age+" AND IMB="+imb.ordinal();
        try (Connection dbConnection = DriverManager.getConnection(DB_URL)) {
            assert dbConnection != null;
            try (Statement statement = dbConnection.createStatement()) {
                ResultSet rs = statement.executeQuery(selection);
                if (rs.next()) {
                    return rs.getDouble("MidMass");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void addMidMass(int age, Sex sex,IMB imb, double midMass) {
        boolean sexData =sex.equals(Sex.MAN);
        String insertTableSQL = "INSERT INTO "+ TableMass
                + " (Sex,MidMass,IMB,Age) " + "VALUES "
                + "("+sexData+","+midMass+","+imb.ordinal()+","+age+")";
        try (Connection dbConnection = DriverManager.getConnection(DB_URL)) {
            assert dbConnection != null;
            try (Statement statement = dbConnection.createStatement()) {
                statement.executeUpdate(insertTableSQL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
