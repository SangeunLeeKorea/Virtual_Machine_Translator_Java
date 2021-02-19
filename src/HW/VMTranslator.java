package HW;

import java.io.File;

public class VMTranslator {
	
	static enum CommandType{
		C_ARITHMETIC, C_PUSH, C_POP, C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL;
	}
	static String currentCommand;
	
	static String fileN = "Main";
	static String IFile = "files//In//"+fileN+".vm";
	static String OFile = "files//Out//"+fileN+".asm";
	
	public static void main(String[] args) {
		System.out.println("From "+IFile+"\n To ---> "+OFile);
		Parser myP = new Parser(IFile);
		CodeWriter myCW = new CodeWriter(OFile, new File(OFile).isDirectory());
		while(myP.hasMoreCommands()) {
			myP.advance();
			CommandType myType = myP.commandType();
			System.out.print(myType);
			if(myType==null) {
				System.out.println("Wrong command is given..");
				break;
			}
			
			switch(myType) {
			case C_ARITHMETIC:
				myCW.writeArithmetic(myP.arg1());
				break;
			case C_PUSH:
				myCW.writePush(myType, myP.arg1(), myP.arg2());
				break;
			case C_POP:
				myCW.writePop(myType, myP.arg1(), myP.arg2());
				break;
			case C_GOTO:
				myCW.writeGoto(myType, myP.arg1(), myP.arg0());
				break;
			case C_IF:
				myCW.writeIf(myP.arg1());
				break;
			case C_LABEL:
				myCW.writeLabel(myType, myP.arg1());
				break;
			case C_CALL:
				myCW.writeCall(myP.arg1(), myP.arg2());
				break;
			case C_FUNCTION:
				myCW.writeFunction(myP.arg1(), myP.arg2());
				break;
			case C_RETURN:
				myCW.writeReturn(myType);
				break;
			default:
				break;
			}
		}
		myCW.close();
	}

}
