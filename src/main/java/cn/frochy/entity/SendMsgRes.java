package cn.frochy.entity;

public class SendMsgRes {
    private String resultCode;
    private String errorCode;

    public SendMsgRes() {
    }

    public SendMsgRes(String resultCode, String errorCode) {
        this.resultCode = resultCode;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "SendMsgRes{" +
                "resultCode='" + resultCode + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
