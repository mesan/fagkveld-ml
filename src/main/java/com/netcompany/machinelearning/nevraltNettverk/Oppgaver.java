package com.netcompany.machinelearning.nevraltNettverk;

import com.netcompany.machinelearning.app.SifferKlassifikatorApp;
import com.netcompany.machinelearning.preprosessering.DataLaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oppgaver {

    private static final Logger LOGG = LoggerFactory.getLogger(Oppgaver.class);

    public static void main(final String[] args) throws Exception {

        // Laster inn bilder. Velg om du vil bruke hele eller deler av datasettet.
        final Boolean lesInnHeleDatasettet = false;
        final DataLaster dataLaster = new DataLaster(lesInnHeleDatasettet);

        // ###############################################################
        // TODO: OPPGAVE 1: PREPROSESSERING -> FLAT UT OG NORMALISER
        // ###############################################################

        // OPPG 1 a) Flat ut bilder. Se hjelpemetoder nederst
        int[][][] treningsbilder = dataLaster.getTreningsbilder();
        int[][][] testbilder = dataLaster.getTestbilder();

        // flateTreningsbilder = flatUtBilder(treningsbilder);
        // flateTestbilder = flatUtBilder(testbilder);

        // OPPG 1 b) Konverter pikselverdiene fra [0, 255] til [-1, 1]) - Normaliser

        //double[][] normaliserteTreningsbilder = normaliser(flateTreningsbilder);
        //double[][] normaliserteTestbilder = normaliser(flateTestbilder);


        // ###############################################################
        // TODO: OPPGAVE 2: NEVRALT NETTVERK -> BYGG NETTVERK
        // ###############################################################

        // Bruk NevralNettverkBygger til å opprette ett nettverk.
        // For nettverkslagene: husk at hvert input-lag må ha samme antall noder som forrige output-laget

        // ###############################################################
        // TODO: OPPGAVE 3: TREN OG TEST
        // ###############################################################

        // ###############################################################
        // TODO: OPPGAVE 4: LEK DEG MED PARAMETRE
        // ###############################################################

        // Prøv med forskjellige lag.
        // Sett forskjellige lengder på epoker.
        // Sett forskjellige batch-størrelser.

        // ###############################################################
        // TODO: OPPGAVE 5: Implementer app
        // ###############################################################

        // Skriv kode i SifferKlassifikator
        //SifferKlassifikatorApp.start();

    }

    // Metodene under kan være til hjelp for oppgave 1:

    private static int[][] flatUtBilder(final int[][][] bilder) {
        final int antallBilder = bilder.length;
        final int antallPixler = bilder[0].length * bilder[0][0].length;
        final int[][] flateBilder = new int[antallBilder][antallPixler];

        /*
        for hvert bilde:
            lagre flatt bilde med hjelpemetoden under.
         */

        return flateBilder;
    }

    private static int[] flatUtEttBilde(final int[][] bilde) {
        int pikselIndeks = 0;
        final int[] flattBilde = new int[bilde.length * bilde[0].length];

        /*
        for hver rad i bilde:
            for hver piksel i raden:
                lagre piksel i et flatt bilde
         */

        return flattBilde;
    }

    private static double[][] normaliser(final int[][] flateBilder) {
        final int antallBilder = flateBilder.length;
        final int antallPiksler = flateBilder[0].length;

        final double[][] normaliserteBilder = new double[antallBilder][antallPiksler];

        final double MAX_PIXEL_VERDI = 255.0; // FORDI VI VET DET :)

        /*

        For hvert bilde:
            For hver piksel:
                    normailser piksel

         */


        return normaliserteBilder;
    }
}
