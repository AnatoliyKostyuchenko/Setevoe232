import com.gluonhq.charm.glisten.control.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Cient implements Initializable {
public ListView<String> listView;
public TextField textField;
private DataInputStream is;
private DataOutputStream os;
    private Object Message;

    public void sendMessage(ActionEvent actionEvent) throws IOException {
    Object client;
    String text = textField.getText();
    Socket socket = new Socket("localhost",8189);
    os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

    File file = new File("C:\test.xml");
    InputStream is = new FileInputStream(file);

    long length = file.length();
    if (length > Integer.MAX_VALUE) {
        System.out.println("File is too large.");
    }
    byte[] bytes = new byte[(int) length];

    os.write(bytes);
    is = new DataInputStream(socket.getInputStream());
    os = new DataOutputStream(socket.getOutputStream());
    os.close();
    is.close();
    socket.close();

    textField.clear();
    os.writeUTF(text);
    os.flush();

}
private void readLoop(){
    try{
        while(true){
            Socket socket = new Socket("localhost",8189);
            os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            File file = new File("C:\test.xml");
            InputStream is = new FileInputStream(file);

            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                System.out.println("File is too large.");
            }
            byte[] bytes = new byte[(int) length];

            os.write(bytes);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            os.close();
            is.close();
            socket.close();

            Platform.runLater(()->{
                listView.getItems().add(file.toString());
                listView.getItems().add((String) Message);
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread readThread = new Thread(this::readLoop);
            readThread.setDaemon(true);
            readThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}