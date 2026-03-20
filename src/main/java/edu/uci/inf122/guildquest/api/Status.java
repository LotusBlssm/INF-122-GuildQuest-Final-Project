package edu.uci.inf122.guildquest.api;

public class Status {
    String msg;
    Option status;

    public String getMsg() {
        return msg;
    }

    public boolean isFail() {
        return status==Option.FAIL;
    }
    public boolean isContinue(){
        return status==Option.CONTINUE;
    }
    public boolean isDone(){
        return status==Option.DONE;
    }

    public enum Option{
        SUCCESS,FAIL,INVALID_INPUT, CONTINUE,DONE
    }
    public Status(Option status, String msg){
        this.status=status;
        this.msg = msg;
    }
    public Status(Option status){
        this.status=status;
        this.msg ="";
    }
}
