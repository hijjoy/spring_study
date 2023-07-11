package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 추상화, 구체화에 모두 의존하는 문제점 -> DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
