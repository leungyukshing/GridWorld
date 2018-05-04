import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.awt.Image;

public class ImageReaderRunner {
  public static void main(String[] args) {
    IImageIO imageioer = new ImplementImageIO();
    IImageProcessor processor = new ImplementImageProcessor();
    Runner.run(imageioer, processor);
  }
}