package cn.mob.anlystic.game.web.report.usage.repository;

import cn.mob.anlystic.game.web.report.usage.domain.Usage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
public interface UsageRepository extends MongoRepository<Usage, String> {

    public List<Usage> findByDateBetween(String from, String to);

}

