package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import controller.ButtonController;
//import model.UpdateAction;

public class ButtonPanel extends JPanel {
    JButton btnTrain, btnCheck, btnExit;   // Used buttons.
    JCheckBox cbHelp;               // Used check box.
    ButtonGroup bgNumbers;          // Group for grouping the toggle buttons.
    JToggleButton[] btnNumbers;     // Used toggle buttons.

    
    public ButtonPanel() {
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlAlign.add(pnlOptions);

        btnTrain = new JButton("Train");
        btnTrain.setFocusable(false);
        pnlOptions.add(btnTrain);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);
    }
    /*
    public void update(Observable o, Object arg) {
        switch ((UpdateAction)arg) {
            case NEW_GAME:
            case CHECK:
            	if(bgNumbers != null)
            		bgNumbers.clearSelection();
                break;
            default:
            	break;
        }
    }
    */
    public void setController(ButtonController buttonController) {
        btnTrain.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
    }
}