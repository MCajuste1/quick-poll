package com.apress.repository;


import com.apress.domain.Poll;
import com.apress.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRespository extends CrudRepository<Vote,Long> {


    @Query(value = "select v.* from Option o, Vote v where o.POLL_ID = ?1 AND v.OPTION_ID = o.OPTION_ID",nativeQuery = true)
     public Iterable<Vote> findbyPoll(Long pollId);

}
