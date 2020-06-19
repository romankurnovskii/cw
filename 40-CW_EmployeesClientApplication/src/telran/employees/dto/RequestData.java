package telran.employees.dto;

import java.io.Serializable;

public class RequestData implements Serializable {
    public Object obj1;
    public Object obj2;

    public RequestData(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
}
