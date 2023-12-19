package com.dcs.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dao.ResponseDao;
import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Response;
import com.dcs.exception.NoEntityFoundException;

@Service
public class ResponseServiceImpl implements IResponseService {

	@Autowired
	private ResponseDao responseDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override

	public ResponseDTO addResponse(ResponseDTO response) {
		Response entity1 = modelMapper.map(response, Response.class);
		entity1 = responseDao.save(entity1);
		
		
		
		return modelMapper.map(entity1, ResponseDTO.class);

	}

	@Override

	public ResponseDTO updateResponse(Integer id ,ResponseDTO response) {
		Optional<Response> entity2 =  responseDao.findById(id);
		if(!entity2.isPresent()) {
			throw new NoEntityFoundException("No Response Found  for this Id, Please create new one");
		}
		Response entity1 = entity2.get();
		entity1.setAnswer(response.getAnswer());
		entity1.setRespDateTime(response.getRespDateTime());
		entity1.setPost(modelMapper.map(response.getPost(), Post.class));
		entity1.setDeveloper(modelMapper.map(response.getDeveloper(), Developer.class));
		entity1 = responseDao.save(entity1);
		return modelMapper.map(entity1, ResponseDTO.class);

	}

	@Override

	public ResponseDTO removeResponse(Integer respId) throws com.dcs.exception.NoEntityFoundException {

		Response existingResponseEntity = responseDao.findById(respId)
				.orElseThrow(() -> new com.dcs.exception.NoEntityFoundException("Wrong response Id"));

		responseDao.deleteById(respId);

		return modelMapper.map(existingResponseEntity, ResponseDTO.class);

	}

	@Override
	public Page<ResponseDTO> getAllResponses(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Response> responsesPage = responseDao.findAll(pageable);
		
		Page<ResponseDTO> responseDto = responsesPage
				.map(re -> modelMapper.map(re, ResponseDTO.class));
		return responseDto;	}

	@Override
	public Integer getNoOfVotesOnResponseByVoteType(String voteType, Integer resId) {
		// TODO Auto-generated method stub
		return null;
	}

}
