package com.gn.mvc.specification;

import org.springframework.data.jpa.domain.Specification;

import com.gn.mvc.entity.ChatMsg;
import com.gn.mvc.entity.ChatRoom;

public class ChatMsgSpecification {

	public static Specification<ChatMsg> formChatRoomEquals(ChatRoom chatRoom){
		return (root,query,criteriaBulder)->
			criteriaBulder.equal(root.get("ChatRoom"),chatRoom);
	}
}
