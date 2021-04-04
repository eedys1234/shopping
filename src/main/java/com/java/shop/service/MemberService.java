package com.java.shop.service;

import com.java.shop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.shop.repository.MemberRepository;
import com.java.shop.dto.MemberRequestDto;
import com.java.shop.dto.MemberResponeDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberRequestDto requestDto) {

        Member member = requestDto.toEntity();
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {

        List<Member> members = memberRepository.findByName(member.getName());
        if(members.size() > 0) {
            throw new IllegalStateException("duplicate name");
        }
    }

    public List<MemberResponeDto> findAll() {
        return memberRepository.findAll().stream().map(MemberResponeDto::new)
                .collect(Collectors.toList());
    }

    public MemberResponeDto findOne(Long id) {
        return new MemberResponeDto(memberRepository.findOne(id));
    }
}
