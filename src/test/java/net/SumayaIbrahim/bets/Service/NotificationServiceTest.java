package net.SumayaIbrahim.bets.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.repository.WaitingListRepository;
import net.SumayaIbrahim.bets.service.NotificationImpl.NotificationServiceImpl;
import net.SumayaIbrahim.bets.service.WaitingListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.SumayaIbrahim.bets.entity.Notification;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.NotificationRepository;
import net.SumayaIbrahim.bets.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private UserService userService;

    @Mock
    WaitingListService waitingListService;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Mock
    WaitingListRepository waitingListRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testing notification creation with a valid user
    @Test
    void testCreateNotification_WithExistingUser() {
        long userId = 1L;
        String message = "Test Notification";
        User user = new User();
        user.setId(userId);

        when(userService.findUserById(userId)).thenReturn(Optional.of(user));

        notificationService.createNotification(userId, message);

        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(captor.capture());
        Notification savedNotification = captor.getValue();

        assertEquals(message, savedNotification.getMessage());
        assertFalse(savedNotification.isRead());
        assertEquals(user, savedNotification.getUser());
    }

    // Testing create notification with a non existing user
    @Test
    void testCreateNotification_WithNonExistingUser() {
        long userId = 1L;
        String message = "Test Notification";

        when(userService.findUserById(userId)).thenReturn(Optional.empty());

        notificationService.createNotification(userId, message);

        verify(notificationRepository, never()).save(any(Notification.class));
    }

    @Test
    void testCreateNotification_WithoutMessage() {
        long userId = 1L;
        String message = null;  // or "" for empty string
        User user = new User();
        user.setId(userId);

        when(userService.findUserById(userId)).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> {
            notificationService.createNotification(userId, message);
        });

        verify(notificationRepository, never()).save(any(Notification.class));
    }


    // Testing send to users in a waiting list function when the waiting list exists
    @Test
    void testSendToUsersInWaitingList_WithExistingWaitingList() {
        Long waitingListId = 1L;
        String message = "A new seat has become available";
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        users.add(user1);
        User user2 = new User();
        user2.setId(2L);
        users.add(user2);
        WaitingList waitingList = new WaitingList();
        waitingList.setId(waitingListId);
        waitingList.setUsers(users);
        when(waitingListRepository.findById(waitingListId)).thenReturn(Optional.of(waitingList));
        when(userService.findUserById(anyLong())).thenReturn(Optional.of(new User()));
        notificationService.sendToUsersInWaitingList(waitingListId, message);
        verify(notificationRepository, times(2)).save(any(Notification.class));
    }

    @Test
    public void testSendToUsersInWaitingList_WaitingListNotFound()
    {
        Long waitingListId = 1L;
        String message = "A new seat has become available";
        when(waitingListRepository.findById(waitingListId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        { notificationService.sendToUsersInWaitingList(waitingListId, message); });
        assertEquals("Waiting list not found", exception.getMessage());
        verify(notificationRepository, never()).save(any(Notification.class));
    }

    // Testing if the waiting list is empty, the system shouldn't store any notifications if
    // the waiting list is empty
    @Test public void testSendToUsersInWaitingList_EmptyUsersList()
    {
        Long waitingListId = 1L; String message = "A new seat has become available! claim it now!";
        WaitingList waitingList = new WaitingList();
        waitingList.setId(waitingListId);
        waitingList.setUsers(new ArrayList<>()); when(waitingListRepository.findById(waitingListId))
            .thenReturn(Optional.of(waitingList));
        notificationService.sendToUsersInWaitingList(waitingListId, message);
        verify(notificationRepository, never()).save(any(Notification.class));
    }

}
