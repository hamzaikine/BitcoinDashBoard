package com.hamzaikine.bitcoindashboard;

import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.entity.FilterType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author hamzaikine
 */
public class BitcoinGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BitcoinDashboard");

        /**
         * Line Chart
         */
        NumberAxis xAxis = new NumberAxis();

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("USD");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Market Price (USD)");
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Average USD market price across major bitcoin exchanges.");

        dataSeries1.getData().add(new XYChart.Data(1, 567));
        dataSeries1.getData().add(new XYChart.Data(5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);
        lineChart.setVisible(false);

        /**
         * *********************************************************
         */
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 10, 0));
        grid.setVgap(8);
        grid.setHgap(8);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        //grid.getColumnConstraints().add(new ColumnConstraints(210)); // column 0 is 210 wide
        GridPane.setConstraints(lineChart, 1, 4);

        //load image
        Image image = new Image("/pic/Bitcoin.jpg");

        BackgroundImage bgImg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        grid.setBackground(new Background(bgImg));
        Label title = new Label();

        // display the current btc price
        try {
            Statistics s = new Statistics();
            StatisticsResponse sr;
            sr = s.getStats();
            //create text

            title.setText("The Current BTC (USD) price: " + sr.getMarketPriceUSD());
            //Setting the color
            title.setTextFill(Color.BLACK);
            title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
            GridPane.setConstraints(title, 1, 1);
            GridPane.setColumnSpan(title, 2);
        } catch (APIException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        }

        //GridPane.setMargin(text, new Insets(20, 1, 50, 1));
        //address Input
        TextField address = new TextField();
        address.setPromptText("Bitcoin Address");
        GridPane.setConstraints(address, 1, 2);

        //address search button
        Button address_button = new Button("Find");
        GridPane.setConstraints(address_button, 2, 2);

        //block hash Input
        TextField block_hash = new TextField();
        block_hash.setPromptText("Block hash");
        GridPane.setConstraints(block_hash, 1, 3);

        //block search button
        Button block_button = new Button("Find");
        GridPane.setConstraints(block_button, 2, 3);

        //adding a textArea
        TextArea textArea = new TextArea();
        GridPane.setConstraints(textArea, 1, 4);
        GridPane.setColumnSpan(textArea, 1);
        // GridPane.setMargin(textArea, new Insets(50, 1, 5, 1));
        textArea.setVisible(false);

        //Setting an action for the block search button
        block_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                // b = be.getBlock("0000000000000000005858d63912f89e244f83d138b4e0b00b54430b2877a57c");
                if (block_hash.getText() != null && !block_hash.getText().trim().isEmpty()) {
                    Block b = null;
                    BlockExplorer be = new BlockExplorer();
                    try {
                        b = be.getBlock(block_hash.getText());
                    } catch (APIException ex) {
                        textArea.setText("invalid block hash");
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    textArea.setVisible(true);
                    lineChart.setVisible(false);
                    textArea.setText("Block Height: " + b.getHeight() + "\n" + "Block size: " + b.getSize() + "\nPrevious Block hash:"
                            + b.getPreviousBlockHash() + "\n" + "No. Transaction in block:" + b.getNTx());

                } else {
                    textArea.setVisible(true);
                    lineChart.setVisible(false);
                    textArea.setText("Please enter a block hash.\n");
                }
            }
        });
        
        //Setting an action for the address search button
        address_button.setOnAction(new EventHandler<ActionEvent>() {
        
            
            @Override
            public void handle(ActionEvent e) {
                if(address.getText() != null && !address.getText().trim().isEmpty()){
                    Address ad = new Address(null,address.getText(),0L,0L,0L,0,null);
                    BlockExplorer be = new BlockExplorer();
                    
                    try {
                        ad = be.getAddress(address.getText(), null, null, null);
                    } catch (APIException ex) {
                        textArea.setText("Invalid Address.\n");
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    lineChart.setVisible(false);
                    textArea.setVisible(true);
                    textArea.setText("\nNo. Transactions:" + ad.getTxCount() +"\nTotal Received:" + ad.getTotalReceived()
                            + "\nTotal Sent:"+ ad.getTotalSent() + "\nFinalBalance" + ad.getFinalBalance());
                   
                    
                }
                
                
            }
            
         });

        //
        Scene scene = new Scene(grid);
        primaryStage.setWidth(610);
        primaryStage.setHeight(400);
        primaryStage.setScene(scene);

        MenuBar menuBar = new MenuBar();
        GridPane.setConstraints(menuBar, 0, 0);
        GridPane.setColumnSpan(menuBar, 3);

        // --- Menu View
        Menu menuView = new Menu("View");
        MenuItem add = new MenuItem("Chart");
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                textArea.setVisible(false);
                lineChart.setVisible(true);
            }
        });
        
        //Display exchange rate for different Currencies
        MenuItem list = new MenuItem("Currency List");
        list.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent t) {
                 lineChart.setVisible(false);
                 ExchangeRate er = new ExchangeRate();
                 Map<String,Currency> mp = new HashMap<String,Currency>();
                 try {
                     mp = er.getTicker();       //get list from the server
                 } catch (APIException ex) {
                     Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                     Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 Iterator<Map.Entry<String, Currency>> mapIterator = mp.entrySet().iterator();
                 while (mapIterator.hasNext()) {
                     Map.Entry<String, Currency> entry = mapIterator.next();
                     textArea.setText(entry.getKey() + ": " + entry.getValue().getSell());
                 }
                 
             }
        
        
        
                     });
        
        menuView.getItems().addAll(add);

        menuBar.getMenus().addAll(menuView);

        //Add everything to grid
        grid.getChildren().addAll(title, textArea, address, block_hash, address_button, block_button, menuBar, lineChart);

        primaryStage.show();

    }

    public static void main(String[] argv) {

        launch(argv);

//        Currency cr;
//        //ExchangeRates er = new ExchangeRates();
//        Map<String,Currency> mp = new HashMap<String,Currency>();
//        try {
//         //    mp = er.getTicker();
//
//         //   BigDecimal e = er.toBTC("USD", BigDecimal.valueOf(1000));
//           //  System.out.println("$" + BigDecimal.valueOf(1000) + " in BTC:" + e );
//
//             Statistics s = new Statistics();
//             StatisticsResponse sr;
//             BlockExplorer be = new BlockExplorer();
//             Block b;
//             Address ad;
//             //b = be.getBlock("0000000000000000005858d63912f89e244f83d138b4e0b00b54430b2877a57c");
//             b = be.getLatestBlock();
//             ad =be.getAddress("3EhLZarJUNSfV6TWMZY1Nh5mi3FMsdHa5U",null,null,null);
//
//             sr = s.getStats();
//
//             System.out.println("Latestblock:" + b.getHeight());
//
//             System.out.println("Address No. Transaction:" + ad.getTxCount());
//             System.out.println("1BTC = " + sr.getMarketPriceUSD() + "$USD.");
//        } catch (APIException ex) {
//            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
////        Iterator<Map.Entry<String, Currency>> mapIterator = mp.entrySet().iterator();
////        while (mapIterator.hasNext()) {
////            Map.Entry<String, Currency> entry = mapIterator.next();
////            System.out.println(entry.getKey() + ": " + entry.getValue().getSell());
////
////        }
//
//
//
//
    }

}
