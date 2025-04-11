package co.edu.uniquindio.CommuSafe.modules.notification.implementation;

import co.edu.uniquindio.CommuSafe.modules.notification.NotificationNotFoundException;
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
        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper ::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationByUserId(String userId){
        ObjectId userObjectId = new ObjectId(userId);
        return notificationRepository.findAllByReceiverOrderByDateDesc(userObjectId)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getUnreadNotificationsByUserId(String userId) {
        ObjectId userObjectId = new ObjectId(userId);
        return notificationRepository.findByReceiverAndRead(userObjectId, false)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));
        return notificationMapper.toDTO(notification);
    }

    @Override
    public CreateNotificationResponseDTO createNotification(CreateNotificationRequestDTO notificationDTO) {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponseDTO(savedNotification);
    }

    @Override
    public NotificationDTO updateNotification(String id, UpdateNotificationDTO notificationDTO) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));

        notification.setMessage(notificationDTO.message());
        notification.setType(notificationDTO.type());
        notification.setRead(notificationDTO.read());

        return notificationMapper.toDTO(notificationRepository.save(notification));
    }

    @Override
    public NotificationDTO markAsRead(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));

        notification.setRead(true);
        return notificationMapper.toDTO(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));
        notificationRepository.deleteById(id);
    }

    @Override
    public long countUnreadNotifications(String userId) {
        ObjectId userObjectId = new ObjectId(userId);
        return notificationRepository.countByReceiverAndRead(userObjectId, false);
    }
}
