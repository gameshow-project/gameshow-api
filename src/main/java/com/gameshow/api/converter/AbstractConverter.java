package com.gameshow.api.converter;

import com.gameshow.api.comment.CommentNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractConverter<E, D> {

    D entityToDto(E entity);

    E dtoToEntity(D dto) throws CommentNotFound;

    default List<D> listEntityToDto(List<E> entities){
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default Page<D> pageEntityToDto(Page<E> entity) {
        return new PageImpl<>(listEntityToDto(entity.getContent()), entity.getPageable(), entity.getTotalElements());
    }

}
