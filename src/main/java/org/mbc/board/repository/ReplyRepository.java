package org.mbc.board.repository;


import org.mbc.board.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 데이터베이스에서 CRUD를 처리하는 JPA                객체    pk타입

    // .save() pk가 없으면 insert, pk가 있으면 update
    // .findById(rno) 1개의 댓글을 가져옴
    // .deleteById(rno) 삭제용

    // 댓글도 페이징 처리가 필요함
    @Query("select r from Reply r where r.board.bno = :bno ")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
    // listOfBoard(100, pageable);

}
