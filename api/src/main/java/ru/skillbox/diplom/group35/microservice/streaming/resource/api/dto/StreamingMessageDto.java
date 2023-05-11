package ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

/**
 * StreamingMessageDto
 *
 * @author Marat Safagareev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StreamingMessageDto<T> extends BaseDto {
  private String type;
  private UUID accountId;
  private T data;
}
