package com.example.mokkitehtava;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class kayttoliittyma extends Application {



    private final ObservableList<Mokki> mokkiLista = FXCollections.observableArrayList();
    private final ObservableList<Asiakas> asiakasLista = FXCollections.observableArrayList();
    private final ObservableList<Varaukset> varauksetLista = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mökkivarausjärjestelmä");


        HBox appleylapalkki = new HBox();
        appleylapalkki.setStyle("-fx-background-color: #2c5f95; -fx-padding: 15px;");
        appleylapalkki.setPrefHeight(30);

        Label logo = new Label("Mökkikodit");
        logo.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        appleylapalkki.getChildren().addAll(logo);

        Label otsikko = new Label("Tervetuloa Mökkikoteihin!");
        otsikko.setStyle("-fx-font-size: 32px; -fx-text-fill: #ffffff;");
        otsikko.setPrefWidth(600);
        otsikko.setAlignment(Pos.CENTER);




        Button mokkiButton = new Button("Mokit");

        mokkiButton.setOnAction(e -> {
            Stage mokkiStage = new Stage();
            mokkiStage.setTitle("Mökkien hallinta");
            mokkiStage.setResizable(false);


            TableView<Mokki> mokkiTable = new TableView<>();
            mokkiTable.setItems(mokkiLista);

            TableColumn<Mokki, String> nimiCol = new TableColumn<>("Nimi");
            nimiCol.setCellValueFactory(data -> data.getValue().nimiProperty());

            TableColumn<Mokki, String> osoiteCol = new TableColumn<>("Osoite");
            osoiteCol.setCellValueFactory(data -> data.getValue().osoiteProperty());

            TableColumn<Mokki, Number> hintaCol = new TableColumn<>("Hinta per yö (€)");
            hintaCol.setCellValueFactory(data -> data.getValue().hintaProperty());

            TableColumn<Mokki, Number> kokoCol = new TableColumn<>("Koko (m²)");
            kokoCol.setCellValueFactory(data -> data.getValue().kokoProperty());

            TableColumn<Mokki, Boolean> paljuCol = new TableColumn<>("Onko palju?");
            paljuCol.setCellValueFactory(data -> data.getValue().onkopaljuProperty());

            mokkiTable.getColumns().addAll(nimiCol, osoiteCol, hintaCol, kokoCol, paljuCol);



            TextField nimiKentta = new TextField();
            nimiKentta.setPromptText("Mökin nimi");

            TextField osoiteKentta = new TextField();
            osoiteKentta.setPromptText("Osoite");

            TextField hintaKentta = new TextField();
            hintaKentta.setPromptText("Hinta per yö (€)");

            TextField kokoKentta = new TextField();
            kokoKentta.setPromptText("Koko (m²)");

            CheckBox paljuBox = new CheckBox("Onko palju?");

            Button lisaaButton = new Button("Lisää mökki");
            lisaaButton.setOnAction(u -> {

                try {
                    String nimi = nimiKentta.getText();
                    String osoite = osoiteKentta.getText();
                    Double hinta = Double.parseDouble(hintaKentta.getText());
                    Double koko = Double.parseDouble(kokoKentta.getText());
                    Boolean onkopalju = paljuBox.isSelected();

                    Mokki uusi = new Mokki(nimi, osoite, hinta, koko, onkopalju);
                    mokkiTable.getItems().add(uusi);

                    nimiKentta.clear();
                    osoiteKentta.clear();
                    hintaKentta.clear();
                    kokoKentta.clear();
                    paljuBox.setSelected(false);
                }
                catch (NumberFormatException ex){
                    vaarinKaytto("Virhe!", "Täytä kaikki kentät oikein");
                }
            });


            Button poistaButton = new Button("Poista valittu mökki");
            poistaButton.setOnAction(i -> {
                Mokki valittu = mokkiTable.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    mokkiTable.getItems().remove(valittu);
                }
            });




            nimiKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    osoiteKentta.requestFocus();
                }
            });
            osoiteKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    hintaKentta.requestFocus();
                }
            });
            hintaKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    kokoKentta.requestFocus();
                }
            });
            kokoKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    lisaaButton.requestFocus();
                }
            });




            Text teksti = new Text("Lisää uusia mökkejä syöttämällä mökin tiedot:");

            HBox kentat = new HBox(10, nimiKentta, osoiteKentta, hintaKentta, kokoKentta, paljuBox);
            HBox napit = new HBox(10, lisaaButton, poistaButton);

            VBox kokohomma = new VBox(10, mokkiTable, teksti, kentat, napit);
            kokohomma.setStyle("-fx-padding: 20;");

            Scene mokkiscene = new Scene(kokohomma, 760, 450);
            mokkiStage.setScene(mokkiscene);
            mokkiStage.show();
        });










        Button asiakasButton = new Button("Asiakkaat");

        asiakasButton.setOnAction(e -> {
            Stage asiakasStage = new Stage();
            asiakasStage.setTitle("Asiakkaat");
            asiakasStage.setResizable(false);

            TableView<Asiakas> asiakasTable = new TableView<>();
            asiakasTable.setItems(asiakasLista);

            TableColumn<Asiakas, Integer> idColumn = new TableColumn<>("Asiakas ID");
            idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());

            TableColumn<Asiakas, String> nimiColumn = new TableColumn<>("Nimi");
            nimiColumn.setCellValueFactory(data -> data.getValue().nimiProperty());

            TableColumn<Asiakas, String> osoiteColumn = new TableColumn<>("Osoite");
            osoiteColumn.setCellValueFactory(data -> data.getValue().osoiteProperty());

            TableColumn<Asiakas, String> spostiColumn = new TableColumn<>("Sähköposti");
            spostiColumn.setCellValueFactory(data -> data.getValue().spostiProperty());

            TableColumn<Asiakas, String> numeroColumn = new TableColumn<>("Puhelinnumero");
            numeroColumn.setCellValueFactory(data -> data.getValue().puhelinnumeroProperty());

            asiakasTable.getColumns().addAll(idColumn, nimiColumn, osoiteColumn, spostiColumn, numeroColumn);



            TextField idKentta = new TextField();
            idKentta.setPromptText("Asiakkaan ID");

            TextField asiakasKentta = new TextField();
            asiakasKentta.setPromptText("Nimi");

            TextField osoiteKentta = new TextField();
            osoiteKentta.setPromptText("Osoite");

            TextField spostiKentta = new TextField();
            spostiKentta.setPromptText("Sähköposti");

            TextField numeroKentta = new TextField();
            numeroKentta.setPromptText("Puhelinnumero");

            Button lisaaButton = new Button("Lisää asiakas");
            lisaaButton.setOnAction(i -> {
                try {

                    Integer id = Integer.parseInt(idKentta.getText());
                    String nimi = asiakasKentta.getText();
                    String osoite = osoiteKentta.getText();
                    String sposti = spostiKentta.getText();
                    String numero = numeroKentta.getText();

                    Asiakas uusi = new Asiakas(id, nimi, osoite, sposti, numero);
                    asiakasTable.getItems().add(uusi);

                    idKentta.clear();
                    asiakasKentta.clear();
                    osoiteKentta.clear();
                    spostiKentta.clear();
                    numeroKentta.clear();

                }

                catch (NumberFormatException ex){
                    vaarinKaytto("Virhe!", "Täytä kaikki kentät oikein!");
                }
            });

            Button poistaButton = new Button("Poista asiakas");
            poistaButton.setOnAction(i -> {
                asiakasTable.getItems().remove(asiakasTable.getSelectionModel().getSelectedItem());
            });

            idKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    asiakasKentta.requestFocus();
                }
            });
            asiakasKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    osoiteKentta.requestFocus();
                }
            });
            osoiteKentta.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                spostiKentta.requestFocus();
            }
        });
            spostiKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    numeroKentta.requestFocus();
                }
            });
            numeroKentta.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    lisaaButton.requestFocus();
                }
            });



            Text teksti = new Text("Lisää uusia asiakkaita syöttämällä tiedot");

            HBox jani = new HBox(10, idKentta, asiakasKentta, osoiteKentta, spostiKentta, numeroKentta);
            HBox toivola = new HBox(10, lisaaButton, poistaButton);

            VBox kokopaska = new VBox(10, asiakasTable, teksti, jani, toivola);
            kokopaska.setStyle("-fx-padding: 20;");

            Scene asiakasScene = new Scene(kokopaska, 860, 450);
            asiakasStage.setScene(asiakasScene);
            asiakasStage.show();

        });





        Button varauksetButton = new Button("Varaukset");

        varauksetButton.setOnAction(e -> {
            Stage varauksetStage = new Stage();
            varauksetStage.setTitle("Varaukset");
            varauksetStage.setResizable(false);


            TableView<Varaukset> varauksetTable = new TableView<>();

            varauksetTable.setItems(varauksetLista);


            TableColumn<Varaukset, String> mokkiCol = new TableColumn<>("Nimi");
            mokkiCol.setCellValueFactory(data -> data.getValue().mokkiProperty());

            TableColumn<Varaukset, String> asiakasCol = new TableColumn<>("Osoite");
            asiakasCol.setCellValueFactory(data -> data.getValue().asiakasProperty());

            TableColumn<Varaukset, LocalDate> alkupvCol = new TableColumn<>("Alkupäivä");
            alkupvCol.setCellValueFactory(data -> data.getValue().alkuPaivaProperty());

            TableColumn<Varaukset, LocalDate> loppupvCol = new TableColumn<>("Loppupäivä");
            loppupvCol.setCellValueFactory(data -> data.getValue().loppuPaivaProperty());


            varauksetTable.getColumns().addAll(mokkiCol,asiakasCol,alkupvCol, loppupvCol);


            ComboBox<Mokki> mokkiCombo = new ComboBox<>();
            mokkiCombo.setItems(mokkiLista);
            mokkiCombo.setPromptText("Valitse mökki");


            ComboBox<Asiakas> asiakasCombo = new ComboBox<>();
            asiakasCombo.setItems(asiakasLista);
            asiakasCombo.setPromptText("Valitse asiakas");





            DatePicker alkupvPicker = new DatePicker();
            alkupvPicker.setPromptText("Alkupäivä");

            DatePicker loppupvPicker = new DatePicker();
            loppupvPicker.setPromptText("Loppupäivä");



            Button lisaavarausbutton = new Button("Lisää varaus");
            lisaavarausbutton.setOnAction(a ->{
                Mokki mokki = mokkiCombo.getValue();
                Asiakas asiakas = asiakasCombo.getValue();
                LocalDate alkupv = alkupvPicker.getValue();
                LocalDate loppupv = loppupvPicker.getValue();


                if (mokki != null && asiakas != null && alkupv != null && loppupv != null) {
                    if (alkupv.isBefore(loppupv))
                    {Varaukset uusiVaraus = new Varaukset(mokki.getNimi(), asiakas.getNimi(), alkupv, loppupv);
                        varauksetTable.getItems().add(uusiVaraus);

                        mokkiCombo.setValue(null);
                        asiakasCombo.setValue(null);
                        alkupvPicker.setValue(null);
                        loppupvPicker.setValue(null);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Täytä kaikki kentät!");
                        alert.show();
                    }

                }
            });


            Button poistavaraus = new Button("Poista varaus");
            poistavaraus.setOnAction(a ->{
                Varaukset valittu = varauksetTable.getSelectionModel().getSelectedItem();
                if (valittu != null){
                    varauksetTable.getItems().remove(valittu);
                }
            });



            HBox kentat = new HBox(10, mokkiCombo,asiakasCombo,alkupvPicker,loppupvPicker);
            HBox napit = new HBox(10, lisaavarausbutton, poistavaraus);


            VBox layout = new VBox(10, varauksetTable, kentat,napit);



            Scene varaukset = new Scene(layout, 700,500);
            varauksetStage.setScene(varaukset);
            varauksetStage.show();

        });



        Button laskutButton = new Button("Laskut");

        laskutButton.setOnAction(e -> {
            Stage laskutStage = new Stage();
            laskutStage.setTitle("Laskut");
            laskutStage.setResizable(false);



            TableView<Lasku> laskutTable = new TableView<>();
            ObservableList<Lasku> laskutLista = FXCollections.observableArrayList();




            TableColumn<Lasku, String> mokkiCol = new TableColumn<>("Mökki");
            mokkiCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMokki()));

            TableColumn<Lasku, String> asiakasCol = new TableColumn<>("Asiakas");
            asiakasCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAsiakas()));

            TableColumn<Lasku, LocalDate> alkuCol = new TableColumn<>("Alku");
            alkuCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAlkuPaiva()));

            TableColumn<Lasku, LocalDate> loppuCol = new TableColumn<>("Loppu");
            loppuCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getLoppuPaiva()));

            TableColumn<Lasku, Double> hintaCol = new TableColumn<>("Hinta €");
            hintaCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getHinta()));

            TableColumn<Lasku, Boolean> maksettuCol = new TableColumn<>("Maksettu");
            maksettuCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().isMaksettu()));


            laskutTable.getColumns().addAll(mokkiCol, asiakasCol, alkuCol, loppuCol, hintaCol, maksettuCol);


            for (Varaukset varaukset : varauksetLista){
                String mokkiNimi = varaukset.getMokki();
                String asiakasNimi = varaukset.getAsiakas();
                LocalDate alku = varaukset.alkuPaivaProperty().get();
                LocalDate loppu = varaukset.loppuPaivaProperty().get();

                long paivat = ChronoUnit.DAYS.between(alku, loppu);
                Mokki mokkiObj = mokkiLista.stream().filter(m->m.getNimi().equals(mokkiNimi)).findFirst().orElse(null);

                double paivahinta = mokkiObj != null ? mokkiObj.getHinta(): 0;
                double kokonaishinta = paivat*paivahinta;


                laskutLista.add(new Lasku(mokkiNimi, asiakasNimi, alku, loppu, kokonaishinta));

            }

            laskutTable.setItems(laskutLista);



            Button merkitsemaksetuksi = new Button("Merkitse maksetuksi");
            merkitsemaksetuksi.setOnAction(a ->{
                Lasku valittu = laskutTable.getSelectionModel().getSelectedItem();
                if (valittu != null){
                    valittu.setMaksettu(true);
                    laskutTable.refresh();
                }
            });

            VBox layout = new VBox(10, laskutTable, merkitsemaksetuksi);
            layout.setPadding(new Insets(10));

            Scene laskut = new Scene(layout, 700,500);
            laskutStage.setScene(laskut);
            laskutStage.show();


        });






        Button raportitButton = new Button("Raportit");

        raportitButton.setOnAction(e -> {
            Stage raportitStage = new Stage();
            raportitStage.setTitle("Raportit");
            Pane raportitpane = new Pane();

            Scene raportit = new Scene(raportitpane, 200,200);
            raportitStage.show();



        });







        HBox nappulat = new HBox(50, mokkiButton, asiakasButton, varauksetButton, laskutButton, raportitButton);
        nappulat.setAlignment(Pos.CENTER);
        VBox sisalto = new VBox(10, otsikko, nappulat);
        sisalto.setAlignment(Pos.CENTER);

        BorderPane koko = new BorderPane();
        koko.setTop(appleylapalkki);
        koko.setCenter(sisalto);


        Scene scene = new Scene(koko, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/basic.css").toExternalForm());

        primaryStage.setTitle("Mökkivarausjärjestelmä");
        primaryStage.setScene(scene);
        primaryStage.show();



    }



    private void vaarinKaytto(String otsikko, String viesti) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(otsikko);
        alert.setContentText(viesti);
        alert.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}








