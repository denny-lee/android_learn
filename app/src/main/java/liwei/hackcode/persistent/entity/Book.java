package liwei.hackcode.persistent.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by liwei on 2018/1/2.
 */

public class Book extends DataSupport {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
