# ImageReader

## Introduction
This is an imagereader code, containing three java files: *ImageProcessorTest.java*, *ImplementImageIO.java*, *ImplementImageProcessor.java*.  

*ImageProcessorTest.java* is used to junit test. First I use the processor to process the photo, then compare the **width**, **height**, and **every pixel** to the answer.  

In *ImplementImageIO.java*, I implement the ImageIO. So I can use this to read a photo by reading a *.bmp* file and write ti to a *.bmp* file.  

In *ImplementImageProcessor.java*, I implement the ImageProcessor. This class can be used to extract a specific color channel in a picture. In this Processor, I can show Red channel, Green channel, Blue channel and grey respectively.
