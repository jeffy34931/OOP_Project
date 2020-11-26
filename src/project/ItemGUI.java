package item;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ItemGUI implements ActionListener{
    private JFrame fr;
    private JPanel panel;
    private JButton item1, item2, item3;
    private JLabel head, space1, space2; 
    public ItemGUI(){
        fr = new JFrame();
        panel = new JPanel();
        item1 = new JButton("Item 1");
        item2 = new JButton("Item 2");
        item3 = new JButton("Item 3");
        head = new JLabel();
        space1 = new JLabel(" ");
        space2 = new JLabel(" ");
        head.setPreferredSize(new Dimension(100, 40));
        item1.setPreferredSize(new Dimension(170, 300));
        item2.setPreferredSize(new Dimension(170, 300));
        item3.setPreferredSize(new Dimension(170, 300));
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        panel.setLayout(new FlowLayout());
        panel.add(item1);
        panel.add(space1);
        panel.add(item2);
        panel.add(space2);
        panel.add(item3);
        fr.setLayout(new BorderLayout());
        fr.add(head, BorderLayout.NORTH);
        fr.add(panel);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(650,450);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(item1)){
            item2.setVisible(false);
            item3.setVisible(false);
            panel.remove(item2);
            panel.remove(item3);
        }
        else if (ae.getSource().equals(item2)){
            item1.setVisible(false);
            item3.setVisible(false);
            panel.remove(item1);
            panel.remove(item3);
        }
        else if (ae.getSource().equals(item3)){
            item1.setVisible(false);
            item2.setVisible(false);
            panel.remove(item1);
            panel.remove(item2);
        }
    }
}