package com.swing.test;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * http://zetcode.com/gfx/java2d/shapesandfills/
 * http://ankit.co/tutorials/java-tutorials/applet/shapes-and-colors-in-applet
 * https://www.mkyong.com/awt/java-awt-drawing-rectangle-line-and-circle/
 * @author kmandal
 *
 */
public class DrawChemicalElementShapes extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void createCaptchaImage(File file) {
		final int circleSize = 20;
		final int halfSize = circleSize / 2;
		final int size = 6;

		BufferedImage image = new BufferedImage(1000, 900,
				BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D g2 = image.createGraphics();
		// Shape line = new Line2D.Double(3, 3, 303, 303);
		// Shape rect = new Rectangle(3, 3, 303, 303);
		// Shape circle = new Ellipse2D.Double(100, 100, 100, 100);
		// Shape roundRect = new RoundRectangle2D.Double(20, 20, 250, 250, 5,
		// 25);

		Shape circle1 = new Ellipse2D.Double(320, 320, circleSize, circleSize);
		Shape rect1 = new Rectangle(320 - size, 320 - size, circleSize + size
				+ size, circleSize + size + size);

		Shape circle2 = new Ellipse2D.Double(620, 320, circleSize, circleSize);
		Shape rect2 = new Rectangle(620 - size, 320 - size, circleSize + size
				+ size, circleSize + size + size);

		Shape line2 = new Line2D.Double(320 + circleSize + size,
				320 + halfSize, 620 - size, 320 + halfSize);

		Shape circle3 = new Ellipse2D.Double(120, 220, circleSize, circleSize);
		Shape rect3 = new Rectangle(120 - size, 220 - size, circleSize + size
				+ size, circleSize + size + size);
		// this is angle wise
		Shape line3 = new Line2D.Double(120 + circleSize + size,
				220 + halfSize, 320 - size, 320 + halfSize - size);
	//	Shape line8 = new Line2D.Double(120 + circleSize + size, 220 + halfSize
		//		+ size, 320 - size, 320 + halfSize);
		// this is double line
		Shape line7 = new Line2D.Double(320 + circleSize + size, 320 + halfSize
				- size, 620 - size, 320 + halfSize - size);

		Shape circle4 = new Ellipse2D.Double(920, 220, circleSize, circleSize);
		Shape rect4 = new Rectangle(920 - size, 220 - size, circleSize + size
				+ size, circleSize + size + size);
		Shape line4 = new Line2D.Double(620 + circleSize + size,
				320 + halfSize, 920 - size, 220 + halfSize);

		// 2nd circle
		Shape circle5 = new Ellipse2D.Double(120, 400, circleSize, circleSize);
		Shape rect5 = new Rectangle(120 - size, 400 - size, circleSize + size
				+ size, circleSize + size + size);
		Shape line5 = new Line2D.Double(120 + circleSize + size,
				400 + halfSize, 320 - size, 320 + halfSize);

		Shape circle6 = new Ellipse2D.Double(920, 400, circleSize, circleSize);
		Shape rect6 = new Rectangle(920 - size, 400 - size, circleSize + size
				+ size, circleSize + size + size);
		Shape line6 = new Line2D.Double(620 + circleSize + size,
				320 + halfSize, 920 - size, 400 + halfSize);
		// g2.draw(line);
		g2.draw(rect1);
		g2.draw(rect2);
		g2.draw(rect3);
		g2.draw(line2);
		g2.draw(line3);
		g2.draw(rect4);
		// g2.draw(line4);
		g2.draw(line4);
		g2.draw(line7);
		g2.draw(rect5);
		g2.draw(line5);
		g2.draw(rect6);
		g2.draw(line6);
		// g2.setColor(Color.red);
		GradientPaint gp1 = new GradientPaint(5, 5, Color.red, 20, 20,
				Color.WHITE, true);
		g2.setPaint(gp1);
		g2.drawString("H", 120, 220);
		g2.fill(circle3);
		// g2.draw(circle1);
		g2.drawString("H", 920, 220);
		g2.fill(circle4);
		g2.drawString("C", 320, 320);
		g2.fill(circle1);
		g2.drawString("C", 620, 320);
		g2.fill(circle2);
		g2.drawString("H", 920, 400);
		g2.fill(circle5);
		g2.drawString("H", 120, 400);
		g2.fill(circle6);
		// g2.draw(roundRect);
		g2.dispose();

		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}


	public static void main(String arg[]) {

		File file=null;
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 // try {
		   /*file = File
		     .createTempFile(timestamp.getTime() + "_Captcha", ".png");*/
		   file = new File("D:\\chemical\\_Captcha.png");
		 /* } catch (IOException e) {
		   e.printStackTrace();
		  }*/
		createCaptchaImage(file);
	}

}