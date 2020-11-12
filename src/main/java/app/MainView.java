package app;

import JsonStorage.JSONImpl;
import YAMLStorage.YAMLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.Specifikacija;
import storage.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainView extends Stage {

    private static MainView instance = null;
    private ToolBar toolbar1;
    private ToolBar toolbar2;
    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;
    private Button btnCreate;
    private Button btnCreateEntity;
    private Button btnUpdate;
    private Button btnDelete;
    //    private Button btnSort;
//    private Button btnFilter;
    private File selectedDir;
    private DirectoryChooser dir;
    private VBox vbx;
    private TableView<Entity> table;

    private MainView() {
        vbx = new VBox();
        Scene sc = new Scene(vbx);
        toolbar1 = new ToolBar();
        toolbar2 = new ToolBar();
        table = new TableView();
        createTable(table);
        toolbarView1();
        toolbarView2();
        vbx.getChildren().addAll(toolbar1, toolbar2, table);
        btnFunc();
        setScene(sc);
        setHeight(500);
        setWidth(550);
        show();
    }

    public void createTable(TableView<Entity> table) {
        TableColumn<Entity, Integer> columnId = new TableColumn<Entity, Integer>("id");
        columnId.setCellValueFactory(new PropertyValueFactory<Entity, Integer>("id"));
        TableColumn<Entity, String> columnIName = new TableColumn<Entity, String>("name");
        columnIName.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        TableColumn<Entity, HashMap<String, Object>> columnIAttributes = new TableColumn<Entity, HashMap<String, Object>>("attributes");
        columnIAttributes.setCellValueFactory(new PropertyValueFactory<Entity, HashMap<String, Object>>("attributes"));
        table.getColumns().addAll(columnId, columnIName, columnIAttributes);
    }

    private void toolbarView1() {
        btnNew = new Button("New File");
        btnOpen = new Button("Open");
        btnSave = new Button("Save");


        dir = new DirectoryChooser();
        btnNew.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                NewFileView.getInstance().show();
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//                FileChooser fileChooser = new FileChooser();
//                File file = fileChooser.showSaveDialog(MainView.getInstance());
//                MainView.getInstance().setSelectedDirectory(file);
//
//                ImportAndExportStorage base = StorageManager.getStorage(file.getAbsolutePath());
//                base.save(file);
            }
        });

        btnOpen.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                File selectedDirectory = dc.showDialog(MainView.getInstance());
                MainView.getInstance().setSelectedDirectory(selectedDirectory);


                if (selectedDirectory.listFiles()[0].getAbsolutePath().endsWith(".json")) {
                    try {
                        Class.forName("JsonStorage.JSONImpl");
                    } catch (ClassNotFoundException e) {
                        System.out.println("nije nasao klasu");
                        e.printStackTrace();
                    }
                }
                 else if(selectedDirectory.listFiles()[0].getAbsolutePath().endsWith(".yml")) {
                    try {
                        Class.forName("YAMLStorage.YAMLImpl");
                    } catch (ClassNotFoundException e) {
                        System.out.println("nije nasao klasu");
                        e.printStackTrace();
                    }
                }
                 else {
                    try {
                        Class.forName("customStorage.CustomImpl");
                    } catch (ClassNotFoundException e) {
                        System.out.println("nije nasao klasu");
                        e.printStackTrace();
                    }
                }

                ImportAndExportStorage base = StorageManager.getStorage(selectedDirectory.getAbsolutePath());
                base.open(selectedDirectory);
                ObservableList<Entity> list = FXCollections.observableArrayList(base.getEntities());
                MainView.getInstance().getTable().setItems(list);
                MainView.getInstance().getTable().refresh();
            }
        });

        toolbar1.getItems().addAll(btnNew, btnOpen, btnSave);
    }


    private void toolbarView2() {
        btnCreate = new Button("New Entity");
        btnCreateEntity = new Button("Add nested Entity");
        btnUpdate = new Button("Update Entity");
        btnDelete = new Button("Delete Entity");
        toolbar2.getItems().addAll(btnCreate, btnCreateEntity, btnUpdate, btnDelete);
    }

    private void btnFunc() {
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                NewEntityView.getInstance().show();
            }
        });

        btnCreateEntity.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                NestedEntityView.getInstance().show();
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                UpdateView.getInstance().show();
                Entity e = MainView.getInstance().getTable().getSelectionModel().getSelectedItem();
                UpdateView.getInstance().getTxtId().setText(String.valueOf(e.getId()));
                UpdateView.getInstance().getTxtName().setText(String.valueOf(e.getName()));
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Entity entity = MainView.getInstance().getTable().getSelectionModel().getSelectedItem();
                ImportAndExportStorage base = StorageManager.getBase();
                Specifikacija spec = new Specifikacija(base);
                spec.deleteEntity(entity.getId(), entity.getName());
                List<Entity> list = base.getEntities();
                ObservableList<Entity> obsList = FXCollections.observableArrayList(list);
                MainView.getInstance().getTable().getItems().clear();
                MainView.getInstance().getTable().setItems(obsList);
            }
        });
    }

    public File getSelectedDirectory() {
        return selectedDir;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.selectedDir = selectedDirectory;
    }

    public TableView<Entity> getTable() {
        return table;
    }

    public void setTable(TableView<Entity> table) {
        this.table = table;
    }

    public static MainView getInstance() {
        if (instance == null)
            instance = new MainView();

        return instance;
    }
}
