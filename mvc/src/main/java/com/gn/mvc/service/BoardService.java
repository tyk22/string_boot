package com.gn.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gn.mvc.dto.AttachDto;
import com.gn.mvc.dto.BoardDto;
import com.gn.mvc.dto.PageDto;
import com.gn.mvc.dto.SearchDto;
import com.gn.mvc.entity.Attach;
import com.gn.mvc.entity.Board;
import com.gn.mvc.repository.AttachRepository;
import com.gn.mvc.repository.BoardRepository;
import com.gn.mvc.specification.BoardSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
//	@Autowired
//	BoardRepository repository;
	
	private final BoardRepository repository;
	private final AttachRepository attachRepository;
	public Board selectBoardOne(Long id) {
		// 조회된게 없다면 null
		return repository.findById(id).orElse(null);
	}
	
	public Page<Board> selectBoardAll(SearchDto searchDto, PageDto pageDto){

		// 시작 , 한페이지에 몇개씩, 
		Pageable pageable = PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage(), Sort.by("regDate").descending());
		if(searchDto.getOrder_type()==2) {
			pageable = PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage(), Sort.by("regDate").ascending());
		}
		
		Specification<Board> spec = (root,query,CriteriaBuilder) -> null;
		if(searchDto.getSearch_type() == 1) {
			spec = spec.and(BoardSpecification.boardTitleContains(searchDto.getSearch_text()));
		}else if(searchDto.getSearch_type() == 2) {
			spec = spec.and(BoardSpecification.boardContentContains(searchDto.getSearch_text()));
		}else if(searchDto.getSearch_type() == 3) {
			spec = spec.and(BoardSpecification.boardTitleContains(searchDto.getSearch_text()))
					.or(BoardSpecification.boardContentContains(searchDto.getSearch_text()));
		}
		Page<Board> list = repository.findAll(spec,pageable);
		return list;
//		List<Board> list = new ArrayList<Board>();
//		if(searchDto.getSearch_type()==1) {
//			// 제목 기준
//			//list=repository.findByBoardTitleContaining(searchDto.getSearch_text());
//			
//			//
//			list=repository.findByTitleLike(searchDto.getSearch_text());
//		}else if(searchDto.getSearch_type()==2) {
//			// 내용 기준
//			//list=repository.findByBoardContentContaining(searchDto.getSearch_text());
//			//
//			list=repository.findByContentLike(searchDto.getSearch_text());
//		}else if(searchDto.getSearch_type()==3) {
//			// 제목 또는 내용 기준
////			list=repository.findByBoardContentContainingOrBoardTitleContaining(searchDto.getSearch_text(),
////					searchDto.getSearch_text());
//			//
//			list=repository.findByTitleOrContentLike(searchDto.getSearch_text(), searchDto.getSearch_text());
//		}else {
//			// WHERE절 없이 검색
//			list = repository.findAll();
//		}
//		return list;
		
//		Sort sort = Sort.by("regDate").descending();
//		if(searchDto.getOrder_type()==2) {
//			sort=Sort.by("regDate").ascending();
//		}
		
	}
	@Transactional(rollbackFor = Exception.class)
	public int createBoard(BoardDto dto, List<AttachDto> attachList) {
		int result = 0;
		try {
			// 1. Board 엔티티 insert
			Board entity = dto.toEntity();
			Board saved = repository.save(entity);
			// 2. insert 결과로 반환받은 PK
			Long boardNo = saved.getBoardNo();
			// 3. attachList에 데이터가 있는 경우
			if(attachList.size()!=0) {
				for(AttachDto attachDto : attachList) {
					attachDto.setBoard_no(boardNo);
					Attach attach = attachDto.toEntity();
					// 4. Attach 엔티티 insert
					attachRepository.save(attach);
				}
			}			
			result = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return result;
		// 1. 매개변수 dto -> entity 로 변경
//		Board parm = Board.builder()
//				.boardTitle(dto.getBoard_title())
//				.boardContent(dto.getBoard_content())
//				.build();
		//Board param = dto.toEntity();
		// 2. Repository의 save() 메소드 호출
		//Board result = repository.save(param);
		// 3. 결과 entity > dto
		//return new BoardDto().toDto(result);
	}
	// 내가쓴거
	public BoardDto updateBoard(BoardDto dto) {
		Board param = dto.toEntity();
		Board result = repository.save(param);
		return new BoardDto().toDto(result);
	}
	
	public int deleteBoard(Long id) {
		int result = 0;
		try {
			Board target = repository.findById(id).orElse(null);
			if(target != null) {
				repository.deleteById(id);
			}
			result = 1;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
//	public int deleteBoard(BoardDto param) {
//		int result = 0;
//		Long boardNo = param.getBoard_no();
//		System.out.println(param);
//		Board target = repository.findById(param.getBoard_no()).orElse(null);
//		if(target!=null) {
//			try {
//				repository.deleteById(boardNo);
//				if(repository.findById(boardNo).isPresent()) {
//					result =1;
//				}
//			}catch(Exception e) {
//				result =0;
//			}
//		}
//		return result;
//	}
	// 강사님 코드 
//	public Board updateBoard(BoardDto param) {
//		Board result = null;
//		// 1. @Id를 쓴 필드를 기준으로 타겟 조회
//		Board target = repository.findById(param.getBoard_no()).orElse(null);
//		// 2. 타겟이 존재하는 경우 업데이트
//		if(target != null) {
//			result = repository.save(param.toEntity());
//		}
//		return result;
//	}
	
//	public int updateBoard(BoardDto dto) {
//		int result =0;
//		Board param = dto.toEntity();
//		try {
//			if(repository.save(param).getBoardNo()!=null) {
//				
//			};
//			
//		}catch (Exception e){
//			
//		}
//		return new BoardDto().toDto(result);
//	}
}
