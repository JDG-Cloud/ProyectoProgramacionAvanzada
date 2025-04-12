import co.edu.uniquindio.CommuSafe.modules.notification.model.Notification;
import co.edu.uniquindio.CommuSafe.modules.notification.repository.NotificationRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificationTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @BeforeEach
    public void setUp() {
        notificationRepository.deleteAll();
    }

    @Test
    public void registerTest() {
        Notification notification = new Notification();
        notification.setMessage("New report in your area");
        notification.setType("NEW_REPORT");
        notification.setDate(LocalDateTime.now());
        notification.setRead(false);
        notification.setReceiver(new ObjectId());
        notification.setReportId(new ObjectId());

        Notification notificationCreated = notificationRepository.save(notification);

        assertNotNull(notificationCreated);
        assertNotNull(notificationCreated.getId());

        System.out.println("Notification created with ID: " + notificationCreated.getId());
    }

    @Test
    public void updateTest() {
        Notification notification = new Notification();
        notification.setMessage("Security alert");
        notification.setType("SECURITY");
        notification.setDate(LocalDateTime.now());
        notification.setRead(false);
        notification.setReceiver(new ObjectId());
        notification.setReportId(new ObjectId());

        Notification savedNotification = notificationRepository.save(notification);
        String notificationId = savedNotification.getId().toString();

        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        assertTrue(optionalNotification.isPresent(), "Notification must exist to update it.");

        Notification notificationToUpdate = optionalNotification.get();
        notificationToUpdate.setRead(true);

        notificationRepository.save(notificationToUpdate);

        Notification updatedNotification = notificationRepository.findById(notificationId).orElseThrow();
        assertTrue(updatedNotification.isRead(), "Notification should be marked as read.");
    }

    @Test
    public void listNotificationsByUserTest() {
        ObjectId userId = new ObjectId();

        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");
        notification1.setType("TYPE_1");
        notification1.setDate(LocalDateTime.now());
        notification1.setRead(false);
        notification1.setReceiver(userId);
        notification1.setReportId(new ObjectId());

        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");
        notification2.setType("TYPE_2");
        notification2.setDate(LocalDateTime.now());
        notification2.setRead(false);
        notification2.setReceiver(userId);
        notification2.setReportId(new ObjectId());

        notificationRepository.save(notification1);
        notificationRepository.save(notification2);

        List<Notification> userNotifications = notificationRepository.findByReceiver(userId);

        assertFalse(userNotifications.isEmpty(), "User notification list should not be empty.");
        assertEquals(2, userNotifications.size(), "There should be two notifications for this user.");
    }

    @Test
    public void deleteTest() {
        Notification notification = new Notification();
        notification.setMessage("Temporary Notification");
        notification.setType("TEMPORARY");
        notification.setDate(LocalDateTime.now());
        notification.setRead(false);
        notification.setReceiver(new ObjectId());
        notification.setReportId(new ObjectId());

        Notification savedNotification = notificationRepository.save(notification);
        String notificationId = savedNotification.getId().toString();

        notificationRepository.deleteById(notificationId);

        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        assertTrue(optionalNotification.isEmpty(), "The notification should have been deleted.");
    }

    @Test
    public void filterUnreadTest() {
        ObjectId userId = new ObjectId();

        Notification readNotification = new Notification();
        readNotification.setMessage("Read notification");
        readNotification.setType("READ");
        readNotification.setDate(LocalDateTime.now());
        readNotification.setRead(true);
        readNotification.setReceiver(userId);
        readNotification.setReportId(new ObjectId());

        Notification unreadNotification = new Notification();
        unreadNotification.setMessage("Unread notification");
        unreadNotification.setType("UNREAD");
        unreadNotification.setDate(LocalDateTime.now());
        unreadNotification.setRead(false);
        unreadNotification.setReceiver(userId);
        unreadNotification.setReportId(new ObjectId());

        notificationRepository.save(readNotification);
        notificationRepository.save(unreadNotification);

        List<Notification> unreadNotifications = notificationRepository.findByReceiverAndRead(userId, false);

        assertEquals(1, unreadNotifications.size(), "There should be one unread notification.");
        assertEquals("Unread notification", unreadNotifications.get(0).getMessage(), "The message should match.");
    }
}