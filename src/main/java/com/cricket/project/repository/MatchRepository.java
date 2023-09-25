package com.cricket.project.repository;

import com.cricket.project.model.Match;
import com.cricket.project.model.MatchScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match,Integer> {
    Match findById(int matchId);
}
