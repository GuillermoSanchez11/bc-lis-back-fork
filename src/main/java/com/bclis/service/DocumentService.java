package com.bclis.service;

import com.bclis.dto.DocumentCreateDTO;
import com.bclis.dto.DocumentResponseDTO;
import com.bclis.persistence.entity.DocumentEntity;
import com.bclis.persistence.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    public DocumentResponseDTO createDocument(DocumentCreateDTO documentCreateDTO) {
        // Convertir el DTO a la entidad
        DocumentEntity documentEntity = modelMapper.map(documentCreateDTO, DocumentEntity.class);

        // Establecer la fecha de creación
        documentEntity.setCreatedAt(LocalDateTime.now());

        // Guardar el documento en la base de datos
        DocumentEntity savedDocument = documentRepository.save(documentEntity);

        // Convertir la entidad guardada en un DTO de respuesta
        return modelMapper.map(savedDocument, DocumentResponseDTO.class);
    }

    public Optional<DocumentResponseDTO> getDocumentById(Long id) {
        return documentRepository.findById(id)
                .map(document -> modelMapper.map(document, DocumentResponseDTO.class));
    }

    public List<DocumentResponseDTO> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(document -> modelMapper.map(document, DocumentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DocumentResponseDTO> updateDocument(Long id, DocumentCreateDTO documentCreateDTO) {
        return documentRepository.findById(id).map(document -> {
            // Mapear los cambios del DTO a la entidad existente
            modelMapper.map(documentCreateDTO, document);

            // Establecer la fecha de actualización
            document.setUpdatedAt(LocalDateTime.now());

            // Guardar los cambios
            DocumentEntity updatedDocument = documentRepository.save(document);

            // Convertir la entidad actualizada a DTO de respuesta
            return modelMapper.map(updatedDocument, DocumentResponseDTO.class);
        });
    }

    // Método para eliminar un documento
    public boolean deleteDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
