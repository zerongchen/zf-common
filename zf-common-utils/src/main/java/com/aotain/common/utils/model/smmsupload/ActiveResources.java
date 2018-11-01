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
 *       &lt;choice>
 *         &lt;element name="findDomainList" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="findDomainListDomain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="findDomainListFirstTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="findDomainListLastTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="block" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="visitsCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="topDomainflag" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="topDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="idcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="findIpList" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="findIpListIp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="findIpListFirstTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="findIpListLastTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="block" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="visitsCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="isInIpSeg" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="idcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "findDomainList",
    "findIpList"
})
@XmlRootElement(name = "activeResources")
public class ActiveResources {

    protected List<FindDomainList> findDomainList;
    protected List<FindIpList> findIpList;

    /**
     * Gets the value of the findDomainList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the findDomainList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFindDomainList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FindDomainList }
     *
     *
     */
    public List<FindDomainList> getFindDomainList() {
        if (findDomainList == null) {
            findDomainList = new ArrayList<FindDomainList>();
        }
        return this.findDomainList;
    }

    /**
     * Gets the value of the findIpList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the findIpList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFindIpList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FindIpList }
     *
     *
     */
    public List<FindIpList> getFindIpList() {
        if (findIpList == null) {
            findIpList = new ArrayList<FindIpList>();
        }
        return this.findIpList;
    }


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
     *         &lt;element name="findDomainListDomain" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="findDomainListFirstTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="findDomainListLastTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="block" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="visitsCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="topDomainflag" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="topDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="idcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "findDomainListDomain",
        "findDomainListFirstTime",
        "findDomainListLastTime",
        "block",
        "visitsCount",
        "topDomainflag",
        "topDomain",
        "ip",
        "idcId",
        "houseId"
    })
    public static class FindDomainList {

        @XmlElement(required = true)
        protected String findDomainListDomain;
        @XmlElement(required = true)
        protected String findDomainListFirstTime;
        @XmlElement(required = true)
        protected String findDomainListLastTime;
        @XmlElement(required = true)
        protected BigInteger block;
        protected long visitsCount;
        @XmlElement(required = true)
        protected BigInteger topDomainflag;
        protected String topDomain;
        @XmlElement(required = true)
        protected String ip;
        @XmlElement(required = true)
        protected String idcId;
        protected long houseId;

        /**
         * Gets the value of the findDomainListDomain property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindDomainListDomain() {
            return findDomainListDomain;
        }

        /**
         * Sets the value of the findDomainListDomain property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindDomainListDomain(String value) {
            this.findDomainListDomain = value;
        }

        /**
         * Gets the value of the findDomainListFirstTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindDomainListFirstTime() {
            return findDomainListFirstTime;
        }

        /**
         * Sets the value of the findDomainListFirstTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindDomainListFirstTime(String value) {
            this.findDomainListFirstTime = value;
        }

        /**
         * Gets the value of the findDomainListLastTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindDomainListLastTime() {
            return findDomainListLastTime;
        }

        /**
         * Sets the value of the findDomainListLastTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindDomainListLastTime(String value) {
            this.findDomainListLastTime = value;
        }

        /**
         * Gets the value of the block property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBlock() {
            return block;
        }

        /**
         * Sets the value of the block property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBlock(BigInteger value) {
            this.block = value;
        }

        /**
         * Gets the value of the visitsCount property.
         * 
         */
        public long getVisitsCount() {
            return visitsCount;
        }

        /**
         * Sets the value of the visitsCount property.
         * 
         */
        public void setVisitsCount(long value) {
            this.visitsCount = value;
        }

        /**
         * Gets the value of the topDomainflag property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTopDomainflag() {
            return topDomainflag;
        }

        /**
         * Sets the value of the topDomainflag property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTopDomainflag(BigInteger value) {
            this.topDomainflag = value;
        }

        /**
         * Gets the value of the topDomain property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTopDomain() {
            return topDomain;
        }

        /**
         * Sets the value of the topDomain property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTopDomain(String value) {
            this.topDomain = value;
        }

        /**
         * Gets the value of the ip property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIp() {
            return ip;
        }

        /**
         * Sets the value of the ip property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIp(String value) {
            this.ip = value;
        }

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
         * Gets the value of the houseId property.
         * 
         */
        public long getHouseId() {
            return houseId;
        }

        /**
         * Sets the value of the houseId property.
         * 
         */
        public void setHouseId(long value) {
            this.houseId = value;
        }

    }


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
     *         &lt;element name="findIpListIp" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="findIpListFirstTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="findIpListLastTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="block" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="visitsCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="isInIpSeg" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="idcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "findIpListIp",
        "findIpListFirstTime",
        "findIpListLastTime",
        "block",
        "visitsCount",
        "isInIpSeg",
        "port",
        "protocol",
        "idcId",
        "houseId"
    })
    public static class FindIpList {

        @XmlElement(required = true)
        protected String findIpListIp;
        @XmlElement(required = true)
        protected String findIpListFirstTime;
        @XmlElement(required = true)
        protected String findIpListLastTime;
        @XmlElement(required = true)
        protected BigInteger block;
        protected long visitsCount;
        @XmlElement(required = true)
        protected BigInteger isInIpSeg;
        protected long port;
        @XmlElement(required = true)
        protected BigInteger protocol;
        @XmlElement(required = true)
        protected String idcId;
        protected long houseId;

        /**
         * Gets the value of the findIpListIp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindIpListIp() {
            return findIpListIp;
        }

        /**
         * Sets the value of the findIpListIp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindIpListIp(String value) {
            this.findIpListIp = value;
        }

        /**
         * Gets the value of the findIpListFirstTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindIpListFirstTime() {
            return findIpListFirstTime;
        }

        /**
         * Sets the value of the findIpListFirstTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindIpListFirstTime(String value) {
            this.findIpListFirstTime = value;
        }

        /**
         * Gets the value of the findIpListLastTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFindIpListLastTime() {
            return findIpListLastTime;
        }

        /**
         * Sets the value of the findIpListLastTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFindIpListLastTime(String value) {
            this.findIpListLastTime = value;
        }

        /**
         * Gets the value of the block property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBlock() {
            return block;
        }

        /**
         * Sets the value of the block property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBlock(BigInteger value) {
            this.block = value;
        }

        /**
         * Gets the value of the visitsCount property.
         * 
         */
        public long getVisitsCount() {
            return visitsCount;
        }

        /**
         * Sets the value of the visitsCount property.
         * 
         */
        public void setVisitsCount(long value) {
            this.visitsCount = value;
        }

        /**
         * Gets the value of the isInIpSeg property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getIsInIpSeg() {
            return isInIpSeg;
        }

        /**
         * Sets the value of the isInIpSeg property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setIsInIpSeg(BigInteger value) {
            this.isInIpSeg = value;
        }

        /**
         * Gets the value of the port property.
         * 
         */
        public long getPort() {
            return port;
        }

        /**
         * Sets the value of the port property.
         * 
         */
        public void setPort(long value) {
            this.port = value;
        }

        /**
         * Gets the value of the protocol property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getProtocol() {
            return protocol;
        }

        /**
         * Sets the value of the protocol property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setProtocol(BigInteger value) {
            this.protocol = value;
        }

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
         * Gets the value of the houseId property.
         * 
         */
        public long getHouseId() {
            return houseId;
        }

        /**
         * Sets the value of the houseId property.
         * 
         */
        public void setHouseId(long value) {
            this.houseId = value;
        }

    }

}
