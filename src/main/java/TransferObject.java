import java.io.Serializable;

public class TransferObject implements Serializable {
    private String tag;
    private String message;

    public TransferObject(Object tag, Object message) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TransferObject{" +
                "tag='" + tag + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public TransferObject(String message) {
        this.message = message;
    }



}
