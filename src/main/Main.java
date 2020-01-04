package main;

import java.awt.EventQueue;

import scrappers.ui.Application;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
            final Application app = new Application();
            app.setVisible(true);
        });
	}

}
