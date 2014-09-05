package cn.mob.anlystic.game.web.report.usage.web;

import cn.mob.anlystic.game.web.report.usage.domain.Usage;
import cn.mob.anlystic.game.web.report.usage.repository.UsageRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
@RestController
@RequestMapping("/usage")
public class UsageController {

    @Resource
    private UsageRepository usageRepository;

    @RequestMapping("/startDate/{startDate}/endDate/{endDate}")
    public List<Usage> list(@PathVariable String startDate, @PathVariable String endDate) {
        return usageRepository.findByDateBetween(startDate, endDate);
    }
}
