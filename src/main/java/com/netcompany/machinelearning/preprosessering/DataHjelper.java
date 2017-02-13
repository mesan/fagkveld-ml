package com.netcompany.machinelearning.preprosessering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

public class DataHjelper {

    public static int ANTALL_KLASSER = 10;
    public static final int MNIST_HOYDE = 28;
    public static final int MNIST_BREDDE = 28;

    private static final Logger LOGG = LoggerFactory.getLogger(DataHjelper.class);
    private static final String ROTMAPPE = System.getProperty("user.dir");
    private static final String DATAMAPPE = ROTMAPPE + "/src/main/java/com/netcompany/machinelearning/data/";
    static String TRENINGSMAPPE = DATAMAPPE + "mnist_png/training";
    static String TESTMAPPE = DATAMAPPE + "mnist_png/testing";


    public static void printBilde(final int[][] array) {
        final int w = array.length;
        final int h = array[0].length;

        for (final int[] anArray : array) {
            for (int k = 0; k < h; k++) {
                System.out.print(anArray[k] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @param bildeFil bildet som {@link File}
     * @return en to-dimensjonal int array representasjon av bilde med verdier innenfor 0-255.
     */
    public static int[][] bildeTilIntArray(final File bildeFil) {
        try {
            final BufferedImage bilde = ImageIO.read(bildeFil);
            final WritableRaster raster = bilde.getRaster();
            final DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

            final int w = bilde.getWidth();
            final int h = bilde.getHeight();

            final int[][] array = new int[w][h];
            for (int row = 0; row < w; row++) {
                for (int col = 0; col < h; col++) {
                    data.getElemFloat(row);
                    array[row][col] = raster.getSample(row, col, 0);
                }
            }
            return array;
        } catch (final Exception e) {
            LOGG.error("Bæsjen traff vifta ved lesing av bilde:" + bildeFil.getAbsolutePath());
        }
        return null;
    }
}
