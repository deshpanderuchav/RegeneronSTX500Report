//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.23 at 06:21:48 PM CEST 
//


package com.liconic.binding.sys;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.liconic.binding.sys1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClimateStated_QNAME = new QName("http://com.liconic.sys", "stated");
    private final static QName _ClimateActual_QNAME = new QName("http://com.liconic.sys", "actual");
    private final static QName _SchedulerStatus_QNAME = new QName("http://com.liconic.sys", "status");
    private final static QName _SchedulerInfo_QNAME = new QName("http://com.liconic.sys", "info");
    private final static QName _SchedulerCommand_QNAME = new QName("http://com.liconic.sys", "command");
    private final static QName _SysScheduler_QNAME = new QName("http://com.liconic.sys", "scheduler");
    private final static QName _SysSysId_QNAME = new QName("http://com.liconic.sys", "sysId");
    private final static QName _SysJobs_QNAME = new QName("http://com.liconic.sys", "jobs");
    private final static QName _SysCmds_QNAME = new QName("http://com.liconic.sys", "cmds");
    private final static QName _SysLabware_QNAME = new QName("http://com.liconic.sys", "labware");
    private final static QName _SysSysName_QNAME = new QName("http://com.liconic.sys", "sysName");
    private final static QName _TubePosUnloadDate_QNAME = new QName("http://com.liconic.sys", "unloadDate");
    private final static QName _TubePosYa_QNAME = new QName("http://com.liconic.sys", "ya");
    private final static QName _TubePosIdDB_QNAME = new QName("http://com.liconic.sys", "idDB");
    private final static QName _TubePosTube_QNAME = new QName("http://com.liconic.sys", "tube");
    private final static QName _TubePosX_QNAME = new QName("http://com.liconic.sys", "x");
    private final static QName _TubePosY_QNAME = new QName("http://com.liconic.sys", "y");
    private final static QName _TubePosInventoryDate_QNAME = new QName("http://com.liconic.sys", "inventoryDate");
    private final static QName _StatedHumidity_QNAME = new QName("http://com.liconic.sys", "humidity");
    private final static QName _StatedO2_QNAME = new QName("http://com.liconic.sys", "o2");
    private final static QName _StatedTemperature_QNAME = new QName("http://com.liconic.sys", "temperature");
    private final static QName _StatedCo2_QNAME = new QName("http://com.liconic.sys", "co2");
    private final static QName _LevelLevelDevId_QNAME = new QName("http://com.liconic.sys", "levelDevId");
    private final static QName _LevelPlate_QNAME = new QName("http://com.liconic.sys", "plate");
    private final static QName _CmdResult_QNAME = new QName("http://com.liconic.sys", "result");
    private final static QName _CmdUser_QNAME = new QName("http://com.liconic.sys", "user");
    private final static QName _CmdType_QNAME = new QName("http://com.liconic.sys", "type");
    private final static QName _CmdValue_QNAME = new QName("http://com.liconic.sys", "value");
    private final static QName _TubelwHeight_QNAME = new QName("http://com.liconic.sys", "height");
    private final static QName _DeviceClimate_QNAME = new QName("http://com.liconic.sys", "climate");
    private final static QName _RackName_QNAME = new QName("http://com.liconic.sys", "name");
    private final static QName _TubeUserId_QNAME = new QName("http://com.liconic.sys", "userId");
    private final static QName _TubeLoadDate_QNAME = new QName("http://com.liconic.sys", "loadDate");
    private final static QName _TubeBarcode_QNAME = new QName("http://com.liconic.sys", "barcode");
    private final static QName _UnitDate_QNAME = new QName("http://com.liconic.sys", "date");
    private final static QName _PlatePitch_QNAME = new QName("http://com.liconic.sys", "pitch");
    private final static QName _PlateTubePositions_QNAME = new QName("http://com.liconic.sys", "tubePositions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.liconic.binding.sys1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sys }
     * 
     */
    public Sys createSys() {
        return new Sys();
    }

    /**
     * Create an instance of {@link Device }
     * 
     */
    public Device createDevice() {
        return new Device();
    }

    /**
     * Create an instance of {@link Labware }
     * 
     */
    public Labware createLabware() {
        return new Labware();
    }

    /**
     * Create an instance of {@link Cmds }
     * 
     */
    public Cmds createCmds() {
        return new Cmds();
    }

    /**
     * Create an instance of {@link Scheduler }
     * 
     */
    public Scheduler createScheduler() {
        return new Scheduler();
    }

    /**
     * Create an instance of {@link Jobs }
     * 
     */
    public Jobs createJobs() {
        return new Jobs();
    }

    /**
     * Create an instance of {@link Stated }
     * 
     */
    public Stated createStated() {
        return new Stated();
    }

    /**
     * Create an instance of {@link Tube }
     * 
     */
    public Tube createTube() {
        return new Tube();
    }

    /**
     * Create an instance of {@link Tstatus }
     * 
     */
    public Tstatus createTstatus() {
        return new Tstatus();
    }

    /**
     * Create an instance of {@link Co2 }
     * 
     */
    public Co2 createCo2() {
        return new Co2();
    }

    /**
     * Create an instance of {@link Rack }
     * 
     */
    public Rack createRack() {
        return new Rack();
    }

    /**
     * Create an instance of {@link Temperature }
     * 
     */
    public Temperature createTemperature() {
        return new Temperature();
    }

    /**
     * Create an instance of {@link Level }
     * 
     */
    public Level createLevel() {
        return new Level();
    }

    /**
     * Create an instance of {@link TubePositions }
     * 
     */
    public TubePositions createTubePositions() {
        return new TubePositions();
    }

    /**
     * Create an instance of {@link Tubelw }
     * 
     */
    public Tubelw createTubelw() {
        return new Tubelw();
    }

    /**
     * Create an instance of {@link Climate }
     * 
     */
    public Climate createClimate() {
        return new Climate();
    }

    /**
     * Create an instance of {@link Cassette }
     * 
     */
    public Cassette createCassette() {
        return new Cassette();
    }

    /**
     * Create an instance of {@link O2 }
     * 
     */
    public O2 createO2() {
        return new O2();
    }

    /**
     * Create an instance of {@link Status }
     * 
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Plate }
     * 
     */
    public Plate createPlate() {
        return new Plate();
    }

    /**
     * Create an instance of {@link Job }
     * 
     */
    public Job createJob() {
        return new Job();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link Unit }
     * 
     */
    public Unit createUnit() {
        return new Unit();
    }

    /**
     * Create an instance of {@link Cmd }
     * 
     */
    public Cmd createCmd() {
        return new Cmd();
    }

    /**
     * Create an instance of {@link Humidity }
     * 
     */
    public Humidity createHumidity() {
        return new Humidity();
    }

    /**
     * Create an instance of {@link Actual }
     * 
     */
    public Actual createActual() {
        return new Actual();
    }

    /**
     * Create an instance of {@link TubePos }
     * 
     */
    public TubePos createTubePos() {
        return new TubePos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stated }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "stated", scope = Climate.class)
    public JAXBElement<Stated> createClimateStated(Stated value) {
        return new JAXBElement<Stated>(_ClimateStated_QNAME, Stated.class, Climate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Actual }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "actual", scope = Climate.class)
    public JAXBElement<Actual> createClimateActual(Actual value) {
        return new JAXBElement<Actual>(_ClimateActual_QNAME, Actual.class, Climate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "status", scope = Scheduler.class)
    public JAXBElement<String> createSchedulerStatus(String value) {
        return new JAXBElement<String>(_SchedulerStatus_QNAME, String.class, Scheduler.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "info", scope = Scheduler.class)
    public JAXBElement<String> createSchedulerInfo(String value) {
        return new JAXBElement<String>(_SchedulerInfo_QNAME, String.class, Scheduler.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "command", scope = Scheduler.class)
    public JAXBElement<String> createSchedulerCommand(String value) {
        return new JAXBElement<String>(_SchedulerCommand_QNAME, String.class, Scheduler.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Scheduler }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "scheduler", scope = Sys.class)
    public JAXBElement<Scheduler> createSysScheduler(Scheduler value) {
        return new JAXBElement<Scheduler>(_SysScheduler_QNAME, Scheduler.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "sysId", scope = Sys.class)
    public JAXBElement<Integer> createSysSysId(Integer value) {
        return new JAXBElement<Integer>(_SysSysId_QNAME, Integer.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Jobs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "jobs", scope = Sys.class)
    public JAXBElement<Jobs> createSysJobs(Jobs value) {
        return new JAXBElement<Jobs>(_SysJobs_QNAME, Jobs.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cmds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "cmds", scope = Sys.class)
    public JAXBElement<Cmds> createSysCmds(Cmds value) {
        return new JAXBElement<Cmds>(_SysCmds_QNAME, Cmds.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Labware }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "labware", scope = Sys.class)
    public JAXBElement<Labware> createSysLabware(Labware value) {
        return new JAXBElement<Labware>(_SysLabware_QNAME, Labware.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "sysName", scope = Sys.class)
    public JAXBElement<String> createSysSysName(String value) {
        return new JAXBElement<String>(_SysSysName_QNAME, String.class, Sys.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "unloadDate", scope = TubePos.class)
    public JAXBElement<String> createTubePosUnloadDate(String value) {
        return new JAXBElement<String>(_TubePosUnloadDate_QNAME, String.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "ya", scope = TubePos.class)
    public JAXBElement<String> createTubePosYa(String value) {
        return new JAXBElement<String>(_TubePosYa_QNAME, String.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "idDB", scope = TubePos.class)
    public JAXBElement<Integer> createTubePosIdDB(Integer value) {
        return new JAXBElement<Integer>(_TubePosIdDB_QNAME, Integer.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tube }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "tube", scope = TubePos.class)
    public JAXBElement<Tube> createTubePosTube(Tube value) {
        return new JAXBElement<Tube>(_TubePosTube_QNAME, Tube.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "x", scope = TubePos.class)
    public JAXBElement<Integer> createTubePosX(Integer value) {
        return new JAXBElement<Integer>(_TubePosX_QNAME, Integer.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "y", scope = TubePos.class)
    public JAXBElement<Integer> createTubePosY(Integer value) {
        return new JAXBElement<Integer>(_TubePosY_QNAME, Integer.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "inventoryDate", scope = TubePos.class)
    public JAXBElement<String> createTubePosInventoryDate(String value) {
        return new JAXBElement<String>(_TubePosInventoryDate_QNAME, String.class, TubePos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "humidity", scope = Stated.class)
    public JAXBElement<String> createStatedHumidity(String value) {
        return new JAXBElement<String>(_StatedHumidity_QNAME, String.class, Stated.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "o2", scope = Stated.class)
    public JAXBElement<String> createStatedO2(String value) {
        return new JAXBElement<String>(_StatedO2_QNAME, String.class, Stated.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "temperature", scope = Stated.class)
    public JAXBElement<String> createStatedTemperature(String value) {
        return new JAXBElement<String>(_StatedTemperature_QNAME, String.class, Stated.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "co2", scope = Stated.class)
    public JAXBElement<String> createStatedCo2(String value) {
        return new JAXBElement<String>(_StatedCo2_QNAME, String.class, Stated.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "unloadDate", scope = Level.class)
    public JAXBElement<String> createLevelUnloadDate(String value) {
        return new JAXBElement<String>(_TubePosUnloadDate_QNAME, String.class, Level.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "levelDevId", scope = Level.class)
    public JAXBElement<Integer> createLevelLevelDevId(Integer value) {
        return new JAXBElement<Integer>(_LevelLevelDevId_QNAME, Integer.class, Level.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "inventoryDate", scope = Level.class)
    public JAXBElement<String> createLevelInventoryDate(String value) {
        return new JAXBElement<String>(_TubePosInventoryDate_QNAME, String.class, Level.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "plate", scope = Level.class)
    public JAXBElement<Plate> createLevelPlate(Plate value) {
        return new JAXBElement<Plate>(_LevelPlate_QNAME, Plate.class, Level.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "status", scope = Cmd.class)
    public JAXBElement<String> createCmdStatus(String value) {
        return new JAXBElement<String>(_SchedulerStatus_QNAME, String.class, Cmd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "result", scope = Cmd.class)
    public JAXBElement<String> createCmdResult(String value) {
        return new JAXBElement<String>(_CmdResult_QNAME, String.class, Cmd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "user", scope = Cmd.class)
    public JAXBElement<String> createCmdUser(String value) {
        return new JAXBElement<String>(_CmdUser_QNAME, String.class, Cmd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "type", scope = Cmd.class)
    public JAXBElement<String> createCmdType(String value) {
        return new JAXBElement<String>(_CmdType_QNAME, String.class, Cmd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "value", scope = Cmd.class)
    public JAXBElement<String> createCmdValue(String value) {
        return new JAXBElement<String>(_CmdValue_QNAME, String.class, Cmd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "height", scope = Tubelw.class)
    public JAXBElement<Integer> createTubelwHeight(Integer value) {
        return new JAXBElement<Integer>(_TubelwHeight_QNAME, Integer.class, Tubelw.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Climate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "climate", scope = Device.class)
    public JAXBElement<Climate> createDeviceClimate(Climate value) {
        return new JAXBElement<Climate>(_DeviceClimate_QNAME, Climate.class, Device.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "name", scope = Rack.class)
    public JAXBElement<String> createRackName(String value) {
        return new JAXBElement<String>(_RackName_QNAME, String.class, Rack.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "height", scope = Rack.class)
    public JAXBElement<Integer> createRackHeight(Integer value) {
        return new JAXBElement<Integer>(_TubelwHeight_QNAME, Integer.class, Rack.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "userId", scope = Tube.class)
    public JAXBElement<Integer> createTubeUserId(Integer value) {
        return new JAXBElement<Integer>(_TubeUserId_QNAME, Integer.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "idDB", scope = Tube.class)
    public JAXBElement<Integer> createTubeIdDB(Integer value) {
        return new JAXBElement<Integer>(_TubePosIdDB_QNAME, Integer.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "info", scope = Tube.class)
    public JAXBElement<String> createTubeInfo(String value) {
        return new JAXBElement<String>(_SchedulerInfo_QNAME, String.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "type", scope = Tube.class)
    public JAXBElement<String> createTubeType(String value) {
        return new JAXBElement<String>(_CmdType_QNAME, String.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "loadDate", scope = Tube.class)
    public JAXBElement<String> createTubeLoadDate(String value) {
        return new JAXBElement<String>(_TubeLoadDate_QNAME, String.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "barcode", scope = Tube.class)
    public JAXBElement<String> createTubeBarcode(String value) {
        return new JAXBElement<String>(_TubeBarcode_QNAME, String.class, Tube.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Humidity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "humidity", scope = Actual.class)
    public JAXBElement<Humidity> createActualHumidity(Humidity value) {
        return new JAXBElement<Humidity>(_StatedHumidity_QNAME, Humidity.class, Actual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link O2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "o2", scope = Actual.class)
    public JAXBElement<O2> createActualO2(O2 value) {
        return new JAXBElement<O2>(_StatedO2_QNAME, O2 .class, Actual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Temperature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "temperature", scope = Actual.class)
    public JAXBElement<Temperature> createActualTemperature(Temperature value) {
        return new JAXBElement<Temperature>(_StatedTemperature_QNAME, Temperature.class, Actual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Co2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "co2", scope = Actual.class)
    public JAXBElement<Co2> createActualCo2(Co2 value) {
        return new JAXBElement<Co2>(_StatedCo2_QNAME, Co2 .class, Actual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "date", scope = Unit.class)
    public JAXBElement<String> createUnitDate(String value) {
        return new JAXBElement<String>(_UnitDate_QNAME, String.class, Unit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "value", scope = Unit.class)
    public JAXBElement<String> createUnitValue(String value) {
        return new JAXBElement<String>(_CmdValue_QNAME, String.class, Unit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "userId", scope = Plate.class)
    public JAXBElement<Integer> createPlateUserId(Integer value) {
        return new JAXBElement<Integer>(_TubeUserId_QNAME, Integer.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "info", scope = Plate.class)
    public JAXBElement<String> createPlateInfo(String value) {
        return new JAXBElement<String>(_SchedulerInfo_QNAME, String.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "pitch", scope = Plate.class)
    public JAXBElement<Integer> createPlatePitch(Integer value) {
        return new JAXBElement<Integer>(_PlatePitch_QNAME, Integer.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "type", scope = Plate.class)
    public JAXBElement<String> createPlateType(String value) {
        return new JAXBElement<String>(_CmdType_QNAME, String.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "loadDate", scope = Plate.class)
    public JAXBElement<String> createPlateLoadDate(String value) {
        return new JAXBElement<String>(_TubeLoadDate_QNAME, String.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "barcode", scope = Plate.class)
    public JAXBElement<String> createPlateBarcode(String value) {
        return new JAXBElement<String>(_TubeBarcode_QNAME, String.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TubePositions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "tubePositions", scope = Plate.class)
    public JAXBElement<TubePositions> createPlateTubePositions(TubePositions value) {
        return new JAXBElement<TubePositions>(_PlateTubePositions_QNAME, TubePositions.class, Plate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "pitch", scope = Cassette.class)
    public JAXBElement<Integer> createCassettePitch(Integer value) {
        return new JAXBElement<Integer>(_PlatePitch_QNAME, Integer.class, Cassette.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.liconic.sys", name = "type", scope = Cassette.class)
    public JAXBElement<String> createCassetteType(String value) {
        return new JAXBElement<String>(_CmdType_QNAME, String.class, Cassette.class, value);
    }

}
