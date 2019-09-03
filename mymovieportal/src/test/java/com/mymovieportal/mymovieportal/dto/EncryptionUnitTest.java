package com.mymovieportal.mymovieportal.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mymovieportal.util.Encryption;

// @RunWith(PowerMockRunner.class)
public class EncryptionUnitTest {

    @Test
    public void testEncrypt() {
        String code = Encryption.encrypt("1qqQQQ");
        assertEquals(code, "MXFxUVFR");
    }

    @Test
    public void testDecrypt() {
        String code = Encryption.decrypt("MXFxUVFR");
        assertEquals(code, "1qqQQQ");
    }
}
