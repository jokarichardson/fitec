package br.com.richardson.service;

import br.com.richardson.model.dto.VendaDTO;
import br.com.richardson.model.entity.Venda;
import br.com.richardson.repository.VendasRepository;
import br.com.richardson.tools.VendasConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class VendasService {
    
    private VendasRepository vendasRepository;
    List<VendaDTO> vendaDTOList;

    @Autowired
    public VendasService(List<VendaDTO> vendaDTOList, VendasRepository vendasRepository) {
        this.vendaDTOList = vendaDTOList;
        this.vendasRepository = vendasRepository;
    }

    public ResponseEntity<?> getVendas() {
        return ResponseEntity.ok(vendaDTOList);
        //return ResponseEntity.ok(vendasRepository.findAll().stream().map(VendasConverter::vendaToVendaDTO).collect(Collectors.toList()));
    }

    public ResponseEntity<?> getVendaById(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado!");
        }
        
        VendaDTO vendaDTO = vendaDTOList.stream().filter(v -> v.getId().toString().equalsIgnoreCase(id)).findFirst().orElse(null);
        
        //Venda venda = vendasRepository.findById(UUID.fromString(id)).orElse(null);

//        if (Objects.isNull(venda)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada.");
//        }
        if (Objects.isNull(vendaDTO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada.");
        }

//        return ResponseEntity.ok(VendasConverter.vendaToVendaDTO(venda));
        return ResponseEntity.ok(vendaDTO);
    }

    public ResponseEntity<?> saveVenda(VendaDTO vendaDTO) {
        vendaDTOList.add(vendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaDTO);
//        Venda venda = new Venda(vendaDTO.getId(), vendaDTO.getValorTotal(), VendasConverter.itemVendaDTOToItemVenda(vendaDTO));
//        Venda resultado = vendasRepository.save(venda);
//        return ResponseEntity.status(HttpStatus.CREATED).body(VendaDTO.builder()
//                .id(resultado.getId())
//                .itensVenda(VendasConverter.itemVendaToItemVendaDTO(resultado.getItensVenda()))
//                .valorTotal(resultado.getValorTotal())
//                .build());
    }

    public ResponseEntity<?> deleteVenda(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado.");

        }
        if (getVendaById(id).getStatusCode().is2xxSuccessful()) {
            //vendasRepository.deleteById(UUID.fromString(id));
            vendaDTOList.removeIf(v -> v.getId().toString().equalsIgnoreCase(id));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrado");
    }
}
