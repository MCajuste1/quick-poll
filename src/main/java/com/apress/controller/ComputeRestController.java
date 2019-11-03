package com.apress.controller;

import com.apress.com.apress.dto.OptionCount;
import com.apress.com.apress.dto.VoteResult;
import com.apress.domain.Vote;
import com.apress.repository.VoteRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ComputeRestController {

    @Inject
    private VoteRespository voteRespository;


    @RequestMapping(value = "/computeresult",method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId){
        VoteResult voteResult = new VoteResult(  );
        Iterable<Vote> allVotes = voteRespository.findbyPoll( pollId );


        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK  );
    }


}
