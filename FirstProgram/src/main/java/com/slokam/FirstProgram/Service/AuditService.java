package com.slokam.FirstProgram.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slokam.FirstProgram.Dao.AuditDao;
import com.slokam.FirstProgram.Pojo.AuditPojo;

@Service
public class AuditService implements IAuditService {

	@Autowired
	private AuditDao auditDao;
	@Override
	public void saveAudit(AuditPojo auditPojo)
	{
		auditDao.save(auditPojo);
	}

}
