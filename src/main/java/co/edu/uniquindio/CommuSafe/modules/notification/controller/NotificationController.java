package co.edu.uniquindio.CommuSafe.modules.notification.controller;

import co.edu.uniquindio.CommuSafe.modules.notification.dto.*;
import co.edu.uniquindio.CommuSafe.modules.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import co.edu.uniquindio.CommuSafe.modules.util.MessageAlertDTO;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Get notifications by user ID
     */
    @GetMapping("{userId}")
    public ResponseEntity<MessageAlertDTO<List<NotificationDTO>>> getNotificationsByUserId(@PathVariable String userId) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, notifications));
    }

    /**
     * Count unread notifications by user ID
     */
    @GetMapping("count-unread/{userId}")
    public ResponseEntity<MessageAlertDTO<Long>> countUnreadNotifications(@PathVariable String userId) {
        Long count = notificationService.countUnreadNotifications(userId);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, count));
    }

    /**
     * Create new notification
     */
    @PostMapping
    public ResponseEntity<MessageAlertDTO<String>> createNotification(@Valid @RequestBody CreateNotificationRequestDTO notificationDTO) {
        notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "notification created successfully"));
    }

    /**
     * Update existing notification
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageAlertDTO<String>> updateNotification(@PathVariable String id,
                                                                      @Valid @RequestBody UpdateNotificationDTO notificationDTO) {
        notificationService.updateNotification(id, notificationDTO);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "notification updated successfully"));
    }

    /**
     * Delete notification
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageAlertDTO<String>> deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok(new MessageAlertDTO<>(false, "notification successfully removed"));
    }
}