package core.domain.messages;

import core.domain.Group;
import core.domain.proxy.ProxyUser;
/**
 * A class representing ecrypted messages - WIP
 * @author Mathieu
 *
 */
public class EncryptMessage extends Message {
	public EncryptMessage(ProxyUser sender, ProxyUser receiver, String content) {
		super();
	}
	
	public EncryptMessage(ProxyUser sender, Group receivers, String content) {
		super(sender, receivers, encrypt(content));
	}
	//TODO : move this
	public static String encrypt(String s) {
		String r = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            r+=c;
        }
        return r;
	}
}
