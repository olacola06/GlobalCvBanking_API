package dto;

public class ErrorLoginDto {
    String status;
    String message;
    String error;

    public String withStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String withMessage(){
        return message;
    }
    public String withError(){
        return error;
    }
}
