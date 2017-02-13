package com.netcompany.machinelearning.nevraltNettverk;

import com.netcompany.machinelearning.preprosessering.DataHjelper;
import com.netcompany.machinelearning.preprosessering.DataLaster;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Oystein Kvamme Repp
 */
public class BildeKlassifikator extends Application {

    private ImageView myImageView;
    private Text klasse;
    private Button btnKlassifiser;
    private File valgtFil;

    @Override
    public void start(final Stage stage) {

        GridPane gridPane = new GridPane();

        final Button btnLoad = new Button("Load");
        final Label klasseLabel = new Label("Predikert tall: ");
        btnKlassifiser = new Button("Klassifiser");
        btnLoad.setOnAction(lastOppKnappLytter);
        btnKlassifiser.setOnAction(klassifiserKnappLytter);
        btnKlassifiser.setDisable(true);

        myImageView = new ImageView();
        klasse = new Text();
        klasse.setText("-");

        final Group gruppe = new Group();
        gridPane.add(btnLoad, 1, 1);
        gridPane.add(btnKlassifiser, 2, 1);
        gridPane.add(myImageView, 1, 2, 2, 1);
        gridPane.add(klasseLabel, 1, 3);
        gridPane.add(klasse, 2, 3);
        gruppe.getChildren().add(gridPane);
        final Scene scene = new Scene(gruppe, 300, 300, Color.WHITE);

        stage.setTitle("Jaja, det er i alle fall ikke Swing...");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);

    }

    private final EventHandler<ActionEvent> klassifiserKnappLytter = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {

            /*TODO Oppgave 5? Bytt ut den heller dårlige random-klassifikatoren under.
            Hint: Tren et nevralt nettverk å få modellen inn her på en eller annen måte.
            Hint: Valgt fil er tilgjengelig i valgtFil
            Hint: DataHjelper har en bildeTilIntArray-metode som sannsynligvis vil være til hjelp*/

            final Boolean lesInnHeleDatasettet = false;
            final DataLaster dataLaster = new DataLaster(lesInnHeleDatasettet);


            final Integer predikertKlasse = (int) (Math.random() * 10);
            klasse.setText(predikertKlasse.toString());
        }
    };

    private final EventHandler<ActionEvent> lastOppKnappLytter = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent t) {
            final FileChooser fileChooser = new FileChooser();

            final FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)",
                                                                                             "*.PNG");
            fileChooser.getExtensionFilters().add(extFilterPNG);

            valgtFil = fileChooser.showOpenDialog(null);

            try {
                final BufferedImage bufferedImage = ImageIO.read(valgtFil);
                final Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                myImageView.setImage(image);
                btnKlassifiser.setDisable(valgtFil == null);
            } catch (final IOException ex) {
                Logger.getLogger(BildeKlassifikator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

}