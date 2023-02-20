package com.mpodtynnikov.energyoutput;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mpodtynnikov.energyoutput.JSONParser.*;
import com.mpodtynnikov.energyoutput.Model.Dao;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;
import org.apache.poi.xwpf.usermodel.*;

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
    public Peoples addCollection(Peoples peoples){
        collections.add(peoples);
        getChildren().add(peoples);
        return peoples;
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
    public void exportToWord(String path,boolean easyWork) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Отчет о газо- и тепловыделении детей в проекте '"+this.getTitle()+"'");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);
        XWPFParagraph mainText = document.createParagraph();
        mainText.setIndentationFirstLine(10);
        mainText.setAlignment(ParagraphAlignment.BOTH);
        String countText = " детей";
        if(this.getCount()==1) countText = " ребенка";
        XWPFRun mainRun = mainText.createRun();
        String workText = "при умственной работе.";
        if(easyWork) workText = "при легкой работе.";
        mainRun.setText("'"+this.getTitle() +"', находящийся по адресу "+this.getPlace()+", расчитан на "+this.getCount()+countText+
                ". В сумме выделяется "+this.getWork(easyWork)+" тепла, "+this.getO2(easyWork)+" кислорода и "+this.getCO2(easyWork)+" углекислого газа "+workText);
        mainRun.setFontFamily("Times New Roman");
        mainRun.setFontSize(14);
        XWPFParagraph mainTextGroup = document.createParagraph();
        mainTextGroup.setIndentationFirstLine(10);
        mainTextGroup.setAlignment(ParagraphAlignment.BOTH);
        for (People people: this.getPeoples()){
            XWPFRun peopleRun = mainTextGroup.createRun();
            peopleRun.setFontFamily("Times New Roman");
            peopleRun.setFontSize(14);
            countText = " детей";
            if(people.getCount()==1) countText = " ребенка";
            String sexText=" мужского пола";
            if(people.getSex()==Sex.WOMEN) sexText=" женского пола";
            String imbText = " с высоким ИМТ";
            switch (people.getImb()){
                case LOW: imbText = " с низким ИМТ";
                    break;
                case LOWER: imbText = " с ИМТ ниже среднего";
                    break;
                case MIDDLE: imbText = " со средним ИМТ";
                    break;
                case HIGHER: imbText = " с ИМТ выше среднего";
                    break;
                default:break;
            }
            String ageText = " в возрасте "+ people.getAge().age.getYear() +" лет";
            if(people.getAge().age.getYear()<2) ageText = " в возрасте 1 года";
            peopleRun.setText("В группе '"+people.getTitle()+"' состоящей из "+people.getCount()+
                    countText+sexText+imbText+ageText +" выделяет "+people.getWork(easyWork)+" теплоты, "+people.getO2(easyWork)+" кислорода и "+people.getCO2(easyWork)+" углекислого газа.");
        }
        for (Peoples collection: this.getCollections())
            collectionToWord(collection,easyWork,document);
        FileOutputStream out = new FileOutputStream(path);
        document.write(out);
        out.close();
        document.close();
    }
    private void collectionToWord(Peoples collection,boolean easyWork, XWPFDocument document)
    {
        XWPFParagraph mainText = document.createParagraph();
        mainText.setIndentationFirstLine(10);
        mainText.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun mainRun = mainText.createRun();
        mainRun.setFontFamily("Times New Roman");
        mainRun.setFontSize(14);
        mainRun.setText("Подколлекция '"+collection.getTitle()+"' находящаяся в месте - "+collection.place+" содержит следующие группы:");
        mainRun.addBreak(BreakType.TEXT_WRAPPING);
        for (People people: this.getPeoples()){
            XWPFRun peopleRun = mainText.createRun();
            peopleRun.setFontFamily("Times New Roman");
            peopleRun.setFontSize(14);
            String countText = " детей";
            if(people.getCount()==1) countText = " ребенка";
            String sexText=" мужского пола";
            if(people.getSex()==Sex.WOMEN) sexText=" женского пола";
            String imbText = " с высоким ИМТ";
            switch (people.getImb()){
                case LOW: imbText = " с низким ИМТ";
                    break;
                case LOWER: imbText = " с ИМТ ниже среднего";
                    break;
                case MIDDLE: imbText = " со средним ИМТ";
                    break;
                case HIGHER: imbText = " с ИМТ выше среднего";
                    break;
                default:break;
            }
            String ageText = " в возрасте "+ people.getAge().age.getYear() +" лет";
            if(people.getAge().age.getYear()<2) ageText = " в возрасте 1 года";
            peopleRun.setText("В группе '"+people.getTitle()+"' состоящей из "+people.getCount()+
                    countText+sexText+imbText+ageText +" выделяет "+people.getWork(easyWork)+" теплоты, "+people.getO2(easyWork)+" кислорода и "+people.getCO2(easyWork)+" углекислого газа.");
            peopleRun.addBreak(BreakType.TEXT_WRAPPING);
        }
        for (Peoples child: collection.getCollections())
            collectionToWord(child,easyWork,document);
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
