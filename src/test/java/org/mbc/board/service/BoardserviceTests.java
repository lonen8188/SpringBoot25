package org.mbc.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.domain.Board;
import org.mbc.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardserviceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        log.info("등록용 테스트 서비스 실행중......");
        log.info(boardService.getClass().getName()); // 객체 생성용 테스트
        //org.mbc.board.service.BoardServiceImpl$$SpringCGLIB$$0

        BoardDTO boardDTO = BoardDTO.builder()
                .title("서비스에서만든제목")
                .content("서비스에서만든내용")
                .writer("서비스님")
                .build(); // 세터 대신 @Builder

        Long bno = boardService.register(boardDTO); // 서비스 구현메서드로 동작함

        log.info("테스트 결과 bno:" + bno);  // 테스트 결과 bno:101

        //Hibernate:
        //    insert
        //    into
        //        board
        //        (content, moddate, regdate, title, writer)
        //    values
        //        (?, ?, ?, ?, ?)
    }
    
    @Test
    public void testModify(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("서비스에서수정된제목")
                .content("서비스에서수정된내용")
                .build();
        boardService.modify(boardDTO); // 프론트에서 객체가 넘어가 수정이되었는 테스트

        //Hibernate:
        //    select
        //        b1_0.bno,
        //        b1_0.content,
        //        b1_0.moddate,
        //        b1_0.regdate,
        //        b1_0.title,
        //        b1_0.writer
        //    from
        //        board b1_0
        //    where
        //        b1_0.bno=?
        //Hibernate:
        //    update
        //        board
        //    set
        //        content=?,
        //        moddate=?,
        //        title=?,
        //        writer=?
        //    where
        //        bno=?
    }
}
