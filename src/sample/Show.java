package sample;

import Multi_threading.Client;
import Multi_threading.Server;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Show extends Application {
    Stage stage = new Stage();

    public void start(Stage primaryStage) throws Exception{
        HBox hBox = new HBox();
        ObservableList<String> strltr = FXCollections.observableArrayList();
        ObservableList<String> strrtl = FXCollections.observableArrayList();
        ArrayList<String> ltr = new ArrayList<>();
        ArrayList<String> rtl = new ArrayList<>();
        while(!Server.info.isEmpty()){
            if(Server.info.get(0).contains("从左到右")) {
                if(ltr.isEmpty()){
                    for(int i = Integer.parseInt(Server.info.get(0).substring(31, 33)) - Server.passing_time; i > 0; i -= Server.passing_time) ltr.add(" ");
                    ltr.add(Server.info.remove(0));
                }
                else {
                    for(int i = Integer.parseInt(Server.info.get(0).substring(31, 33)) - Integer.parseInt(ltr.get(ltr.size() - 1).substring(31, 33)) - Server.passing_time; i > 0; i -= Server.passing_time) ltr.add(" ");
                    ltr.add(Server.info.remove(0));
                }
                //else ltr.add(Server.info.remove(0));
            }
            else {
                if(rtl.isEmpty()){
                    for(int i = Integer.parseInt(Server.info.get(0).substring(31, 33)) - Server.passing_time; i > 0; i -= Server.passing_time) rtl.add(" ");
                    rtl.add(Server.info.remove(0));
                }
                else{
                    for(int i = Integer.parseInt(Server.info.get(0).substring(31, 33)) - Integer.parseInt(rtl.get(rtl.size() - 1).substring(31, 33)) - Server.passing_time; i > 0; i -= Server.passing_time) rtl.add(" ");
                    rtl.add(Server.info.remove(0));
                }
                //else rtl.add(Server.info.remove(0));
            }
        }
        while(!ltr.isEmpty()) strltr.add(ltr.remove(0));
        while(!rtl.isEmpty()) strrtl.add(rtl.remove(0));


        ListView<String> listL = new ListView<String>();
        listL.setPrefWidth(200);
        listL.setPlaceholder(new Label("没有数据"));
        listL.setItems(strltr);
        listL.setCellFactory(TextFieldListCell.forListView());
        //设置单元格尺寸
        listL.setFixedCellSize(50);


        ListView<String> listR = new ListView<String>();
        listR.setPrefWidth(200);
        listR.setPlaceholder(new Label("没有数据"));
        listR.setItems(strrtl);
        listR.setCellFactory(TextFieldListCell.forListView());
        //设置单元格尺寸
        listR.setFixedCellSize(50);


        hBox.getChildren().addAll(listL, listR);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("show");
        primaryStage.setHeight(500);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    public void newPage(){
        try {
            start(stage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
