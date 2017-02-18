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
        int[][] flateTreningsbilder = flatUtBilder(dataLaster.getTreningsbilder());
        int[][] flateTestbilder = flatUtBilder(dataLaster.getTestbilder());

        // OPPG 1 b) Konverter pikselverdiene fra [0, 255] til [-1, 1])
        double[][] normaliserteTreningsbilder = normaliser(flateTreningsbilder);
        double[][] normaliserteTestbilder = normaliser(flateTestbilder);

        // ###############################################################
        // TODO: OPPGAVE 2: NEVRALT NETTVERK -> BYGG NETTVERK
        // ###############################################################

        // Bruk NevralNettverkBygger til å opprette ett nettverk.
        // For nettverkslagene: husk at hvert input-lag må ha samme antall noder som forrige output-laget

        /*
        final NevraltNettverk nevraltNettverk = new NevraltNettverkBygger()
                .leggTilLag(784, 200)
                .leggTilLag(200, 10)
                .bygg();

        nevraltNettverk.setAntallEpoker(100);
        nevraltNettverk.setBatchStorrelse(40);

        // ###############################################################
        // TODO: OPPGAVE 3: TREN OG TEST
        // ###############################################################

        nevraltNettverk.tren(normaliserteTreningsbilder, dataLaster.getTreningsfasit());

        nevraltNettverk.evaluer(normaliserteTestbilder, dataLaster.getTestfasit());
        */

        // ###############################################################
        // TODO: OPPGAVE 4: LEK DEG MED PARAMETRE
        // ###############################################################

        // Prøv med forskjellige lag.
        // Sett forskjellige lengder på epoker.
        // Sett forskjellige batch-størrelser.

        final NevraltNettverk nevraltNettverk = new NevraltNettverkBygger()
                .leggTilLag(784, 400)
                .leggTilLag(400, 50)
                .leggTilLag(50, 10)
                .bygg();

        nevraltNettverk.setAntallEpoker(100);
        nevraltNettverk.setBatchStorrelse(40);

        nevraltNettverk.tren(normaliserteTreningsbilder, dataLaster.getTreningsfasit());
        nevraltNettverk.evaluer(normaliserteTestbilder, dataLaster.getTestfasit());

        // ###############################################################
        // TODO: OPPGAVE 5: Implementer app
        // ###############################################################

        // Skriv kode i SifferKlassifikator
        //SifferKlassifikatorApp.start();

    }

    // Metodene under kan være til hjelp for oppgave 1:

    private static int[][] flatUtBilder(final int[][][] bilder) {
        final int numberOfImages = bilder.length;
        final int[][] flateBilder = new int[numberOfImages][bilder[0].length * bilder[0][0].length];

        for (int i = 0; i < numberOfImages; i++) {
            flateBilder[i] = flatUtEttBilde(bilder[i]);
        }
        return flateBilder;
    }

    private static int[] flatUtEttBilde(final int[][] bilde) {
        int pikselindeks = 0;
        final int[] flattBilde = new int[bilde.length * bilde[0].length];
        for (final int[] rad : bilde) {
            for (final int piksel : rad) {
                flattBilde[pikselindeks] = piksel;
                pikselindeks++;
            }
        }
        return flattBilde;
    }

    private static double[][] normaliser(final int[][] bilder) {
        final int antallBilder = bilder.length;
        final int antallPiksler = bilder[0].length;

        final double[][] normaliserteBilder = new double[antallBilder][antallPiksler];

        final double MAX_PIXEL_VERDI = 255.0; // FORDI VI VET DET :)

        for (int bildeIndeks = 0; bildeIndeks < antallBilder; bildeIndeks++) {
            for (int piksel = 0; piksel < antallPiksler; piksel++) {
                normaliserteBilder[bildeIndeks][piksel] = (bilder[bildeIndeks][piksel] / MAX_PIXEL_VERDI) * 2 - 1;
            }
        }
        return normaliserteBilder;
    }
}
