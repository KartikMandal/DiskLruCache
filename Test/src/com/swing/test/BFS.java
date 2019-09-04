/*
 * Copyright (c) 2018
 * kamical india pvt Ltd.
 * village+PO saharda,Ps Pingla
 * Dist Paschim Medinipur
 * State WestBengal
 * Pin 721131
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the the copyright holder nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
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
import java.util.ArrayList;  
import java.util.LinkedList;  
import java.util.Queue;  

import javax.imageio.ImageIO;
/**
 * https://www.codeproject.com/Questions/1168932/I-want-convert-adjacency-matrix-to-adjanceny-list
 * @author kmandal
 *
 */
public class BFS  
{   
 private Queue<Node> queue;                              // Queue that will contain some of Nodes
 static ArrayList<Node> nodes=new ArrayList<Node>();              
 
 public BFS()  
 {  
     queue = new LinkedList<Node>();                 // Initialization of Queue  
 }  

	/**
	 * <pre>
	 * find neighbors of node using adjacency matrix
	 * if adjacency_list[i][j]==1, then nodes at index i and index j are connected
	 * The chemical Element is
	 *  <ul>
	 *   <li><code>[C<sub>2</sub>H<sub>4</sub>]</code></li> 
	 *   </ul>
	 *  Node node1 =new Node("C",2,320,320);  
	 *      Node node2 =new Node("C",2,620,320);  
	 *      Node node3 =new Node("H",1,120, 220);
	 *      Node node4 =new Node("H",1,120, 400); 
	 *      Node node5 =new Node("H",1,920, 220);  
	 *      Node node6 =new Node("H",1,920, 400);
	 *      
	 *  int adjacencyList[][] = {  
	 *      {0,1,1,1,0,0},  // Node 1: 1  
	 *      {1,0,0,0,1,1},  // Node 2 :2  
	 *      {1,0,0,0,0,0},  // Node 3: 3  
	 *      {1,0,0,0,0,0},  // Node 4: 4  
	 *      {0,1,0,0,0,0},  // Node 5: 5  
	 *      {0,1,0,0,0,0},  // Node 6: 6  
	 *       
	 *      };
	 * </pre>
	 * 
	 * @param adjacencyList
	 * @param x
	 * @return
	 */
 public ArrayList<Node> findNeighbours(int adjacencyList[][],Node x)  
 {  
     int nodeIndex=-1;  
     ArrayList<Node> neighbours=new ArrayList<Node>();  
     for (int i = 0; i < nodes.size(); i++) 
     {  
         if(nodes.get(i).equals(x))  
         {  
             nodeIndex=i;  
             break;  
         }  
     }   
     if(nodeIndex!=-1)  
     {       
         for (int j = 0; j < adjacencyList[nodeIndex].length; j++) 
         {  
             if(adjacencyList[nodeIndex][j]==1)  
             {  
                 neighbours.add(nodes.get(j));  
             }  
         }  
     }  
     return neighbours;  
 }
 
 
 final int circleSize = 20;
 final int halfSize = circleSize / 2;
 final int size = 6;
 static File file=new File("D:\\chemical\\_Captcha.png");
 /**
  * <pre>
  * Push Node in the Queue then remove it [FIFO]
  * Get Neighbours from the brevious function findNeighbours and add to Queue Nodes that only not visited
  * Printing Nodes Visited at each step and The Number of Vertex visited at each step
  * </pre>
  * @param adjacencyList
  * @param node
  */
 public  void bfs(int adjacencyList[][], Node node)  
 {  
	 BufferedImage image = new BufferedImage(1000, 900,BufferedImage.TYPE_BYTE_INDEXED);
	 Graphics2D g2 = image.createGraphics();
	 Shape circle2 = null;
	 Shape rect2 = null;
	 Shape line2 = null;
	 Shape line3 = null;
	 GradientPaint gp1 = null;
	 queue.add(node);  
     node.visited=true;  
     while (!queue.isEmpty())  
     {   
         Node element=queue.remove();  
         circle2 = new Ellipse2D.Double(element.xAxisPooint, element.yAxisPooint, circleSize, circleSize);
         rect2 = new Rectangle(element.xAxisPooint - size, element.yAxisPooint - size, circleSize + size
 				+ size, circleSize + size + size);
 		g2.draw(rect2);
 		gp1 = new GradientPaint(5, 5, Color.red, 20, 20,
				Color.WHITE, true);
		g2.setPaint(gp1);
		g2.drawString(element.atomName, element.xAxisPooint, element.yAxisPooint);
		g2.fill(circle2);
		
        ArrayList<Node> neighbours=findNeighbours(adjacencyList,element);  
         for (int i = 0; i < neighbours.size(); i++) {  
         Node nodeNeighbour=neighbours.get(i); 
         if(nodeNeighbour!=null && !nodeNeighbour.visited)  
         {  
             queue.add(nodeNeighbour); 
             circle2 = new Ellipse2D.Double(nodeNeighbour.xAxisPooint, nodeNeighbour.yAxisPooint, circleSize, circleSize);
             rect2 = new Rectangle(nodeNeighbour.xAxisPooint - size, nodeNeighbour.yAxisPooint - size, circleSize + size
     				+ size, circleSize + size + size);
             g2.draw(rect2);
             if(nodeNeighbour.lineNo==1){
     		 line2 = new Line2D.Double(element.xAxisPooint + circleSize + size,
     				element.yAxisPooint + halfSize, nodeNeighbour.xAxisPooint - size, nodeNeighbour.yAxisPooint + halfSize);
     		
     		 g2.draw(line2);
             }else{
            	 
            	  line3 = new Line2D.Double(element.xAxisPooint + circleSize + size, element.yAxisPooint + halfSize
          				- size, nodeNeighbour.xAxisPooint - size, nodeNeighbour.yAxisPooint + halfSize - size);
            	 line2 = new Line2D.Double(element.xAxisPooint + circleSize + size,
          				element.yAxisPooint + halfSize, nodeNeighbour.xAxisPooint - size, nodeNeighbour.yAxisPooint + halfSize);
          		
          		 g2.draw(line2);
          		g2.draw(line3);
             }
     		
     		gp1 = new GradientPaint(5, 5, Color.red, 20, 20,
    				Color.WHITE, true);
    		g2.setPaint(gp1);
    		g2.drawString(nodeNeighbour.atomName, nodeNeighbour.xAxisPooint, nodeNeighbour.yAxisPooint);
    		g2.fill(circle2);
             nodeNeighbour.visited=true;  
         }   
     }  
     }
     g2.dispose();
     try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");
       
 }   
 public static void main(String arg[])  
 {   
     //Graph that you want Doing BFS on it
     //Adding Nodes
	 //https://www.slideshare.net/Reihaneh_SOH/application-of-graph-theory-in-drug-design
	 //https://github.com/jagracar/grafica/blob/master/src/grafica/GPlot.java
     Node node1 =new Node("C",0,320,320);  
     Node node2 =new Node("C",2,620,320);  
     Node node3 =new Node("H",1,120, 220);
     Node node4 =new Node("H",1,120, 400); 
     Node node5 =new Node("H",1,920, 220);  
     Node node6 =new Node("H",1,920, 400);  
     Node node7 =new Node("C",2,920,320);
     nodes.add(node1);  
     nodes.add(node2);  
     nodes.add(node3);  
     nodes.add(node4);  
     nodes.add(node5);  
     nodes.add(node6);  
     nodes.add(node7);
     //AdjacencyList that compines adjacencyMatrix with edge lists
     int adjacencyList[][] = {  
     {0,1,1,1,0,0,0},  // Node 1: 1  
     {1,0,0,0,1,1,1},  // Node 2 :2  
     {1,0,0,0,0,0,0},  // Node 3: 3  
     {1,0,0,0,0,0,0},  // Node 4: 4  
     {0,1,0,0,0,0,0},  // Node 5: 5  
     {0,1,0,0,0,0,0},  // Node 6: 6  
     {0,1,0,0,0,0,0},
     };  
     BFS bfsExample = new BFS();  
     bfsExample.bfs(adjacencyList, node1);   //Calling to bfs function with root vertex 
     System.out.println();
 }  
}  
