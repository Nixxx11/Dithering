import java.awt.image.BufferedImage;

public class FloydSteinbergDithering {
    private static final int[][] NEIGHBOURS = new int[][]{
            {1, 0},
            {-1, 1},
            {0, 1},
            {1, 1}
    };
    private static final int[] COEFFICIENTS = new int[]{
            7,
            3,
            5,
            1
    };

    public static void process(final BufferedImage image, final int colorCount) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int oldPixel = image.getRGB(x, y);
                final int oldR = ImageUtils.getR(oldPixel);
                final int oldG = ImageUtils.getG(oldPixel);
                final int oldB = ImageUtils.getB(oldPixel);

                final int newR = toNewColor(oldR, colorCount);
                final int newG = toNewColor(oldG, colorCount);
                final int newB = toNewColor(oldB, colorCount);

                image.setRGB(x, y, ImageUtils.getPixel(ImageUtils.getA(oldPixel), newR, newG, newB));
                for (int n = 0; n < NEIGHBOURS.length; n++) {
                    final int x1 = x + NEIGHBOURS[n][0];
                    final int y1 = y + NEIGHBOURS[n][1];
                    if (x1 < 0 || x1 >= width || y1 < 0 || y1 >= height) {
                        continue;
                    }
                    final int pixel = image.getRGB(x1, y1);
                    final int r = addDiff(ImageUtils.getR(pixel), oldR - newR, n);
                    final int g = addDiff(ImageUtils.getG(pixel), oldG - newG, n);
                    final int b = addDiff(ImageUtils.getB(pixel), oldB - newB, n);

                    image.setRGB(x1, y1, ImageUtils.getPixel(ImageUtils.getA(pixel), r, g, b));
                }
            }
        }
    }

    private static int toNewColor(final int color, final int colorCount) {
        return (color * colorCount / 256) * 255 / (colorCount - 1);
    }

    private static int addDiff(final int color, final int diff, final int n) {
        return ImageUtils.toColor(color + diff * COEFFICIENTS[n] / 16);
    }
}
