package com.gn.mvc.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gn.mvc.entity.ChatRoom;
import com.gn.mvc.repository.ChatRoomRepository;
import com.gn.mvc.security.MemberDetails;
import com.gn.mvc.specification.ChatRoomSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
	
	
	private final ChatRoomRepository repository;
	
	public ChatRoom selectChatRoomOne(Long id) {
		
		return repository.findById(id).orElse(null);
	}
	
	
	public List<ChatRoom> selectChatRoomAll(){
		Authentication authentication
		=SecurityContextHolder.getContext().getAuthentication();
		
		MemberDetails md = (MemberDetails)authentication.getPrincipal();
		
		Specification<ChatRoom> spec = (root,query,criteriaBuilder)->null;
		spec = spec.and(ChatRoomSpecification.formMemberEquals(md.getMember()));
		// toMemberEquls > form 2개 잘못씀
		spec = spec.or(ChatRoomSpecification.toMemberEquals(md.getMember()));
		
		List<ChatRoom> list = repository.findAll(spec);
		return list;
	}
}
