/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class Window implements ActionListener, MouseListener {

    private JFrame frame;
    private JButton button_attack;
    private JPanel top_panel, bottom_panel, card_bottom_panel, text_bottom_panel, in_game_panel, all_card_panel;

    //Set Attribute in Menu
    private JPanel menu_panel, menu_button_panel, empty_panel_top;
    private JButton menu_button_play;
    private String in_game_text;
    private int in_game_select_state = 0; // 0 = c1 select

    //Set Attribute in Game
    private JPanel player_hp_panel, mon_hp_panel, button_panel;
    private JButton text_button;

    //Set Attribute in Bottom of Game
    private JPanel p1_stat_panel, p2_stat_panel;
    private JLabel p1_name, p2_name, p1_hp, p2_hp, p1_mp, p2_mp, p1_speed, p2_speed;

    private static int state = 1;

    private Integer[] speed = new Integer[4];

    private CardLayout c1 = new CardLayout(), c_frame = new CardLayout();
    private boolean in_audition = false;

    final static String MENU = "MENU";
    final static String IN_GAME = "IN_GAME";
    
    //Set Attribute Font
    public Font font, sizedFont;

    Font customFont;

    public Window(int width, int height, String title, Run run) throws IOException, FontFormatException {
       
        //Add New Font
        InputStream is = Window.class.getResourceAsStream("img/Pixellari.ttf");
        font = Font.createFont(Font.TRUETYPE_FONT, is);
        sizedFont = font.deriveFont(12f);

        //set frame
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        all_card_panel = new JPanel();
        all_card_panel.setLayout(c_frame);

        empty_panel_top = new JPanel();
        empty_panel_top.setPreferredSize(new Dimension(1200, 100));
        //MENU PANEL
        menu_panel = new JPanel();
        menu_panel.setLayout(new BorderLayout());

        menu_button_panel = new JPanel();
        menu_button_panel.setLayout(new FlowLayout());

        menu_button_play = new JButton("Start");
        menu_button_play.addActionListener(this);
        menu_button_panel.add(menu_button_play);

        menu_panel.add(empty_panel_top, BorderLayout.NORTH);
        menu_panel.add(menu_button_panel, BorderLayout.CENTER);

        //IN-GAME PANEL
        in_game_panel = new JPanel();
        in_game_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        top_panel = new JPanel();
        bottom_panel = new JPanel();
        bottom_panel.setLayout(new GridLayout(1, 3));
        bottom_panel.setPreferredSize(new Dimension(1185, 191));

        card_bottom_panel = new JPanel();
        card_bottom_panel.setLayout(c1);
        card_bottom_panel.setPreferredSize(new Dimension(1200, 195));

        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(2, 2));

        text_bottom_panel = new JPanel();
        text_bottom_panel.setLayout(new BorderLayout());

        text_button = new JButton("");
        text_button.setBackground(new Color(64, 72, 80));
        text_bottom_panel.add(text_button);

        //In-game Player HP Panel
        player_hp_panel = new JPanel();
        player_hp_panel.setLayout(new GridLayout(2, 2));
        player_hp_panel.setBackground(new Color(64, 72, 80));
        
        p1_name = new JLabel(Run.c1.getName(), SwingConstants.CENTER);
        p1_name.setFont(sizedFont.deriveFont(Font.BOLD, 30f));
        p1_name.setForeground(Color.WHITE);
        
        p2_name = new JLabel(Run.c2.getName(), SwingConstants.CENTER);
        p2_name.setFont(sizedFont.deriveFont(Font.BOLD, 30f));
        p2_name.setForeground(Color.WHITE);
        
        p1_stat_panel = new JPanel();
        p1_stat_panel.setBackground(new Color(64, 72, 80));
        p1_stat_panel.setLayout(new GridLayout(3, 1));
        p1_hp = new JLabel("HP : " + Run.c1.getHp() + "/" + Run.c1.getMax_hp(), SwingConstants.CENTER);
        p1_hp.setFont(sizedFont.deriveFont(Font.BOLD, 22f));
        p1_hp.setForeground(Color.WHITE);
        p1_stat_panel.add(p1_hp);
        p1_mp = new JLabel("MP : " + Run.c1.getMp() + "/" + Run.c1.getMax_mp(), SwingConstants.CENTER);
        p1_mp.setFont(sizedFont.deriveFont(Font.BOLD, 22f));
        p1_mp.setForeground(Color.WHITE);
        p1_stat_panel.add(p1_mp);
        p1_speed = new JLabel("Speed : " + Run.c1.getSpeed(), SwingConstants.CENTER);
        p1_speed.setFont(sizedFont.deriveFont(Font.BOLD, 22f));
        p1_speed.setForeground(Color.WHITE);
        p1_stat_panel.add(p1_speed);
        
        p2_stat_panel = new JPanel();
        p2_stat_panel.setBackground(new Color(64, 72, 80));
        p2_stat_panel.setLayout(new GridLayout(1, 2));
        
        player_hp_panel.add(p1_name);
        player_hp_panel.add(p1_stat_panel);
        player_hp_panel.add(p2_name);
        player_hp_panel.add(p2_stat_panel);
        bottom_panel.add(player_hp_panel);
        
        //In-game Monster HP Panel
        mon_hp_panel = new JPanel();
        mon_hp_panel.setBackground(new Color(64, 72, 80));
        bottom_panel.add(mon_hp_panel);

        button_attack = new JButton();
        button_attack.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/b1.png"))));
        button_attack.addMouseListener(this);
        button_attack.addActionListener(this);
        button_attack.setFocusable(false);

        JButton b2 = new JButton();
        b2.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/b2.png"))));
        b2.setFocusable(false);
        JButton b3 = new JButton();
        b3.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/b3.png"))));
        b3.setFocusable(false);
        JButton b4 = new JButton();
        b4.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("img/b4.png"))));
        b4.setFocusable(false);

        button_panel.add(button_attack);
        button_panel.add(b2);
        button_panel.add(b3);
        button_panel.add(b4);
        bottom_panel.add(card_bottom_panel);
        //select_bottom_panel.add(button_panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        top_panel.add(run);
        top_panel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, 0));
        in_game_panel.add(top_panel);
        card_bottom_panel.add("select", button_panel);
        card_bottom_panel.add("text", text_bottom_panel);
        c1.show(card_bottom_panel, "select");
        in_game_panel.add(bottom_panel);

        all_card_panel.add(in_game_panel, IN_GAME);
        all_card_panel.add(menu_panel, MENU);
        c_frame.show(all_card_panel, MENU);

        frame.add(all_card_panel);

        frame.setVisible(true);
        run.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu_button_play)) {//กดเริ่มเกมใหม่
            c_frame.show(all_card_panel, IN_GAME);
            frame.requestFocusInWindow();

            speed[0] = Run.c1.getSpeed();
            speed[1] = Run.c2.getSpeed();
            speed[2] = Run.m1.getSpeed();
            speed[3] = Run.m2.getSpeed();
            Arrays.sort(speed, Collections.reverseOrder());

            Audition.setSpeed(speed);
            System.out.println(Arrays.toString(Audition.getSpeed()));
        } else if (e.getSource().equals(button_attack)) {//กดเริ่มเกมใหม่
            c1.show(card_bottom_panel, "text");
//            Audition.setIs_show(true);
//            Audition.setTime_run(true);
//            Run.start = System.nanoTime();
//            Audition.setIs_run(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
