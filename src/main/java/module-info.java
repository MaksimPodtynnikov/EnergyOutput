module com.mpodtynnikov.energyoutput {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.mpodtynnikov.energyoutput to javafx.fxml;
    exports com.mpodtynnikov.energyoutput;
}