package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootJpaApplication.class)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void save() {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getUsername(), member.getUsername());
        assertEquals(findMember, member);
        System.out.println("findMember == member: " + (findMember == member));
    }
}