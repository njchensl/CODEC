package me.nanjingchj.codec;

import javax.swing.*;

public class GUI {
    private JPanel pnl;
    private JButton btnEncode;
    private JButton btnDecode;
    private JTextField txtToEncode;
    private JTextField txtEncoded;
    private JTextField txtToDecode;
    private JTextField txtDecoded;

    public GUI() {
        btnEncode.addActionListener(e -> {
            String txtToEncode = this.txtToEncode.getText();


        });
        btnDecode.addActionListener(e -> {
            String txtToDecode = this.txtToDecode.getText();


        });
    }
}
