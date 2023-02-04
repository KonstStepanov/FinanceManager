package UtilClasses;

import java.io.*;

/**
 * класс для сериализации запроса пользователя к серверу
 */
public class RequestClass implements Serializable {
    protected String requestTitle;
    protected String requestDate;
    protected Integer requestSum;
    protected String requestYear;
    protected String requestMonth;
    protected String requestDay;

    public RequestClass(String requestTitle, String requestDate,
                        Integer requestSum, String requestYear,
                        String requestMonth, String requestDay) {
        this.requestTitle = requestTitle;
        this.requestDate = requestDate;
        this.requestSum = requestSum;
        this.requestYear = requestYear;
        this.requestMonth = requestMonth;
        this.requestDay = requestDay;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public Integer getRequestSum() {
        return requestSum;
    }

    public String getRequestYear() {
        return requestYear;
    }

    public String getRequestMonth() {
        return requestMonth;
    }

    public String getRequestDay() {
        return requestDay;
    }

    @Override
    public String toString() {
        return "RequestClass{" +
                "requestTitle='" + requestTitle + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", requestSum=" + requestSum +
                ", requestYear='" + requestYear + '\'' +
                ", requestMonth='" + requestMonth + '\'' +
                ", requestDay='" + requestDay + '\'' +
                '}' + '\n';
    }
}
