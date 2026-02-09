package view;

import javax.swing.*;
import java.awt.*;

public class ViewUtils {
    public static int showDialog(Component parent, JPanel panel, String title) {
        // Show the dialog using JOptionPane.showConfirmDialog()
        return JOptionPane.showConfirmDialog(
                parent,                       // Parent component (null for screen center)
                panel,                      // The message/component to display
                title,        // Dialog title
                JOptionPane.OK_CANCEL_OPTION, // Option type (OK/Cancel buttons)
                JOptionPane.PLAIN_MESSAGE   // Message type (no specific icon)
        );
    }

}
