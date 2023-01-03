package com.algaworks.algafood.api.assembler;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);
    }    
    
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModel formaPagamentoModel = 
                createModelWithId(formaPagamento.getId(), formaPagamento);
        
        modelMapper.map(formaPagamento, formaPagamentoModel);
        
        formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        
        return formaPagamentoModel;
    }
    
    public  CollectionModel<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> entities) {
    	 return super.toCollectionModel(entities)
    	            .add(algaLinks.linkToFormasPagamento());
    }
    
}
