package com.corejava.binarySearch;

 

import java.util.Scanner;

 

public class BinarySearch {

 

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("enter the size of array");

        int arraySize = scan.nextInt();

        int [] numbers = new int[arraySize];

        for(int i=0 ; i< arraySize ; i++) {

            System.out.println("enter the "+(i+1)+"st element : ");

            numbers[i] = scan.nextInt();

        }

        System.out.println("enter the element to find");

        int numberToFind = scan.nextInt();

        

        for(int i =0; i<numbers.length;i++) {

            for(int j=i; j< numbers.length; j++) {

                if(numbers[j]<numbers[i]) {

                    

                    numbers[i] = numbers[i]+numbers[j];

                    numbers[j] = numbers[i]-numbers[j];

                    numbers[i] = numbers[i]-numbers[j];

                }

            }

        }

        int elementIndex = binarySearch(numbers, numberToFind);

        if(elementIndex >=0) {

            System.out.println("element sorted and found in index "+elementIndex);

        }

        else {

            System.out.println("given element is not an part of array");

        }

        scan.close();

 

    }

    

    public static int binarySearch(int [] numbers, int numberToFind) {

        int low =0;

        int high = numbers.length-1;

        

        while(low <= high) {

            int middlePosition =(low + high)/2;

            int middleNumber = numbers[middlePosition];

            

            if(numberToFind == middleNumber) {

                return middlePosition;

            }

            if(numberToFind < middleNumber) {

                high = middlePosition - 1;

            }else {

                low = middlePosition + 1;

            }

        }

        return -1;

            

            

        }

    }

 
