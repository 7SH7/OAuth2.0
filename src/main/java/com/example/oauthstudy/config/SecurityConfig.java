package com.example.oauthstudy.config;

import com.example.oauthstudy.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {

		this.customOAuth2UserService = customOAuth2UserService;
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.csrf((csrf) -> csrf.disable());

		http
			.formLogin((login) -> login.disable());

		http
			.httpBasic((basic) -> basic.disable());


//		oauth2Login() 메서드를 사용하여 OAuth 2.0 로그인을 활성화하고, (구현해야하는 filter같은 것을 기본적으로 지원해주는 것!)
//		loginPage() 메서드를 사용하여 사용자가 로그인하려고 할 때 리디렉션할 URL을 지정합니다.

//		oauth2Client() 메서드를 사용하여 OAuth 2.0 클라이언트 지원을 활성화합니다. (oauth2Login에서 기본적으로 제공해주는 filter에 속한 것들을 일일히 다 customize해줘야한다. -> 복잡! )
//		이 메서드는 OAuth 2.0 로그인을 사용하여 사용자가 로그인하려고 할 때 리디렉션할 URL을 지정합니다.

		
		http
			.oauth2Login((oauth2) -> oauth2
				.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
					.userService(customOAuth2UserService)));

		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated());

		return http.build();
	}
}