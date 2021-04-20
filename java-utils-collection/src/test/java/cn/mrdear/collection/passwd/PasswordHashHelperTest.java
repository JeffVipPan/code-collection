package cn.mrdear.collection.passwd;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class PasswordHashHelperTest {

    @Test
    public void createHash() {

        String passwd = "123456";

        Set<String> result = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            result.add(PasswordHashHelper.createHash(passwd));
        }
        Assert.assertEquals(10, result.size());

        for (String s : result) {
            Assert.assertTrue(PasswordHashHelper.validatePassword(passwd, s));
        }
    }
}