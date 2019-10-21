import com.pms.utils.Digests;
import com.pms.utils.Encodes;

/**
 * Created by  on 2017/7/27.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public class PasswordTest {

    private static final int SALT_SIZE = 8;

    private static final String HASH_ALGORITHM = "SHA-1";
    private static final int HASH_INTERATIONS = 1024;
    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {

        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /*public static void main(String[] args){
        System.out.println(PasswordTest.entryptPassword("123456"));
    }*/

}
