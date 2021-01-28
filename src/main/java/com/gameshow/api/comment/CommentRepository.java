package com.gameshow.api.comment;

import com.gameshow.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    Page<Comment> findAllByGameIdAndParentNull(Long id, Pageable pageable);

    Page<Comment> findAllByGameIdOrderByLike(Long id, Pageable pageable);

    Page<Comment> findAllByParentId(Long parentId, Pageable pageable);

    @Override
    void deleteById(Long commentId);

    int countByParentId(Long parentId);

}
