import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.awt.Image;
import javax.imageio.ImageIO;


public class ImageProcessorTest {

  /* variables for photo 1 */
  /* origin image */
  private String filepathOne = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/1.bmp";

  /* red image */
  private String filepathRedOne = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/1_red_goal.bmp";

  /* green image */
  private String filepathGreenOne = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/1_green_goal.bmp";

  /* blue image */
  private String filepathBlueOne = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/1_blue_goal.bmp";

  /* gray image */ 
  private String filepathGrayOne = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/1_gray_goal.bmp";

  /* variables for photo 2 */
  /* origin image */
  private String filepathTwo = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/2.bmp";

  /* red image */
  private String filepathRedTwo = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/2_red_goal.bmp";

  /* green image */
  private String filepathGreenTwo = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/2_green_goal.bmp";

  /* blue image */
  private String filepathBlueTwo = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/2_blue_goal.bmp";

  /* gray image */ 
  private String filepathGrayTwo = "/home/administrator/Desktop/GridWorld/Section3/ImageReader/bmptest/goal/2_gray_goal.bmp";

  /* Testing */
  @Test
  /* Test input */
  public void testIOOne() {
    auxIO(filepathOne);
  }

  @Test
  /* Test Red */
  public void testRedOne() {
    aux(filepathRedOne, filepathOne, 1);
  }

  @Test
  /* Test Green */
  public void testGreenOne() {
    aux(filepathGreenOne, filepathOne, 2);
  }

  @Test
  /* Test Blue */
  public void testBlueOne() {
    aux(filepathBlueOne, filepathOne, 3);
  }

  @Test
  /* Test Gray */
  public void testGrayOne() {
    aux(filepathGrayOne, filepathOne, 4);
  }

  @Test
  /* Test input */
  public void testIOTwo() {
    auxIO(filepathTwo);
  }

  @Test
  /* Test Red */
  public void testRedTwo() {
    aux(filepathRedTwo, filepathTwo, 1);
  }

  @Test
  /* Test Green */
  public void testGreenTwo() {
    aux(filepathGreenTwo, filepathTwo, 2);
  }

  @Test
  /* Test Blue */
  public void testBlueTwo() {
    aux(filepathBlueTwo, filepathTwo, 3);
    
  }

  @Test
  /* Test Gray */
  public void testGrayTwo() {
      aux(filepathGrayTwo, filepathTwo, 4);
  }

  /* Auxilary funtion to test gray */
  private void aux(String path1, String path2, int type) {
    try {
      // Standared Answer
      FileInputStream inputstream = new FileInputStream(path1);
      BufferedImage answer = ImageIO.read(inputstream);

      // My Answer
      ImplementImageIO input = new ImplementImageIO();
      Image image = input.myRead(path2);

      // My Processor
      ImplementImageProcessor myProcessor = new ImplementImageProcessor();
      Image myImage = null;
      if (type == 1) {
        myImage = myProcessor.showChanelR(image);
      }
      else if (type == 2) {
        myImage = myProcessor.showChanelG(image);
      }
      else if (type == 3) {
        myImage = myProcessor.showChanelB(image);
      }
      else if (type == 4) {
        myImage = myProcessor.showGray(image);
      }
      

      // Compare the size of the picture
      assertEquals(myImage.getHeight(null), answer.getHeight(null));
      assertEquals(myImage.getWidth(null), answer.getWidth(null));

      // Compare every pixel in the picture
      BufferedImage my = new BufferedImage(myImage.getWidth(null), myImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
      my.getGraphics().drawImage(myImage, 0, 0,null);
      for (int y = 0; y < answer.getHeight(); y++) {
        for (int x = 0; x < answer.getWidth(); x++) {  
          assertEquals(my.getRGB(x, y), answer.getRGB(x, y));
        }
      }
    }
    catch (Exception ex) {

    }
  }

  private void auxIO(String path) {
    try {
      // Standared Answer
      FileInputStream inputstream = new FileInputStream(path);
      BufferedImage answer = ImageIO.read(inputstream);

      // My Answer
      ImplementImageIO input = new ImplementImageIO();
      Image myImage = input.myRead(path);
      
      // Compare the size of the picture
      assertEquals(myImage.getHeight(null), answer.getHeight(null));
      assertEquals(myImage.getWidth(null), answer.getWidth(null));

      // Compare every pixel in the picture
      BufferedImage my = new BufferedImage(myImage.getWidth(null), myImage.getHeight(null), BufferedImage.TYPE_INT_RGB);  
      my.getGraphics().drawImage(myImage, 0, 0,null);
      for (int y = 0; y < answer.getHeight(); y++) {
        for (int x = 0; x < answer.getWidth(); x++) {  
              assertEquals(my.getRGB(x, y), answer.getRGB(x, y));
        }
      }
          
    }
    catch (Exception ex) {
      
    }
  }
}