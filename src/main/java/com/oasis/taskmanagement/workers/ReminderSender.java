package com.oasis.taskmanagement.workers;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.TaskReminder;
import com.oasis.taskmanagement.model.enums.ReminderStatus;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import com.oasis.taskmanagement.repositories.ReminderRepository;
import com.oasis.taskmanagement.repositories.TaskRepository;
import com.oasis.taskmanagement.repositories.dynamic_search_query_builder.ReminderSearchSpecification;
import com.oasis.taskmanagement.services.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.KeyValuePair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ReminderSender {

    private final static Logger logger = LoggerFactory.getLogger(ReminderSender.class);
    private final ReminderRepository reminderRepository;
    private final ReminderSearchSpecification reminderSearchSpecification;
    private final ReminderService reminderService;
    private final TaskRepository taskRepository;

    private final ExecutorService executorService;

    public ReminderSender(ReminderRepository reminderRepository, ReminderSearchSpecification reminderSearchSpecification, ReminderService reminderService, TaskRepository taskRepository) {
        this.reminderRepository = reminderRepository;
        this.reminderSearchSpecification = reminderSearchSpecification;
        this.reminderService = reminderService;
        this.taskRepository = taskRepository;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @Scheduled(fixedRate = 60000*10) // Check for due reminders every 10 minute
    public void sendReminders() {
        ReminderRequestDto reminderForDueTasks = new ReminderRequestDto();
        reminderForDueTasks.setStatus(ReminderStatus.ACTIVE);
        reminderForDueTasks.setTime(OffsetDateTime.now(Clock.systemUTC()));
        List<TaskReminder> dueReminders = reminderRepository.findAll(
                reminderSearchSpecification.createSearchSpecificationFrom(reminderForDueTasks),
                0,
                100
        );
        if (dueReminders != null && !dueReminders.isEmpty()) {
            dueReminders.stream()
                    .filter(Objects::nonNull)
                    .forEach(reminder -> executorService.submit(() -> sendReminder(reminder)));
        }
    }

    private void sendReminder(TaskReminder reminder) {
        TaskEntity taskEntity = taskRepository.findById(reminder.getTask().getId())
                .orElse(null);
        if (taskEntity != null && taskEntity.getUser() != null &&
                taskEntity.getStatus().equals(TaskStatus.PENDING)) {
            logger.info("Sending reminder", new KeyValuePair("task", reminder.getTask().getName()));
            reminderService.sendReminder(taskEntity.getUser().getEmail(), reminder.getMessage());
            logger.info("Reminder sent successfully", new KeyValuePair("task", reminder.getTask().getName()));
        } else if (taskEntity != null && taskEntity.getStatus().equals(TaskStatus.COMPLETED)) {
            reminder.setStatus(ReminderStatus.COMPLETED);
            reminderRepository.save(reminder);
        } else if (taskEntity != null && taskEntity.getStatus().equals(TaskStatus.CANCELED)) {
            reminder.setStatus(ReminderStatus.CANCELED);
            reminderRepository.save(reminder);
        }
    }
}
