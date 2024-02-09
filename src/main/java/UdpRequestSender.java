/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Michelle
 */
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpRequestSender extends JFrame {
  


    private JTextField messageField;
    private JTextField port;
    private JTextField cible;
    private JLabel label1;
    private JLabel label2;
    private JButton start;//sendButton;


    public UdpRequestSender() {
        //setTitle("UDP Request Sender");
        setTitle("dos attack");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        label1 = new JLabel("select your target");
        label2 = new JLabel("ip");
        messageField = new JTextField();
        /*sendButton*/ start = new JButton("start");

        /*sendButton*/ start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération du message à envoyer
                String message = messageField.getText();
                // Envoi du message en UDP
                sendUdpRequest(message);
                
            }
        });

        // Configuration de la disposition
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(messageField);
        /*add(sendButton);*/  add(start);
        add(label1);
        add(label2);
        

        // Affichage de la fenêtre
        setVisible(true);
    }

    private void sendUdpRequest(String message) {
        try {
            // Adresse IP et port de destination
            InetAddress serverAddress = InetAddress.getByName(cible.getText()); // Mettez l'adresse IP du serveur ici
            int serverPort = Integer.parseInt(port.getText()); // Mettez le port du serveur ici
            
            // Création d'une socket UDP
            DatagramSocket socket= new DatagramSocket();

            // Conversion du message en tableau de bytes
            byte[] data = message.getBytes();

            // Création d'un paquet UDP
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);

            // Envoi du paquet
            socket.send(packet);
               
            // Fermeture de la socket
            socket.close();

            // Affichage d'un message de confirmation (vous pouvez le remplacer par ce que vous souhaitez)
            JOptionPane.showMessageDialog(this, "Message sent successfully!");

        } catch (IOException ex) {
            // Gestion des erreurs
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UdpRequestSender();
            }
        });
        System.out.println("hello word");
    }

    
}
