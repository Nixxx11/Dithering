import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) {
        if (args.length != 3) {
            System.out.println("Expected 3 arguments: <input> <output> <color count>");
            return;
        }
        final String inputPath = args[0];
        final String outputPath = args[1];
        final int colorCount;
        try {
            colorCount = Integer.parseInt(args[2]);
        } catch (final NumberFormatException e) {
            System.out.printf("Invalid number: %s%n", args[2]);
            return;
        }
        if (colorCount < 1 || colorCount > 255) {
            System.out.println("Invalid color count: " + colorCount);
            return;
        }

        final BufferedImage img;
        try {
            img = ImageIO.read(new File(inputPath));
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        final long timeBegin = System.currentTimeMillis();
        FloydSteinbergDithering.process(img, 2);
        final long timeEnd = System.currentTimeMillis();
        try {
            ImageIO.write(img, "png", new File(String.format("%s.png", outputPath)));
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("Finished in %d ms%n", timeEnd - timeBegin);
    }
}
