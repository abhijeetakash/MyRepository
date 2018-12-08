package com.slokam.FirstProgram.Util;

import java.beans.PropertyDescriptor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.slokam.FirstProgram.Pojo.AuditPojo;
import com.slokam.FirstProgram.Service.IAuditService;


@Component
public class AuditUtil {
	 
	@Autowired
	private IAuditService iauditService;
	
	public void audit(Object oldObject, Object newObject)
	{
		BeanWrapper oldWrapper = PropertyAccessorFactory.forBeanPropertyAccess(oldObject);
		BeanWrapper newWrapper = PropertyAccessorFactory.forBeanPropertyAccess(newObject);
		PropertyDescriptor[] descriptor  = oldWrapper.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : descriptor)
		{
			String propertyName = propertyDescriptor.getName();
			String newvalue = newWrapper.getPropertyValue(propertyName).toString();
			String oldvalue = oldWrapper.getPropertyValue(propertyName).toString();
			
			if(!newvalue.equals(oldvalue))
			{
			AuditPojo auditPojo = new AuditPojo();
			auditPojo.setPropertyName(propertyName);
			auditPojo.setClassName(oldObject.getClass().getName());
			auditPojo.setNewValue(newvalue);
			auditPojo.setOldValue(oldvalue);
			auditPojo.setOperation("operation");
			auditPojo.setUser("user");
			iauditService.saveAudit(auditPojo);
			}
		}
	}

}
