package edu.uci.inf122.guildquest.entities.nonlivings;

class ChestStatus {
    enum Status {CLOSED, OPEN, LOOTED, LOCKED}
    Status status;
    public ChestStatus(Status status){
        this.status = status;
    }
    public boolean isOpen() {return status==Status.OPEN;}
    public boolean isClosed() {return status==Status.CLOSED;}
    public boolean isLooted() {return status==Status.LOOTED;}
    public boolean isLocked() {return status==Status.LOCKED;}

}
