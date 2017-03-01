package techtrix.example.com.techtrix;

/**
 * Created by risha on 01-02-2017.
 */

public class NotifyMessage {
    int id;
    String messDesc;
    String eventName;

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getMessDesc() {
        return messDesc;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessDesc(String messDesc) {
        this.messDesc = messDesc;
    }
}
