package it.carcheck.model.interfaces;

import it.carcheck.model.bean.PeopleBean;

public interface IPeople extends IDatabaseOperation<PeopleBean> {
	public PeopleBean doRetrieveByFiscalCode(String code);
}
