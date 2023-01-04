package com.mpodtynnikov.energyoutput;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Peoples extends TreeItem<String> implements Energy{
    private String title;
    SimpleModule javafxModule = new SimpleModule();
    public Peoples(String title)
    {
        super("",new Text(title));
        this.title = title;
        javafxModule.addSerializer(PeopleState.class, new PeopleSerializer());
        javafxModule.addDeserializer(People.class, new PeopleDeserializer());
    }
    List<People> peoples = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
        ((Text)getGraphic()).setText(title);
    }
    public int getCount()
    {
        int count=0;
        for (People people:peoples) {
            count += people.count;
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
    @Override
    public double getCO2(boolean easyWork) {
        double CO2 = 0;
        for (People people : peoples) {
            CO2+=people.getCO2(easyWork);
        }
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
        return work;
    }

    @Override
    public double getO2(boolean easyWork) {
        double O2 = 0;
        for (People people : peoples) {
            O2+=people.getO2(easyWork);
        }
        return O2;
    }
    public void saveToJSON(String path)
    {
        List<PeopleState> peopleStates =new ArrayList<>();
        for (People people : peoples)
            peopleStates.add(new PeopleState(people));
        try (Writer writer =  new FileWriter(path)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(javafxModule);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writerFor(new TypeReference<ArrayList<PeopleState>>() { }) // указали какой тип подсовываем
                    .withDefaultPrettyPrinter() // кстати эта строчка чтобы в файлике все красиво печаталось
                    .writeValue(writer, peopleStates); // а это непосредственно запись
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<People> getFromJSON(String path)
    {
        try (Reader reader =  new FileReader(path)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(javafxModule);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.readerFor(new TypeReference<ArrayList<People>>() { })
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
}
