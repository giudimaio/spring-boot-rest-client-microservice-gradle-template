package com.__CORP_NAME__.it.__APP_NAME__.data.repository;

import com.__CORP_NAME__.it.__APP_NAME__.model.entity.business.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {
}
