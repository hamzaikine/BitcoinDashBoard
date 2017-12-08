package com.hamzaikine.bitcoindashboard;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.blockchain.api.APIException;
import info.blockchain.api.HttpClient;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.chart.CategoryAxis;
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
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.*;

/**
 *
 * @author hamzaikine
 */
public class BitcoinGui extends Application {

    LineChart<String, Number> chart;
    XYChart.Series<String, Number> series;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("BitcoinDashboard");

        /**
         * Line Chart
         */
        final CategoryAxis xAxis = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("USD");
        chart = new LineChart<String, Number>(xAxis, yAxis);

        series = new XYChart.Series<String, Number>();
        series.setName("Average USD market price across major bitcoin exchanges.");

        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("format", "json");
            String response;
            response = HttpClient.getInstance().get("charts/" + "market-price", params);
            JsonObject chartJson = new JsonParser().parse(response).getAsJsonObject();

            // getting address
            JsonArray values = (JsonArray) chartJson.get("values");

            JsonElement element = values.get(0);

            //JsonObject object = element.getAsJsonObject();
            for (JsonElement point : values) {
                JsonObject object = point.getAsJsonObject();
                long unix = object.get("x").getAsLong();
                Date date = new Date(unix * 1000L);
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                series.getData().add(new XYChart.Data(dateFormat.format(date), object.get("y").getAsDouble()));
            }

        } catch (APIException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        }

        chart.getData().add(series);
        chart.setVisible(false);

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
        GridPane.setConstraints(chart, 1, 4);
        /**
         * *************************************************************************
         */
        //load image
        Image image = new Image("/pic/Bitcoin.jpg");

        BackgroundImage bgImg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        grid.setBackground(new Background(bgImg));
        Label title = new Label();

        /**
         * *************************************************************************
         */
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

        /**
         * *************************************************************************
         */
        //GridPane.setMargin(text, new Insets(20, 1, 50, 1));
        //creating buttons and textfields
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
                   
                    long size = b.getSize()/1000;
                    
                    chart.setVisible(false);
                    textArea.setVisible(true);
                    textArea.setText("Block Height:\t" + b.getHeight() + "\n" + "Block size:\t" + size +" KB"+ "\nPrevious Block hash:\t"
                            + b.getPreviousBlockHash() + "\n" + "No. Transaction in block:\t" + b.getNTx());

                } else {
                    textArea.setVisible(true);
                    chart.setVisible(false);
                    textArea.setText("Please enter a block hash.\n");
                }
            }
        });

        //Setting an action for the address search button
        address_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (address.getText() != null && !address.getText().trim().isEmpty()) {
                    Address ad = new Address(null, address.getText(), 0L, 0L, 0L, 0, null);
                    BlockExplorer be = new BlockExplorer();

                    try {
                        ad = be.getAddress(address.getText(), null, null, null);
                    } catch (APIException ex) {
                        textArea.setText("Invalid Address.\n");
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    chart.setVisible(false);
                    textArea.setVisible(true);
                    long tx = ad.getTxCount();
                    long totRecv = ad.getTotalReceived()/100000000;
                    long totSent = ad.getTotalSent()/100000000;
                    long FinalBal = ad.getFinalBalance()/100000000;
                    textArea.setText("No. Transactions:\t" + tx + "\nTotal Received:\t" + totRecv
                            + " BTC\nTotal Sent:\t" + totSent+ " BTC\nFinalBalance:\t" + FinalBal +" BTC");

                } else {
                    textArea.setVisible(true);
                    chart.setVisible(false);
                    textArea.setText("Please enter an address.\n");
                }

            }

        });

        //the scene
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
        /**
         * *************************************************************************
         */
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                textArea.setVisible(false);
                chart.setVisible(true);
            }
        });

        /**
         * *************************************************************************
         */
        //Display exchange rate for different Currencies
        MenuItem clist = new MenuItem("Currency List");
        clist.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                textArea.setVisible(true);
                chart.setVisible(false);
                ExchangeRate er = new ExchangeRate();
                Map<String, Currency> mp = new HashMap<String, Currency>();
                try {
                    mp = er.getTicker();       //get list from the server
                } catch (APIException ex) {
                    Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                textArea.appendText("\n\n\n");
                Iterator<Map.Entry<String, Currency>> mapIterator = mp.entrySet().iterator();
                while (mapIterator.hasNext()) {
                    Map.Entry<String, Currency> entry = mapIterator.next();
                    textArea.appendText(entry.getKey() + ": " + entry.getValue().getSell() + "\n");
                }

            }

        });

        menuView.getItems().addAll(add, clist);

        menuBar.getMenus().addAll(menuView);

        //Add everything to grid
        grid.getChildren().addAll(title, textArea, address, block_hash, address_button, block_button, menuBar, chart);

        primaryStage.show();

    }

    public static void main(String[] argv) {

        launch(argv);

    }

}
