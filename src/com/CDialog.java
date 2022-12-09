package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by jim2 on 4/10/2017.
 */
public class CDialog extends JDialog implements ActionListener, PropertyChangeListener {
    private String typedText = null;
    private JTextField textField;
    private JOptionPane optionPane;
    private boolean yesNoCancel = false;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    private String btnString3 = "Cancel";
    private String msgString1;
    private String msgString2;
    private char _BS = 0x08;
    private boolean _backSpaceFound=false;
    private boolean _rightBracketFound=false;
    private char _CTRL_RBRK = 0x1d;
    private int _timerSeconds = 0;

    /**
     * Returns null if the typed string was invalid;
     * otherwise, returns the string as the user entered it.
     */
    public String getValidatedText() {
//        if(Register.get().getRegister().getUseAsKiosk())
//            return box.getText();
//        else
        return typedText;
    }

    public void setMessages(String MsgString1, String MsgString2) {
//        if(Register.get().getRegister().getUseAsKiosk()){
//            box = EKiosk.getInstance();
//            box.SmallMessageSetup(MsgString1+MsgString2,null,null,null,true, KioskScreen);
//        }
        this.msgString1 = MsgString1;
        this.msgString2 = MsgString2;
        yesNoCancel = false;
    }

    public void setMessages(String MsgString1, String MsgString2, String BtnString1, String BtnString2, String BtnString3) {
//        if(Register.get().getRegister().getUseAsKiosk()){
//            box = EKiosk.getInstance();
//            box.SmallMessageSetup(MsgString1+MsgString2,null,null,null,true, KioskScreen);
//        }else {
        this.msgString1 = MsgString1;
        this.msgString2 = MsgString2;
        this.btnString1 = BtnString1;
        this.btnString2 = BtnString2;
        this.btnString3 = BtnString3;
        yesNoCancel = true;
//        }
    }

    public void setTimer(int seconds){
        _timerSeconds = seconds;
    }

    public boolean getScannerUsed() {
        return (_backSpaceFound && _rightBracketFound);
    }

    public boolean getSwiperUsed()
    {
//        if(Register.get().getRegister().getUseAsKiosk()){
//            return box.getSwiperUsed();
//        }else {
        if (getValidatedText() != null) {
            String strTemp = getValidatedText();
            int percent = strTemp.indexOf("%");
            int semi = strTemp.indexOf(";");
            int length = strTemp.length();
            if ((semi >= 1) && (percent >= 0)) {
                try {
                    String strFirst = strTemp.substring(percent + 1, (semi));
                    String strSecond = strTemp.substring(semi + 1, (length));
                    if (strFirst.equals(strSecond)) {
                        length = strFirst.length();
                        typedText = (strFirst.substring(0, (length - 1)));
                        return true;
                    }
                }catch(StringIndexOutOfBoundsException ex){
                    return false;
                }
            }
        }
//        }
        return false;
    }

    public String getSwipeCardNumber()
    {
        if(getValidatedText() != null) {
            String strTemp = getValidatedText();
            int percent = strTemp.indexOf("%");
            int semi = strTemp.indexOf(";");
            if ((semi >= 1) && (percent >= 0))
                return strTemp.substring(percent + 1, (semi-1));
            else
                return "";
        }
        else
            return "";
    }

    public void showIt() {
        //reset scannerUsed
        typedText = null;
        this.setAutoRequestFocus(true);
        _backSpaceFound = false;
        _rightBracketFound = false;
        //setTitle("");
        this.setModal(true);
        this.setAlwaysOnTop(true);
        textField = new JTextField(10);
        Object[] array;
        JPanel spacer1 = new JPanel(null);
        spacer1.setPreferredSize(new Dimension(20,30));

        if (msgString2.length() < 40 && msgString1.length() < 40) {
            JLabel lblMessage1 = new JLabel(msgString1);
            JLabel lblMessage2 = new JLabel(msgString2);
            lblMessage1.setFont(new Font("Segoe UI", Font.BOLD, 30));
            lblMessage2.setFont(new Font("Segoe UI", Font.BOLD, 30));
            if(!yesNoCancel)
                array = new Object[]{lblMessage1, lblMessage2, textField};
            else
                array = new Object[]{lblMessage1, lblMessage2,spacer1};
        } else {
            if(!yesNoCancel)
                array = new Object[]{msgString1, msgString2, textField};
            else
                array = new Object[]{msgString1, msgString2,spacer1};
        }

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options;
        if(!yesNoCancel)
            options = new Object[]{btnString1, btnString2};
        else
            options = new Object[]{btnString1, btnString2, btnString3};
        textField.setFont(new Font("Segoe UI", Font.BOLD, 20));

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                yesNoCancel ? JOptionPane.YES_NO_CANCEL_OPTION : JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);
        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                optionPane.setValue(new Integer(
                        JOptionPane.CLOSED_OPTION));
            }
        });

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                textFieldKeyTyped(e);
            }
        });

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        if(_timerSeconds > 0)
            startTimer();
        textField.setEnabled(true);
        textField.requestFocus();
        setVisible(true);
    }

    /**
     * This method handles events for the text field.
     */

    private void startTimer(){
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                do{
                    try {
                        Thread.sleep(1000);
                        _timerSeconds--;
                    }catch(InterruptedException e){
                        _timerSeconds--;
                    }
                }while(_timerSeconds > 0);
                clearAndHide();
            }
        });
        newThread.start();
    }

    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    private void textFieldKeyTyped(KeyEvent e) {
        int key = e.getKeyChar();

        if(key == KeyEvent.VK_BACK_SPACE)
            _backSpaceFound=true;
        if(key == KeyEvent.VK_NONCONVERT)
            _rightBracketFound = true;
    }


    /**
     * This method reacts to state changes in the option pane.
     */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
                JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
                if(!yesNoCancel) {
                    typedText = textField.getText();
                    if ((typedText != "")) {
                        //we're done; clear and dismiss the dialog
                        clearAndHide();
                    } else {
                        //text was invalid
                        _backSpaceFound = false;
                        _rightBracketFound = false;
                        textField.selectAll();
                        JOptionPane.showMessageDialog(
                                CDialog.this,
                                "Sorry, \"" + typedText + "\" "
                                        + "isn't a valid response.\n",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        typedText = null;
                        textField.requestFocusInWindow();
                    }
                }else{
                    typedText = btnString1;
                    clearAndHide();
                }
            } else if(btnString2.equals(value) && yesNoCancel){
                typedText = btnString2;
                clearAndHide();
            }else if(btnString3.equals(value) && yesNoCancel){
                if(btnString3.equals("Cancel"))
                    typedText = null;
                else
                    typedText = btnString3;
                clearAndHide();
            }else{ //user closed dialog or clicked cancel
                typedText = null;
                clearAndHide();
            }
        }
    }
    /**
     * This method clears the dialog and hides it.
     */
    public void clearAndHide() {
        textField.setText(null);
        setVisible(false);
    }

    /**
     * Creates the reusable dialog.
     */
    public CDialog(Frame aFrame) {
        super(aFrame, true);
    }
}
