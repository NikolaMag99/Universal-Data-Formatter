package app;

import contoller.ContollerAddEntity;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewEntityView  extends Stage {
    private static NewEntityView instance=null;
    private Label lblEnterId;
    private TextArea txtArea;
    private TextField txtId;
    private TextField txtName;
    private Button btnAddEntity;

    private  NewEntityView() {

        lblEnterId =new Label("Enter ID");
        lblEnterId.setPrefSize(100, 20);
        txtId=new TextField();
        HBox hbx=new HBox(10);
        hbx.getChildren().addAll(lblEnterId);

        txtName=new TextField("Name");
        txtArea=new TextArea("Additional parameters");

        VBox vbx=new VBox(10);
        vbx.setPadding(new Insets(10));
        btnAddEntity=new Button("Add Entity");
        btnAddEntity.setOnAction(new ContollerAddEntity());
        vbx.getChildren().addAll(hbx,txtId,txtName,txtArea,btnAddEntity);
        Scene sc=new Scene(vbx);
        setScene(sc);
        setWidth(350);
        setHeight(450);
        show();
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

    public static  NewEntityView getInstance(){
        if(instance==null)
            instance=new NewEntityView();
        return instance;
    }

}
