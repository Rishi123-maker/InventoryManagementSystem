package com.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import com.project.repository.UserRepository;

@Configuration
public class SecurityConfig {
	private final MyUserDetailsService userDetails;
	public SecurityConfig(MyUserDetailsService userDetails)
	{
		this.userDetails=userDetails;
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
        .cors(t -> t.disable())
            .authorizeHttpRequests(auth -> auth
            	 .requestMatchers("/register/").permitAll()
                .requestMatchers("/products/admin/**","/suppliers/admin/**","/reports/admin/**","/orders/admin/**","/stocks/admin/**").hasRole("ADMIN")
                //hasAuthority("ROLE_ADMIN")  // Restrict `/admin/**` to ADMIN users
                .requestMatchers("/orders/customer/**").hasRole("CUSTOMER")
                
                // Restrict `/user/**` to USER role
                // All other requests need authentication
            )
          
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).httpBasic(Customizer.withDefaults()); // Stateless authentication
             
        return http.build();
    }
   

    // ✅ Define AuthenticationManager (Uses AuthenticationProvider)
   @Bean
   public PasswordEncoder passwordEncoder() {
	   
	   return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   
   }
   
//    @Bean
//    public MyUserDetailsService userDetailsService(UserRepository userRepository) {
//    	System.out.println("user bean initialized");
//        return new MyUserDetailsService(userRepository);
//    }
}
