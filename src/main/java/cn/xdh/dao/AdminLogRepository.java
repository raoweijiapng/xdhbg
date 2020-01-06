package cn.xdh.dao;

import cn.xdh.entity.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminLogRepository extends JpaSpecificationExecutor<AdminLog>,JpaRepository<AdminLog, Integer> {


}
