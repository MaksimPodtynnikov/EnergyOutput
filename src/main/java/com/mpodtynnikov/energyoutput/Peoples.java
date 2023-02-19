package com.mpodtynnikov.energyoutput;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mpodtynnikov.energyoutput.JSONParser.*;
import com.mpodtynnikov.energyoutput.Model.Dao;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Peoples extends TreeItem<String> implements Energy{
    private String title;
    private String place;
    SimpleModule javafxModule = new SimpleModule();
    List<People> peoples = new ArrayList<>();
    List<Peoples> collections = new ArrayList<>();
    public Peoples(String title, String place, Dao generator)
    {
        super("",new Text(title));
        this.title = title;
        this.place = place;
        javafxModule.addSerializer(PeoplesState.class,new PeoplesSerializer());
        javafxModule.addDeserializer(Peoples.class, new PeoplesDeserializer(generator));
    }
    public void setTitle(String title) {
        this.title = title;
        ((Text)getGraphic()).setText(title);
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public List<Peoples> getCollections() {
        return collections;
    }

    public void setPlace(String address)
    {
        this.place = address;
    }
    public int getCount()
    {
        int count=0;
        for (People people:peoples) {
            count += people.count;
        }
        for (Peoples peoples:collections) {
            count += peoples.getCount();
        }
        return count;
    }
    public void add(People people){
        peoples.add(people);
        getChildren().add(people);
    }
    public void remove(People people)
    {
        peoples.remove(people);
        getChildren().remove(people);
    }
    public void addCollection(Peoples peoples){
        collections.add(peoples);
        getChildren().add(peoples);
    }
    public void removeCollection(Peoples peoples)
    {
        collections.remove(peoples);
        getChildren().remove(peoples);
    }
    @Override
    public double getCO2(boolean easyWork) {
        double CO2 = 0;
        for (People people : peoples) {
            CO2+=people.getCO2(easyWork);
        }
        for (Peoples peoples: collections)
            CO2+=peoples.getCO2(easyWork);
        return CO2;
    }
    public void clear()
    {
        peoples.clear();
        getChildren().clear();
    }
    @Override
    public double getWork(boolean easyWork) {
        double work = 0;
        for (People people : peoples) {
            work+=people.getWork(easyWork);
        }
        for (Peoples peoples: collections)
            work+=peoples.getWork(easyWork);
        return work;
    }

    @Override
    public double getO2(boolean easyWork) {
        double O2 = 0;
        for (People people : peoples) {
            O2+=people.getO2(easyWork);
        }
        for (Peoples peoples: collections)
            O2+=peoples.getO2(easyWork);
        return O2;
    }
    public void saveToJSON(String path,boolean easyWork)
    {
        try (Writer writer =  new FileWriter(path)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(javafxModule);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writerFor(new TypeReference<PeoplesState>() { }) // указали какой тип подсовываем
                    .withDefaultPrettyPrinter() // кстати эта строчка чтобы в файлике все красиво печаталось
                    .writeValue(writer, new PeoplesState(this,easyWork)); // а это непосредственно запись
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Peoples getFromJSON(String path)
    {
        try (Reader reader =  new FileReader(path)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(javafxModule);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.readerFor(new TypeReference<Peoples>() { })
                    .readValue(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }
}
