package com.listas.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.listas.dto.inputs.ItemInput;
import com.listas.dto.outputs.ItemOutput;
import com.listas.entities.ItemEntity;

@Component
public class ItemConvert {

	@Autowired
	private ModelMapper model;

	public ItemOutput entityToOutput(ItemEntity itemEntity) {
		return model.map(itemEntity, ItemOutput.class);
	}

	public List<ItemOutput> entityToOutput(List<ItemEntity> itemsEntity) {
		return itemsEntity.stream().map(item -> this.entityToOutput(item)).collect(Collectors.toList());
	}

	public ItemEntity inputToEntity(ItemInput itemInput) {
		return model.map(itemInput, ItemEntity.class);
	}

	public void copyDataInputToEntity(ItemInput itemInput, ItemEntity itemEntity) {
		model.map(itemInput, itemEntity);
	}
}
