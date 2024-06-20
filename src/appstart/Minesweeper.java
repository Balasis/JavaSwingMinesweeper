package appstart;

import gui.GUIInitializer;

import javax.swing.*;

public class Minesweeper {//project reformatted with intellij
    public static void main(String[] args) {
        GUIInitializer guiInitializer = new GUIInitializer();
        SwingUtilities.invokeLater(() -> guiInitializer.run());
    }

}