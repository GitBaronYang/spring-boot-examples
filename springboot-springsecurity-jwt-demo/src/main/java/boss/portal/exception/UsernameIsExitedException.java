package boss.portal.exception;

import org.springframework.security.core.AuthenticationException;


/** 
* @author  yang
* @E-mail: 
* @date 创建时间：2018年2月6日 下午6:08:45 
* @version 1.0   
*/
public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}