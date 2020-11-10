package contoller;

import app.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.StorageManager;

import java.io.File;

public class ControllerOpen implements EventHandler<ActionEvent> {

    public void handle(ActionEvent event) {

        DirectoryChooser dc = new DirectoryChooser();
        File selectedDirectory = dc.showDialog(MainView.getInstance());
        MainView.getInstance().setSelectedDirectory(selectedDirectory);

        try {
            Class.forName("JsonStorage.JSONImpl");
        } catch (ClassNotFoundException e) {
            System.out.println("nije nasao klasu");
            e.printStackTrace();
        }
        ImportAndExportStorage base = StorageManager.getStorage(selectedDirectory.getAbsolutePath());
        base.open(selectedDirectory);
        ObservableList<Entity> list= FXCollections.observableArrayList(base.getEntities());
        MainView.getInstance().getTable().setItems(list);


    }

}