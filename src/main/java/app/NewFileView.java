package app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewFileView extends Stage {

    private static NewFileView instance = null;
    private File chosenDir;
    private Button btnChoose;
    private TextField txtName;

    private NewFileView() {
        txtName = new TextField("Enter new name");
        btnChoose = new Button("Choose folder");

          btnChoose.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  DirectoryChooser dc = new DirectoryChooser();
                  File selectedDirectory = dc.showDialog(NewFileView.getInstance());
                  MainView.getInstance().setSelectedDirectory(selectedDirectory);
                  String sDir = MainView.getInstance().getSelectedDirectory().getAbsolutePath() + "\\" +
                          NewFileView.getInstance().getTxtName().getText();
                  System.out.println(sDir);
                  File newDir = new File(sDir);
                  if (!newDir.exists()) {
                      newDir.mkdir();
                      MainView.getInstance().setSelectedDirectory(newDir);
                  }
                  NewFileView.getInstance().close();
              }
          });

        HBox hb = new HBox(30);
        VBox vb = new VBox(40);
        vb.getChildren().addAll(hb, btnChoose);
        vb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(txtName);
        hb.setAlignment(Pos.CENTER);

        Scene sc = new Scene(vb);
        setScene(sc);
        setWidth(450);
        setHeight(220);
    }

    public File getChosenDirectory() {
        return chosenDir;
    }

    public void setChosenDirectory(File chosenDirectory) {
        this.chosenDir = chosenDirectory;
    }


    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public static NewFileView getInstance() {
        if (instance == null)
            instance = new NewFileView();
        return instance;
    }
}
