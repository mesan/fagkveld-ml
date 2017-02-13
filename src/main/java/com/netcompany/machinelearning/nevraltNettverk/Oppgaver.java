package com.netcompany.machinelearning.nevraltNettverk;

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
        // OPPGAVE 1: PREPROSESSERING -> FLAT UT OG NORMALISER
        // ###############################################################

        // OPPG 1 a) Flat ut bilder
        int[][] flateTreningsbilder = flatUtBilder(dataLaster.getTreningsbilder());
        int[][] flateTestbilder = flatUtBilder(dataLaster.getTestbilder());

        // OPPG 1 b) Konverter pikselverdiene fra [0, 255] til [-1, 1])
        double[][] normaliserteTreningsbilder = normaliser(flateTreningsbilder);
        double[][] normaliserteTestbilder = normaliser(flateTestbilder);

    }

    //  ###########################################################
    //  Metodene under er ment kun ment som forslag til oppg. 1
    //  ###########################################################

    private static int[][] flatUtBilder(final int[][][] bilder) {
        //TODO: Flat ut bildene fra 2 dimensjoner til 1.
        return null;
    }
    private static int[] flatUtEttBilde(final int[][] bilde) {
        //TODO: Hjelpemetode til "flatUtBilder" metoden over?
        return null;
    }

    private static double[][] normaliser(final int[][] bilder) {
        //TODO: Normaliser dataene
        return null;
    }
}
