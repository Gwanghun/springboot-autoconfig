package hello.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    public final JdbcTemplate template;

    public MemberRepository(JdbcTemplate template) {
        this.template = template;
    }

    // 예제
    public void initTable() {
        template.execute("create table member(memberId varchar primary key, name varchar)");
    }

    public void save(Member member) {
        template.update("insert into member(memberId, name) values(?, ?)",
                member.getMemberId(),
                member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject("select * from member where memberId = ?",
                BeanPropertyRowMapper.newInstance(Member.class),        // Member.class를 이용해서 Member 객체를 생성(쿼리 결과 값이 객채로 매핑)
                memberId);
    }

    public List<Member> findAll() {
        return template.query("select * from member",
                BeanPropertyRowMapper.newInstance(Member.class));
    }



}
