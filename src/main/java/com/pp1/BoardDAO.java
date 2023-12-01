package com.pp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BoardDAO {
    private static final String BOARD_INSERT = "INSERT INTO board (title, writer, content, regdate, cnt) VALUES (?, ?, ?, ?, ?)";
    private static final String BOARD_DELETE = "DELETE FROM board WHERE seq=?";
    private static final String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";
    private static final String BOARD_GET = "SELECT * FROM board WHERE seq=?";
    private static final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertBoard(BoardVO vo) {
        try {
            Date currentDate = new Date();
            return jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent(), currentDate, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteBoard(int seq) {
        try {
            jdbcTemplate.update(BOARD_DELETE, seq);
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }

        return 1;
    }

    public int updateBoard(BoardVO vo) {
        try {
            return jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getSeq());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public BoardVO getBoard(int seq) {
        try {
            return jdbcTemplate.queryForObject(BOARD_GET, new Object[]{seq}, new BeanPropertyRowMapper<>(BoardVO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BoardVO> getBoardList() {
        try {
            return jdbcTemplate.query(BOARD_LIST, new BeanPropertyRowMapper<>(BoardVO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("빈배열");
        return new ArrayList<>();
    }
}
