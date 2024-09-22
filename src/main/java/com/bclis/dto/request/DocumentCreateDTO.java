package com.bclis.dto.request;

import com.bclis.persistence.entity.DocumentEntity.DocumentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreateDTO {

    private Long userId;
    private String name;
    private byte[] content;
    private DocumentState state;
    private Long typeId;
}
