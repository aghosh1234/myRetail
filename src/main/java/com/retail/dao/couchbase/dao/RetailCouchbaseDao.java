package com.retail.dao.couchbase.dao;



import java.io.Serializable;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import com.retail.dao.couchbase.model.Product;

@Repository("retailCouchbaseDao")
public interface RetailCouchbaseDao extends CouchbaseRepository<Product, Serializable>{
	
}
