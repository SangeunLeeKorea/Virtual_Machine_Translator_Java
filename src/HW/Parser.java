package HW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	
	Scanner inFile;
	String currentLine;
	String[] token = {"", "", ""};
	int numberOfToken;
	
	public Parser(String fileName) {
		try {
			inFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasMoreCommands() {
		System.out.println("\n\nhasMoreCommands --> ");
		boolean retVAl = false;
		while(inFile.hasNext()) {
			currentLine = inFile.nextLine();
			currentLine = currentLine.trim();
			System.out.println(currentLine);
			
			if (!currentLine.isEmpty()) {
				if(!((currentLine.charAt(0)=='/')&&(currentLine.charAt(1)=='/'))) {
					retVAl=true;
					break;
				}
			}
		}
		System.out.println(currentLine+" "+retVAl);
		return retVAl;
	}
	
	public void advance() {
		System.out.print("Advance --> ");
		int position=0;
		token[0]="";
		token[1]="";
		token[2]="";
		numberOfToken = 0;
		while(numberOfToken<3 && position<currentLine.length()) {
			if(currentLine.charAt(position)!=' ') {
				token[numberOfToken] += currentLine.charAt(position);
				position++;
			} else {
				numberOfToken++;
				position++;
			}
		}
	}
	
	VMTranslator.CommandType commandType(){
		System.out.print("commandType --> "+token[0]+", ");
		switch(token[0]) {
		case "add":
		case "sub":
		case "neg":
		case "eq":
		case "gt":
		case "lt":
		case "and":
		case "or":
		case "not":
			token[1] = token[0];
			return VMTranslator.CommandType.C_ARITHMETIC;
		case "push":
			return VMTranslator.CommandType.C_PUSH;
		case "pop":
			return VMTranslator.CommandType.C_POP;
		case "label":
			return VMTranslator.CommandType.C_LABEL;
		case "if-goto":
			return VMTranslator.CommandType.C_IF;
		case "goto":
			return VMTranslator.CommandType.C_GOTO;
		case "if":
			return VMTranslator.CommandType.C_IF;
		case "function":
			return VMTranslator.CommandType.C_FUNCTION;
		case "return":
			return VMTranslator.CommandType.C_RETURN;
		case "call":
			return VMTranslator.CommandType.C_CALL;
		default:
			return null;
		}
	}
	
	String arg1() {
		return token[1];
	}
	
	int arg2() {
		return getValue(token[2]);
	}
	
	String arg0() {
		return token[0];
	}
	
	private int getValue(String str) {
		int result = 0;
		if(str.charAt(0)<='9'&&str.charAt(0)>='0') {
			for(int i=0;i<str.length();i++) {
				int d=(int)(str.charAt(i)-'0');
				if(d<0 || d>9) {
					return -1;
				} else {
					result = result * 10 + d;
				}
			}
		}
		return result;
	}

}
