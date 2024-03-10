package AppStart;

import javax.swing.*;

public class Minesweeper {
    private static GUIInitializer guiInitializer;
    public static void main(String[] args) {
       guiInitializer = new GUIInitializer();
        SwingUtilities.invokeLater(() -> guiInitializer.run());
    }

    public static GUIInitializer getGuiInitializer() {
        return guiInitializer;
    }
}