package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.Specifikacija;
import storage.StorageManager;

import java.util.HashMap;
import java.util.Map;

public class NewEntityView extends Stage {
    private static NewEntityView instance = null;
    private Label lblEnterId;
    private TextArea txtArea;
    private TextField txtId;
    private TextField txtName;
    private Button btnAddEntity;
    private CheckBox checkBox;
    boolean autoInc;

    private NewEntityView() {
        checkBox = new CheckBox("AutoIncrement");
        lblEnterId = new Label("Enter ID");
        lblEnterId.setPrefSize(100, 20);
        txtId = new TextField();
        HBox hbx = new HBox(10);
        hbx.getChildren().addAll(checkBox, lblEnterId);

        txtName = new TextField("Name");
        txtArea = new TextArea("Additional parameters");

        VBox vbx = new VBox(10);
        vbx.setPadding(new Insets(10));
        btnAddEntity = new Button("Add Entity");
        btnAddEntity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = NewEntityView.getInstance().getTxtName().getText();
                String attributes = NewEntityView.getInstance().getTxtArea().getText();
                autoInc = NewEntityView.getInstance().getCheckBox().isSelected();
                String[] keyValue = attributes.split(",");
                Map<String, Object> map = new HashMap<String, Object>();
                for (String str : keyValue) {
                    String array[] = str.split(":");
                    map.put(array[0], array[1]);
                }
                String baseName = MainView.getInstance().getSelectedDirectory().getAbsolutePath();
                ImportAndExportStorage storage = StorageManager.getStorage(baseName);
                Specifikacija spec = new Specifikacija(storage);
                if (autoInc) {
                    spec.createEntity(name, map);
                } else {
                    int id = Integer.parseInt(NewEntityView.getInstance().getTxtId().getText());
                    spec.createEntity(id, name, map);
                }
                ObservableList<Entity> list = FXCollections.observableArrayList(storage.getEntities());
                MainView.getInstance().getTable().getItems().clear();
                MainView.getInstance().getTable().refresh();
                MainView.getInstance().getTable().setItems(list);
                MainView.getInstance().getTable().refresh();
            }
        });
        vbx.getChildren().addAll(hbx, txtId, txtName, txtArea, btnAddEntity);
        Scene sc = new Scene(vbx);
        setScene(sc);
        setWidth(350);
        setHeight(450);
        show();
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isAutoInc() {
        return autoInc;
    }

    public void setAutoInc(boolean autoInc) {
        this.autoInc = autoInc;
    }

    public static void setInstance(NewEntityView instance) {
        NewEntityView.instance = instance;
    }

    public Label getLblEnterId() {
        return lblEnterId;
    }

    public void setLblEnterId(Label lblEnterId) {
        this.lblEnterId = lblEnterId;
    }

    public TextArea getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(TextArea txtArea) {
        this.txtArea = txtArea;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public Button getBtnAddEntity() {
        return btnAddEntity;
    }

    public void setBtnAddEntity(Button btnAddEntity) {
        this.btnAddEntity = btnAddEntity;
    }

    public static NewEntityView getInstance() {
        if (instance == null)
            instance = new NewEntityView();
        return instance;
    }

}
