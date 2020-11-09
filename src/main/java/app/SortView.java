package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortView extends Stage {

    private static SortView instance = null;
    private Label lblSelect;
    private ComboBox<String> comboBox;
    private Button btnApply;
    private CheckBox checkBox;

    private SortView() {
        VBox vb = new VBox(15);
        lblSelect = new Label("Sort by");
        comboBox = new ComboBox<String>();
        List<String> list = new ArrayList<String>();
        list.add("name");
        list.add("id");
        Set<String> set = new HashSet<String>();
//        ImportAndExportStorage base = StorageManager.getStorage();
//        for (Entity e : base.getEntities()) {
//            for (String s : e.getAttributes().keySet()) {
//                set.add(s);
//            }
//        }
        list.addAll(set);
        ObservableList<String> comboBoxList = FXCollections.observableArrayList(list);
        checkBox = new CheckBox();
        btnApply = new Button("Apply");

        vb.getChildren().addAll(lblSelect, this.comboBox, btnApply);
        vb.setAlignment(Pos.CENTER);

        Scene sc = new Scene(vb);
        setScene(sc);
        setWidth(200);
        setHeight(150);
    }

    public static void setInstance(SortView instance) {
        SortView.instance = instance;
    }

    public Label getLblSelect() {
        return lblSelect;
    }

    public void setLblSelect(Label lblSelect) {
        this.lblSelect = lblSelect;
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public Button getBtnApply() {
        return btnApply;
    }

    public void setBtnApply(Button btnApply) {
        this.btnApply = btnApply;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public static SortView getInstance() {
        if (instance == null)
            instance = new SortView();
        return instance;
    }

}
