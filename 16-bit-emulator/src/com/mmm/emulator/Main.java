package com.mmm.emulator;

import java.io.IOException;

import com.mmm.emulator.emulate.Emulator;

public class Main {

	public static void main(String[] args) throws IOException {
		Emulator e = new Emulator();
		e.loadBootSector();
		System.out.println("Starting emulator");
		while(e.step());
		System.out.println("Stopping emulator");
	}

}
