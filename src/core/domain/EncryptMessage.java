package core.domain;

public class EncryptMessage extends Message {
	public EncryptMessage(User sender, User receiver, String content) {
		super(sender, receiver, encrypt(content));
	}
	
	public EncryptMessage(User sender, Group receivers, String content) {
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
