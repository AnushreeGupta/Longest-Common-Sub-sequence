import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LCS_Anushree_Gupta_agupta38 {
	
	final static String input = "input.txt";
	
	//Declaring 2D - array for storing the opt values
	int[][] opt = new int[1001][1001];
	
	//Arrays for the two input texts
	char [] A = new char[1001];
	char [] B = new char[1001];
	
	//Lengths of array A and B
	int lengthA, lengthB = 0;

	public static void main(String[] args) throws IOException {
		
		LCS_Anushree_Gupta_agupta38 L = new LCS_Anushree_Gupta_agupta38();
		
		//Reading the input.txt file
	    File query = new File(input); 
		BufferedReader bufferReader = new BufferedReader(new FileReader(query));
		
		//Create a file and write to output.txt
	     File output = new File("output.txt");
	     BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		        
		L.getInput(bufferReader);
		L.getopt();
		L.findlcs(writer);
		
		bufferReader.close();
		writer.close();

	}
	
	void getInput(BufferedReader reader) throws IOException {
		
		String line1;
		String line2;
		
		if(reader.ready())
		{ 
			line1 = reader.readLine();
		}
		else
		{
			line1 = "";
		}
		
		if(reader.ready())
		{ 
			line2 = reader.readLine();
		}
		else
		{
			line2 = "";
		}
		
		lengthA = line1.length();
		lengthB = line2.length();
			
		System.out.println("String 1: "+line1);
		System.out.println("String 2: "+line2);
		
		//A and B are indexed as such that they start from 1 rather than 0  
		int j = 1; int k = 1;
		
		for(int i = 0; i < lengthA; i++)
		{
			A[j] = line1.charAt(i);
			j++;
		}
		
		for(int i = 0 ; i < lengthB; i++)
		{
			B[k] = line2.charAt(i);
			k++;
		}
		//System.out.println(A[lengthA]);
		//System.out.println(B[lengthB]);			
	}
	
	void getopt()
	{
		for(int i = 0; i < lengthA; i++)
		{
			opt[0][i] = 0;
		}
		
		for(int i = 1; i <= lengthA; i++)
		{	
			opt[i][0] = 0;
			
			for(int j = 1; j <= lengthB; j++)
			{
				if(A[i] == B[j])
					{ opt[i][j] = opt[i-1][j-1] + 1; }
				
				else if(opt[i][j-1] >= opt[i-1][j])
					{ opt[i][j] = opt[i][j-1]; }
				
				else
					{ opt[i][j] = opt[i-1][j]; }
			}
		}
		
		/**  For printing LCS to console
		for(int i = 0; i <= lengthA; i++)
		{	
			for(int j = 0; j <= lengthB; j++)
			{ 	System.out.print(opt[i][j]+ " ");
			}
			System.out.println(" ");
		} **/
		
	}
	
	void findlcs(BufferedWriter writer) throws IOException
	{
		writer.write(opt[lengthA][lengthB] + "\n");
	
		// Character array to store the LCS
		int index = opt[lengthA][lengthB];
		char [] lcs = new char[index];
		 
		int i = lengthA;
		int j = lengthB;
		
		while (i > 0 && j > 0)
		{
			//If the current character in A[] and B[] are same, then current character is added to lcs
			if (A[i] == B[j])
		      {	  
		          lcs[index-1] = A[i]; 
		          i--; 
		          j--; 
		          index--;    
		      }
		 
		      // If not same, then find the larger of two and go in the direction of larger value
		      else if (opt[i-1][j] > opt[i][j-1])
		         i--;
		      else
		         j--;
		 }
		System.out.print("Final LCS: ");
		System.out.println(lcs);
		writer.write(lcs);	
	}
	
}
