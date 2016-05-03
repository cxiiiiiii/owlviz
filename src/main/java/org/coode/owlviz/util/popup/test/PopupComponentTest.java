package org.coode.owlviz.util.popup.test;


import org.coode.owlviz.util.popup.PopupComponent;
import org.coode.owlviz.util.popup.PopupComponentEvent;
import org.coode.owlviz.util.popup.PopupComponentListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Feb 16, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class PopupComponentTest extends JFrame {



    private static Logger log = LoggerFactory.getLogger(PopupComponentTest.class);

    private PopupComponent popup;

    private JComponent comp;

    private JList list;

    private JPanel panl;

    private JButton button;

    public PopupComponentTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Popup Test");

        setSize(800, 600);

        getContentPane().setLayout(new BorderLayout());

        final JPanel pan = new JPanel();

        getContentPane().add(pan);

        list = new JList(new Object[]{"One", "Two", "Three", "Four"});

        panl = new JPanel(new BorderLayout());

        panl.add(list);

        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    log.info("TRACE(PopupComonentTest): Setting list ");

                    list.setListData(new Object[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"});
                }
            }
        });


        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        button = new JButton();

        popup = new PopupComponent(panl, false, 5000, false);

        popup.addPopupComponentListener(new PopupComponentListener() {
            public void popupClosed(PopupComponentEvent e) {
                log.info("Popup Closed");
            }
        });

        pan.setLayout(new BorderLayout());


        pan.add(comp = new JButton("Test button 1......."), BorderLayout.WEST);

        pan.add(new JButton("Test button 2......."), BorderLayout.EAST);

        pan.add(new JButton("Test button 3"), BorderLayout.NORTH);

        pan.add(new JButton("Test button 4"), BorderLayout.SOUTH);

        pan.add(new JPanel());


        comp.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    log.info("TRACE(PopupComponentTest): Show popup at " + e.getPoint());

                    popup.showPopup(comp, e.getPoint().x, e.getPoint().y);
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");

        menuBar.add(menu);

        menu.add(new AbstractAction("Set button component") {
            /**
             *
             */
            private static final long serialVersionUID = -999335719559335568L;

            public void actionPerformed(ActionEvent e) {
                popup.setContent(button);
            }
        });

        menu.add(new AbstractAction("Set list component") {


            public void actionPerformed(ActionEvent e) {
                popup.setContent(panl);
            }
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        PopupComponentTest t = new PopupComponentTest();
    }
}
