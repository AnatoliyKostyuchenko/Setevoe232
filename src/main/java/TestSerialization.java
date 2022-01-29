

import java.io.*;
import java.io.File;
import java.net.Socket;

public class TestSerialization {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TransferObject object = new TransferObject("tag", "Message");
        System.out.println(object);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.txt"));
        os.writeObject(object);

        ObjectInputStream is = new ObjectInputStream(new FileInputStream("data.txt"));
        TransferObject object1 = (TransferObject) is.readObject();
        System.out.println(object);
        StringBuilder sb = new StringBuilder();
    }
}
