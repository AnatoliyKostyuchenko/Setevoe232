import javafx.fxml.FXML;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server= new ServerSocket(8189);
        while(true){ServerSocket serverSocket = null;


            Socket socket = null;


            DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte[] bytes = new byte[1024];

            is.read(bytes);
            System.out.println(bytes);

            FileOutputStream fos = new FileOutputStream("C:\test.xml");
            fos.write(bytes);
            socket = server.accept();
            System.out.println("New client...");
            new Thread(new Xran(socket)).start();
        }
    }
}
