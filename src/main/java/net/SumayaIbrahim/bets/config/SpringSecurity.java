package net.SumayaIbrahim.bets.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration

public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()// means anyone can access the register link
                                .requestMatchers("/index").permitAll() // anyone can access the homepage
                                .requestMatchers("events/**").hasRole("ADMIN") // only admins can access the events
                                .requestMatchers("/users/**").hasRole("ADMIN")
                                .requestMatchers("/users/all-users/delete").hasRole("ADMIN")
                                .requestMatchers("/users/edit-user/**").hasRole("ADMIN") // Allow admins
                                .requestMatchers("/view/get-events").permitAll() // Allowing anyone to view the list of available events
                                .requestMatchers("view/view-event").permitAll()// Allowing anyone to view the event details
                                .requestMatchers("/view/buy-ticket").hasAnyRole("ADMIN","ATTENDEE") // Only allowing Attendees and admins to buy tickets
                                .requestMatchers("/tickets/all-tickets").hasRole("ADMIN") // Only allowing Admins to view all tickets
                                .requestMatchers("/tickets/my-tickets").hasAnyRole("ADMIN","ATTENDEE") // Only allowing Admins to view all tickets
                                .requestMatchers("/tickets/refund/**").hasAnyRole("ADMIN","ATTENDEE") // Only allowing Admins to view all tickets

                                .requestMatchers("/waiting-lists/**").hasAnyRole("ADMIN","ATTENDEE")
                                .requestMatchers("/notifications/**").hasAnyRole("ADMIN","ATTENDEE")









                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/index") // in case of a successful login, user will be taken to the link specified in here
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}