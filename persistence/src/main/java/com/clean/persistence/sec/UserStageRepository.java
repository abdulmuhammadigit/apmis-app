package com.clean.persistence.sec;

import java.util.List;

import com.clean.domain.entity.sec.UserStage;
import com.clean.domain.entity.sec.UserStagePK;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface UserStageRepository extends JpaRepository<UserStage,UserStagePK>,JpaSpecificationExecutor<UserStage> {
    List<UserStage> findAllByUserId(Long userId);
    
    @Query("select us, s from UserStage us join Stage s on us.stageId = s.id join WorkFlow w on s.workFlowId = w.id where us.userId=:userId and w.entityId =:entityId")
    List<UserStage> findAllByUserIdAndEntityId( @Param("userId") Long userId,@Param("entityId") Integer entityId);
}
