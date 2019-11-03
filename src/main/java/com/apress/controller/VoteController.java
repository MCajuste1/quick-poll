package com.apress.controller;

//Creates a voting system
import com.apress.domain.Vote;
import com.apress.repository.VoteRespository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;

@RestController
public class VoteController {

    @Inject
    private VoteRespository voteRespository;


    @RequestMapping(value = "/poll/{pollId}/vote", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
         vote = voteRespository.save( vote );


         // Set the headers for new resource

        HttpHeaders responseHeaders = new HttpHeaders(  );
        responseHeaders.setLocation( ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" ).buildAndExpand( vote.getId() ).toUri() );
        return new ResponseEntity<>( null, responseHeaders, HttpStatus.CREATED );
    }

    @RequestMapping(value = "/polls/{pollId}votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes (@PathVariable Long pollId){
        return voteRespository.findbyPoll( pollId );
    }

}
