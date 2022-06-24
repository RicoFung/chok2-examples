/*
 * 授权服务器元信息（issue 端点）
 * http://localhost:9000/.well-known/oauth-authorization-server
 * 
 * 参考：https://felord.blog.csdn.net/article/details/123551894
 */
package com.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.domain.erp.service.TbUserInfo0aService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import chok.common.BeanFactory;

@Configuration(proxyBeanMethods = false)
public class AuthServerConfig
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	// 仅用于【注册方式：数据库】
	@Autowired
	TbUserInfo0aService			tbUserInfo0aService;
	@Autowired
	RedisTemplate<String, ?>	redisTemplate;

	/**
	 * 默认
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception
	{
		// 默认配置
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		// 仅用于【注册方式：数据库】
		http.userDetailsService(tbUserInfo0aService);
		return http.formLogin(Customizer.withDefaults()).build();
	}

	/**
	 * 自定义端点逻辑示例
	 */
//	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception
//	{
//		// 仅用于【注册方式：数据库】
//		http.userDetailsService(tbUserInfo0aService);
//		// 参考源码： OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
//		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
//		http.requestMatcher(endpointsMatcher)
//				.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//				.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher)).apply(authorizationServerConfigurer);
//		// token 端点
//		// authorizationServerConfigurer.tokenEndpoint(
//		// tokenEndpoint -> tokenEndpoint.accessTokenResponseHandler(new
//		// AuthenticationSuccessHandler()
//		// {
//		// @Override
//		// public void onAuthenticationSuccess(HttpServletRequest request,
//		// HttpServletResponse response,
//		// Authentication authentication) throws IOException, ServletException
//		// {
//		// OAuth2AccessTokenAuthenticationToken clientAuthentication =
//		// (OAuth2AccessTokenAuthenticationToken) authentication;
//		// System.out.println("***********************************");
//		// System.out.println("THE ACCESS TOKEN ...");
//		// System.out.println("***********************************");
//		// System.out.println(clientAuthentication.getAccessToken().getTokenValue());
//		//
//		// }
//		// }));
//		// revoke 端点
////		authorizationServerConfigurer.tokenRevocationEndpoint(tokenRevocationEndpoint -> tokenRevocationEndpoint
////				.revocationResponseHandler(new AuthenticationSuccessHandler()
////				{
////					@SuppressWarnings({ "unchecked", "rawtypes" })
////					@Override
////					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
////							Authentication authentication) throws IOException, ServletException
////					{
////						ObjectMapper objectMapper = new ObjectMapper();
////						RestResult result = new RestResult();
////						try
////						{
////							// 将 revoke token 加入黑名单逻辑(Redis)
////							OAuth2TokenRevocationAuthenticationToken authenticationToken = (OAuth2TokenRevocationAuthenticationToken) authentication;
////							log.debug("[REVOKE_TOKEN] => {}", authenticationToken.getToken());
////							SetOperations setOps = redisTemplate.opsForSet();
////							setOps.add("TOKEN_BLACK_LIST", authenticationToken.getToken());
////						}
////						catch (Exception e)
////						{
////							result.setSuccess(false);
////							result.setCode(RestConstants.ERROR_CODE3);
////							result.setMsg(e.getMessage());
////						}
////						finally
////						{
////							result.setPath(request.getServletPath());
////							result.setTimestamp(String.valueOf(new Date().getTime()));
////							response.setContentType("application/json;charset=UTF-8");
////							try
////							{
////								objectMapper.writeValue(response.getOutputStream(), result);
////							}
////							catch (Exception e)
////							{
////								log.error(objectMapper.writeValueAsString(e));
////							}
////							finally
////							{
////								log.error(objectMapper.writeValueAsString(result));
////							}
////						}
////					}
////				}).errorResponseHandler(new AuthenticationFailureHandler()
////				{
////					@Override
////					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
////							AuthenticationException exception) throws IOException, ServletException
////					{
////						ObjectMapper objectMapper = new ObjectMapper();
////						RestResult result = new RestResult();
////						result.setSuccess(false);
////						result.setCode(RestConstants.ERROR_CODE3);
////						result.setMsg(exception.getMessage());
////						result.setPath(request.getServletPath());
////						result.setTimestamp(String.valueOf(new Date().getTime()));
////						response.setContentType("application/json;charset=UTF-8");
////						try
////						{
////							objectMapper.writeValue(response.getOutputStream(), result);
////						}
////						catch (Exception e)
////						{
////							log.error(objectMapper.writeValueAsString(e));
////						}
////						finally
////						{
////							log.error(objectMapper.writeValueAsString(result));
////						}
////					}
////				}));
//		return http.formLogin(Customizer.withDefaults()).build();
//	}

	/**
	 * 【注册方式：数据库】 认证方式：CLIENT_SECRET_BASIC 注意：OPENID 不可单独使用
	 * 
	 * @return
	 */
	@Bean
	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate)
	{
		// DB 有数据
		JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
		return registeredClientRepository;
		// DB 无数据（即：启动时才写库）
		// RegisteredClient registeredClient =
		// RegisteredClient.withId(UUID.randomUUID().toString())
		// .clientId("rico-client")
		// .clientSecret("{noop}123")
		// .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
		// .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
		// .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
		// // 回调地址名单，不在此列将被拒绝 而且只能使用IP或者域名 不能使用 localhost
		// // client 为 springboot 的回调地址
		// .redirectUri("http://127.0.0.1:8080/login/oauth2/code/rico-client-oidc")
		// .redirectUri("http://127.0.0.1:8080/authorized")
		// // client 为 vue 的回调地址
		// .redirectUri("http://127.0.0.1:7090/spring-oauth2-code-v1") // 非弹窗
		// .redirectUri("http://127.0.0.1:7090/oauth2/callback") // 弹窗
		// // client 为外网服务的回调地址
		// .redirectUri("https://oidcdebugger.com/debug")
		// .scope(OidcScopes.OPENID)
		// .scope("test.read")
		// .build();
		//
		// // Save registered client in db as if in-memory
		// JdbcRegisteredClientRepository registeredClientRepository = new
		// JdbcRegisteredClientRepository(jdbcTemplate);
		// registeredClientRepository.save(registeredClient);
		// return registeredClientRepository;
	}

	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate((DataSource) BeanFactory.getBean("dataSourceMybatis"));
	}

	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository)
	{
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	@Bean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository)
	{
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}

	/**
	 * 【注册方式：内存】 认证方式：CLIENT_SECRET_BASIC 注意：OPENID 不可单独使用
	 * http://auth-server:9000/oauth2/authorize?client_id=rico-client&client_secret=123&response_type=code&scope=test.read&redirect_uri=https://www.baidu.com
	 * 
	 * @return
	 */
	// @Bean
	// public RegisteredClientRepository registeredClientRepository()
	// {
	// RegisteredClient registeredClient =
	// RegisteredClient.withId(UUID.randomUUID().toString())
	// .clientId("rico-client")
	// .clientSecret("{noop}123")
	// .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
	// .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	// .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
	// // 回调地址名单，不在此列将被拒绝 而且只能使用IP或者域名 不能使用 localhost
	// // client 为 springboot 的回调地址
	// .redirectUri("http://127.0.0.1:8080/login/oauth2/code/rico-client-oidc")
	// .redirectUri("http://127.0.0.1:8080/authorized")
	// // client 为 vue 的回调地址
	// .redirectUri("http://127.0.0.1:7090/spring-oauth2-code-v1") // 非弹窗
	// .redirectUri("http://127.0.0.1:7090/oauth2/callback") // 弹窗
	// // client 为外网服务的回调地址
	// .redirectUri("https://oidcdebugger.com/debug")
	// .scope(OidcScopes.OPENID)
	// .scope("test.read")
	// .build();
	// return new InMemoryRegisteredClientRepository(registeredClient);
	// }

	@Bean
	public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException
	{
		RSAKey rsaKey = generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

	private static RSAKey generateRsa() throws NoSuchAlgorithmException
	{
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
	}

	private static KeyPair generateRsaKey() throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}

	@Bean
	public ProviderSettings providerSettings()
	{
		return ProviderSettings.builder().issuer("http://auth-server:9000").build();
	}
}
