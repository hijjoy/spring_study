package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 실행순서가 보장이 되어있지 않기 때문에 하나 실행되고 store지우기 위해 설정
    // test간 서로 의존관계가 없어야함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("hyewon");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result : "+ (result == member));
        // Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result); // option + enter로 static import
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findByName("test1").get(); // .get()사용시 Optional 벗겨서 결과값 반환
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
