package com.srarts.config;

import com.srarts.product.entity.TestEntity;
import com.srarts.product.repository.TestEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TestEntityRepository repository;

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            TestEntity entity = new TestEntity();
            entity.setName("Auditing Test");

            repository.save(entity);
        }

    }
}