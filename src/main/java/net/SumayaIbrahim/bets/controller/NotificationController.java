package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.entity.Notification;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.NotificationService;
import net.SumayaIbrahim.bets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    private UserService userService;

    // Display user notifications
    @GetMapping("/user-notifications")
    public String getNotifications(Principal principal, Model model) {
        Long userId = userService.findUserByEmail(principal.getName()).getId();
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        model.addAttribute("notifications", notifications);
        return "notifications";
    }
}
