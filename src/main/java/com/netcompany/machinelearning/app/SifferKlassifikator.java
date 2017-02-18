package com.netcompany.machinelearning.app;

import com.netcompany.machinelearning.nevraltNettverk.NevraltNettverk;
import com.netcompany.machinelearning.nevraltNettverk.NevraltNettverkBygger;
import com.netcompany.machinelearning.preprosessering.DataHjelper;
import com.netcompany.machinelearning.preprosessering.DataLaster;

import java.io.File;
import java.util.Arrays;

class SifferKlassifikator {

    private NevraltNettverk modell;

    SifferKlassifikator() {
        final NevraltNettverkBygger nnBygger = new NevraltNettverkBygger();
        modell = nnBygger
                .leggTilLag(784, 200)
                .leggTilLag(200, 150)
                .leggTilLag(150, 10).bygg();

        modell.setBatchStorrelse(200);
        modell.setAntallEpoker(200);

        final DataLaster dataLaster = new DataLaster(true);
        final int[][][] treningData = dataLaster.getTreningsbilder();
        final int[] treningFasit = dataLaster.getTreningsfasit();

        final double[][] treningDataInput = normaliserBilder(flatUtBilder(treningData));

        modell.tren(treningDataInput, treningFasit);
    }

    final Integer prediker(final File bildefil) {

        /* Oppgave 5: Bytt ut den heller dårlige random-klassifikatoren under.

        Hint: Tren et nevralt nettverk å få modellen inn her på en eller annen måte.
        Hint: DataHjelper har en bildeTilIntArray-metode som sannsynligvis vil være til hjelp*/

        final int[][] bilde2D = DataHjelper.bildeTilIntArray(bildefil);
        final int[] bilde1D = flatUtEttBilde(bilde2D);
        final double[] normalisertBilde = normaliserEttBilde(bilde1D);

        return modell.prediker(normalisertBilde);
    }

    private int[][] flatUtBilder(final int[][][] bilder2D) {
        final int[][] flateBilder = new int[bilder2D.length][];
        for (int i = 0; i < flateBilder.length; i++) {
            flateBilder[i] = flatUtEttBilde(bilder2D[i]);
        }
        return flateBilder;
    }

    private int[] flatUtEttBilde(final int[][] bilde) {
        return Arrays.stream(bilde).flatMapToInt(Arrays::stream).toArray();
    }

    private double[][] normaliserBilder(final int[][] bilder) {
        final double[][] normaliserteBilder = new double[bilder.length][];
        for (int i = 0; i < bilder.length; i++) {
            normaliserteBilder[i] = normaliserEttBilde(bilder[i]);
        }
        return normaliserteBilder;
    }

    private double[] normaliserEttBilde(final int[] bilde) {
        final double MAKS_PIKSEL_VERDI = 255.0;
        return Arrays.stream(bilde)
                     .mapToDouble(pikselverdi -> (pikselverdi / MAKS_PIKSEL_VERDI) * 2 - 1)
                     .toArray();
    }
}

