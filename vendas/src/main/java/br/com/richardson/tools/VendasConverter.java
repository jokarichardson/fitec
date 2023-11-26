package br.com.richardson.tools;

import br.com.richardson.model.dto.ItemVendaDTO;
import br.com.richardson.model.dto.VendaDTO;
import br.com.richardson.model.entity.ItemVenda;
import br.com.richardson.model.entity.Venda;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VendasConverter {
    public static VendaDTO vendaToVendaDTO(Venda venda) {
        if (Objects.isNull(venda)) {
            return null;
        }
        return VendaDTO.builder()
                .id(venda.getId())
                .itensVenda(itemVendaToItemVendaDTO(venda.getItensVenda()))
                .valorTotal(venda.getValorTotal())
                .build();
    }
    
    public static List<ItemVendaDTO> itemVendaToItemVendaDTO(List<ItemVenda> itensVenda) {
        if (CollectionUtils.isEmpty(itensVenda)) {
            return null;
        }
        
        List<ItemVendaDTO> itemVendaDTOS = new ArrayList<>();
        
        itensVenda.forEach(i -> itemVendaDTOS.add(ItemVendaDTO.builder()
                .id(i.getId())
                .idVenda(i.getIdVenda())
                .idProduto(i.getIdProduto())
                .quantidade(i.getQuantidade())
                .valorUnitario(i.getValorUnitario())
                .valorTotal(i.getValorTotal())
                .idGarantia(i.getIdGarantia())
                .build()));
        
        return itemVendaDTOS; 
    }

    public static List<ItemVenda> itemVendaDTOToItemVenda(VendaDTO vendaDTO) {
        if (CollectionUtils.isEmpty(vendaDTO.getItensVenda())) {
            return null;
        }
        
        Venda venda = new Venda();
        venda.setId(vendaDTO.getId());
        venda.setItensVenda(new ArrayList<>());
        
        vendaDTO.getItensVenda().forEach(i -> venda.getItensVenda().add(new ItemVenda(i.getId(), i.getIdVenda(), i.getIdProduto(), i.getQuantidade(), i.getValorUnitario(), i.getValorTotal(), i.getIdGarantia())));
        
        return venda.getItensVenda();
    }
}
