package com.cricket.project.repository;

import com.cricket.project.model.InningStats;
import com.cricket.project.model.Match;
import com.cricket.project.model.Team;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InningRepository {
    private final MongoTemplate mongoTemplate;

    public InningRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public InningStats saveInning(InningStats inningStats) {
        return mongoTemplate.save(inningStats);
    }

    public InningStats findInningByMatchId(int matchId,int battingTeamId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("matchId").is(matchId).and("battingTeamId").is(battingTeamId));
        return mongoTemplate.findOne(query,InningStats.class);
    }
}
