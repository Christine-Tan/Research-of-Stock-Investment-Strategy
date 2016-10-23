package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jiayiwu on 16/10/23.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Entity
public class test {
    @Id
    int id;
    String name;
    String content;

    public test() {
    }

    public test(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
