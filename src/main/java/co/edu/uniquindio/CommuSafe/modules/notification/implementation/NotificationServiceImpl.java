package co.edu.uniquindio.CommuSafe.modules.notification.implementation;

import co.edu.uniquindio.CommuSafe.modules.notification.dto.*;
import co.edu.uniquindio.CommuSafe.modules.notification.mapper.NotificationMapper;
import co.edu.uniquindio.CommuSafe.modules.notification.model.Notification;
import co.edu.uniquindio.CommuSafe.modules.notification.repository.NotificationRepository;
import co.edu.uniquindio.CommuSafe.modules.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByUserId(String userId) {
        if (!ObjectId.isValid(userId)) {
            throw new RuntimeException("Invalid user ID: " + userId);
        }

        ObjectId userObjectId = new ObjectId(userId);
        List<Notification> notifications = notificationRepository.findAllByReceiverOrderByDateDesc(userObjectId);

        return notifications.stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getUnreadNotificationsByUserId(String userId) {
        if (!ObjectId.isValid(userId)) {
            throw new RuntimeException("Invalid user ID: " + userId);
        }

        ObjectId userObjectId = new ObjectId(userId);
        List<Notification> notifications = notificationRepository.findByReceiverAndRead(userObjectId, false);

        return notifications.stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(String id) {
        if (!ObjectId.isValid(id)) {
            throw new RuntimeException("Invalid notification ID: " + id);
        }

        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            throw new RuntimeException("The notification with the id was not found" + id);
        }

        return notificationMapper.toDTO(notificationOptional.get());
    }

    @Override
    public CreateNotificationResponseDTO createNotification(CreateNotificationRequestDTO notificationDTO) {
        if (!ObjectId.isValid(notificationDTO.receiver())) {
            throw new RuntimeException("Invalid recipient ID: " + notificationDTO.receiver());
        }

        try {
            Notification notification = notificationMapper.toEntity(notificationDTO);
            Notification savedNotification = notificationRepository.save(notification);

            return new CreateNotificationResponseDTO(
                    savedNotification.getId().toString(),
                    savedNotification.getMessage(),
                    savedNotification.getDate(),
                    savedNotification.getType(),
                    savedNotification.isRead(),
                    savedNotification.getReportId() != null ? savedNotification.getReportId().toString() : null,
                    savedNotification.getReceiver().toString()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error creating notification: " + e.getMessage());
        }
    }

    @Override
    public NotificationDTO updateNotification(String id, UpdateNotificationDTO notificationDTO) {
        if (!ObjectId.isValid(id)) {
            throw new RuntimeException("Invalid notification ID: " + id);
        }

        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            throw new RuntimeException("The notification with the id was not found " + id);
        }

        Notification notification = notificationOptional.get();
        notification.setMessage(notificationDTO.message());
        notification.setType(notificationDTO.type());
        notification.setRead(notificationDTO.read());

        Notification updatedNotification = notificationRepository.save(notification);
        return notificationMapper.toDTO(updatedNotification);
    }

    @Override
    public NotificationDTO markAsRead(String id) {
        if (!ObjectId.isValid(id)) {
            throw new RuntimeException("Invalid notification ID: " + id);
        }

        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            throw new RuntimeException("The notification with the id was not found " + id);
        }

        Notification notification = notificationOptional.get();
        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);

        return notificationMapper.toDTO(updatedNotification);
    }

    @Override
    public void deleteNotification(String id) {
        if (!ObjectId.isValid(id)) {
            throw new RuntimeException("Invalid notification ID: " + id);
        }

        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            throw new RuntimeException("The notification with the id was not found" + id);
        }

        notificationRepository.deleteById(id);
    }

    @Override
    public long countUnreadNotifications(String userId) {
        if (!ObjectId.isValid(userId)) {
            throw new RuntimeException("Invalid user ID: " + userId);
        }

        ObjectId userObjectId = new ObjectId(userId);
        return notificationRepository.countByReceiverAndRead(userObjectId, false);
    }
}
