package com.temp;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransactionHelper {

	private static PlatformTransactionManager txnMgr;
	private DefaultTransactionDefinition definition;
	private TransactionStatus status;

	static {
		txnMgr = SpringAwareHelper.getBean(PlatformTransactionManager.class);
	}

	public void begin() {
		log.debug("Transaction begin");
		this.definition = new DefaultTransactionDefinition();
		this.status = txnMgr.getTransaction(this.definition);
	}

	public void commit() {
		if (this.status != null) {
			log.debug("Transaction commit");
			txnMgr.commit(this.status);
		}
	}

	public void rollBack() {
		if (this.status != null) {
			log.debug("Transaction rollBack");
			txnMgr.rollback(this.status);
		}
	}
}
