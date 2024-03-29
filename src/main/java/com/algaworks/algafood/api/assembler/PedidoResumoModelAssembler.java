package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>  {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;    
	
    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }
	
	public PedidoResumoModel toModel(Pedido pedido) {
	    PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
	    modelMapper.map(pedido, pedidoModel);
	    
	    if (algaSecurity.podePesquisarPedidos()) {
	    	pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
	    }
	   
	    if (algaSecurity.podeConsultarRestaurantes()) {
	    	pedidoModel.getRestaurante().add(
	    			algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
	    }

	    if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
	    	pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
	    }
	    
	    return pedidoModel;
	}
	
	public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}
	
}