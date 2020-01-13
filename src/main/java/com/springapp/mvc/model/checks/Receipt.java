package com.springapp.mvc.model.checks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.springapp.mvc.fns.PropertiesUser;
import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.Model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.lang.annotation.Native;
import java.util.List;

/**
 * Created by oem on 28.11.18.
 */

@Entity
@Table(name = "receipt")
public class Receipt extends Model {




    @Column(name = "ecashTotalSum")
    @SerializedName("ecashTotalSum")
    @Expose
    private int ecashTotalSum;

    @Column(name = "userInn")
    @SerializedName("userInn")
    @Expose
    private String userInn;


    @SerializedName("items")
    @Expose
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> items;



    @Column(name = "requestNumber")
    @SerializedName("requestNumber")
    @Expose
    private int requestNumber;

    @Column(name = "provisionSum")
    @SerializedName("provisionSum")
    @Expose
    private int provisionSum;

    @Column(name = "fiscalSign")
    @SerializedName("fiscalSign")
    @Expose
    private String fiscalSign;

    @Column(name = "fiscalDocumentNumber")
    @SerializedName("fiscalDocumentNumber")
    @Expose
    private int fiscalDocumentNumber;

    @Column(name = "operationType")
    @SerializedName("operationType")
    @Expose
    private int operationType;

    @Column(name = "taxationType")
    @SerializedName("taxationType")
    @Expose
    private int taxationType;

    @Column(name = "messageFiscalSign")
    @SerializedName("messageFiscalSign")
    @Expose
    private long messageFiscalSign;

    @Column(name = "fiscalDriveNumber")
    @SerializedName("fiscalDriveNumber")
    @Expose
    private String fiscalDriveNumber;


    @Column(name = "machineNumber")
    @SerializedName("machineNumber")
    @Expose
    private String machineNumber;


    @Column(name = "cashTotalSum")
    @SerializedName("cashTotalSum")
    @Expose
    private int cashTotalSum;


    @Column(name = "internetSign")
    @SerializedName("internetSign")
    @Expose
    private int internetSign;


    @Column(name = "shiftNumber")
    @SerializedName("shiftNumber")
    @Expose
    private int shiftNumber;


    @Column(name = "operator")
    @SerializedName("operator")
    @Expose
    private String operator;


    @Column(name = "retailPlace")
    @SerializedName("retailPlace")
    @Expose
    private String retailPlace;


    @Column(name = "dateTime")
    @SerializedName("dateTime")
    @Expose
    private String dateTime;


    @Column(name = "ndsNo")
    @SerializedName("ndsNo")
    @Expose
    private int ndsNo;



    @Column(name = "nds10")
    @SerializedName("nds10")
    @Expose
    private int nds10;


    @Column(name = "nds18")
    @SerializedName("nds18")
    @Expose
    private int nds18;


    @Column(name = "totalSum")
    @SerializedName("totalSum")
    @Expose
    private int totalSum;


    @Column(name = "fiscalDocumentFormatVer")
    @SerializedName("fiscalDocumentFormatVer")
    @Expose
    private int fiscalDocumentFormatVer;


    @Transient
    @SerializedName("rawData")
    @Expose
    private String rawData;

    @Column(name = "prepaidSum")
    @SerializedName("prepaidSum")
    @Expose
    private int prepaidSum;


    @Column(name = "paymentAgentType")
    @SerializedName("paymentAgentType")
    @Expose
    private int paymentAgentType;


    @Column(name = "sellerAddress")
    @SerializedName("sellerAddress")
    @Expose
    private String sellerAddress;


    @Transient
    @SerializedName("propertiesUser")
    @Expose
    private PropertiesUser propertiesUser;

    @Column(name = "fnsSite")
    @SerializedName("fnsSite")
    @Expose
    private String fnsSite;

    @Column(name = "user")
    @SerializedName("user")
    @Expose
    private String user;

    @Column(name = "receiptCode")
    @SerializedName("receiptCode")
    @Expose
    private int receiptCode;

    @Column(name = "creditSum")
    @SerializedName("creditSum")
    @Expose
    private int creditSum;


    @Column(name = "kktRegId")
    @SerializedName("kktRegId")
    @Expose
    private String kktRegId;

    @Column(name = "buyerAddress")
    @SerializedName("buyerAddress")
    @Expose
    private String buyerAddress;


    @ManyToOne(optional = true)
    @JoinColumn(name = "day_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Day day;


    public Receipt() {
        super();
    }


    public int getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(int ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getProvisionSum() {
        return provisionSum;
    }

    public void setProvisionSum(int provisionSum) {
        this.provisionSum = provisionSum;
    }

    public String getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public int getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(int fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(int taxationType) {
        this.taxationType = taxationType;
    }

    public long getMessageFiscalSign() {
        return messageFiscalSign;
    }

    public void setMessageFiscalSign(long messageFiscalSign) {
        this.messageFiscalSign = messageFiscalSign;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public int getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(int cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public int getInternetSign() {
        return internetSign;
    }

    public void setInternetSign(int internetSign) {
        this.internetSign = internetSign;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRetailPlace() {
        return retailPlace;
    }

    public void setRetailPlace(String retailPlace) {
        this.retailPlace = retailPlace;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getNdsNo() {
        return ndsNo;
    }

    public void setNdsNo(int ndsNo) {
        this.ndsNo = ndsNo;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getFiscalDocumentFormatVer() {
        return fiscalDocumentFormatVer;
    }

    public void setFiscalDocumentFormatVer(int fiscalDocumentFormatVer) {
        this.fiscalDocumentFormatVer = fiscalDocumentFormatVer;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public int getPrepaidSum() {
        return prepaidSum;
    }

    public void setPrepaidSum(int prepaidSum) {
        this.prepaidSum = prepaidSum;
    }

    public int getPaymentAgentType() {
        return paymentAgentType;
    }

    public void setPaymentAgentType(int paymentAgentType) {
        this.paymentAgentType = paymentAgentType;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public PropertiesUser getPropertiesUser() {
        return propertiesUser;
    }

    public void setPropertiesUser(PropertiesUser propertiesUser) {
        this.propertiesUser = propertiesUser;
    }

    public String getFnsSite() {
        return fnsSite;
    }

    public void setFnsSite(String fnsSite) {
        this.fnsSite = fnsSite;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public int getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(int creditSum) {
        this.creditSum = creditSum;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }


    public int getNds10() {
        return nds10;
    }

    public void setNds10(int nds10) {
        this.nds10 = nds10;
    }

    public int getNds18() {
        return nds18;
    }

    public void setNds18(int nds18) {
        this.nds18 = nds18;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "ecashTotalSum=" + ecashTotalSum +
                ", userInn='" + userInn + '\'' +
                ", items=" + items +
                ", requestNumber=" + requestNumber +
                ", provisionSum=" + provisionSum +
                ", fiscalSign=" + fiscalSign +
                ", fiscalDocumentNumber=" + fiscalDocumentNumber +
                ", operationType=" + operationType +
                ", taxationType=" + taxationType +
                ", messageFiscalSign=" + messageFiscalSign +
                ", fiscalDriveNumber='" + fiscalDriveNumber + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                ", cashTotalSum=" + cashTotalSum +
                ", internetSign=" + internetSign +
                ", shiftNumber=" + shiftNumber +
                ", operator='" + operator + '\'' +
                ", retailPlace='" + retailPlace + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", ndsNo=" + ndsNo +
                ", totalSum=" + totalSum +
                ", fiscalDocumentFormatVer=" + fiscalDocumentFormatVer +
                ", rawData='" + rawData + '\'' +
                ", prepaidSum=" + prepaidSum +
                ", paymentAgentType=" + paymentAgentType +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", propertiesUser=" + propertiesUser +
                ", fnsSite='" + fnsSite + '\'' +
                ", user='" + user + '\'' +
                ", receiptCode=" + receiptCode +
                ", creditSum=" + creditSum +
                ", kktRegId='" + kktRegId + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                '}';
    }
}
