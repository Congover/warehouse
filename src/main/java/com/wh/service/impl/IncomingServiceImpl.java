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
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.IncomingRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.service.DictionaryService;
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
    private DictionaryService dictionaryService;

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
	double sum = 0;
	List<String[]> data = new ArrayList<String[]>();
	for (Incoming entity : entities) {
	    String[] entityData = new String[6];
	    entityData[0] = Utils.convertDateToStr(entity.getCreateDate());
	    if (entity.getContragent() != null) {
		entityData[1] = entity.getContragent().getName();
	    }
	    if (entity.getProduct() != null) {
		entityData[2] = entity.getProduct().getName();
	    }
	    if (entity.getProductCount() != null) {
		entityData[3] = entity.getProductCount().toString();
		sum += entity.getProductCount().doubleValue();
	    }
	    if (entity.getStore() != null) {
		entityData[4] = entity.getStore().getName();
	    }
	    entityData[5] = entity.getComment();
	    data.add(entityData);
	}
	String[] entityData = new String[] { "", "", "ИТОГО:", String.valueOf(sum), "", "" };
	data.add(entityData);
	return ReportHelper.createReport("Incoming", reportParams, columnNames, data);
    }

    private List<Incoming> findEntitiesForReport(Date dateStart, Date dateEnd, Long contragentId, Long productId,
	    Long storeId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select c from Incoming c where c.createDate >= :startDate and c.createDate <= :endDate");
	List<Product> products = productId == null ? new ArrayList<Product>() : dictionaryService
		.findProductWithChildren(productId);
	if (contragentId != null) {
	    sb.append(" and c.contragent = :contragent");
	}
	if (productId != null) {
	    sb.append(" and (");
	    for (int q = 0; q < products.size(); q++) {
		if (q != 0) {
		    sb.append(" or ");
		}
		sb.append(" c.product = :product" + q);
	    }
	    sb.append(")");
	}
	if (storeId != null) {
	    sb.append(" and c.store = :store");
	}
	TypedQuery<Incoming> query = entityManager.createQuery(sb.toString(), Incoming.class);
	query.setParameter("startDate", dateStart, TemporalType.DATE);
	query.setParameter("endDate", dateEnd, TemporalType.DATE);
	if (contragentId != null) {
	    query.setParameter("contragent", contragentRepository.findOne(contragentId));
	}
	if (productId != null) {
	    int index = 0;
	    for (Product p : products) {
		query.setParameter("product" + index, p);
		index++;
	    }
	}
	if (storeId != null) {
	    query.setParameter("store", storeRepository.findOne(storeId));
	}
	return query.getResultList();
    }

    @Override
    public void save(String date, Long contragentId, Long productId, Double productCount, Long storeId, String comment) {
	Product product = productRepository.findOne(productId);
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
    public void update(Long id, String date, Long contragentId, Long productId, Double productCount, Long storeId,
	    String comment) {
	Incoming entity = incomingRepository.findOne(id);
	entity.setComment(comment);
	entity.setCreateDate(Utils.parse(date));
	entity.setProduct(productRepository.findOne(productId));
	entity.setProductCount(productCount);
	entity.setStore(storeRepository.findOne(storeId));
	entity.setContragent(contragentRepository.findOne(contragentId));
	incomingRepository.save(entity);
    }

    @Override
    public Boolean delete(Long id) {
	incomingRepository.delete(id);
	return true;
    }

    @Override
    public List<Incoming> findByContragentId(Long contragentId) {
	return incomingRepository.findByContragentContragentId(contragentId);
    }
}
