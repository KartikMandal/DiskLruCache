package com.kartik.gause;

import java.io.*;
public class Gauss_Elimination {
    public static void main(String args[])throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("No of variables to be dealt with ::");
        int n=Integer.parseInt(br.readLine());
        //double v[][]=new double[n][n+1];
        
        double v[][]={
            { 1.0, 3.0, -2.0, 5.0 }, 
            { 3.0, 5.0, 6.0, 7.0  }, 
            { 2.0, 4.0, 3.0, 8.0  }, 
        };
        /*int i,j;
        for(i=0;i<n;i++)
        {
            System.out.println("Equation ::"+(i+1)+"\n");
            for(j=0;j<n;j++)
            {
                System.out.println("Enter the coefficient of variable ::"+(j+1));
                v[i][j]=Double.parseDouble(br.readLine());
            }
            System.out.println("\nEnter the value of the right hand side of equation ::"+(i+1));
            v[i][j]=Double.parseDouble(br.readLine());
        }*/
        gauss(v,n,n+1);
    }
    public static void gauss(double v[][],int r,int c)
    {
        double t[]=new double[c];
        int i=0,j=0,k=0,m=0,n=0,x=0;
        double temp=0;
        for(i=0;i<r-1;i++)
        {
            temp=v[i][i];
            for(j=0;j<c;j++)
            {
                t[j]=v[i][j]/temp;
                v[i][j]=t[j];
            }
            k=0;
            for(m=i+1;m<r;m++)
            {
                temp=v[m][x];
                for(n=0;n<c;n++)
                {
                    v[m][n]=v[m][n]-(t[n]*temp);
                }
            }
            for(k=0;k<c;k++)
                t[k]=0;
            temp=0;
            x++;
        }
        for(i=r-1;i>0;i--)
        {
            v[i][c-1]=v[i][c-1]/v[i][i];
            v[i][i]=0;
            for(j=i-1;j>=0;j--)
            {
                v[j][c-1]=v[j][c-1]-(v[j][i]*v[i][c-1]);
                v[j][i]=0;
            }
        }
        System.out.println();
        for(i=0;i<r;i++)
        {
         System.out.println("The value of term "+(i+1)+" is "+v[i][c-1]);
        }
    }
}
