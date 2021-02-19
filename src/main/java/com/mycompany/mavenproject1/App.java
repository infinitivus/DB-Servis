package com.mycompany.mavenproject1;



/**
 * JavaFX App
 */
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    private TableView<Person> table;

    private void insert(int rowIndex, String LASTNAME, String FIRSTNAME, int editCol) {
        table.getItems().add(rowIndex, new Person(LASTNAME, FIRSTNAME));
        table.getSelectionModel().clearAndSelect(rowIndex);
        table.edit(rowIndex, table.getColumns().get(editCol));
    }

    @Override
    public void start(Stage primaryStage) {
        table = new TableView<>();
        table.setEditable(true);

        table.getColumns().add(column("Фамилия", Person::LASTNAMEProperty));
        table.getColumns().add(column("Имя_Отчество", Person::FIRSTNAMEProperty));

        table.getItems().addAll(
        
            new Person("Двойной левый клик для правки", "Enter для сохранения"));


        table.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            MenuItem addBefore = new MenuItem("Insert leg before");
            MenuItem addAfter = new MenuItem("Insert leg after");
            ContextMenu menu = new ContextMenu(addBefore, addAfter);
            addBefore.setOnAction(e -> {
                String LASTNAME = row.getIndex() == 0 ? "" : table.getItems().get(row.getIndex()-1).getFIRSTNAME();
                String FIRSTNAME = table.getItems().get(row.getIndex()).getLASTNAME() ;
                insert(row.getIndex(), LASTNAME, FIRSTNAME, 0);
            });
            addAfter.setOnAction(e -> {
                String LASTNAME = table.getItems().get(row.getIndex()).getFIRSTNAME();
                String FIRSTNAME = row.getIndex() == table.getItems().size()-1 
                        ? "" 
                        : table.getItems().get(row.getIndex()+1).getLASTNAME() ;
                insert(row.getIndex()+1, LASTNAME, FIRSTNAME, 1);
            });
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu)null)
                    .otherwise(menu));
            return row ;
        });

        Label label = new Label("Build Itinerary");
        Button add = new Button("Add leg");
        HBox controls = new HBox(5, label, add);
        controls.setPadding(new Insets(10));
        add.setOnAction(e -> insert(table.getItems().size(), "Фамилия", "Имя_Отчество", 0));

        
            BorderPane root = new BorderPane(table);
        root.setPadding(new Insets(15, 15, 15, 15));
        BorderPane.setMargin(table, new Insets(2, 10, 10, 10));
       // BorderPane.setMargin(imageView, new Insets(2, 10, 10, 10));
        
        //root.setCenter(tableView);
    
     //   root.setBottom(bot);
        
        
        
        
        
        
        
        Group but =new Group();// Создание панели Group для блока кнопок
        
   		Button baseCustomer = new Button("База заказчиков");
   		baseCustomer.setTooltip(new Tooltip("Информация о клиентах сервиса"));//всплывающая подсказка
   		baseCustomer.setPrefSize(150,30);// размер кнопки
   		baseCustomer.setTranslateY(20);//сдвиг вниз от панели
   		baseCustomer.setTranslateX(20);// сдвиг вправо от панели
   		but.getChildren().add(baseCustomer);// Вставляем в Group but
   		
   		baseCustomer.setOnMouseClicked((new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent event) { 
               root.setTop(controls);
            } 
         })); 
   		
   		
   		
   		
   		
      
   		Button basePart = new Button("База запчастей");
   		basePart.setTooltip(new Tooltip("Информация о наличии и стоимости запчастей"));//всплывающая подсказка
   		basePart.setPrefSize(150,30);// размер кнопки
   		basePart.setTranslateY(60);//сдвиг вниз от панели
   		basePart.setTranslateX(20);//сдвиг вправо от панел
   		but.getChildren().add(basePart);// Вставляем в Group but
   		
   		basePart.setOnMouseClicked((new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent event) { 
            //   root.setCenter(table);
            } 
         })); 
   		
   		
   		
   		
   
   		Button baseWork = new Button("База работ");
   		baseWork.setTooltip(new Tooltip("Информация о стоимости работ"));//всплывающая подсказка
   		baseWork.setPrefSize(150,30);// размер кнопки
   		baseWork.setTranslateY(100);//сдвиг вниз от панели
   		baseWork.setTranslateX(20);//сдвиг вправо от панели
   		but.getChildren().add(baseWork);// Вставляем в Group but
 
   		Button calendar=new Button("Рабочий календарь");
   		calendar.setTooltip(new Tooltip("Календарь рабочего времени"));//всплывающая подсказка
   		calendar.setPrefSize(150,30);// размер кнопки
   		calendar.setTranslateY(140);//сдвиг вниз от панели
   		calendar.setTranslateX(20);//сдвиг вправо от панели
   		but.getChildren().add(calendar);// Вставляем в Group but
   
   		Button setting=new Button("Настройки");
   		setting.setTooltip(new Tooltip("Настройки программы"));//всплывающая подсказка
   		setting.setPrefSize(150,30);// размер кнопки
   		setting.setTranslateY(180);//сдвиг вниз от панели
   		setting.setTranslateX(20);//сдвиг вправо от панели
   		but.getChildren().add(setting);// Вставляем в Group but
   
   
        
        
        
        root.setLeft(but);
        Scene scene = new Scene(root, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        
       
    }


    private static <S> TableColumn<S,String> column(String title, Function<S, StringProperty> property) {
        TableColumn<S,String> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        col.setCellFactory(TextFieldTableCell.forTableColumn());
        col.setPrefWidth(200);
        return col ;
    }

    public static class Person {
        private final StringProperty LASTNAME = new SimpleStringProperty();
        private final StringProperty FIRSTNAME = new SimpleStringProperty();
        private final StringProperty PHONE = new SimpleStringProperty();
        private final StringProperty BIRTHDAY = new SimpleStringProperty();
        private final StringProperty MODELTS = new SimpleStringProperty();
        private final StringProperty NUMBERTS = new SimpleStringProperty();
        public Person(String LASTNAME, String FIRSTNAME) {
            setLASTNAME(LASTNAME);
            setFIRSTNAME(FIRSTNAME);
        }

        public final StringProperty LASTNAMEProperty() {
            return this.LASTNAME;
        }


        public final String getLASTNAME() {
            return this.LASTNAMEProperty().get();
        }


        public final void setLASTNAME(final String LASTNAME) {
            this.LASTNAMEProperty().set(LASTNAME);
        }


        public final StringProperty FIRSTNAMEProperty() {
            return this.FIRSTNAME;
        }


        public final String getFIRSTNAME() {
            return this.FIRSTNAMEProperty().get();
        }


        public final void setFIRSTNAME(final String FIRSTNAME) {
            this.FIRSTNAMEProperty().set(FIRSTNAME);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}