package br.com.richardson.service;

import br.com.richardson.model.dto.GarantiaDTO;
import br.com.richardson.model.entity.Garantia;
import br.com.richardson.repository.GarantiasRepository;
import br.com.richardson.tools.GarantiasConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GarantiasService {
    @Autowired
    GarantiasRepository garantiasRepository;
    
    @Autowired
    List<GarantiaDTO> garantiaDTOList;

    public ResponseEntity<?> getGarantias() {
        return ResponseEntity.ok(garantiasRepository.findAll().stream().map(GarantiasConverter::garantiaToGarantiaDTO).collect(Collectors.toList()));
    }

    public ResponseEntity<?> getGarantiaById(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado.");
        }
        Garantia garantia = garantiasRepository.findById(UUID.fromString(id)).orElse(null);

        if (Objects.isNull(garantia)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garantia não encontrada.");
        }
        
        return ResponseEntity.ok(GarantiasConverter.garantiaToGarantiaDTO(garantia));
    }

    public ResponseEntity<?> saveGarantia(GarantiaDTO garantiaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GarantiasConverter.garantiaToGarantiaDTO(garantiasRepository.save(GarantiasConverter.garantiaDTOToGarantia(garantiaDTO))));
    }

    public ResponseEntity<?> deleteGarantia(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado.");
        }
        if (getGarantiaById(id).getStatusCode().is2xxSuccessful()) {
            garantiasRepository.deleteById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garantia não encontrada.");
    }
}
