package com.kartik.neighbour.print;

public class Node 
{
    int data;
    Node left, right, neighbor;
  
    Node(int data) 
    {
        this.data = data;
        left = right = neighbor = null;
    }
}
