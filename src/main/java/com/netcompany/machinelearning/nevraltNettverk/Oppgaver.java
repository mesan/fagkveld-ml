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

        // OPPG 1 a) Flat ut bilder

        // OPPG 1 b) Konverter pikselverdiene fra [0, 255] til [-1, 1])

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
        return new int[0][];
    }

    private static int[] flatUtEttBilde(final int[][] bilde) {
        return new int[0];
    }

    private static double[][] normaliser(final int[][] bilder) {
        return new double[0][];
    }
}
