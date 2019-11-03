package com.apress.controller;


import com.apress.com.apress.exception.ResourceNotFoundException;
import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;


    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById( pollId );
        if (!poll.isPresent()) {
            throw new ResourceNotFoundException( "Poll with id" + pollId + "not found" );

        }
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>( pollRepository.findAll(), HttpStatus.OK );
    }


    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {

        poll = pollRepository.save( poll );

        // Sets the location header for the new source

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" ).buildAndExpand( poll.getId() ).toUri();
        responseHeaders.setLocation( newPollUri );
        return new ResponseEntity<>( null, responseHeaders, HttpStatus.CREATED );
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll( pollId );
        Optional<Poll> p = pollRepository.findById( pollId );
        return new ResponseEntity<>( p, HttpStatus.OK );
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)

    //@RequestMapping
    public ResponseEntity<?> updatepoll(Poll poll, @PathVariable Long pollId) {
        verifyPoll( pollId );
        pollRepository.save( poll );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        //verifyPoll( pollId );
        pollRepository.deleteById( pollId );
        return new ResponseEntity<>( HttpStatus.OK );

    }

}