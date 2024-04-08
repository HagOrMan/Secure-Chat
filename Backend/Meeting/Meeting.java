package Backend.Meeting;

import java.util.ArrayList;
import java.time.LocalDateTime;
public class Meeting {
    ArrayList<String> userIDs;
    public LocalDateTime meetingTime;
    public String meetingLink;
    public String getMeetingLink() {
        return meetingLink;
    }
    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }
    public ArrayList<String> getMeetingParticipants() {
        return userIDs;
    }

}
