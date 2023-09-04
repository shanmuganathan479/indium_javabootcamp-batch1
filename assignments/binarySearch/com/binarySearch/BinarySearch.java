package com.corejava.binarySearch.com.binarySearch;

import java.util.Scanner;
public class BinarySearch {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the size of array");

        int arraySize = scan.nextInt();

        int [] numbers = new int[arraySize];

        for(int i=0 ; i< arraySize ; i++) {

            System.out.println("Enter the "+(i+1)+"st element : ");

            numbers[i] = scan.nextInt();

        }

        System.out.println("Which element you want to find");

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

            System.out.println("The element in "+elementIndex+ "index position");

        }

        else {

            System.out.println("Not found");

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