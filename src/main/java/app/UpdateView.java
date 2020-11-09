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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class UpdateView extends Stage {

    private static UpdateView instance = null;
    private Button btnUpdate;
    private TextField txtId;
    private TextField txtValue;
    private TextField txtName;
    private TextField txtAttribute;
    private Label lblId;
    private Label lblValue;
    private Label lblName;
    private Label lblAttribute;


    private UpdateView() {
        txtId = new TextField("Id:");
        txtName = new TextField("Name");
        txtAttribute = new TextField("Attribute");
        txtValue = new TextField("Attribute value");
        btnUpdate = new Button("Change");
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id=Integer.parseInt(UpdateView.getInstance().getTxtId().getText());
                String name=UpdateView.getInstance().getTxtName().getText();
                String key=UpdateView.getInstance().getTxtAttribute().getText();
                String value=UpdateView.getInstance().getTxtValue().getText();
               // ImportExportStorage  base= ExportImportManager.getStorage();
              //  CRUDOperations crudOperations=new CRUDOperations(base);
             //   crudOperations.updateEntity(id, name, key, value);
             //   List<Entity> list=base.getEntities();
            //    ObservableList<Entity> obsList= FXCollections.observableArrayList(list);
                MainView.getInstance().getTable().getItems().clear();
              //  MainView.getInstance().getTable().setItems(obsList);
            }
        });

        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);

        gp.add(txtId, 0, 0);
        gp.add(txtName, 0, 1);
        gp.add(txtAttribute, 1, 0);
        gp.add(txtValue, 1, 1);


        gp.setAlignment(Pos.CENTER);

        VBox vb = new VBox(20);
        vb.getChildren().addAll(gp, btnUpdate);
        vb.setAlignment(Pos.CENTER);

        Scene sc = new Scene(vb);
        setScene(sc);
        setWidth(450);
        setHeight(350);
        show();
    }

    public static void setInstance(UpdateView instance) {
        UpdateView.instance = instance;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public TextField getTxtValue() {
        return txtValue;
    }

    public void setTxtValue(TextField txtValue) {
        this.txtValue = txtValue;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtAttribute() {
        return txtAttribute;
    }

    public void setTxtAttribute(TextField txtAttribute) {
        this.txtAttribute = txtAttribute;
    }

    public Label getLblId() {
        return lblId;
    }

    public void setLblId(Label lblId) {
        this.lblId = lblId;
    }

    public Label getLblValue() {
        return lblValue;
    }

    public void setLblValue(Label lblValue) {
        this.lblValue = lblValue;
    }

    public Label getLblName() {
        return lblName;
    }

    public void setLblName(Label lblName) {
        this.lblName = lblName;
    }

    public Label getLblAttribute() {
        return lblAttribute;
    }

    public void setLblAttribute(Label lblAttribute) {
        this.lblAttribute = lblAttribute;
    }

    public static UpdateView getInstance() {
        if (instance == null)
            instance = new UpdateView();
        return instance;
    }

}
