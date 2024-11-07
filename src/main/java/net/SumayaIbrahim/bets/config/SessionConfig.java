package net.SumayaIbrahim.bets.config;


import net.SumayaIbrahim.bets.Sessions.UserSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SessionConfig {
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession userSession()
    {
        return new UserSession();
    }
}
