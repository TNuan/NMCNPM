package utility;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ConfirmByPassword {

    public static String enterConfirmByPassword() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        
        panel.add(label);
        panel.add(pass);
        
        String[] options = new String[] { "OK", "Cancel" };
        int option = JOptionPane.showOptionDialog(null, panel, "Xác nhận",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
        if(option==0) // pressing OK button
        {
            char[] password = pass.getPassword();
            String rs = new String(password);
            return rs;
        }
        return null;
    }
   
}
