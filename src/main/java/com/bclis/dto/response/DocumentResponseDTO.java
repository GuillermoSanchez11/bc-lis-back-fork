package com.bclis.dto.response;

import com.bclis.persistence.entity.DocumentEntity.DocumentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponseDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String name;
    private byte[] content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DocumentState state;
    private Long typeId;
    private String typeName;
}
