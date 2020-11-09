package app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilterView extends Stage {
    private static FilterView instance = null;
    private Button btnFilter;
    private TextField txtId;
    private TextField txtName;
    private TextField txtAttribute;
    private TextField txtValue;

    private FilterView() {
        txtId = new TextField("ID");
        txtName = new TextField("Name");
        txtAttribute = new TextField("Attribute");
        txtValue = new TextField("Attribute value");
        btnFilter = new Button("Filter");

        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);

        gp.add(txtId, 0, 0);
        gp.add(txtName, 0, 1);
        gp.add(txtAttribute, 1, 0);
        gp.add(txtValue, 1, 1);


        gp.setAlignment(Pos.CENTER);

        VBox vb = new VBox(20);
        vb.getChildren().addAll(gp, btnFilter);
        vb.setAlignment(Pos.CENTER);

        Scene sc = new Scene(vb);
        setScene(sc);
        setWidth(400);
        setHeight(300);
        show();
    }

    public static void setInstance(FilterView instance) {
        FilterView.instance = instance;
    }

    public Button getBtnFilter() {
        return btnFilter;
    }

    public void setBtnFilter(Button btnFilter) {
        this.btnFilter = btnFilter;
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

    public TextField getTxtAttribute() {
        return txtAttribute;
    }

    public void setTxtAttribute(TextField txtAttribute) {
        this.txtAttribute = txtAttribute;
    }

    public TextField getTxtValue() {
        return txtValue;
    }

    public void setTxtValue(TextField txtValue) {
        this.txtValue = txtValue;
    }

    public static FilterView getInstance() {
        if (instance == null)
            instance = new FilterView();
        return instance;
    }
}
