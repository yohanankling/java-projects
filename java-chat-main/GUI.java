//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class GUI extends JFrame {
//    private Client client;
//    JFrame f;
//    NewFrame frame;
//    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//    int HEIGHT = (int) (size.height/1.5);
//    int WIDTH = (int) (size.width/1.5);
//    JMenuBar mb;
//    JMenu save_load;
//    JMenu algorithm;
//    JMenuItem exit;
//    JMenuItem isConnected;
//
//    public GUI(Client client) {
//        this.client = client;
//        f = new JFrame("Menu");
//        mb = new JMenuBar();
//        save_load = new JMenu("Save/Load");
//        exit = new JMenuItem("add node");
//
//        algorithm = new JMenu("algorithm");
//        isConnected = new JMenuItem("is Connected");
//
//
//        save_load.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser chooser = new JFileChooser("path");
//                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//                    client.disconnect();
//                }
//            }
//        });
//        save_load.add(exit);
//
//        mb.add(save_load);
//        f.setJMenuBar(mb);
//        f.setSize(WIDTH, HEIGHT);
//        f.setTitle("ice-breaker");
//        f.setLayout(new BorderLayout());
//        frame = new NewFrame(client);
//        f.add(frame);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//    }
//
////    public static void main(String[] args) {
////        Client.runGUI("src/graph/G1.json");
////    }
//}