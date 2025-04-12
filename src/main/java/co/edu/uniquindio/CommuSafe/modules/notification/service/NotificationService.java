package co.edu.uniquindio.CommuSafe.modules.notification.service;

import co.edu.uniquindio.CommuSafe.modules.notification.dto.*;
import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getAllNotifications();
    List<NotificationDTO> getNotificationsByUserId(String userId);
    List<NotificationDTO> getUnreadNotificationsByUserId(String userId);
    NotificationDTO getNotificationById(String id);
    CreateNotificationResponseDTO createNotification(CreateNotificationRequestDTO notificationDTO);
    NotificationDTO updateNotification(String id, UpdateNotificationDTO notificationDTO);
    NotificationDTO markAsRead(String id);
    void deleteNotification(String id);
    long countUnreadNotifications(String userId);
}
