package ldapconnection;

import javax.naming.Context;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPCreateUser {

    public static void main(String[] args) {

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://11.11.11.154:2389"); // LDAP 서버 URL
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=ldapmaster"); // LDAP 관리자 계정
        env.put(Context.SECURITY_CREDENTIALS, "P@ssw0rd!"); // LDAP 관리자 비밀번호

        try {
            // LDAP 서버에 연결
            DirContext ctx = new InitialDirContext(env);
            System.out.println("LDAP 서버에 연결되었습니다.");

            // 새 엔트리를 생성할 DN 설정
            String newEntryDN = "cn=newuser,ou=Users,o=lgphilips";

            // 새 엔트리에 추가할 속성 값 설정
            Attributes attrs = new BasicAttributes();
            attrs.put(new BasicAttribute("objectclass", "inetOrgPerson"));
            attrs.put(new BasicAttribute("cn", "newuser"));
            attrs.put(new BasicAttribute("sn", "NewUser"));
            attrs.put(new BasicAttribute("mail", "newuser@example.com"));
            attrs.put(new BasicAttribute("uid", "newuser123"));
            attrs.put(new BasicAttribute("userPassword", "newuserpassword"));

            // 새 엔트리 추가
            ctx.createSubcontext(newEntryDN, attrs);
            System.out.println("새 엔트리가 성공적으로 추가되었습니다.");

            // LDAP 서버 연결 종료
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

