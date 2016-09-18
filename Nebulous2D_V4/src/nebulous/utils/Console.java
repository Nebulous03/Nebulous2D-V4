package nebulous.utils;

public class Console {
	
	//TODO: Update this to control prefix
	
	public static void println(Object text){
		System.out.println("[ " + "NEBULOUS-ENGINE" + " ] " + text);
	}
	
	public static void print(Object text){
		System.out.print("[ " + "NEBULOUS-ENGINE" + " ] " + text);
	}
	
	public static void printNative(Object text){
		System.out.print(text);
	}
	
	public static void printErr(Object text){
		System.err.println("[ " + "NEBULOUS-ENGINE" + " ] " + text);
	}
	
	public static void printMOTD(String text){
		System.out.print(	
				
		"-------------------------------------------\n" +
		text + " \n" +
		"-------------------------------------------\n"
		
		);
	}
	
}
