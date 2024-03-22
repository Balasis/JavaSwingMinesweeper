package appstart;

import gui.GUIInitializer;

import javax.swing.*;

public class Minesweeper {
    public static void main(String[] args) {
        GUIInitializer guiInitializer = new GUIInitializer();
        SwingUtilities.invokeLater(() -> guiInitializer.run());
    }

}