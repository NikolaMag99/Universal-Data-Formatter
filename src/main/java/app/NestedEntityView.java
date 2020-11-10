package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.StorageManager;

import java.util.HashMap;
import java.util.Map;

public class NestedEntityView extends Stage {
    private static NestedEntityView instance = null;
    private TextField txtValue;
    private TextField txtAttribute;
    private TextField txtKey;
    private Button btnAdd;
    private Button btnApply;
    private Map<String, String> mapAttributes;


    private NestedEntityView() {
        txtAttribute = new TextField("Name");
        VBox vb = new VBox(20);
        mapAttributes = new HashMap<String, String>();
        txtValue = new TextField("Value");
        txtKey = new TextField("Attribute");


        HBox hb1 = new HBox(20);

        btnAdd = new Button("Add");
        btnApply = new Button("Apply attribute");

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                mapAttributes.put(txtKey.getText(), txtValue.getText());
                System.out.println(txtKey);
                System.out.println(txtValue);
                txtKey.clear();
                txtValue.clear();
                System.out.println(txtKey);
                System.out.println(txtValue);
            }
        });


        btnApply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Entity entity = MainView.getInstance().getTable().getSelectionModel().getSelectedItem();
                String str = NestedEntityView.getInstance().getTxtAttribute().getText();
                String baseName = MainView.getInstance().getSelectedDirectory().getAbsolutePath();
                ImportAndExportStorage storage = StorageManager.getStorage(baseName);
                storage.getEntities().remove(entity);
                entity.getAttributes().put(str, NestedEntityView.getInstance().getMapAttributes());
                storage.getEntities().add(entity);
                storage.save(storage.getEntities(), storage.getBaseName());
                ObservableList<Entity> list = FXCollections.observableArrayList(storage.getEntities());
                MainView.getInstance().getTable().getItems().clear();
                MainView.getInstance().getTable().setItems(list);
                MainView.getInstance().getTable().refresh();

            }
        });

        hb1.getChildren().addAll(txtKey, txtValue, btnAdd);
        vb.getChildren().addAll(txtAttribute, hb1, btnApply);
        vb.setAlignment(Pos.CENTER);

        Scene sc = new Scene(vb);
        setWidth(500);
        setHeight(300);
        setScene(sc);
    }


    public static void setInstance(NestedEntityView instance) {
        NestedEntityView.instance = instance;
    }

    public TextField getTxtValue() {
        return txtValue;
    }

    public void setTxtValue(TextField txtValue) {
        this.txtValue = txtValue;
    }

    public TextField getTxtAttribute() {
        return txtAttribute;
    }

    public void setTxtAttribute(TextField txtAttribute) {
        this.txtAttribute = txtAttribute;
    }

    public TextField getTxtKey() {
        return txtKey;
    }

    public void setTxtKey(TextField txtKey) {
        this.txtKey = txtKey;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnApply() {
        return btnApply;
    }

    public void setBtnApply(Button btnApply) {
        this.btnApply = btnApply;
    }

    public Map<String, String> getMapAttributes() {
        return mapAttributes;
    }

    public void setMapAttributes(Map<String, String> mapAttributes) {
        this.mapAttributes = mapAttributes;
    }

    public static NestedEntityView getInstance() {
        if (instance == null)
            instance = new NestedEntityView();
        return instance;
    }
}
