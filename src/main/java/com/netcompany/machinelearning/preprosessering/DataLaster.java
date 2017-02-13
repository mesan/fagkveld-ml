package com.netcompany.machinelearning.preprosessering;

import com.google.common.collect.Lists;
import com.twelvemonkeys.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DataLaster {

    private final Logger LOGG = LoggerFactory.getLogger(DataLaster.class);
    private static final double ANDEL_AV_DATA = 0.05;
    private static final double ALL_DATA = 1.0;

    private final Datasett treningsdata;
    private final Datasett testdata;

    public DataLaster(final Boolean lesInnHeleDatasettet) {
        if (lesInnHeleDatasettet) {
            LOGG.info("Laster all trenings- og testdata. Dette kan ta en stund...");

            treningsdata = lastBilder(ALL_DATA, DataHjelper.TRENINGSMAPPE);
            testdata = lastBilder(ALL_DATA, DataHjelper.TESTMAPPE);

            LOGG.info("Ferdig med å laste inn bilder.");
        } else {
            LOGG.info(String.format("Laster inn subset (%.0f%%) av trenings- og testdata.", ANDEL_AV_DATA * 100));

            treningsdata = lastBilder(ANDEL_AV_DATA, DataHjelper.TRENINGSMAPPE);
            testdata = lastBilder(ANDEL_AV_DATA, DataHjelper.TESTMAPPE);

            LOGG.info("Ferdig med å laste inn bilder.");
        }
    }

    private Datasett lastBilder(final double andelAvData, final String stiTilDatamappe) {
        final List<int[][]> bilder = Lists.newArrayList();
        final List<Integer> fasit = Lists.newArrayList();

        File f = new File(stiTilDatamappe);
        Validate.isTrue(f.exists(), String.format("%s finnes ikke", stiTilDatamappe));

        final ArrayList<String> dataklasseStier = Lists.newArrayList(f.list());
        for (final String mappe : dataklasseStier) {
            int antallPerKlasse = 0;
            if (mappe.equalsIgnoreCase(".DS_STORE"))
                continue;
            final String bildemappeSti = stiTilDatamappe + File.separator + mappe;
            final ArrayList<String> bildeStier = hentBildeStier(bildemappeSti);
            for (final String bildesti : bildeStier) {
                if (antallPerKlasse++ > bildeStier.size() * andelAvData) {
                    break;
                }
                bilder.add(DataHjelper.bildeTilIntArray(new File(bildemappeSti + File.separator + bildesti)));
                fasit.add(Integer.parseInt(mappe));
            }
        }
        final int[][][] bildeArray = new int[bilder.size()][DataHjelper.MNIST_HOYDE][DataHjelper.MNIST_BREDDE];
        final int[] fasitArray = fasit.stream().mapToInt(Integer::intValue).toArray();
        bilder.toArray(bildeArray);
        return new Datasett(bildeArray, fasitArray);
    }

    private ArrayList<String> hentBildeStier(final String bildemappeSti) {
        final File f = new File(bildemappeSti);
        return new ArrayList<>(Arrays.asList(f.list()));
    }

    public int[][][] getTreningsbilder() {
        return treningsdata.getBilder();
    }

    public int[][][] getTestbilder() {
        return testdata.getBilder();
    }

    public int[] getTreningsfasit() {
        return treningsdata.getFasit();
    }

    public int[] getTestfasit() {
        return testdata.getFasit();
    }

    /**
     * Intern klasse som brukes til å holde features (bildedata) og fasit (klasse) for datasettene.
     */
    private final class Datasett {

        final int[][][] bilder;
        final int[] fasit;

        private Datasett(final int[][][] bilder, final int[] fasit) {
            this.bilder = bilder;
            this.fasit = fasit;
        }

        private int[][][] getBilder() {
            return bilder;
        }

        private int[] getFasit() {
            return fasit;
        }
    }

}
