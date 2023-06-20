package cop.project.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import cop.project.payment.dbo.DbSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;


@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public Integer getSequenceNumber(String sequenceName) {

        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seqNo", 1);

        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
                DbSequence.class);

        return !Objects.isNull(counter) ? counter.getSeqNo() : 1;
    }

}
