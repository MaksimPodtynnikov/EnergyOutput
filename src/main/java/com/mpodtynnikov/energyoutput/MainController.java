package com.mpodtynnikov.energyoutput;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public TreeView<String> elementsTree;
    public ChoiceBox<String> IMTSelect;
    public TextField titleText;
    public RadioButton manSelect;
    public RadioButton womanSelect;
    public TextField countText;
    public TextField energyText;
    public TextField o2Text;
    public TextField co2Text;
    public VBox peopleBox;
    public DatePicker birthdayText;
    public ToggleGroup sexGroup;
    Peoples collection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection = new Peoples("Коллекция");
        ObservableList<String> imbs = FXCollections.observableArrayList("низкий","ниже среднего","средний","выше среднего","высокий");
        IMTSelect.setItems(imbs);
        elementsTree.setRoot(collection);
        collectionBoot();
        MenuItem menuItem = new MenuItem("Добавить группу людей");
        menuItem.setOnAction(e-> settingPeople());
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(menuItem);
        collection.getGraphic().setOnContextMenuRequested(e-> contextMenu.show(collection.getGraphic(),e.getScreenX(),e.getScreenY()));
        collection.getGraphic().setOnMouseClicked(e->collectionBoot());
        countText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                countText.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
    private void settingPeople()
    {
        People people = new People(Sex.MAN,new Age(LocalDate.now().getYear()-1, 1, 1),
                IMB.MIDDLE,"Люди",1);
        collection.add(people);
        people.getGraphic().setOnMouseClicked(e->peopleBoot(people));
        MenuItem menuItem = new MenuItem("Удалить");
        menuItem.setOnAction(e-> collection.remove(people));
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(menuItem);
        people.getGraphic().setOnContextMenuRequested(e-> contextMenu.show(people.getGraphic(),e.getScreenX(),e.getScreenY()));
    }
    public void collectionBoot()
    {
        peopleBox.setVisible(false);
        countText.setEditable(false);
        titleText.textProperty().setValue(collection.getTitle());
        titleText.setOnAction(e-> collection.setTitle(titleText.getText()));
        countText.textProperty().setValue(String.valueOf(collection.getCount()));
        energyText.textProperty().setValue(String.valueOf(collection.getWork(true)));
        o2Text.textProperty().setValue(String.valueOf(collection.getO2(true)));
        co2Text.textProperty().setValue(String.valueOf(collection.getCO2(true)));
    }
    public void peopleBoot(People people)
    {
        peopleBox.setVisible(true);
        countText.setEditable(true);
        update(people);
        titleText.textProperty().setValue(people.getTitle());
        titleText.setOnAction(e-> people.setTitle(titleText.getText()));
        countText.textProperty().setValue(String.valueOf(people.count));
        countText.setOnAction(e->{
            people.count = Integer.parseInt(countText.getText());
            update(people);
        });
        if (people.sex == Sex.MAN) {
            manSelect.setSelected(true);
        } else {
            womanSelect.setSelected(true);
        }
        manSelect.setOnMouseClicked(e-> {
            if(manSelect.isSelected())
                people.sex = Sex.MAN;
            update(people);
        });
        womanSelect.setOnMouseClicked(e-> {
            if(womanSelect.isSelected())
                people.sex = Sex.WOMEN;
            update(people);
        });
        IMTSelect.getSelectionModel().select(people.getImb().ordinal());
        IMTSelect.setOnHidden(e->{
            people.setImb(getIMB(IMTSelect.getSelectionModel().getSelectedIndex()));
            update(people);
        });
        birthdayText.setValue(people.age.birthday);
        birthdayText.setOnHidden(e->{
            people.age =new Age(LocalDate.parse(birthdayText.getEditor().getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getMonthValue(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getDayOfMonth());
            update(people);
        });
        birthdayText.setOnKeyTyped(e->{
            people.age =new Age(LocalDate.parse(birthdayText.getEditor().getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getMonthValue(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getDayOfMonth());
            update(people);
        });
    }
    private void update(People people)
    {
        energyText.textProperty().setValue(String.valueOf(people.getWork(true)));
        o2Text.textProperty().setValue(String.valueOf(people.getO2(true)));
        co2Text.textProperty().setValue(String.valueOf(people.getCO2(true)));
    }
    public IMB getIMB(int value)
    {
        return switch (value)
                {
                    case 0 -> IMB.LOW;
                    case 1 -> IMB.LOWER;
                    case 2 -> IMB.MIDDLE;
                    case 3 -> IMB.HIGHER;
                    default -> IMB.HIGH;
                };
    }
    @FXML
    private void saveToJSON()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор папки для сохранения");
        fileChooser.setInitialFileName("Коллекция");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model", "*.json"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null)
            collection.saveToJSON(file.getPath());
    }
    @FXML
    private void openJSON()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить данные");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            collection.clear();
            for (People people : collection.getFromJSON(file.getPath())) {
                collection.add(people);
                people.getGraphic().setOnMouseClicked(e -> peopleBoot(people));
                MenuItem menuItem = new MenuItem("Удалить");
                menuItem.setOnAction(e -> collection.remove(people));
                ContextMenu contextMenu = new ContextMenu();
                contextMenu.getItems().add(menuItem);
                people.getGraphic().setOnContextMenuRequested(e -> contextMenu.show(people.getGraphic(), e.getScreenX(), e.getScreenY()));
            }
        }
    }
}