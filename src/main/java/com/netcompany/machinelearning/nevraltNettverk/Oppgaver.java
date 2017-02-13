package com.netcompany.machinelearning.nevraltNettverk;

import com.netcompany.machinelearning.preprosessering.DataLaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oppgaver {

    private static final Logger LOGG = LoggerFactory.getLogger(Oppgaver.class);

    public static void main(final String[] args) throws Exception {

        // ###############################################################
        // OPPGAVE 1: PREPROSSESERING -> FLAT UT OG NORMALISER
        // ###############################################################

        final Boolean lesInnHeleDatasettet = false;
        final DataLaster dataLaster = new DataLaster(lesInnHeleDatasettet);

        // OPPG 1 a) Flat ut bilder
        int[][] flateBilder = flatUtBilder(dataLaster.getTreningsbilder());

        // OPPG 1 b) Konverter pikselverdiene fra [0, 255] til [-1, 1] (alt. [0, 1])
        double[][] normaliserteBilder = normaliser(flateBilder);

    }

    //  ###########################################################
    //  Metodene under er ment kun ment som forslag til oppg. 1
    //  ###########################################################

    private static int[][] flatUtBilder(final int[][][] images) {
        //TODO: Flat ut bildene fra 2 dimensjoner til 1.
        return null;
    }
    private static int[] flatUtEttBilde(final int[][] image) {
        //TODO: Hjelpemetode til "flatUtBilder" metoden over?
        return null;
    }

    private static double[][] normaliser(final int[][] trainingImages) {
        //TODO: Normaliser dataene
        return null;
    }
}
