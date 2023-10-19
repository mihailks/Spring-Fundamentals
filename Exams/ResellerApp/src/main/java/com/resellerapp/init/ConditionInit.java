package com.resellerapp.init;

import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.enums.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class ConditionInit implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public ConditionInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (conditionRepository.count() == 0) {
            List<ConditionEntity> conditions = new ArrayList<>();

            Arrays.stream(ConditionNameEnum.values())
                    .forEach(conditionNameEnum -> {
                        ConditionEntity condition = new ConditionEntity();
                        condition.setName(conditionNameEnum);
                        conditions.add(condition);
                    });

            conditionRepository.saveAll(conditions);
        }
    }
}
