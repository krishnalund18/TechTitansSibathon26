package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PredictaPayGUI extends Application {

    private LineChart<String, Number> forecastChart;
    private NumberAxis yAxis;
    private Label balanceLabel;
    private ListView<String> alertListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StorageManager.initialize();

        HBox root = new HBox();
        root.setStyle("-fx-background-color: #f1f5f9;");

        VBox sidebar = createSidebar();
        VBox mainContent = createMainContent();
        HBox.setHgrow(mainContent, Priority.ALWAYS);

        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1200, 800);

        // CSS for improved visibility and professional look
        scene.getStylesheets().add("data:text/css," +
                ".sidebar { -fx-background-color: #1e293b; }" +
                ".label { -fx-text-fill: #f8fafc; }" +
                ".primary-button { -fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; }" +
                ".primary-button:hover { -fx-background-color: #2563eb; }" +
                ".chart-series-line { -fx-stroke: #3b82f6; -fx-stroke-width: 3px; }" +
                ".list-cell { -fx-text-fill: #1e293b; -fx-font-weight: bold; }");

        primaryStage.setTitle("PredictaPay | Financial Intelligence");
        primaryStage.setScene(scene);
        primaryStage.show();

        refreshUI();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(12);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(300);
        sidebar.getStyleClass().add("sidebar");

        Label title = new Label("PREDICTAPAY");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        balanceLabel = new Label("Balance: $0.00");
        balanceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #10b981; -fx-font-weight: bold;");

        // --- One-Time Transaction Section ---
        Label tTitle = new Label("Add One-Time Transaction");
        tTitle.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 0 0;");

        TextField tDesc = new TextField(); tDesc.setPromptText("Description");
        TextField tAmount = new TextField(); tAmount.setPromptText("Amount");
        ComboBox<String> tType = new ComboBox<>();
        tType.getItems().addAll("INCOME", "EXPENSE");
        tType.setValue("EXPENSE");
        tType.setMaxWidth(Double.MAX_VALUE);

        Button addTBtn = new Button("Add Transaction");
        addTBtn.getStyleClass().add("primary-button");
        addTBtn.setMaxWidth(Double.MAX_VALUE);
        addTBtn.setOnAction(e -> {
            try {
                double amt = Double.parseDouble(tAmount.getText());
                Transaction t = new Transaction(LocalDate.now(), tType.getValue(), tDesc.getText(), amt);
                StorageManager.addTransaction(t);
                refreshUI();
                tDesc.clear(); tAmount.clear();
            } catch (Exception ex) { showErr("Invalid Transaction Amount"); }
        });

        // --- Recurring Schedule Section ---
        Label sTitle = new Label("Add Recurring Schedule");
        sTitle.setStyle("-fx-font-weight: bold; -fx-padding: 15 0 0 0;");

        TextField sDesc = new TextField(); sDesc.setPromptText("Bill/Salary Name");
        TextField sAmount = new TextField(); sAmount.setPromptText("Amount");

        ComboBox<String> sType = new ComboBox<>();
        sType.getItems().addAll("INCOME", "EXPENSE");
        sType.setValue("EXPENSE");
        sType.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> freqBox = new ComboBox<>();
        freqBox.getItems().addAll("DAILY", "WEEKLY", "MONTHLY");
        freqBox.setValue("MONTHLY");
        freqBox.setMaxWidth(Double.MAX_VALUE);

        Button addSBtn = new Button("Add Schedule");
        addSBtn.getStyleClass().add("primary-button");
        addSBtn.setMaxWidth(Double.MAX_VALUE);
        addSBtn.setOnAction(e -> {
            try {
                double amt = Double.parseDouble(sAmount.getText());
                // Create internal transaction for the schedule object
                Transaction innerT = new Transaction(LocalDate.now(), sType.getValue(), sDesc.getText(), amt);
                Schedule s = new Schedule(innerT, freqBox.getValue(), LocalDate.now());
                StorageManager.addSchedule(s);
                refreshUI();
                sDesc.clear(); sAmount.clear();
            } catch (Exception ex) { showErr("Invalid Schedule Details"); }
        });

        sidebar.getChildren().addAll(
                title, balanceLabel, new Separator(),
                tTitle, tDesc, tAmount, tType, addTBtn,
                new Separator(),
                sTitle, sDesc, sAmount, sType, freqBox, addSBtn
        );

        return sidebar;
    }

    private VBox createMainContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(25));
        content.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

        // 1. UPPER TEXT HEADER
        Label upperHeader = new Label("Financial State (30-Day Forecast)" +
                "");
        upperHeader.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");

        // Chart Configuration
        CategoryAxis xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Timeline");
        yAxis.setLabel("Balance ($)");

        forecastChart = new LineChart<>(xAxis, yAxis);
        forecastChart.setTitle("Projected Cash Flow");
        forecastChart.setAnimated(false);
        forecastChart.setCreateSymbols(true);

        // 2. LIQUIDITY ALERTS SECTION
        Label alertTitle = new Label("Liquidity Alerts");
        alertTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");

        alertListView = new ListView<>();
        alertListView.setPrefHeight(180);
        // Force high-contrast style for visibility
        alertListView.setStyle("-fx-control-inner-background: #f8fafc; -fx-border-color: #e2e8f0;");

        content.getChildren().addAll(upperHeader, forecastChart, alertTitle, alertListView);
        return content;
    }

    private void refreshUI() {
        UserFinancialData data = StorageManager.getData();
        double currentBalance = data.getBalance();
        balanceLabel.setText(String.format("Balance: $%.2f", currentBalance));

        SimulationEngine engine = new SimulationEngine();

        // Use engine to simulate. Note: Engine loop should start from i=1 to prevent jump.
        Map<LocalDate, Double> forecast = engine.simulateFutureBalances(data, data.getSchedules(), 30);
        Map<LocalDate, Double> sortedForecast = new TreeMap<>(forecast);

        forecastChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Predicted Balance");

        LocalDate today = LocalDate.now();

        // LOGIC FIX: Anchor chart to current balance at "Today"
        series.getData().add(new XYChart.Data<>(today.toString(), currentBalance));

        // LOGIC FIX: Only add points AFTER today to avoid double-counting/jumping
        sortedForecast.forEach((date, bal) -> {
            if (date.isAfter(today)) {
                series.getData().add(new XYChart.Data<>(date.toString(), bal));
            }
        });

        forecastChart.getData().add(series);

        // POPULATE ALERTS
        alertListView.getItems().clear();
        List<String> alerts = engine.generateLiquidityAlerts(forecast, 100.0);
        if (alerts.isEmpty()) {
            alertListView.getItems().add("✅ All clear! No liquidity issues predicted.");
        } else {
            alertListView.getItems().addAll(alerts);
        }
    }

    private void showErr(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}