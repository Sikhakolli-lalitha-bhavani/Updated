package com.dcs.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dcs.dto.ResponseDTO;
import com.dcs.exception.NoEntityFoundException;
@Service
@Qualifier("responseServiceImpl")
public interface IResponseService {

	ResponseDTO  addResponse(ResponseDTO  response);
	ResponseDTO  removeResponse(Integer respId) throws NoEntityFoundException;

	
	Integer getNoOfVotesOnResponseByVoteType(String  voteType, Integer resId);

	Page<ResponseDTO> getAllResponses(int page, int size);

	ResponseDTO updateResponse(Integer id, ResponseDTO response);

}