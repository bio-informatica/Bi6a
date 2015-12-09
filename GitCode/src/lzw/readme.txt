Simple Text File Compressor/Decompressor Readme File
Copyright (C) 2001, Cheok Yan Cheng
yccheok@yahoo.com
http://www.geocities.com/yccheok/lzw/lzw.html

Description
===========
This is a simple java standlone application program which can compress and decompress the ASCII text file. (The text file which usually have *.txt extension). The detail on LZW method used in this program can be found at the author web site http://www.geocities.com/yccheok/lzw/lzw.html. It is note that this compressor/decompressor can be only used on ASCII text file and not binary file.

System Requirement
==================
This programe can be run in any platform that installed with JRE (Java Runtime Enviroment) which can be downloaded from http://java.sun.com

Copyright/Disclaims
===================
This software is provided AS IS, WITHOUT WARRANTY OF ANY KIND.  THE AUTHOR OF THIS SOFTWARE DISCLAIMS ALL WARRANTIES OF ANY KIND.  IN NO EVENT SHALL THE AUTHOR OF THIS SOFTWARE BE LIABLE FOR ANY DAMAGES WHATSOEVER INCLUDING DIRECT, INDIRECT, INCIDENTAL, CONSEQUENTIAL LOSS OF BUSINESS PROFITS OR SPECIAL DAMAGES, EVEN IF THE AUTHOR OF THIS SOFTWARE HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.

Registration
==================
No registration is needed. You are freely to modify and distribute the source code and binary files for your own use.  


Quick Start
===================
1. Unzip lzw_j.zip which contains
	Compress.class	(Compressor in Java Byte Code)
	Decompress.class(Decompressor in Java Byte Code)
	Element.class	(Data structure file which is needed during the
			 execution of Decompressor program)
	file.txt	(sample text file)
	readme.txt      (The file that you are reading now)
	Compress.java	(Source code)
	Decompress.java	(Source code)
	Element.java	(Source code)

2. To compress the sample text file, use "jre -cp <path where you locate the Compress.class> Compress file.txt". A new file named file.txt.zzz will be created. 

3. To decompress the compressed file, use "jre -cp <path where you locate the Decompress.class> Decompress file.txt.zzz". A new file named file.txt will be created. 

Bug Report/Feedback
===================
Please email bug report or feedback to yccheok@yahoo.com 