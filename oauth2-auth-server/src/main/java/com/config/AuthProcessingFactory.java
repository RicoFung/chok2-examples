package com.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import chok2.util.core.EncryptionUtil;

public final class AuthProcessingFactory
{

	@SuppressWarnings("deprecation")
	public static PasswordEncoder createDelegatingPasswordEncoder()
	{
		String encodingId = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(encodingId, new BCryptPasswordEncoder());
		encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
		encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
		encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
		encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(
				"secret", // 盐值
				185000,   // 哈希迭代次数
				256,       // 哈希长度（位）
				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256 // 算法
				));
		encoders.put("scrypt", new SCryptPasswordEncoder(
				16384, // CPU cost (2^14)
				8,     // Memory cost
				1,     // Parallelization
				32,    // Key length
				16     // Salt length
		));
		encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
		encoders.put("SHA-256",	new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
		encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
		encoders.put("argon2", new Argon2PasswordEncoder(
			16, // 盐的长度
			32, // 哈希的长度
			1,  // 并行性（线程数）
			65536, // 内存消耗（单位：KB）
			3      // 哈希迭代次数
		));
		// 加入自定义前缀
//		encoders.put("null", ErpPasswordEncoder());
		DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
		// 指定没有前缀时使用的方式
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(ErpPasswordEncoder());
		return delegatingPasswordEncoder;
	}

	private static PasswordEncoder ErpPasswordEncoder()
	{
		return new PasswordEncoder()
		{
			@Override
			public String encode(CharSequence rawPassword)
			{
				return epoMD5(rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword)
			{
				String epoPassword = epoMD5(rawPassword);
				return epoPassword.equals(encodedPassword);
			}
		};
	}

	/**
	 * EPO MD5 兼容旧DRP逻辑，最多只取前8位进行加密
	 * 
	 * @param rawPassword
	 * @return
	 */
	private static String epoMD5(CharSequence rawPassword)
	{
		String pwd1 = (String) rawPassword;
		String pwd2 = pwd1.length() <= 8 ? pwd1 : pwd1.substring(0, 8);
		String epoPassword = EncryptionUtil.getMD5(pwd2);
		return epoPassword;
	}
}
