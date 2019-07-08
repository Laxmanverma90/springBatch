package com.hcl.processor;

import org.springframework.batch.item.ItemProcessor;

import com.hcl.bean.PersonBean;

public class PersonItemProcessor implements ItemProcessor<PersonBean, PersonBean> {

	@Override
	public PersonBean process(PersonBean item) throws Exception {
		PersonBean personBean = (PersonBean) item;
		personBean.setFirstName(personBean.getFirstName().toUpperCase());
		personBean.setLastName(personBean.getLastName().toLowerCase());
		personBean.setEmail(personBean.getEmail().substring(0, personBean.getEmail().indexOf('@')+1)+"hcl.com");
		String address = personBean.getAddress();
		if(address.contains("Delhi")) {
			personBean.setAddress(address+" - 110001");
		} else if(address.contains("Bangalore")) {
			personBean.setAddress(address+" - 560001");
		}
		return personBean;
	}

}
