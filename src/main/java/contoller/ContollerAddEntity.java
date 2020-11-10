package contoller;

import app.MainView;
import app.NewEntityView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.Specifikacija;
import storage.StorageManager;

import java.util.HashMap;
import java.util.Map;

public class ContollerAddEntity implements EventHandler<ActionEvent> {

    public void handle(ActionEvent event) {

        String name = NewEntityView.getInstance().getTxtName().getText();
        String attributes = NewEntityView.getInstance().getTxtArea().getText();
        String[] keyValue= attributes.split("\n");
        Map<String, Object> map = new HashMap<String, Object>();
        for (String str : keyValue) {
            String array[] = str.split("=");
            map.put(array[0], array[1]);
        }
        String baseName = MainView.getInstance().getSelectedDirectory().getAbsolutePath();
        ImportAndExportStorage storage = StorageManager.getStorage(baseName);
        Specifikacija spec = new Specifikacija(storage);
        int id = Integer.parseInt(NewEntityView.getInstance().getTxtId().getText());
        System.out.println(id);
        System.out.println(name);
        System.out.println(map);
        spec.createEntity(id, name, map);
        ObservableList<Entity> list = FXCollections.observableArrayList(storage.getEntities());
        MainView.getInstance().getTable().getItems().clear();
        MainView.getInstance().getTable().refresh();
        MainView.getInstance().getTable().setItems(list);
        MainView.getInstance().getTable().refresh();

    }
}
