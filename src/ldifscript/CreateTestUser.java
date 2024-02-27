package ldifscript;

import java.io.IOException;
import java.util.Base64;

public class CreateTestUser {
    public static void main(String[] args) throws IOException {

        for(int i = 1001; i <= 2000; i++) {
            String origin = "cn=test" + i + ",cn=Users,SECAUTHORITY=DEFAULT";

            // Encoding origin string to Base64
            String encodedOrigin = Base64.getEncoder().encodeToString(origin.getBytes());

            String str =
                    "dn:: "+ encodedOrigin +"\n" +
                            "objectClass: ePerson\n" +
                            "objectClass: inetOrgPerson\n" +
                            "objectClass: organizationalPerson\n" +
                            "objectClass: person\n" +
                            "objectClass: top\n" +
                            "cn:: 7JeE7Z2s6528\n" +
                            "cn:: 7Z2s6528\n" +
                            "sn:: 7JeE\n" +
                            "uid: test"+ i +"\n" +
                            "userPassword:: cHJlY3VyczByIQ==\n" +
                            "\n" +
                            "dn: principalName=test" + i + ",cn=Users,secAuthority=Default\n" +
                            "objectclass: cimManagedElement\n" +
                            "objectclass: eUser\n" +
                            "objectclass: secUser\n" +
                            "objectclass: top\n" +
                            "secAuthority: Default\n" +
                            "secLoginType: Default:LDAP\n" +
                            "principalName: test" + i + "\n" +
                            "secAcctValid: TRUE\n" +
                            "secDN:: " + encodedOrigin + "\n" +
                            "secDomainId: Default%test" + i + "\n" +
                            "secHasPolicy: TRUE\n" +
                            "secPwdLastChanged: 20240227010411.0Z\n" +
                            "secPwdValid: TRUE\n" +
                            "secUUID: 1dd82428-d50c-11ee-b023-000c2938e930\n" +
                            "\n" +
                            "dn: cn=Policies,principalName=test" + i + ",cn=Users,secAuthority=Default\n" +
                            "objectclass: container\n" +
                            "objectclass: top\n" +
                            "cn: Policies\n" +
                            "\n" +
                            "dn: cn=Policy,cn=Policies,principalName=test" + i + ",cn=Users,secAuthority=Default\n" +
                            "objectclass: ePasswordPolicy\n" +
                            "objectclass: secPolicy\n" +
                            "objectclass: top\n" +
                            "cn: Policy\n" +
                            "secPwdSpaces: FALSE\n" +
                            "\n";

            System.out.println(str);
        }
    }
}
