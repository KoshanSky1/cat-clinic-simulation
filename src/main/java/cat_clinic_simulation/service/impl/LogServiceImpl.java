package cat_clinic_simulation.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cat_clinic_simulation.model.MessageLog;
import cat_clinic_simulation.repository.MessageLogRepository;
import cat_clinic_simulation.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService {
    private final MessageLogRepository repository;

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public void saveLogMessage(String message) {
        MessageLog messageLog = new MessageLog();
        messageLog.setMessage(message);
        repository.save(messageLog);
        log.info("Added a new logMessage: '{}'", message);
    }
}