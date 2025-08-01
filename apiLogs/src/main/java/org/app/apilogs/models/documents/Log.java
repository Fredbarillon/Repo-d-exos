package org.app.apilogs.models.documents;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "logs")
public class Log {
    @Id
    private String id;

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotBlank(message = "Source cannot be blank")
    private String source;

    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    @NotNull(message = "Level cannot be null")
    private EnumLevel level;
}
