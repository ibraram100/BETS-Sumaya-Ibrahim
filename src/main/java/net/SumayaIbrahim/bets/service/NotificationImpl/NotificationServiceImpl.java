package net.SumayaIbrahim.bets.service.NotificationImpl;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.entity.Notification;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.repository.NotificationRepository;
import net.SumayaIbrahim.bets.repository.WaitingListRepository;
import net.SumayaIbrahim.bets.service.NotificationService;
import net.SumayaIbrahim.bets.service.UserService;
import net.SumayaIbrahim.bets.service.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;
    private UserService userService;
    private WaitingListService waitingListService;
    @Autowired
    private WaitingListRepository waitingListRepository;


    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }



    public void createNotification(Long userId, String message)
    {
        // Checking if user exists
        if (userService.findUserById(userId).isEmpty())
        {
            // if the user doesn't exist, just don't create the notification
            return;
        }
        // Checking if the message is empty, or just full of spaces which is empty too
        if (message == null || message.trim().isEmpty())
        {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRead(false);
        Optional<User> user = userService.findUserById(userId);
        if (user.isPresent()) {
            notification.setUser(user.get());
        }
        notificationRepository.save(notification);
    }

    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }


    // Sending a notification to all users when a new seat becomes available
    public void sendToUsersInWaitingList(Long waitingListId,String message) {


        Optional<WaitingList> waitingList = waitingListRepository.findById(waitingListId);
        if(waitingList.isEmpty()) {
            throw new RuntimeException("Waiting list not found");
        }
        List<User> users = waitingList.get().getUsers();
        for (User user : users) {
            this.createNotification(user.getId(),message);

        }

    }
}
