package ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

/**
 * MessageDto
 *
 * @author Marat Safagareev
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageDto extends BaseDto {
  private ZonedDateTime time;
  private UUID authorId;
  private UUID recipientId;
  private String messageText;
  private String readStatus;
  private UUID dialogId;
}
