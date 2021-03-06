import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  
  /** Method to set the red and green to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    } 
  }
  
  
  /** Method to negate the color values */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }      
  }
  
  
  /** Method to average the three color values as to form a grayscale picture */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int total = pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue();
        pixelObj.setRed(total/3);
        pixelObj.setGreen(total/3);
        pixelObj.setBlue(total/3);
      }
    }      
  }  
  
  
  /** Method to make fish more visible in an ocean picture */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int contrast = 4;
        int aquaValue = (pixelObj.getBlue() + pixelObj.getGreen())/2;
        pixelObj.setRed(contrast*pixelObj.getRed());
        pixelObj.setGreen((contrast*(pixelObj.getGreen()-aquaValue))+aquaValue);
        pixelObj.setBlue((contrast*(pixelObj.getBlue()-aquaValue))+aquaValue);
      }
    }      
  }  
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }  
  
  
    /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-1-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  
  /** Method that mirrors the picture around a 
  * horizontal mirror in the center of the picture
  * from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-1-row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }
  
  
  /** Method that mirrors a 45-45-90 right traingle
   * at the bottom left of the picture*/
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel firstPixel = null;
    Pixel secondPixel = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0;col < pixels[0].length && col < row; col++)
      {
        firstPixel = pixels[row][col];
        
        if(row < pixels[0].length)
        {
          secondPixel = pixels [col][row];
          secondPixel.setColor(firstPixel.getColor());
        }
      }
    }  
  }
  

  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());

        count++;
      }
    }
    
    System.out.println(count);
  }
  
  /** Method that mirrors just the arms of a snowman*/
  public void mirrorArms()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < 190; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        
        if(topPixel.getRed() > topPixel.getGreen()
        && topPixel.getRed() < 200
        && row > 160)
        {
            bottomPixel = pixels[190+(190-row)][col];
            bottomPixel.setColor(topPixel.getColor());
        }
      }
    } 
  }
  
  /** Method that mirrors a gull on a beach */
  public void mirrorGull()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < 330; row++)
    {
      for (int col = 0; col < 350; col++)
      {
        leftPixel = pixels[row][col];
        if(col > 230 &&
        row > 230 &&
        (leftPixel.getBlue() < 110 ||
        leftPixel.getBlue()/leftPixel.getRed() < 1.1))
        {
            rightPixel = pixels[row][350+(350-col)];
            rightPixel.setColor(leftPixel.getColor());
        }
      }
    } 
    }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to 
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to 
    * @param startCol the start col to copy to
    */
  public void partialCopy(Picture fromPic,
  int fromStartRow, int fromStartCol,
  int fromEndRow, int fromEndCol,
  int toStartRow, int toStartCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = fromStartRow, toRow = toStartRow; 
         fromRow < fromEndRow &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = fromStartCol, toCol = toStartCol; 
           fromCol < fromEndCol &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }  
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  /** Another method to create a collage of several pictures */
  public void myCollage()
  {
      Picture topLeft = new Picture("beach.jpg");
      topLeft.zeroBlue();
      Picture topRight = new Picture("beach.jpg");
      topRight.mirrorDiagonal();
      Picture bottomLeft = new Picture("beach.jpg");
      bottomLeft.negate();      
      Picture bottomRight = new Picture("beach.jpg");
      bottomRight.grayscale();      
      
      partialCopy(topLeft,0,0,240,320,0,0);
      partialCopy(topRight,0,320,240,640,0,320);
      partialCopy(bottomLeft,240,0,480,320,240,0);
      partialCopy(bottomRight,240,320,480,640,240,320);
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel downPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color downColor = null;
    
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        downPixel = pixels[row+1][col];
        
        rightColor = rightPixel.getColor();
        downColor = downPixel.getColor();
        
        if (leftPixel.colorDistance(rightColor) > edgeDist
        || leftPixel.colorDistance (downColor) > edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
    /** Method to show large changes in color
    * works by checking if neighboring pixels are more different than would be expected for a color area
    * @param sensitivity, the amount of color change relative to normal necessary to denote an edge
    */
  public void edgeDetection2(Picture fromPic,double sensitivity)
  {
    Pixel centerPixel = null;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel upPixel = null;
    Pixel downPixel = null;
    Pixel toPixel = null;
    Pixel[][] pixels = fromPic.getPixels2D();
    Pixel[][] toPixels = this.getPixels2D();
    Color leftColor = null;
    Color rightColor = null;
    Color upColor = null;
    Color downColor = null;
    
    for (int row = 1; row < pixels.length-1; row++)
    {
      for (int col = 1; 
           col < pixels[0].length-1; col++)
      {
        centerPixel = pixels[row][col];
        leftPixel = pixels[row] [col-1];
        rightPixel = pixels[row][col+1];
        upPixel = pixels[row-1] [col];
        downPixel = pixels[row+1][col];
        
        leftColor = leftPixel.getColor();
        rightColor = rightPixel.getColor();
        upColor = upPixel.getColor();
        downColor = downPixel.getColor();
        
       boolean isEdge = edgeValue(centerPixel,leftColor,rightColor,upColor,downColor) > sensitivity;
        
       toPixel = toPixels[row][col];
       
       if(isEdge) toPixel.setColor(Color.BLACK);
       else toPixel.setColor(Color.WHITE);
      }
    }
  }
  
  private double edgeValue (Pixel center, Color left, Color right, Color up, Color down)
  {
      double[] distances  = {center.colorDistance(left),
          center.colorDistance(right),
          center.colorDistance(up),
          center.colorDistance(down)};
          
      double greatestDistance = Double.MIN_VALUE;
      
      for(double d: distances)
      {
          if(d>greatestDistance) greatestDistance = d;
      }
          
      return greatestDistance;
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
