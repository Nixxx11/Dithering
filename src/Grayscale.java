import java.awt.image.BufferedImage;

public class Grayscale {
    public static void process(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, toGrayscale(image.getRGB(x, y)));
            }
        }
    }

    private static int toGrayscale(final int pixel) {
        final int average = (ImageUtils.getR(pixel) + ImageUtils.getG(pixel) + ImageUtils.getB(pixel)) / 3;
        return ImageUtils.getPixel(ImageUtils.getA(pixel), average, average, average);
    }
}
