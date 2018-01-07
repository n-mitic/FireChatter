package nsi.firechatter.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Chat {

    @Exclude
    public String id;

    @Exclude
    public String avatarUrl;

    @Exclude
    public String lastMsg;

    @Exclude
    public Object lastMsgDate;

    @Exclude
    public String lastMsgSenderId;

    @Exclude
    public String  lastMsgSenderName;

    public String name;
    public String lastMsgId;
    public Map<String, Object> members = new HashMap<>();

    public Chat() {
        // Default constructor required for calls to DataSnapshot.getValue(Chat.class)
    }

}
