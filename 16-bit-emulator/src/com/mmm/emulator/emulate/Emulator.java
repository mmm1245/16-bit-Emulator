package com.mmm.emulator.emulate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Emulator {
	public char[] memory;
	
	public char RIP = 0;
	public char RSP  = 1000;
	public char RA  = 0;
	public char RB  = 0;
	public char RC  = 0;
	public char RAL  = 0;
	public char RS1  = 0;
	public char RS2  = 0;
	
	public boolean halted = false;
	
	
	public Emulator() {
		memory = new char[65535];
		for(int i = 0;i < 65535;i++) {
			memory[i] = 0;
		}
	}
	
	public void loadBootSector() throws IOException {
		File directory = new File(new File(".").getCanonicalFile().getPath() + File.separatorChar + "disc");
		if(!directory.exists())
			directory.mkdirs();
		File file = new File(new File(".").getCanonicalFile().getPath() + File.separatorChar + "disc" + File.separatorChar + "sector0");
		System.out.println("loading bootsector from " + file.getAbsolutePath());
		if(!file.exists()) {
			file.createNewFile();
			return;
		}
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		System.out.println("reading data...");
		for(int i = 0;i < 1000;i++) {
			int data = fileReader.read();
			if(data == -1) {
				System.out.println();
				return;
			}
			memory[i] = (char) data;
			System.out.print(data);
		}
		System.out.println();
	}
	
	public boolean step() {
		char command = memory[RIP];
		switch (command) {
		case 0:
			halted = true;
			return false;
		case 1:
			System.out.println(memory[RIP+1]);
			RIP += 1;
			break;

		default:
			System.out.println("Unknown op-code: 0x" + Integer.toHexString(command));
			break;
		}
		RIP += 1;
		return !halted;
	}
}
