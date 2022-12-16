module com.handball.handballdesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fazecast.jSerialComm;

    opens com.handball.handballdesktopapp to javafx.fxml;
    exports com.handball.handballdesktopapp;
}