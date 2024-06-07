public final class ImageUtils {
    public static int getA(final int pixel) {
        return (pixel >> 24) & 0xff;
    }

    public static int getR(final int pixel) {
        return (pixel >> 16) & 0xff;
    }

    public static int getG(final int pixel) {
        return (pixel >> 8) & 0xff;
    }

    public static int getB(final int pixel) {
        return pixel & 0xff;
    }

    private static boolean isColor(final int c) {
        return c == (c & 0xff);
    }

    public static int getPixel(final int a, final int r, final int g, final int b) {
        if (!isColor(a) || !isColor(r) || !isColor(g) || !isColor(b)) {
            throw new IllegalArgumentException("Invalid color");
        }
        return a << 24 | (r << 16) | (g << 8) | b;
    }

    public static int toColor(final int c) {
        return Math.max(0, Math.min(255, c));
    }
}
