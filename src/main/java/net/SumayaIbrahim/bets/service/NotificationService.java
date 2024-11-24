package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.entity.Notification;

import java.util.List;

public interface NotificationService {
    public List<Notification> getNotificationsByUserId(Long userId);
    public void createNotification(Long userId, String message);
    public void markNotificationAsRead(Long notificationId);
    public void sendToUsersInWaitingList(Long waitingListId,String message);
    
}
