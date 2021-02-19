package HW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import HW.VMTranslator.CommandType;

public class CodeWriter {

	FileOutputStream outFile;
	String thisFileName;
	int labelCount;
	String currentFunctionName;
	int returnCount;

	public CodeWriter(String fileName, boolean isDirectory) {
		
		try {
			setFileName(fileName);
			outFile = new FileOutputStream(fileName);
			if (isDirectory) {
				writeInit();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setFileName(String fileName) {
		File file = new File(fileName);
		thisFileName = file.getName();
		int extPos = thisFileName.indexOf(".vm");
		if(extPos>0) {
			thisFileName = thisFileName.substring(0, extPos);
		}
		currentFunctionName = thisFileName;
		labelCount = 0;
		returnCount = 0;
	}
	
	public void writeInit() {
		String code;
		try {
			code = "//writeInit"+'\n';
			outFile.write(code.getBytes());
			
			code = "@256"+'\n';
			outFile.write(code.getBytes());
			code = "D=A"+'\n';
			outFile.write(code.getBytes());
			code = "@SP"+'\n';
			outFile.write(code.getBytes());
			code = "M=D"+'\n';
			outFile.write(code.getBytes());
			
			currentFunctionName = "writeInit";
			writeCall("Sys.init", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeArithmetic(String command) {
		try {
			String code = "// " + command + "\n";
			outFile.write(code.getBytes());
			switch (command) {
			case "add":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D+M" + '\n';
				outFile.write(code.getBytes());
				break;
			case "sub":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=M-D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "neg":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "M=-D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "eq":
				code = "@0" + '\n';
				outFile.write(code.getBytes());
				code = "D=A" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=D-1" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=M-D" + '\n';
				outFile.write(code.getBytes());

				code = "@LABEL" + labelCount + '\n';
				outFile.write(code.getBytes());
				code = "D;JEQ" + '\n';
				outFile.write(code.getBytes());

				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "(LABEL" + labelCount + ")" + '\n';
				outFile.write(code.getBytes());
				labelCount++;

				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "gt":
				code = "@0" + '\n';
				outFile.write(code.getBytes());
				code = "D=A" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=D-1" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M-D" + '\n';
				outFile.write(code.getBytes());

				code = "@LABEL" + labelCount + '\n';
				outFile.write(code.getBytes());
				code = "D;JGT" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "(LABEL" + labelCount + ")" + '\n';
				outFile.write(code.getBytes());
				labelCount++;
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;

			case "lt":
				code = "@0" + '\n';
				outFile.write(code.getBytes());
				code = "D=A" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=D-1" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=M-D" + '\n';
				outFile.write(code.getBytes());

				code = "@LABEL" + labelCount + '\n';
				outFile.write(code.getBytes());
				code = "D;JLT" + '\n';
				outFile.write(code.getBytes());

				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "(LABEL" + labelCount + ")" + '\n';
				outFile.write(code.getBytes());
				labelCount++;
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "A=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "and":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D&M" + '\n';
				outFile.write(code.getBytes());
				break;
			case "or":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D|M" + '\n';
				outFile.write(code.getBytes());
				break;
			case "not":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=!D" + '\n';
				outFile.write(code.getBytes());
				break;
			default:
				System.out.println("Error: " + command);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writePush(VMTranslator.CommandType cType, String segment, int i) {
		try {
			String code;
			code = "// push " + segment + " " + i + '\n';
			outFile.write(code.getBytes());

			switch (segment) {
			case "constant":
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "D=A" + '\n';
				outFile.write(code.getBytes());
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "local":
				code = "@LCL" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "A=A+D" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "argument":
				code = "@ARG" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "A=A+D" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "this":
				code = "@THIS" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "A=A+D" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "that":
				code = "@THAT" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "A=A+D" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "temp":
				int temp = 5 + i;
				code = "@" + temp + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "pointer":
				if (i == 0) {
					code = "@THIS" + '\n';
					outFile.write(code.getBytes());
					code = "A=M" + '\n';
					outFile.write(code.getBytes());
					code = "D=M" + '\n';
					outFile.write(code.getBytes());
				} else if (i == 1) {
					code = "@THAT" + '\n';
					outFile.write(code.getBytes());
					code = "A=M" + '\n';
					outFile.write(code.getBytes());
					code = "D=M" + '\n';
					outFile.write(code.getBytes());
				} else {
					System.out.println("Source Code Error!");
					return;
				}
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "static":
				code = "@" + getName(thisFileName) + "." + i + '\n';
				outFile.write(code.getBytes());
				System.out.println("variable name = " + code);
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			default:
				System.out.println("Error: " + segment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getName(String fullName) {
		int i;
		String s;
		i = fullName.indexOf('.');
		s = "";
		int j = i - 1;
		while (fullName.charAt(j) != '\\' && j > 0) {
			s = fullName.charAt(j) + s;
			j--;
		}
		return s;
	}

	void writePop(VMTranslator.CommandType cType, String segment, int i) {
		String code;
		try {
			code = "// pop " + segment + " " + i + '\n';
			outFile.write(code.getBytes());

			switch (segment) {
			case "local":
				code = "@LCL" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "D=D+A" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "A=M" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "argument":
				code = "@ARG" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "A=M" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "this":
				code = "@THIS" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "D=D+A" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "A=M" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "that":
				code = "@THAT" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "D=D+A" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());

				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@R13" + '\n';
				outFile.write(code.getBytes());
				code = "A=M" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "temp":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());
				code = "@" + i + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
				break;
			case "pointer":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				if (i == 0) {
					code = "@THIS" + '\n';
					outFile.write(code.getBytes());
					code = "A=M" + '\n';
					outFile.write(code.getBytes());
					code = "M=D" + '\n';
					outFile.write(code.getBytes());
				} else if (i == 1) {
					code = "@THAT" + '\n';
					outFile.write(code.getBytes());
					code = "A=M" + '\n';
					outFile.write(code.getBytes());
					code = "M=D" + '\n';
					outFile.write(code.getBytes());
				} else {
					System.out.println("Source Code Error!");
					return;
				}
				break;
			case "static":
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M-1" + '\n';
				outFile.write(code.getBytes());
				code = "D=M" + '\n';
				outFile.write(code.getBytes());

				code = "@" + getName(thisFileName) + "." + i + '\n';
				outFile.write(code.getBytes());
				System.out.println("variable name = " + code);
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
			default:
				System.out.println("Error: " + segment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void close() {
		try {
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeGoto(CommandType myType, String label, String arg) {
		String code;
		try {
			code = "// goto " + label + '\n';
			outFile.write(code.getBytes());
			
			code = "@" + currentFunctionName+"$"+label + '\n';
			outFile.write(code.getBytes());
			code = "0;JMP" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeIf(String label) {
		String code;
		try {
			code = "// if-goto " + label + '\n';
			outFile.write(code.getBytes());
			
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "AM=M-1" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@0" + '\n';
			outFile.write(code.getBytes());
			code = "D=D-A" + '\n';
			outFile.write(code.getBytes());
			
			code = "@" + currentFunctionName+"$"+label + '\n';
			outFile.write(code.getBytes());
			code = "D;JNE" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLabel(CommandType myType, String label) {
		String code;
		try {
			code = "// label " + label + '\n';
			outFile.write(code.getBytes());

			code = "(" + currentFunctionName+"$"+label + ")" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCall(String funcName, int nArgs) {
		String code;
		try {
			code = "// push return-address " + '\n';
			outFile.write(code.getBytes());
			String retLabel = currentFunctionName + "$ret."+ returnCount;
			returnCount++;
			code = "@"+retLabel + '\n';
			outFile.write(code.getBytes());
			code = "D=A" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "AM=M+1" + '\n';
			outFile.write(code.getBytes());
			code = "A=A-1" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			saveFrame("LCL");
			saveFrame("ARG");
			saveFrame("THIS");
			saveFrame("THAT");
			
			code = "//ARG = SP-n-5" + '\n';
			outFile.write(code.getBytes());
			code = "@"+nArgs + '\n';
			outFile.write(code.getBytes());
			code = "D=A" + '\n';
			outFile.write(code.getBytes());
			code = "@5" + '\n';
			outFile.write(code.getBytes());
			code = "D=D+A" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "@D=M-D" + '\n';
			outFile.write(code.getBytes());
			code = "@ARG" + '\n';
			outFile.write(code.getBytes());
			code = "@M=D" + '\n';
			outFile.write(code.getBytes());
			
			code = "// LCL = SP" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@LCL" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			
			code = "// goto "+funcName + '\n';
			outFile.write(code.getBytes());
			code = "@"+funcName + '\n';
			outFile.write(code.getBytes());
			code = "0;JMP" + '\n';
			outFile.write(code.getBytes());
			code = "("+retLabel+")" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFrame(String string) {
		String code;
		try {
			code = "// push" +string+ '\n';
			outFile.write(code.getBytes());
			
			code = "@"+string + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "AM=M+1" + '\n';
			outFile.write(code.getBytes());
			code = "A=A-1" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFunction(String funcName, int nVars) {
		String code;
		try {
			code = "// writeFunction: "+ funcName + '\n';
			outFile.write(code.getBytes());
			
			code = "("+ funcName + ")"+'\n';
			outFile.write(code.getBytes());
			code = "// set local variables to 0" + '\n';
			outFile.write(code.getBytes());
			code = "@0" + '\n';
			outFile.write(code.getBytes());
			code = "D=A" + '\n';
			outFile.write(code.getBytes());
			for (int i=0;i<nVars;i++) {
				code = "@SP" + '\n';
				outFile.write(code.getBytes());
				code = "AM=M+1" + '\n';
				outFile.write(code.getBytes());
				code = "A=A-1" + '\n';
				outFile.write(code.getBytes());
				code = "M=D" + '\n';
				outFile.write(code.getBytes());
			}
			currentFunctionName = funcName;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeReturn(CommandType myType) {
		String code;
		try {
			code = "// R13 = LCL, R14 = return address" + '\n';
			outFile.write(code.getBytes());
			code = "@LCL" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@R13" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			code = "@5" + '\n';
			outFile.write(code.getBytes());
			code = "A=D-A" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@R14" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			
			code = "// ARG[0]=return value" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "AM=M-1" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@ARG" + '\n';
			outFile.write(code.getBytes());
			code = "A=M" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			
			code = "// SP=ARG+1" + '\n';
			outFile.write(code.getBytes());
			code = "@ARG" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "D=D+1" + '\n';
			outFile.write(code.getBytes());
			code = "@SP" + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
			
			code = "// restore Frame" + '\n';
			outFile.write(code.getBytes());
			restoreFrame("THAT");
			restoreFrame("THIS");
			restoreFrame("ARG");
			restoreFrame("LCL");
			
			code = "@R14" + '\n';
			outFile.write(code.getBytes());
			code = "A=M" + '\n';
			outFile.write(code.getBytes());
			code = "0;JMP" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void restoreFrame(String string) {
		String code;
		try {
			code = "@R13" + '\n';
			outFile.write(code.getBytes());
			code = "AM=M-1" + '\n';
			outFile.write(code.getBytes());
			code = "D=M" + '\n';
			outFile.write(code.getBytes());
			code = "@"+string + '\n';
			outFile.write(code.getBytes());
			code = "M=D" + '\n';
			outFile.write(code.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
