//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.23 at 06:21:48 PM CEST 
//


package com.liconic.binding.sys;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tubePos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tubePos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDB" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ya" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inventoryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unloadDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tube" type="{http://com.liconic.sys}tube" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tubePos", propOrder = {
    "idDB",
    "x",
    "y",
    "ya",
    "inventoryDate",
    "unloadDate",
    "tube"
})
public class TubePos {

    @XmlElementRef(name = "idDB", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> idDB;
    @XmlElementRef(name = "x", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> x;
    @XmlElementRef(name = "y", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> y;
    @XmlElementRef(name = "ya", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ya;
    @XmlElementRef(name = "inventoryDate", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<String> inventoryDate;
    @XmlElementRef(name = "unloadDate", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<String> unloadDate;
    @XmlElementRef(name = "tube", namespace = "http://com.liconic.sys", type = JAXBElement.class, required = false)
    protected JAXBElement<Tube> tube;

    /**
     * Gets the value of the idDB property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdDB() {
        return idDB;
    }

    /**
     * Sets the value of the idDB property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdDB(JAXBElement<Integer> value) {
        this.idDB = value;
    }

    /**
     * Gets the value of the x property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setX(JAXBElement<Integer> value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setY(JAXBElement<Integer> value) {
        this.y = value;
    }

    /**
     * Gets the value of the ya property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getYa() {
        return ya;
    }

    /**
     * Sets the value of the ya property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setYa(JAXBElement<String> value) {
        this.ya = value;
    }

    /**
     * Gets the value of the inventoryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInventoryDate() {
        return inventoryDate;
    }

    /**
     * Sets the value of the inventoryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInventoryDate(JAXBElement<String> value) {
        this.inventoryDate = value;
    }

    /**
     * Gets the value of the unloadDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUnloadDate() {
        return unloadDate;
    }

    /**
     * Sets the value of the unloadDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUnloadDate(JAXBElement<String> value) {
        this.unloadDate = value;
    }

    /**
     * Gets the value of the tube property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Tube }{@code >}
     *     
     */
    public JAXBElement<Tube> getTube() {
        return tube;
    }

    /**
     * Sets the value of the tube property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Tube }{@code >}
     *     
     */
    public void setTube(JAXBElement<Tube> value) {
        this.tube = value;
    }

}
