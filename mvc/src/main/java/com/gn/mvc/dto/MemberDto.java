package com.gn.mvc.dto;

import java.time.LocalDateTime;

import com.gn.mvc.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
	private Long member_no;
	private String member_id;
	private String member_pw;
	private String member_name;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	private String member_role;
	// 굳인 안넣어도 잘 들어가는 이유? spring이 친철해서 되는거
	public Member toEntity() {
		return Member.builder()
				.memberNo(member_no)
				.memberId(member_id)
				.memberPw(member_pw)
				.memberName(member_name)
				.memberRole(member_role)
//				.regDate(reg_date)
//				.modDate(mod_date)
				.build();
	}
	// 위에는 필요없고 아래에만 추가하면 됨
	public MemberDto toDto(Member member) {
		return MemberDto.builder()
				.member_no(member.getMemberNo())
				.member_id(member.getMemberId())
				.member_pw(member.getMemberPw())
				.member_name(member.getMemberName())
				.reg_date(member.getRegDate())
				.mod_date(member.getModDate())
				.build();
	}
}
