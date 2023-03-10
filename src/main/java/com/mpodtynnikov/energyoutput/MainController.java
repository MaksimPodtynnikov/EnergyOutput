package com.mpodtynnikov.energyoutput;

import com.mpodtynnikov.energyoutput.Model.ModelPass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
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
    public TextField placeText;
    public ToggleButton easyWork;
    public ChoiceBox<Integer> yearsBox;
    public Label ageText;
    public Label placeLabel;
    Peoples collection;
    ModelPass modelPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        birthdayText.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(LocalDate.now().minusMonths(1)) || item.isBefore(LocalDate.now().minusYears(17)));
                    }});
        placeLabel.visibleProperty().bind(placeText.visibleProperty());
        placeLabel.managedProperty().bind(placeLabel.visibleProperty());
        placeText.managedProperty().bind(placeText.visibleProperty());
        peopleBox.managedProperty().bind(peopleBox.visibleProperty());
        modelPass = new ModelPass();
        yearsBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17));
        yearsBox.managedProperty().bind(yearsBox.visibleProperty());
        yearsBox.visibleProperty().bind(birthdayText.visibleProperty().not());
        birthdayText.managedProperty().bind(birthdayText.visibleProperty());
        collection = new Peoples("?????????? ????????????","", modelPass.getGenerator());
        ObservableList<String> imbs = FXCollections.observableArrayList("????????????","???????? ????????????????","??????????????","???????? ????????????????","??????????????");
        IMTSelect.setItems(imbs);
        elementsTree.setRoot(collection);
        collectionBoot(collection);
        MenuItem menuItemGroup = new MenuItem("???????????????? ???????????? ??????????");
        menuItemGroup.setOnAction(e-> {
            People people = new People(Sex.MAN,new Age(LocalDate.now().getYear()-1, 1, 1),
                    IMB.MIDDLE,"?????????? ????????????",1, modelPass.getGenerator());
            collection.add(people);
            settingPeople(people);
        });
        MenuItem menuItemCollection = new MenuItem("???????????????? ????????????????????????");
        menuItemCollection.setOnAction(e-> {
            Peoples peoples = new Peoples("?????????? ??????????????????","", modelPass.getGenerator());
            collection.addCollection(peoples);
            settingCollection(collection,peoples);
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(menuItemGroup,menuItemCollection);
        collection.getGraphic().setOnContextMenuRequested(e-> contextMenu.show(collection.getGraphic(),e.getScreenX(),e.getScreenY()));
        collection.getGraphic().setOnMouseClicked(e->collectionBoot(collection));
        countText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                countText.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
    private void settingCollection(Peoples parent,Peoples peoples)
    {
        peoples.getGraphic().setOnMouseClicked(e->collectionBoot(peoples));
        MenuItem menuItemGroup = new MenuItem("???????????????? ???????????? ??????????");
        menuItemGroup.setOnAction(e-> {
            People people = new People(Sex.MAN,new Age(LocalDate.now().getYear()-1, 1, 1),
                    IMB.MIDDLE,"?????????? ????????????",1, modelPass.getGenerator());
            peoples.add(people);
            settingPeople(people);
        });
        MenuItem menuItemCollection = new MenuItem("???????????????? ????????????????????????");
        menuItemCollection.setOnAction(e-> {
            Peoples child = new Peoples("?????????? ??????????????????","", modelPass.getGenerator());
            peoples.addCollection(child);
            settingCollection(peoples,child);
        });
        MenuItem menuItemRemove = new MenuItem("?????????????? ????????????????????????");
        menuItemRemove.setOnAction(e-> parent.removeCollection(peoples));
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(menuItemGroup,menuItemCollection,menuItemRemove);
        peoples.getGraphic().setOnContextMenuRequested(e->contextMenu.show(peoples.getGraphic(),e.getScreenX(),e.getScreenY()));
    }
    private void settingPeople(People people)
    {
        people.getGraphic().setOnMouseClicked(e->peopleBoot(people));
        MenuItem menuItem = new MenuItem("??????????????");
        menuItem.setOnAction(e-> ((Peoples)people.getParent()).remove(people));
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(menuItem);
        people.getGraphic().setOnContextMenuRequested(e-> contextMenu.show(people.getGraphic(),e.getScreenX(),e.getScreenY()));
    }
    public void collectionBoot(Peoples collection)
    {
        peopleBox.setVisible(false);
        countText.setEditable(false);
        titleText.textProperty().setValue(collection.getTitle());
        placeText.setVisible(true);
        placeText.textProperty().setValue(collection.getPlace());
        placeText.setOnAction(e -> collection.setPlace(placeText.getText()));
        titleText.setOnAction(e-> collection.setTitle(titleText.getText()));
        countText.textProperty().setValue(String.valueOf(collection.getCount()));
        energyText.textProperty().setValue(String.valueOf(collection.getWork(easyWork.isSelected())));
        o2Text.textProperty().setValue(String.valueOf(collection.getO2(easyWork.isSelected())));
        co2Text.textProperty().setValue(String.valueOf(collection.getCO2(easyWork.isSelected())));
    }
    private void countSetting(People people)
    {
        if(people.count>1) {
            people.yearsMode = true;
            birthdayText.setVisible(false);
            if(people.getYears()>=1)
                yearsBox.getSelectionModel().select(people.getYears()-1);
            else {
                yearsBox.getSelectionModel().select(0);
                people.setYears(yearsBox.getValue());
            }
            ageText.setText("??????????????");
        }
        else{
            people.yearsMode = false;
            birthdayText.setVisible(true);
            birthdayText.setValue(people.getAge().age);
            ageText.setText("???????? ????????????????");
        }
    }
    public void peopleBoot(People people)
    {
        countSetting(people);
        placeText.setVisible(false);
        yearsBox.setOnAction(null);
        yearsBox.setOnAction(e->{
            people.setYears(yearsBox.getValue());
            update(people);
        });
        easyWork.setOnMouseClicked(e->update(people));
        peopleBox.setVisible(true);
        countText.setEditable(true);
        titleText.textProperty().setValue(people.getTitle());
        titleText.setOnAction(e-> people.setTitle(titleText.getText()));
        countText.textProperty().setValue(String.valueOf(people.count));
        countText.setOnAction(e->{
            people.count = Integer.parseInt(countText.getText());
            countSetting(people);
            update(people);
        });
        if (people.getSex() == Sex.MAN) {
            manSelect.setSelected(true);
        } else {
            womanSelect.setSelected(true);
        }
        manSelect.setOnMouseClicked(e-> {
            if(manSelect.isSelected())
                people.setSex(Sex.MAN);
            update(people);
        });
        womanSelect.setOnMouseClicked(e-> {
            if(womanSelect.isSelected())
                people.setSex(Sex.WOMEN);
            update(people);
        });
        IMTSelect.setOnAction(null);
        IMTSelect.getSelectionModel().select(people.getImb().ordinal());
        IMTSelect.setOnAction(e->{
            people.setImb(getIMB(IMTSelect.getSelectionModel().getSelectedIndex()));
            update(people);
        });
        birthdayText.setValue(people.getAge().birthday);
        birthdayText.setOnHidden(e->{
            people.setAge(new Age(LocalDate.parse(birthdayText.getEditor().getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getMonthValue(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getDayOfMonth()));
            update(people);
        });
        birthdayText.setOnKeyTyped(e->{
            people.setAge(new Age(LocalDate.parse(birthdayText.getEditor().getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getMonthValue(),
                    LocalDate.parse(birthdayText.getEditor().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")).getDayOfMonth()));
            update(people);
        });
        update(people);
    }
    private void update(People people)
    {
        energyText.textProperty().setValue(String.valueOf(people.getWork(easyWork.isSelected())));
        o2Text.textProperty().setValue(String.valueOf(people.getO2(easyWork.isSelected())));
        co2Text.textProperty().setValue(String.valueOf(people.getCO2(easyWork.isSelected())));
    }
    public IMB getIMB(int value)
    {
        switch (value)
        {
            case 0: return IMB.LOW;
            case 1: return IMB.LOWER;
            case 2: return IMB.MIDDLE;
            case 3: return IMB.HIGHER;
            default: return IMB.HIGH;
        }
    }
    @FXML
    private void saveToJSON()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("?????????? ?????????? ?????? ????????????????????");
        fileChooser.setInitialFileName(collection.getTitle());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model", "*.json"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null)
            collection.saveToJSON(file.getPath(),easyWork.isSelected());
    }
    @FXML
    private void exportWord() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("?????????? ?????????? ?????? ????????????????????");
        fileChooser.setInitialFileName(collection.getTitle());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("document", "*.docx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null)
            collection.exportToWord(file.getPath(),easyWork.isSelected());
    }
    @FXML
    private void openJSON()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("?????????????????? ????????????");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            collection.clear();
            Peoples temp = collection.getFromJSON(file.getPath());
            collection.setPlace(temp.getPlace());
            collection.setTitle(temp.getTitle());
            collectionLoad(temp,collection);
        }
    }
    private void collectionLoad(Peoples origin,Peoples destination)
    {
        for (Peoples peoples : origin.getCollections()) {
            Peoples newPeoples = destination.addCollection(new Peoples(peoples.getTitle(), peoples.getPlace(), modelPass.getGenerator()));
            settingCollection(destination, newPeoples);
            collectionLoad(peoples, newPeoples);
        }
        for (People people : origin.getPeoples()) {
            People newPeople = new People(people.getSex(),people.getAge(),people.getImb(),people.getTitle(), people.getCount(), modelPass.getGenerator());
            destination.add(newPeople);
            settingPeople(newPeople);
        }
    }
    @FXML
    private void help()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "?? ?????????? ?????????? ???????????????????? ?????????????????? ???????????? ??????????????. ?????? ???????????? ???????????????????????? ???????? ?????????????? ?????? ???? ?????????????? ???????????? ????????????????." +
                        "???????????????? ?????????????? ???? 2 ????????: ?????????????????? - ???????????????????? ?????? ?????????? ?? ????????????, ???????????????????? ???????????? ???????????????????? ?????? ??????????????.\n?????? ???????????? ???????????????? ???????????? ???? ???????? ?????? ?? ???????????? ????????????????, ?????????? ???????? ?? ???????????? ?????????? ???????????? ???????????? ??????????????????." +
                        "?????? ???????????????????? ?????????????????? ???????????????????? ???????????? Enter, ???????????????? ?? ???????? ??????????.\n?????????? ?? ?????????????????????????? - mpodtynnikov@mail.ru");
        alert.setHeaderText("????????????????????");
        alert.setTitle("?? ??????????????????");
        alert.show();
    }
}