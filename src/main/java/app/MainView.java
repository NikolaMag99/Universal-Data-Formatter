package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
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
    //    private Button btnCreateEntity;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnSort;
    private Button btnFilter;
    private File selectedDir;
    private DirectoryChooser dir;
    private VBox vbx;
    private TableView<MainView> table;

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

    public void createTable(TableView<MainView> table) {
        TableColumn<MainView, Integer> columnId = new TableColumn<MainView, Integer>("id");
        columnId.setCellValueFactory(new PropertyValueFactory<MainView, Integer>("id"));
        TableColumn<MainView, String> columnIName = new TableColumn<MainView, String>("name");
        columnIName.setCellValueFactory(new PropertyValueFactory<MainView, String>("name"));
        TableColumn<MainView, HashMap<String, Object>> columnIAttributes = new TableColumn<MainView, HashMap<String, Object>>("attributes");
        columnIAttributes.setCellValueFactory(new PropertyValueFactory<MainView, HashMap<String, Object>>("attributes"));
        table.getColumns().addAll(columnId, columnIName, columnIAttributes);
    }

    private void toolbarView1() {
        btnNew = new Button("New File");
        btnOpen = new Button("Open");
        btnSave = new Button("Save");
        btnSort = new Button("Sort");
        btnFilter = new Button("Filter");

        dir = new DirectoryChooser();
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewFileView.getInstance().show();
            }
        });

        btnFilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FilterView.getInstance().show();
            }
        });

        btnSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SortView.getInstance().show();
            }
        });
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                File selectedDirectory = dc.showDialog(MainView.getInstance());
                MainView.getInstance().setSelectedDirectory(selectedDirectory);

                try {
                    Class.forName("JSONStorage.JStorage");
                } catch (ClassNotFoundException e) {
                    System.out.println("nije nasao klasu");
                    e.printStackTrace();
                }
//                ImportAndExportStorage base = StorageManager.getStorage(selectedDirectory.getAbsolutePath());
//                base.open(selectedDirectory);
//                ObservableList<Entity> list = FXCollections.observableArrayList(base.getEntities());
//                MainView.getInstance().getTable().setItems(list);

            }
        });

        toolbar1.getItems().addAll(btnNew, btnOpen, btnSave, btnSort, btnFilter);
    }


    private void toolbarView2() {
        btnCreate = new Button("New Entity");
//        btnCreateEntity = new Button("Dodaj ugnj. ent.");
        btnUpdate = new Button("Update Entity");
        btnDelete = new Button("Delete Entity");
        toolbar2.getItems().addAll(btnCreate, btnUpdate, btnDelete);
    }

    private void btnFunc() {
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                NewEntityView.getInstance().show();
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                UpdateView.getInstance().show();
//                Entity e=ReaderView.getInstance().getTable().getSelectionModel().getSelectedItem();
//                UpdateView.getInstance().getTxtId().setText(String.valueOf(e.getId()));
//                UpdateView.getInstance().getTxtName().setText(String.valueOf(e.getName()));
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Entity e=MainView.getInstance().getTable().getSelectionModel().getSelectedItem();
//                ImportandExportStorage base = StorageManager.getStorage();
//                Specifikacija crud = new Specifikacija(base);
//                crud.deleteEntity(e.getId(), e.getName());
//                List<Entity> list=base.getEntities();
//                ObservableList<Entity> obsList= FXCollections.observableArrayList(list);
//                MainView.getInstance().getTable().getItems().clear();
//                MainView.getInstance().getTable().setItems(obsList);
            }
        });
    }

    public File getSelectedDirectory() {
        return selectedDir;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.selectedDir = selectedDirectory;
    }

    public TableView<MainView> getTable() {
        return table;
    }

    public void setTable(TableView<MainView> table) {
        this.table = table;
    }

    public static MainView getInstance() {
        if (instance == null)
            instance = new MainView();

        return instance;
    }
}
