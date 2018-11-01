//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.14 at 02:35:40 下午 CST 
//


package com.aotain.common.utils.model.smmsupload;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="houseAmount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="errHouseAmount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="errHouseId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idcId",
    "houseAmount",
    "errHouseAmount",
    "errHouseId",
    "timeStamp"
})
@XmlRootElement(name = "activeState")
public class ActiveState {

    @XmlElement(required = true)
    protected String idcId;
    @XmlElement(required = true)
    protected BigInteger houseAmount;
    @XmlElement(required = true)
    protected BigInteger errHouseAmount;
    @XmlElement(type = Long.class)
    protected List<Long> errHouseId;
    @XmlElement(required = true)
    protected String timeStamp;

    /**
     * Gets the value of the idcId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdcId() {
        return idcId;
    }

    /**
     * Sets the value of the idcId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdcId(String value) {
        this.idcId = value;
    }

    /**
     * Gets the value of the houseAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHouseAmount() {
        return houseAmount;
    }

    /**
     * Sets the value of the houseAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHouseAmount(BigInteger value) {
        this.houseAmount = value;
    }

    /**
     * Gets the value of the errHouseAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getErrHouseAmount() {
        return errHouseAmount;
    }

    /**
     * Sets the value of the errHouseAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setErrHouseAmount(BigInteger value) {
        this.errHouseAmount = value;
    }

    /**
     * Gets the value of the errHouseId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errHouseId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrHouseId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getErrHouseId() {
        if (errHouseId == null) {
            errHouseId = new ArrayList<Long>();
        }
        return this.errHouseId;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

}
