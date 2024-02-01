package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    // class에서 ctrl shift T 누르면 test 파일 자동생성

    private final MemberRepository memberRepository;

    @Autowired // 스프링컨테이어에 있는 레포지토리 서비스에 주입해줌
    public MemberService(MemberRepository memberRepository){
        // 외부에서 넣어주도록
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // findByName이라는 로직이 나올 때는 메서드로 뽑는게 좋다
        // 단축키: ctrl shift alt T
        validateDuplicateMember(member); // 중복회원 검증
        // ctrl alt v return
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // optional을 이쁘게 반환하는 방법
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
