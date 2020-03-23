import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;


public class Simple_Graph {
static int globMatrix[][];
	public static void main(String[] args) throws FileNotFoundException {
		//Read file from user input
		Scanner file = new Scanner(System.in);
		System.out.print("Please enter the full file directory path: ");
		
		//ensure that the user input a file that exists and read it.
		try {
			String filename = file.nextLine();
			File inputFile = new File(filename);
			Scanner sc = new Scanner(inputFile);
		
			//Create Placeholder array that the data will be read into
			ArrayList<Integer> holderArray = new ArrayList<Integer>();
		
			//While the file contains integers add them to the ArrayList created above
			while(sc.hasNextInt()) {
				int temp = sc.nextInt();
				holderArray.add(temp);
			}//end while

			//close file
			//file.close();
			sc.close();
		
			int index = 0; //variable to keep track of current index in the ArrayList
		
			int vertices = holderArray.get(index); //variable for the number of vertices in the graph
		
			int matrix[][] = new int [vertices][vertices]; //create the two dim array
			globMatrix = matrix;
		
			//Insert data from the ArrayList into a two dim array not including the vertices variable
			for(int i = 0; i < vertices; i++) {
				for(int j = 0; j< vertices; j++) {
					matrix[i][j] = holderArray.get(index+1);
					index++;
				}//end inner-for
			}//end outer-for
		
			boolean simpleGraph = true; //variable to hold value of the graph as either simple or not
		
			//Definition of a simple graph does not allow for vertices to create an edge from it to itself
			//Create loop to check that the vertices do not create an edge from it to itself
			for(int k = 0; k < vertices; k++){
				if(matrix[k][k] != 0) {
					simpleGraph = false;
				}//end if
			}//end for
		
			//Definition of a simple graph does not allow for multiple edges connecting the same vertices
			//Create loop to check that the vertices are at most connected to other vertices once
			for(int l = 0; l < vertices; l++) {
				for(int m = 0; m < vertices; m++) {
					if(matrix[l][m] > 1) {
						simpleGraph = false;
					}//end if
				}//end inner-for
			}//end outer-for
		
			//Print to user result of text file
			System.out.println("Simple Graph: "+ simpleGraph);
		
		}//end try
		
		//if the file does not exist catch it and output exception statement
		catch(FileNotFoundException fe) {
			System.out.println("File not found, check your input for correct path");
		}//end catch
		
		/*
		// PART 2 BELOW
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter the points you want to check for maximum complexity (seperate points with ',')");
		String input = null;
		input = file.next();
		//input = k.nextLine();
		//split the input into the correct labels
		String[] values = input.split(",");
		//create temp array to play with and to not effect the original array
		String[] tempValues = new String[values.length];
		//summation is where values are added to give future values a place to reference to (to see if something directly connected to the value in question is already there)
		ArrayList <String>summation =new ArrayList<String>();;
		//connections tell us what the value is actually connected to. this is run against the summations list
		ArrayList <String> connections = new ArrayList<String>();
		//sum keeps track of the total component calculated. it will always be a min of 1. (user cannot input nothing)
		int sum=1;
		
		String[] availablePoints = new String[globMatrix[0].length];//gives a list of all available points in the (a,b,c,etc) format
		for (int j=0; j<globMatrix[0].length;j++) {
			availablePoints[j] = (Character.toString((char) (j + 97)));
		}//end of for loop
		
		
		//copy values over to tempValues
		for (int i =0;i<values.length;i++) {
			tempValues[i]=values[i];
		}//end of for loop
		
		for (int i=0;i<availablePoints.length;i++) {
			for (int j=0;j<tempValues.length;j++) {
				if(availablePoints[i]!=null && availablePoints[i].equals(tempValues[j])) {
					availablePoints[i]=null;
				}//end of if statement
			}//end of nested if
		}//end of main for loop
		
		
		
		while (availablePoints != null) {
			int x;
			for (x=0;x<availablePoints.length;x++) {
				if (availablePoints[x] !=null) {
					x=availablePoints.length +2;
				}
			}//end of for loop
			
			for (int i =0;i<availablePoints.length;i++) {
				if (availablePoints[i]!=null) {
					//convert the character to a number based on the Ascii value. a=0, b=1, c=2 etc
					int num = ((int) availablePoints[i].charAt(0)-97) ;
					for (int j=0; j<globMatrix[0].length;j++) {
						if(globMatrix[num][j]==1){
							//if a connection is found, add it to the connection list for future comparison
							int target = j+97;
							connections.add(Character.toString((char) target));
						}//end of if statement
					}//end of for loop
					if (summation.isEmpty()&&(availablePoints[i]!=null)) {
						//if summation is empty, add the first point chosen
						summation.add(availablePoints[i]);
						availablePoints[i]=null;
					}//end of if statement
					else if (!Collections.disjoint(summation, connections)){
							//summation.contains(connections)) {
						summation.add(availablePoints[i]);
						availablePoints[i]=null;
					}//end of else if statement
				}//end of if statement
				//remove everything out of connections since this will repeat with the next point
				connections.removeAll(connections);
			}//end of for loop
			if (summation.size()>= sum) {
			//if summArray is bigger than the current sum, change sum to the new value as that means a higher component was found
			sum = summation.size();
			summation.removeAll(summation);
			}//end of if statement
			else {
				summation.removeAll(summation);
			}
			if (x==availablePoints.length) {
				availablePoints = null;
			}
		}//end of while loop
		System.out.println("The maximum component given is " + sum);
		*/
		
		
		//BEGIN PART 3 BELOW
		ArrayList <String> allPoints = new ArrayList<String>();//array list to hold a value of all the points alphabetically. need this to compare points too
		ArrayList[] finalResult = new ArrayList[globMatrix.length];//array of array lists. Each index holds an array for each of the levels of a tree
		ArrayList<String> connections = new ArrayList<String>();//array list to hold all the connections of a point
		//init the array of array lists with empty array lists
		for (int i=0;i<finalResult.length;i++) {
			finalResult[i] = new ArrayList<String>();
		}//end of for loop
		//loop gives an array of all the points in the graph as lowercase letters
		for (int i=0;i<globMatrix[0].length;i++) {
			allPoints.add(Character.toString((char)(i+97)));
		}//end of for loop
		//check for 'a' incase graph is empty (not needed really)
		if (allPoints.contains("a")) {
			finalResult[0].add("a");
			allPoints.remove("a");
		}//end of if statement
		//go through finalResult (each arrayList) and check all the points connections
		for (int a=0; a<finalResult.length;a++) {
			//create an iterator to go through array list
			Iterator check = finalResult[a].iterator();
			while (check.hasNext()) {
				String main = (String) check.next();
				int target = ((int) main.charAt(0)-97);
				//convert into number
				//go through the graph and check for connections ( have to make sure that allPoints has the point) that means we have not already added the point
				for (int i=0;i<globMatrix[target].length;i++) {
					if (globMatrix[target][i] == 1) {
						if (allPoints.contains(Character.toString((char)(i+97)))) {
							connections.add(Character.toString((char)(i+97)));
							finalResult[a+1].add(Character.toString((char)(i+97)));
							allPoints.remove((Character.toString((char)(i+97))));
						}//end of nested If
					}//end of outer if statement
				}//end of nested for loop
			}//end of nested while loop
		}//end of main for loop
		//go through the finalResult and make print all the results (checks to make sure a level has points too)
		for (int i=0;i<finalResult.length;i++) {
			Iterator print = finalResult[i].iterator();
			if (!finalResult[i].isEmpty()) {
				String temp;
				System.out.println("The following points are " + i + " steps away from the first point.");
				while(print.hasNext()) {
					temp = (String) print.next();
					System.out.println(temp.toString());
				}//end of while loop
			}
		}//end of for loop
		file.close();
		//k.close();
		

		
	}//end main
}//end class