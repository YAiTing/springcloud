package com.yb.springcloud.springcloudsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangb
 * @Description:
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Value("${resource.ids}")
	private String resourceIds;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}

	//JWT 转换器
	//keytool -genkeypair -alias test -keyalg RSA -keypass foobar -keystore keystore.jks -storepass foobar
	@Bean
	public MyJwtAccessToken jwtAccessTokenConverter() {
		MyJwtAccessToken converter = new MyJwtAccessToken();
		KeyStoreKeyFactory kskf = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray());
		KeyPair keyPair = kskf.getKeyPair("test");
		converter.setKeyPair(keyPair);
		return converter;
	}

	/**
	 * 自定义JWT信息
	 */
	private class MyJwtAccessToken extends JwtAccessTokenConverter {
		@Override
		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
			Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
			if (authentication.getPrincipal() instanceof  User) {
				User user = (User) authentication.getPrincipal();
				additionalInformation.put("username", user.getUsername());
			}
			return oAuth2AccessToken;
		}
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// "authorization_code", "refresh_token", "password", "implicit","client_credentials"
		String finalPassword = passwordEncoder.encode("secret");
		clients.inMemory()
				//client_id
				.withClient("client")
				.resourceIds(StringUtils.split(resourceIds,","))
				//client_secret
				.secret(finalPassword)
				.authorities("client")
				 // 该client允许的授权类型
				.authorizedGrantTypes("refresh_token","client_credentials","authorization_code","password","implicit")
//				.scopes("openid");
				.scopes("app")
				.and()
				.withClient("client2")
				.resourceIds(StringUtils.split(resourceIds,","))
				.secret(finalPassword)
				.authorizedGrantTypes("refresh_token", "password")
				.authorities("client")
				.scopes("app");
//				.accessTokenValiditySeconds(6000)
//				.refreshTokenValiditySeconds(6000);
//		.withClientDetails(new JdbcClientDetailsService(dataSource));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager)
				//内存
//				.tokenStore(tokenStore());
				//refresh_token需要userDetailsService
//				.reuseRefreshTokens(false).userDetailsService(userDetailsService);
				//JWT
				.accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
				// 开启/oauth/token_key验证端口无权限访问
				.tokenKeyAccess("permitAll()")
				// 开启/oauth/check_token验证端口认证权限访问
				.checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}
}
