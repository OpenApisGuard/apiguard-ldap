package org.apiguard.security.ldap.service;

import org.apiguard.security.ldap.exceptions.ApiGuardLdapException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class LdapServiceTest {

    private static LdapService service;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        service = new LdapService();
    }

    @Test
    public void testLdap() throws Exception {
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "password", "dc=example,dc=com", "uid", "tesla", "password");
        Assert.assertTrue(authenticate);
    }

    @Test
    public void testInvalidUrlLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        boolean authenticate = service.authenticate("ldap://localhost:111", "cn=read-xxx,dc=example,dc=com", "password", "dc=example,dc=com", "uid", "tesla", "password");
    }

    @Test
    public void testInvalidAdminDnLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-xxx,dc=example,dc=com", "password", "dc=example,dc=com", "uid", "tesla", "password");
    }

    @Test
    public void testInvalidAdminPasswordLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        expectedEx.expectMessage("Invalid admin credentails");
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "xxx", "dc=example,dc=com", "uid", "tesla", "password");
    }

    @Test
    public void testInvalidUserBaseLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "password", "dc=example,dc=xxx", "uid", "tesla", "password");
    }

    @Test
    public void testInvalidUserAttrLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        expectedEx.expectMessage("Invalid user id");
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "password", "dc=example,dc=com", "cn", "tesla", "password");
    }

    @Test
    public void testInvalidUserNameLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        expectedEx.expectMessage("Invalid user id");
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "password", "dc=example,dc=com", "uid", "tesl", "password");
    }

    @Test
    public void testInvalidPasswordLdap() throws Exception {
        expectedEx.expect(ApiGuardLdapException.class);
        expectedEx.expectMessage("Invalid user credentails");
        boolean authenticate = service.authenticate("ldap://ldap.forumsys.com:389", "cn=read-only-admin,dc=example,dc=com", "password", "dc=example,dc=com", "uid", "tesla", "xxx");
    }
}
