import java.text.DecimalFormat;
import java.util.Vector;


public class Element1 {
	public double[][] array;
	public Vector<Vector  <Integer>> vector;
	public Vector<Integer> min;
	public String Xmin;
	public String Ymin;
	
	public Element1(double[][] array,Vector<Vector  <Integer>> vector){
		this.array = array;
		this.vector = vector;
		
		
	}
	
	
	public void print(){
		 for(int t = 0; t < array.length; t++){
				for (int o = 0; o < array.length; o++){
					if (array[t][o] == 99.00 || array[t][o] == 0.00 ){
						System.out.print("X" + " ");
						
					}
					else{
					DecimalFormat df = new DecimalFormat("0.000");
					System.out.print(df.format(array[t][o]).replace(",", ".") + " ");		
					}
				}
		System.out.println();
		
		
		
	}
	
	
	
	
}}
