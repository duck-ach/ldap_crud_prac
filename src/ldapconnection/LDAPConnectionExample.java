package ldapconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class LDAPConnectionExample {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            String[] searchLdap = br.readLine().split(" ");
        }


        Hashtable<String, String> env = new Hashtable<>();
        // LDAP Context 초기 컨텍스트 팩토리
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://11.11.11.154:2389"); // LDAP 서버 URL
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP 신임정보
        env.put(Context.SECURITY_PRINCIPAL, "cn=ldapmaster"); // LDAP 관리자 계정
        env.put(Context.SECURITY_CREDENTIALS, "P@ssw0rd!"); // LDAP 관리자 비밀번호

        try {
            // LDAP 서버에 연결
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Connected LDAP!");

            // 검색할 DN 설정
            String searchBase = "cn=이태근,ou=ltg5820,o=LGPHILIPS";

            // 검색할 LDAP 속성 및 값 설정
            String searchFilter = "(objectClass=*)";

            // 검색 결과에서 가져올 속성 설정
            String[] requiredAttrs = {"cn", "mail", "uid"};

            // LDAP 검색 수행
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.OBJECT_SCOPE); // 단일 객체 범위로 설정
            searchControls.setReturningAttributes(requiredAttrs);
            NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, searchControls);

            // 검색 결과가 존재하는지 확인
            if (results.hasMore()) {
                SearchResult searchResult = results.next();
                Attributes attrs = searchResult.getAttributes();

                // 각 속성 값 출력
                Attribute cnAttr = attrs.get("cn");
                Attribute mailAttr = attrs.get("mail");
                Attribute uidAttr = attrs.get("uid");

                // 값이 존재하는 경우에만 출력
                if (cnAttr != null) {
                    System.out.println("CN: " + cnAttr.get());
                }
                if (mailAttr != null) {
                    System.out.println("Mail: " + mailAttr.get());
                }
                if (uidAttr != null) {
                    System.out.println("UID: " + uidAttr.get());
                }
            } else {
                System.out.println("검색된 결과가 없습니다.");
            }


            // 검색 결과 출력
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                Attributes attrs = searchResult.getAttributes();
                Attribute cnAttr = attrs.get("cn");
                Attribute mailAttr = attrs.get("mail");
                Attribute uidAttr = attrs.get("uid");

                System.out.println("CN: " + cnAttr.get());
                System.out.println("Mail: " + mailAttr.get());
                System.out.println("UID: " + uidAttr.get());
            }

            // LDAP 서버 연결 종료
            ctx.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
