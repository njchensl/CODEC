package me.nanjingchj.codec;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;
//import java.util.Base64;

public class GUI {
    private JPanel pnl;
    private JButton btnEncode;
    private JButton btnDecode;
    private JTextArea txtToEncode;
    private JTextArea txtEncoded;
    private JTextArea txtToDecode;
    private JTextArea txtDecoded;
    private JScrollPane spToEncode;
    private JScrollPane spEncoded;
    private JScrollPane spToDecode;
    private JScrollPane spDecoded;

    public GUI() {
        btnEncode.addActionListener(e -> {
            String txtToEncode = this.txtToEncode.getText();
            byte[] encodedBytes = txtToEncode.getBytes(StandardCharsets.UTF_8);
            //String encoded = new String(Base64.getEncoder().encode(encodedBytes));
            String encoded = encode(encodedBytes);
            txtEncoded.setText(encoded);
            this.txtToEncode.setText("");
        });
        btnEncode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    // change look and feel
                    try {
                        UIManager.setLookAndFeel(UIManager.getLookAndFeel() instanceof FlatIntelliJLaf ? new FlatDarculaLaf() : new FlatIntelliJLaf());
                        SwingUtilities.updateComponentTreeUI(pnl);
                    } catch (UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                } else if (e.getClickCount() == 4) {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        SwingUtilities.updateComponentTreeUI(pnl);
                    } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnDecode.addActionListener(e -> {
            String txtToDecode = this.txtToDecode.getText();
            //byte[] base64Bytes = Base64.getDecoder().decode(txtToDecode);
            //txtDecoded.setText(new String(base64Bytes, StandardCharsets.UTF_8));
            txtDecoded.setText(new String(decode(txtToDecode), StandardCharsets.UTF_8));
            this.txtToDecode.setText("");
        });
    }

    @NotNull
    private static String encode(@NotNull byte[] byteArray) {
        BitIterableByteArray array = new BitIterableByteArray(byteArray);
        StringBuilder result = new StringBuilder();
        for (boolean b : array) {
            result.append(b ? 1 : 0);
        }
        return result.toString();
    }

    @NotNull
    private static byte[] decode(@NotNull String s) {
        int length = s.length() / 8;
        byte[] data = new byte[length];
        int byteCounter = 0;
        for (; byteCounter < length; byteCounter++) {
            String byteString = s.substring(byteCounter * 8, byteCounter * 8 + 8);
            //System.out.println(Long.parseLong(byteString, 2));
            long result = Long.parseLong(byteString, 2);
            data[byteCounter] = (byte) (result > 127 ? result - 256 : result);
        }
        return data;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setContentPane(new GUI().pnl);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setPreferredSize(new Dimension(400, 400));
            f.pack();
            f.setVisible(true);
        });
    }
}
