package group.cards.mappers.impl;

import group.cards.domain.model.CardEntity;
import group.cards.dto.CardDto;
import group.cards.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapperImpl implements Mapper<CardDto, CardEntity> {

  private ModelMapper modelMapper;

  public CardMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public CardEntity mapTo(CardDto cardDto) {
    return modelMapper.map(cardDto, CardEntity.class);
  }

  @Override
  public CardDto mapFrom(CardEntity cardEntity) {
    return modelMapper.map(cardEntity, CardDto.class);
  }
}
