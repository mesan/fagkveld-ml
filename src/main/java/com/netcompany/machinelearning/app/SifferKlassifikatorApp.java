package com.netcompany.machinelearning.app;

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

public class SifferKlassifikatorApp extends Application {

    private ImageView myImageView;
    private ImageView klasseIllustratedView;
    private Text klasse;
    private Button btnKlassifiser;
    private File valgtFil;
    private SifferKlassifikator klassifikator;

    @Override
    public void start(final Stage stage) {

        final GridPane gridPane = new GridPane();

        final Button btnLastInn = new Button("Velg bilde");
        final Label klasseLabel = new Label("Predikert tall: ");
        btnKlassifiser = new Button("Klassifiser");
        btnLastInn.setOnAction(lastOppKnappLytter);
        btnKlassifiser.setOnAction(klassifiserKnappLytter);
        btnKlassifiser.setDisable(true);
        klasse = new Text();
        klasse.setText("-");

        myImageView = new ImageView();
        klasseIllustratedView = new ImageView();

        gridPane.add(btnLastInn, 0, 0);
        gridPane.add(btnKlassifiser, 1, 0);
        gridPane.add(myImageView, 0, 1, 2, 1);
        gridPane.add(klasseLabel, 0, 2);
        gridPane.add(klasse, 1, 2);
        gridPane.add(klasseIllustratedView, 0, 3, 2, 1);

        final Group gruppe = new Group();
        gruppe.getChildren().add(gridPane);
        final Scene scene = new Scene(gruppe, 300, 650, Color.WHITE);

        btnLastInn.setPrefWidth(150);
        btnKlassifiser.setPrefWidth(150);
        myImageView.setPreserveRatio(true);
        myImageView.setFitWidth(300);
        klasseIllustratedView.setPreserveRatio(true);
        klasseIllustratedView.setFitWidth(300);

        btnLastInn.setMaxWidth(Double.MAX_VALUE);
        btnKlassifiser.setMaxWidth(Double.MAX_VALUE);

        klassifikator = new SifferKlassifikator();

        stage.setTitle("AI is taking over");
        stage.setScene(scene);
        stage.show();
    }


    private final EventHandler<ActionEvent> klassifiserKnappLytter = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            final Integer predikertSiffer = klassifikator.prediker(valgtFil);
            klasse.setText(predikertSiffer.toString());

            final String root = System.getProperty("user.dir");
            final String path = root + "/src/main/java/com/netcompany/machinelearning/data/prediction/"
                    + predikertSiffer.toString() + ".png";
            final File file = new File(path);
            final Image image = new Image(file.toURI().toString());
            klasseIllustratedView.setImage(image);
        }
    };

    private final EventHandler<ActionEvent> lastOppKnappLytter = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent t) {

            final FileChooser filvelger = new FileChooser();

            final FileChooser.ExtensionFilter filtypeFilterPng = new FileChooser.ExtensionFilter("PNG files (*.png)",
                                                                                                 "*.PNG");
            filvelger.getExtensionFilters().add(filtypeFilterPng);

            valgtFil = filvelger.showOpenDialog(null);

            try {
                final BufferedImage bufferedImage = ImageIO.read(valgtFil);
                final Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                myImageView.setImage(image);
                btnKlassifiser.setDisable(valgtFil == null);
            } catch (final IOException ex) {
                Logger.getLogger(SifferKlassifikatorApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static void main(final String[] args) {
        launch();
    }

    public static void start() {
        String[] args = new String[] {"Start SifferKlassifikatorApp"};
        main(args);
    }
}