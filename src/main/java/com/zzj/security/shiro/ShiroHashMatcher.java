package com.zzj.security.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by Administrator on 2018/8/14.
 */
public class ShiroHashMatcher extends HashedCredentialsMatcher {
    public ShiroHashMatcher() {
        setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        setHashIterations(1024);
        setStoredCredentialsHexEncoded(true);
    }

    public String getCredentialHash(Object credentials, Object salt) {
        return new SimpleHash(this.getHashAlgorithmName(), credentials, salt, this.getHashIterations()).toHex();
    }

}
