package boss.portal.entity;

import javax.persistence.*;


/** 
* @author  yang
* @E-mail: 
* @date 创建时间：2018年2月6日 下午6:08:55 
* @version 1.0   
*/
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
