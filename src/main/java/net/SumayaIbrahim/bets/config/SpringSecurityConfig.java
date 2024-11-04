//package net.SumayaIbrahim.bets.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//// What we are basically doing is making some basic authinthication in here, so only people with a username and a password can use the system
//@Configuration
//@EnableWebSecurity
//
//public class SpringSecurityConfig {
//
//    // This one is very important, as spring boot can't deal with plain text passwords, they must be encoded first
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return  new BCryptPasswordEncoder();
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) -> {
////                  authorize.requestMatchers(HttpMethod.POST, "api/**").hasRole("ADMIN");// means only admins can use POST requests on all the subfolders of /api
//                    authorize.requestMatchers("/api/auth/register").permitAll();// Allows anyone to access the subfolders within auth
//                    authorize.requestMatchers("/api/events/allevents").permitAll(); // Allows anyone to view events
//                    authorize.requestMatchers("api/auth/login").permitAll();
//                    authorize.anyRequest().authenticated(); // accept any request, without postman seems to not work probably
//                }).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    // Basically we are defining who can access the api
//    @Bean
//    public UserDetailsService userDetailsService(){
//        // this User class is not the same as the one we created in entity, it's from spring security package
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("pass"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
