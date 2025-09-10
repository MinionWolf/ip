package command;

public class Events extends Task {
    private String from;
    private String to;
    private boolean eventStatus;


    public Events(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.eventStatus = true;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEventStatus() {
        return (eventStatus ? "E" : " ");
    }

    public void setEventStatus(boolean eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public String getTask(){
        return "[" + getEventStatus() + "] " +  super.getTask();
    }
}
