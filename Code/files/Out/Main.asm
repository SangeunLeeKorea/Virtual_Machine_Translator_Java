// writeFunction: Main.fibonacci
(Main.fibonacci)
// set local variables to 0
@0
D=A
// push argument 0
@ARG
D=M
@0
A=A+D
D=M
@SP
AM=M+1
A=A-1
M=D
// push constant 2
@2
D=A
@SP
AM=M+1
A=A-1
M=D
// lt
@0
D=A
@R13
M=D-1
@SP
AM=M-1
D=M
A=A-1
M=M-D
@LABEL0
D;JLT
@R13
M=M+1
(LABEL0)
@R13
D=M
@SP
A=M-1
M=D
// if-goto IF_TRUE
@SP
AM=M-1
D=M
@0
D=D-A
@Main.fibonacci$IF_TRUE
D;JNE
// goto IF_FALSE
@Main.fibonacci$IF_FALSE
0;JMP
// label IF_TRUE
(Main.fibonacci$IF_TRUE)
// push argument 0
@ARG
D=M
@0
A=A+D
D=M
@SP
AM=M+1
A=A-1
M=D
// R13 = LCL, R14 = return address
@LCL
D=M
@R13
M=D
@5
A=D-A
D=M
@R14
M=D
// ARG[0]=return value
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// SP=ARG+1
@ARG
D=M
D=D+1
@SP
M=D
// restore Frame
@R13
AM=M-1
D=M
@THAT
M=D
@R13
AM=M-1
D=M
@THIS
M=D
@R13
AM=M-1
D=M
@ARG
M=D
@R13
AM=M-1
D=M
@LCL
M=D
@R14
A=M
0;JMP
// label IF_FALSE
(Main.fibonacci$IF_FALSE)
// push argument 0
@ARG
D=M
@0
A=A+D
D=M
@SP
AM=M+1
A=A-1
M=D
// push constant 2
@2
D=A
@SP
AM=M+1
A=A-1
M=D
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// push return-address 
@Main.fibonacci$ret.0
D=A
@SP
AM=M+1
A=A-1
M=D
// pushLCL
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
// pushARG
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
// pushTHIS
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
// pushTHAT
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
//ARG = SP-n-5
@1
D=A
@5
D=D+A
@SP
@D=M-D
@ARG
@M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto Main.fibonacci
@Main.fibonacci
0;JMP
(Main.fibonacci$ret.0)
// push argument 0
@ARG
D=M
@0
A=A+D
D=M
@SP
AM=M+1
A=A-1
M=D
// push constant 1
@1
D=A
@SP
AM=M+1
A=A-1
M=D
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// push return-address 
@Main.fibonacci$ret.1
D=A
@SP
AM=M+1
A=A-1
M=D
// pushLCL
@LCL
D=M
@SP
AM=M+1
A=A-1
M=D
// pushARG
@ARG
D=M
@SP
AM=M+1
A=A-1
M=D
// pushTHIS
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
// pushTHAT
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
//ARG = SP-n-5
@1
D=A
@5
D=D+A
@SP
@D=M-D
@ARG
@M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto Main.fibonacci
@Main.fibonacci
0;JMP
(Main.fibonacci$ret.1)
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
// R13 = LCL, R14 = return address
@LCL
D=M
@R13
M=D
@5
A=D-A
D=M
@R14
M=D
// ARG[0]=return value
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// SP=ARG+1
@ARG
D=M
D=D+1
@SP
M=D
// restore Frame
@R13
AM=M-1
D=M
@THAT
M=D
@R13
AM=M-1
D=M
@THIS
M=D
@R13
AM=M-1
D=M
@ARG
M=D
@R13
AM=M-1
D=M
@LCL
M=D
@R14
A=M
0;JMP
