package com.wh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.wh.entity.Shipment;
import com.wh.repositories.AddressRepository;
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.ShipmentRepository;
import com.wh.repositories.StoreRepository;
import com.wh.repositories.TransportRepository;
import com.wh.service.DictionaryService;
import com.wh.service.ShipmentService;
import com.wh.utils.ReportHelper;
import com.wh.utils.Utils;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Resource
    private ShipmentRepository shipmentRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private StoreRepository storeRepository;

    @Resource
    private ContragentRepository contragentRepository;

    @Resource
    private AddressRepository addressRepository;

    @Resource
    private TransportRepository transportRepository;

    @Resource
    private DictionaryService dictionaryService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shipment> findAll() {
	return (List<Shipment>) shipmentRepository.findAll();
    }

    @Override
    public void save(String date, Long contragentId, Long productId, Integer productCount, Long storeId,
	    Long transportId, Long addressId, Boolean paymentType, String comment) {
	Product product = productRepository.findOne(productId);
	Shipment entity = new Shipment();
	entity.setCreateDate(Utils.parse(date));
	entity.setContragent(contragentRepository.findOne(contragentId));
	entity.setProduct(product);
	entity.setProductCount(productCount.doubleValue());
	entity.setStore(storeRepository.findOne(storeId));
	entity.setAddress(addressRepository.findOne(addressId));
	entity.setTransport(transportRepository.findOne(transportId));
	entity.setPaymentType(paymentType);
	entity.setComment(comment);
	shipmentRepository.save(entity);
    }

    @Override
    public Shipment find(Long id) {
	return shipmentRepository.findOne(id);
    }

    @Override
    public void update(Long id, String date, Long contragentId, Long storeId, Long transportId, Long addressId,
	    Boolean paymentType, String comment) {
	Shipment entity = shipmentRepository.findOne(id);
	entity.setComment(comment);
	entity.setCreateDate(Utils.parse(date));
	entity.setStore(storeRepository.findOne(storeId));
	entity.setAddress(addressRepository.findOne(addressId));
	entity.setTransport(transportRepository.findOne(transportId));
	entity.setPaymentType(paymentType);
	entity.setContragent(contragentRepository.findOne(contragentId));
	shipmentRepository.save(entity);
    }

    @Override
    public HSSFWorkbook generateReport(String dateStart, String dateEnd, Long contragentId, Long productId,
	    Long storeId, Long transportId, Boolean paymentType) {
	List<Shipment> entities = findEntitiesForReport(Utils.parse(dateStart), Utils.parse(dateEnd), contragentId,
		productId, storeId, transportId, paymentType);
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
	if (transportId != null) {
	    reportParams.add(new String[] { "Транспорт", transportRepository.findOne(transportId).getName() });
	}
	if (paymentType != null) {
	    reportParams.add(new String[] { "Тип оплаты", Utils.getShipmentPaymentType(paymentType) });
	}
	String[] columnNames = new String[] { "Дата", "Покупатель", "Товар", "Количество", "Склад", "Транспорт",
		"Пункт", "Тип оплаты", "Примечание" };
	List<String[]> data = new ArrayList<String[]>();
	double sum = 0;
	for (Shipment entity : entities) {
	    String[] entityData = new String[columnNames.length];
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
	    if (entity.getTransport() != null) {
		entityData[5] = entity.getTransport().getName();
	    }
	    if (entity.getAddress() != null) {
		entityData[6] = entity.getAddress().getFullAddress();
	    }
	    if (entity.getPaymentType() != null) {
		entityData[7] = Utils.getShipmentPaymentType(entity.getPaymentType());
	    }
	    entityData[8] = entity.getComment();
	    data.add(entityData);
	}
	String[] entityData = new String[] { "", "", "ИТОГО:", String.valueOf(sum), "", "", "", "", "" };
	data.add(entityData);
	return ReportHelper.createReport("Shipment", reportParams, columnNames, data);
    }

    private List<Shipment> findEntitiesForReport(Date dateStart, Date dateEnd, Long contragentId, Long productId,
	    Long storeId, Long transportId, Boolean paymentType) {
	StringBuilder sb = new StringBuilder();
	sb.append("select c from Shipment c where c.createDate >= :startDate and c.createDate <= :endDate");
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
	if (transportId != null) {
	    sb.append(" and c.transport = :transport");
	}
	if (paymentType != null) {
	    sb.append(" and c.paymentType = :paymentType");
	}
	TypedQuery<Shipment> query = entityManager.createQuery(sb.toString(), Shipment.class);
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
	if (transportId != null) {
	    query.setParameter("transport", transportRepository.findOne(transportId));
	}
	if (paymentType != null) {
	    query.setParameter("paymentType", paymentType);
	}
	return query.getResultList();
    }

    @Override
    public HSSFWorkbook generateBalanceReport(String dateStart, String dateEnd, Long productId, Long storeId) {
	List<String[]> reportParams = new ArrayList<String[]>();
	reportParams.add(new String[] { "Период с", dateStart });
	reportParams.add(new String[] { "Период по", dateEnd });
	reportParams.add(new String[] { "Товар", productRepository.findOne(productId).getName() });
	reportParams.add(new String[] { "Склад", storeRepository.findOne(storeId).getName() });

	String[] columnNames = new String[] { "Товар", "Остаток на начало периода", "Остаток на конец периода" };
	Date startDate = Utils.parse(dateStart);
	Calendar cal = Calendar.getInstance();
	cal.setTime(startDate);
	cal.add(Calendar.DAY_OF_MONTH, -1);
	startDate = cal.getTime();
	Date endDate = Utils.parse(dateEnd);
	List<String[]> data = new ArrayList<String[]>();
	List<Product> products = productId == null ? new ArrayList<Product>() : dictionaryService
		.findProductWithChildren(productId);
	double startSum = 0;
	double endSum = 0;

	for (Product p : products) {
	    double startIncomingBalance = findSum(startDate, p.getProductId(), storeId, Incoming.class.getName());
	    double startShipmentBalance = findSum(startDate, p.getProductId(), storeId, Shipment.class.getName());
	    double endIncomingBalance = findSum(endDate, p.getProductId(), storeId, Incoming.class.getName());
	    double endShipmentBalance = findSum(endDate, p.getProductId(), storeId, Shipment.class.getName());
	    data.add(new String[] { p.getName(), String.valueOf(startIncomingBalance - startShipmentBalance),
		    String.valueOf(endIncomingBalance - endShipmentBalance) });
	    startSum = startSum + (startIncomingBalance - startShipmentBalance);
	    endSum = endSum + (endIncomingBalance - endShipmentBalance);
	}
	String[] entityData = new String[] { "ИТОГО:", String.valueOf(startSum), String.valueOf(endSum) };
	data.add(entityData);
	return ReportHelper.createReport("Balance", reportParams, columnNames, data);
    }

    private double findSum(Date date, Long productId, Long storeId, String clazz) {
	StringBuilder sb = new StringBuilder();
	sb.append("select sum(c.productCount) from " + clazz
		+ " c where c.createDate <= :date and c.product = :product");
	if (storeId != null) {
	    sb.append(" and c.store = :store");
	}
	TypedQuery<Double> query = entityManager.createQuery(sb.toString(), Double.class);
	query.setParameter("date", date, TemporalType.DATE);
	query.setParameter("product", productRepository.findOne(productId));
	if (storeId != null) {
	    query.setParameter("store", storeRepository.findOne(storeId));
	}
	Double res = query.getSingleResult();
	return res != null ? res.doubleValue() : 0;
    }

    @Override
    public Boolean delete(Long id) {
	shipmentRepository.delete(id);
	return true;
    }

    @Override
    public List<Shipment> findByContragentId(Long contragentId) {
	return shipmentRepository.findByContragentContragentId(contragentId);
    }

}
