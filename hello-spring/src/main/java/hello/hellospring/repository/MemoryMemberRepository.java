package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store =new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프 돌깅 ~
                .filter(member -> member.getName().equals(name))
                .findAny(); // 루프돌다가 같으면 바로 반환, 다 돌앗는데 없으면(null이면) optional로 반환
    }

    @Override
    public List<Member> findAll() { // List형태로 반환하는게 편리
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
