package com.audio.commons.exception;

/**
 * author daigai
 */
public class PortalException extends Exception
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5514328886826952129L;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 异常返回结果
     */
    private String result;

    /**
     * 构造函数1
     */
    public PortalException()
    {
        errorCode = "";
        result = "error";
    }

    /**
     * 构造函数2
     */
    public PortalException(String errorCode)
    {
        this.errorCode = "";
        result = "error";
        this.errorCode = errorCode;
    }

    /**
     * 构造函数3
     */
    public PortalException(String errorCode, String message)
    {
        super(message);
        this.errorCode = "";
        result = "error";
        this.errorCode = errorCode;
    }

    /**
     * 构造函数4
     */
    public PortalException(String errorCode, Throwable cause)
    {
        super(cause);
        this.errorCode = "";
        result = "error";
        this.errorCode = errorCode;
    }

    /**
     * 构造函数5
     */
    public PortalException(String errorCode, String message, Throwable cause)
    {
        super(message, cause);
        this.errorCode = "";
        result = "error";
        this.errorCode = errorCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Error Code:<")
                .append(errorCode)
                .append("> ")
                .append(super.toString())
                .toString();
    }

    public String getMessage()
    {
        String message = (new StringBuilder()).append("Error Code:<")
                .append(errorCode)
                .append("> ")
                .toString();
        if (getCause() != null)
            message = (new StringBuilder()).append(message)
                    .append(super.getMessage())
                    .toString();
        return message;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

}
