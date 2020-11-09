package contoller;

import app.MainView;
import app.NewEntityView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class ContollerAddEntity implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

        String name = NewEntityView.getInstance().getTxtName().getText();
        String attributes = NewEntityView.getInstance().getTxtArea().getText();
        String[] keyValuePairs = attributes.split("\n");
        Map<String, Object> map = new HashMap<String, Object>();
        for (String s : keyValuePairs) {
            String arr[] = s.split(":");
            map.put(arr[0], arr[1]);
        }
        String baseName = MainView.getInstance().getSelectedDirectory().getAbsolutePath();
//        ImportAndExportStorage base = StorageManager.getStorage(baseName);
//        Specifikacija crudOperation = new Specifikacija(base);
//        int id = Integer.parseInt(NewEntityView.getInstance().getTxtId().getText());
//        crudOperation.createEntity(id, name, map);
//
//        ObservableList<Entity> list = FXCollections.observableArrayList(base.getEntities());
//        MainView.getInstance().getTable().getItems().clear();
//        MainView.getInstance().getTable().setItems(list);

    }
}
