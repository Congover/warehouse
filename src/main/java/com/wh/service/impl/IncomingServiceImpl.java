package com.wh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.wh.entity.Incoming;
import com.wh.entity.Product;
import com.wh.entity.ProductQuantity;
import com.wh.entity.ProductType;
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.IncomingRepository;
import com.wh.repositories.ProductQuantityRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.service.IncomingService;
import com.wh.utils.ReportHelper;
import com.wh.utils.Utils;

@Service
public class IncomingServiceImpl implements IncomingService {

    @Resource
    private IncomingRepository incomingRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private StoreRepository storeRepository;

    @Resource
    private ContragentRepository contragentRepository;

    @Resource
    private ProductQuantityRepository productQuantityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Incoming> findAll() {
	return (List<Incoming>) incomingRepository.findAll();
    }

    @Override
    public HSSFWorkbook generateReport(String dateStart, String dateEnd, Long contragentId, Long productId, Long storeId) {
	List<Incoming> entities = findEntitiesForReport(Utils.parse(dateStart), Utils.parse(dateEnd), contragentId,
		productId, storeId);
	List<String[]> reportParams = new ArrayList<String[]>();
	reportParams.add(new String[] { "Период с", dateStart });
	reportParams.add(new String[] { "Период по", dateEnd });
	if (contragentId != null) {
	    reportParams.add(new String[] { "Контрагент", contragentRepository.findOne(contragentId).getName() });
	}
	if (productId != null) {
	    reportParams.add(new String[] { "Товар", productRepository.findOne(productId).getName() });
	}
	if (storeId != null) {
	    reportParams.add(new String[] { "Склад", storeRepository.findOne(storeId).getName() });
	}
	String[] columnNames = new String[] { "Дата", "Поставщик", "Товар", "Количество", "Склад", "Примечание" };
	List<String[]> data = new ArrayList<String[]>();
	for (Incoming entity : entities) {
	    String[] entityData = new String[6];
	    entityData[0] = Utils.convertDateToStr(entity.getCreateDate());
	    entityData[1] = entity.getContragent().getName();
	    entityData[2] = entity.getProduct().getName();
	    entityData[3] = entity.getProductCount().toString();
	    entityData[4] = entity.getStore().getName();
	    entityData[5] = entity.getComment();
	    data.add(entityData);
	}
	return ReportHelper.createReport("Incoming", reportParams, columnNames, data);
    }

    private List<Incoming> findEntitiesForReport(Date dateStart, Date dateEnd, Long contragentId, Long productId,
	    Long storeId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select c from Incoming c where c.createDate >= :startDate and c.createDate <= :endDate");
	TypedQuery<Incoming> query = entityManager.createQuery(sb.toString(), Incoming.class);
	query.setParameter("startDate", dateStart, TemporalType.DATE);
	query.setParameter("endDate", dateEnd, TemporalType.DATE);
	return query.getResultList();
    }

    @Override
    public void save(String date, Long contragentId, Long productId, Double productCount, Long storeId, String comment) {
	Product product = productRepository.findOne(productId);
	ProductQuantity pq = product.getProductQuantity();
	if (ProductType.BAG.equals(product.getProductType())) {
	    pq.setBagCount(pq.getBagCount() != null ? pq.getBagCount() + productCount.intValue() : productCount
		    .intValue());
	} else {
	    pq.setProductCount(pq.getProductCount() != null ? pq.getProductCount() + productCount : productCount);
	}
	productQuantityRepository.save(pq);

	Incoming entity = new Incoming();
	entity.setCreateDate(Utils.parse(date));
	entity.setContragent(contragentRepository.findOne(contragentId));
	entity.setProduct(product);
	entity.setProductCount(productCount);
	entity.setStore(storeRepository.findOne(storeId));
	entity.setComment(comment);
	incomingRepository.save(entity);
    }

    @Override
    public Incoming find(Long id) {
	return incomingRepository.findOne(id);
    }

    @Override
    public void update(Long id, String date, Long contragentId, Long storeId, String comment) {
	Incoming entity = incomingRepository.findOne(id);
	entity.setComment(comment);
	entity.setCreateDate(Utils.parse(date));
	entity.setStore(storeRepository.findOne(storeId));
	entity.setContragent(contragentRepository.findOne(contragentId));
	incomingRepository.save(entity);
    }
}
