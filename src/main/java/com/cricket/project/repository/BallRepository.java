package com.cricket.project.repository;

import com.cricket.project.model.Ball;
import com.cricket.project.model.PlayerScoreCard;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class BallRepository {
    private final MongoTemplate mongoTemplate;
    BallRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Data
    public static class Result {
        private int result;
    }

    public void save(Ball ball)
    {
        mongoTemplate.save(ball);
    }
    public int wickets(int matchId,int battingTeamId)
    {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("matchId").is(matchId).and("battingTeamId").is(battingTeamId)
                ),
                Aggregation.group().sum("wickets").as("result")
        );
        AggregationResults<Result> result = mongoTemplate.aggregate(
                aggregation, "ball", Result.class
        );

        Result aggregatedResult = result.getUniqueMappedResult();

        return aggregatedResult.getResult();
    }

    public int playerWicketsTakenForMatch(int matchId,int bowlerId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("matchId").is(matchId).and("bowlerId").is(bowlerId)
                ),
                Aggregation.group().sum("wickets").as("result")
        );
        AggregationResults<Result> result = mongoTemplate.aggregate(
                aggregation, "ball", Result.class
        );
        Result aggregatedResult = result.getUniqueMappedResult();
        if (aggregatedResult != null) {
            return aggregatedResult.getResult();
        }
        return 0;
    }

    public int playerRunsScoredForMatch(int matchId,int playerId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("matchId").is(matchId).and("strikerId").is(playerId)
                ),
                Aggregation.group().sum("runs").as("result")
        );
        AggregationResults<Result> result = mongoTemplate.aggregate(
                aggregation, "ball", Result.class
        );
        Result aggregatedResult = result.getUniqueMappedResult();
        if (aggregatedResult != null) {
            return aggregatedResult.getResult();
        }
        return 0;
    }

    public int playerNumberOf4(int matchId,int playerId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("matchId").is(matchId).and("strikerId").is(playerId)
                ),
                Aggregation.project()
                        .andExpression("cond(eq(runs, 4), 1, 0)").as("isFour"),
                Aggregation.group().sum("isFour").as("result")
        );
        AggregationResults<Result> result = mongoTemplate.aggregate(
                aggregation, "ball", Result.class
        );
        Result aggregatedResult = result.getUniqueMappedResult();
        if (aggregatedResult != null) {
            return aggregatedResult.getResult();
        }
        return 0;
    }

    public int playerNumberOf6(int matchId,int playerId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("matchId").is(matchId).and("strikerId").is(playerId)
                ),
                Aggregation.project()
                        .andExpression("cond(eq(runs, 6), 1, 0)").as("isSix"),
                Aggregation.group().sum("isSix").as("result")
        );
        AggregationResults<Result> result = mongoTemplate.aggregate(
                aggregation, "ball", Result.class
        );
        Result aggregatedResult = result.getUniqueMappedResult();
        if (aggregatedResult != null) {
            return aggregatedResult.getResult();
        }
        return 0;
    }


}


//    player runs scored 0
//        wicket taken 5
//        InningStats(matchId=2, battingTeamId=2, inningNumber=1, runs=208, wickets=5)
//        InningStats(matchId=2, battingTeamId=1, inningNumber=2, runs=212, wickets=7)
//        winner 1

//    player runs scored 34
//        wicket taken 0