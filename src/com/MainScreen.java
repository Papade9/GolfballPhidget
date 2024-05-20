package com;

import java.awt.event.*;
import javax.swing.border.*;
import com.db.HQuery;
import com.db.tableclass.*;
import com.phidget22.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Nov 10 18:09:11 MST 2022
 */

/**
 * @author Taylor Carlston
 */
public class MainScreen extends JFrame {
    private HopperFunctionButton _btnTopLeft;
    private HopperFunctionButton _btnTopMiddle;
    private HopperFunctionButton _btnTopRight;
    private HopperFunctionButton _btnBottomLeft;
    private HopperFunctionButton _btnBottomMiddle;
    private HopperFunctionButton _btnBottomRight;
    private Hopper _hopper1;
    private Hopper _hopper2;
    private Hopper _hopper3;
    private Hopper _hopper4;
    private Hopper _hopper5;
    private Hopper _hopper6;
    private static MainScreen _instance = null;
    private Integer _ballCredits = 0;
    private LocalDateTime _lastButtonPressed = LocalDateTime.now();
    private ArrayList<TicketTypeEntity> _ticketTypes = new ArrayList<>();
    private boolean _acceptETabs, _processingTicket, _restart, _highVolumeMode;
    private String _printPath;
    private int _cashierId;
    private Integer _totalGolfballMachines = 0;
    private Integer _mode = 0;
    public static final Integer PHIDGET_TEST = 1;
    public static final Integer NORMAL = 0;
    public static final Integer TEST = 2;
    private Hopper[] _hoppers = new Hopper[6];
    private int _lastHopperRingFlashed, _lastHopperQuantityFlashed = 0, _lastRapidFireHopper = 0;
    private LocalDateTime _startTime = LocalDateTime.now();
    private LocalDateTime _lastHeartbeatRecorded = LocalDateTime.now().minusMinutes(10);
    private LocalDateTime _lastCardScan = LocalDateTime.now();
    private Long _lastTicketProcessed = 0L;
    private ArrayList<TicketScan> _last10TicketsProcessed = new ArrayList<>();
    private LocalDateTime _lastInputKeyReleased = LocalDateTime.now();

    public synchronized static MainScreen getInstance(){
        if(_instance == null)
            _instance = new MainScreen();
        return _instance;
    }

    public Integer getMode(){return _mode;}

    public LocalDateTime getStartTime(){return _startTime;}

    private MainScreen() {
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            if(!_mode.equals(PHIDGET_TEST)) {
                _hopper1.closePorts();
                _hopper2.closePorts();
                _hopper3.closePorts();
                _hopper4.closePorts();
                _hopper5.closePorts();
                _hopper6.closePorts();
                try {
                    Phidget.finalize(0);
                }catch(PhidgetException ex){

                }
                System.exit(0);
            }
            }
        });
        Register.get().load();
        _acceptETabs = Register.get().getRegister().getAutoCompletGameSale();
        btnAcceptEtab.setBackground(Color.GREEN);
        btnAcceptEtab.setText("Clear Credits");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim);
        if(Settings.getSetting("topLeft.color","error").equals(""))
            Settings.setSetting("topLeft.color",chooseColor(Settings.getSetting("topLeft.name","error")));
        if(Settings.getSetting("topMiddle.color","error").equals(""))
            Settings.setSetting("topMiddle.color",chooseColor(Settings.getSetting("topMiddle.name","error")));
        if(Settings.getSetting("topRight.color","error").equals(""))
            Settings.setSetting("topRight.color",chooseColor(Settings.getSetting("topRight.name","error")));
        if(Settings.getSetting("bottomMiddle.color","error").equals(""))
            Settings.setSetting("bottomMiddle.color",chooseColor(Settings.getSetting("bottomMiddle.name","error")));
        if(Settings.getSetting("bottomLeft.color","error").equals(""))
            Settings.setSetting("bottomLeft.color",chooseColor(Settings.getSetting("bottomLeft.name","error")));
        if(Settings.getSetting("bottomRight.color","error").equals(""))
            Settings.setSetting("bottomRight.color",chooseColor(Settings.getSetting("bottomRight.name","error")));
        addUIButtons();
    }

    public void initialize(){
        ScheduledExecutorService _service = new ScheduledThreadPoolExecutor(1);
        ScheduledExecutorService _heartbeatService = new ScheduledThreadPoolExecutor(1);
        if(_mode.equals(NORMAL)) {
            _hopper1 = new Hopper(Settings.getSetting("topLeft.name", "error"), 1, "topLeft");
            _hopper2 = new Hopper(Settings.getSetting("topMiddle.name", "error"), 2, "topMiddle");
            _hopper3 = new Hopper(Settings.getSetting("topRight.name", "error"), 3, "topRight");
            _hopper4 = new Hopper(Settings.getSetting("bottomLeft.name", "error"), 4, "bottomLeft");
            _hopper5 = new Hopper(Settings.getSetting("bottomMiddle.name", "error"), 5, "bottomMiddle");
            _hopper6 = new Hopper(Settings.getSetting("bottomRight.name", "error"), 6, "bottomRight");
            _hoppers[0] = _hopper1;
            _hoppers[1] = _hopper2;
            _hoppers[2] = _hopper3;
            _hoppers[3] = _hopper4;
            _hoppers[4] = _hopper5;
            _hoppers[5] = _hopper6;
        }else if(!_mode.equals(TEST)){
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(1).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(1).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(1).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(1).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(1).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(1).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(1).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(1).getDispenseSensorChannel());
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(2).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(2).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(2).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(2).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(2).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(2).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(2).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(2).getDispenseSensorChannel());
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(3).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(3).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(3).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(3).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(3).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(3).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(3).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(3).getDispenseSensorChannel());
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(4).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(4).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(4).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(4).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(4).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(4).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(4).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(4).getDispenseSensorChannel());
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(5).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(5).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(5).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(5).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(5).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(5).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(5).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(5).getDispenseSensorChannel());
            System.out.println("HopperConfig1: ButtonLightChannel: " + HopperConfig.getHopperConfig(6).getButtonLightChannel()[0] + "," + HopperConfig.getHopperConfig(6).getButtonLightChannel()[1] + " Motor: " + HopperConfig.getHopperConfig(6).getMotorChannel()[0] + "," + HopperConfig.getHopperConfig(6).getMotorChannel()[1] + " Ring: " + HopperConfig.getHopperConfig(6).getRingChannel()[0] + "," + HopperConfig.getHopperConfig(6).getRingChannel()[1] + " Button: " +  HopperConfig.getHopperConfig(6).getButtonChannel() + " Dispense: " + HopperConfig.getHopperConfig(6).getDispenseSensorChannel());
            DigitalOutput output = null;
            Integer hub = 0;
            CDialog dialog = new CDialog(null);
            while(1 == 1) {
                dialog.setMessages("Hub", "");
                dialog.showIt();
                hub = Integer.parseInt(dialog.getValidatedText());
                dialog.setMessages("Port", "");
                dialog.showIt();
                try {
                    if (output != null) {
                        output.setState(false);
                        output.close();
                    }

                    output = new DigitalOutput();
                    output.setHubPort(hub);
                    output.setChannel(Integer.parseInt(dialog.getValidatedText()));
                    output.open();
                    int loopcount = 0;
                    while(!output.getAttached()) {
                        System.out.println("Loop: " + loopcount);
                        Thread.sleep(1000);
                        loopcount++;
                    }
                    System.out.println("output parent idString: " + output.getParent().getPhidgetIDString());
                    System.out.println("output idString: " + output.getPhidgetIDString());
                    System.out.println("output deviceVersion: " + output.getDeviceVersion());
                    System.out.println("output HubDeviceVersion: " + output.getHub().getDeviceVersion());
                    if(!Register.get().getRegister().getForceAllCCardValidate())
                        output.setLEDForwardVoltage(LEDForwardVoltage.VOLTS_5_6);
                    output.setState(true);
                    System.out.println("Output State: " + output.getState());
                } catch (PhidgetException ex) {
                    System.out.println("PhidgetExeption: " + ex.getMessage());
                    addLogEntry("Exception: " + ex);
                }catch(InterruptedException ie){
                    System.out.println("InterruptedExeption: " + ie.getMessage());
                }
            }
        }
        addBtnListener(_btnTopLeft, _hopper1);
        addBtnListener(_btnTopMiddle, _hopper2);
        addBtnListener(_btnTopRight,_hopper3);
        addBtnListener(_btnBottomLeft,_hopper4);
        addBtnListener(_btnBottomMiddle,_hopper5);
        addBtnListener(_btnBottomRight,_hopper6);
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && Util.isLong(txtInput.getText()) && Duration.between(_lastCardScan,LocalDateTime.now()).toMillis() > 2000L && !_processingTicket){
                    _lastCardScan = LocalDateTime.now();
                    if (countEnabledHoppers() > 1 || _totalGolfballMachines.equals(1)) {
                        _processingTicket = true;
                        processTicket();
                    } else if (_totalGolfballMachines > 1) {
                        PlayAudioFile.playSound("./audio/outOfBalls.wav", true, true);
                    }
                    txtInput.setText("");
                }else if(e.getKeyChar() == KeyEvent.VK_ENTER && !_processingTicket && Duration.between(_lastCardScan,LocalDateTime.now()).toMillis() > 2000L){
                    if(txtInput.getText().equals("TestNotify")) {
                        MessagingService.getInstance().sendPuttsNotification("Golfball Machine", " golfballs are out in " + Register.get().getRegister().getRegisterShortName().trim(), false, true, new String[]{"CART05"});
                    }else {
                        if (txtInput.getText().equals("credit")) {
                            PlayAudioFile.playSound("./audio/golf-start.wav", false, false);
                            _ballCredits++;
                        } else {
                            PlayAudioFile.playSound("./audio/tryAgain.wav", true, true);
                        }
                    }
                    txtInput.setText("");
                }else if(Duration.between(_lastInputKeyReleased, LocalDateTime.now()).getSeconds() > 2L){
                    txtInput.setText("");
                }
                _lastInputKeyReleased = LocalDateTime.now();
            }
        });
        _heartbeatService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    if (_lastHeartbeatRecorded.compareTo(LocalDateTime.now().minusMinutes(2)) < 0) {
                        _lastHeartbeatRecorded = LocalDateTime.now();
                        _totalGolfballMachines = 1;
                        ZonedDateTime zonedHeartbeat = _lastHeartbeatRecorded.atZone(ZoneId.systemDefault());
                        ZonedDateTime azZonedHeartbeat = zonedHeartbeat.withZoneSameInstant(ZoneId.of("America/Phoenix"));
                        Register.get().getRegister().setTimeClockDepartments(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(azZonedHeartbeat));
                        new HQuery.update("hibernate.cfg.xml", RegisterSetupEntity.class).query(Register.get().getRegister());
                        HQuery.HQueryTuple[] params = new HQuery.HQueryTuple[2];
                        params[0] = new HQuery.HQueryTuple("this", Register.get().getRegister().getRegisterId());
                        params[1] = new HQuery.HQueryTuple("four", 4);
                        ArrayList<RegisterSetupEntity> others = new HQuery.select("from RegisterSetupEntity where active=true and registerType=:four and registerId!=:this", "hibernate.cfg.xml", params).query();
                        for (RegisterSetupEntity register : others)
                            if (register.getTimeClockDepartments() != null && LocalDateTime.parse(register.getTimeClockDepartments().replace('T', ' '), Util.dtfDateTimeLong).compareTo(LocalDateTime.now().minusMinutes(30).minusSeconds(30)) > 0)
                                _totalGolfballMachines++;

                    }
                } catch (Exception ex) {
                    addLogEntry("Exception: " + ex.getMessage());
                }
            }
        },0L,20L,TimeUnit.MINUTES);
        _service.scheduleAtFixedRate(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                try {
                    if (count == 0 || count == 10 || count == 20) {
                        checkHoppersEnabled();
                        txtInput.requestFocus();
                    }
                    if (_ballCredits < 1 || Duration.between(_lastButtonPressed, LocalDateTime.now()).toMillis() > 10000L)
                        randomizeRingLights();
                    count++;
                    if (count > 29)
//                        if (count > 29 && _restart)
                        count = 0;
                    /*if (count > 30 && !_restart) {
                        try {
                            java.lang.Runtime.getRuntime().exec("java -jar /home/golf/GolfballPhidget/GolfballPhidget.jar -restart");
                            System.exit(0);
                        } catch (IOException ex) {
                        }
                    }*/
                    if(count > 1 && Duration.between(_lastInputKeyReleased,LocalDateTime.now()).toMillis() > 100 && !_processingTicket) {
                        txtInput.setText("");
                    }
                }catch(Exception ex){
                    addLogEntry("Exception: " + ex.getMessage());
                }
            }
        },800L,1000L, TimeUnit.MILLISECONDS);
        _printPath = ((PrintPathEntity) new HQuery.selectRecord("from PrintPathEntity where id=:id", "hibernate.cfg.xml",new HQuery.HQueryTuple("id",Register.get().getRegister().getReceiptPrinterId())).query()).getPath();
        _cashierId = ((CashierEntity) new HQuery.selectRecord("from CashierEntity where profileId=:kiosk", "hibernate.cfg.xml",new HQuery.HQueryTuple("kiosk",-8183)).query()).getCashierId();
        setIconImage(new ImageIcon(MainScreen.class.getResource("/image/golfland_nreg_icon.png")).getImage());

        toggleSetup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_mode.equals(TEST)) {
                    Integer hub = 0;
                    CDialog dialog = new CDialog(null);
                    dialog.setMessages("Hub", "");
                    dialog.showIt();
                    hub = Integer.parseInt(dialog.getValidatedText());
                    dialog.setMessages("Port", "");
                    dialog.showIt();
                    String response = HopperConfig.getPort(hub, Integer.parseInt(dialog.getValidatedText()));
                    addLogEntry(response);
                    if (response.contains("hopper1"))
                        _hopper1.test();
                    if (response.contains("hopper2"))
                        _hopper2.test();
                    if (response.contains("hopper3"))
                        _hopper3.test();
                    if (response.contains("hopper4"))
                        _hopper4.test();
                    if (response.contains("hopper5"))
                        _hopper5.test();
                    if (response.contains("hopper6"))
                        _hopper6.test();
                }else if(toggleSetup.isSelected()){
                    if(_acceptETabs) {
                        btnAcceptEtab.setFont(new Font("Segoe UI", Font.PLAIN, 22));
                        btnAcceptEtab.setBackground(Color.GREEN);
                    }else{
                        Font font = new Font("Segoe UI", Font.PLAIN, 22);
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                        btnAcceptEtab.setFont(new Font(attributes));
                        btnAcceptEtab.setBackground(Color.RED);
                    }
                    btnAcceptEtab.setText("Accept E-Tabs");
                }else{
                    btnAcceptEtab.setFont(new Font("Segoe UI", Font.PLAIN, 22));
                    btnAcceptEtab.setBackground(Color.GREEN);
                    btnAcceptEtab.setText("Clear Credits");
                }
            }
        });
    }

    public void sensorState(int hopper, boolean state){
        switch(hopper){
            case 1:
                radio1.setSelected(state);
                break;
            case 2:
                radio2.setSelected(state);
                break;
            case 3:
                radio3.setSelected(state);
                break;
            case 4:
                radio4.setSelected(state);
                break;
            case 5:
                radio5.setSelected(state);
                break;
            case 6:
                radio6.setSelected(state);
        }
    }

    public void setRestart(boolean restart){
        _restart = restart;
    }

    public void setMode(Integer mode){_mode = mode;}

    public int getBallCredits(){return _ballCredits;}

    private void randomizeRingLights(){
        Integer numLights;
        Integer hopper = 0;
        do {
            numLights = ThreadLocalRandom.current().nextInt(0, 3);
        }while(numLights.equals(_lastHopperQuantityFlashed));
        HashSet<Integer> lightsToFlash = new HashSet<>();
        for(int i=0;i<numLights;i++) {
            do {
                hopper = ThreadLocalRandom.current().nextInt(0, 6);
            } while (numLights == 1 && hopper.equals(_lastHopperRingFlashed) || lightsToFlash.contains(hopper));
            lightsToFlash.add(hopper);
        }
        Iterator<Integer> it = lightsToFlash.iterator();
        for(int i=0;i<numLights;i++)
            _hoppers[it.next()].flashRing();
        if(numLights == 1)
            _lastHopperRingFlashed = hopper;
        _lastHopperQuantityFlashed = numLights;
    }
    public void decrementCredits(){
        _ballCredits--;
        if(_ballCredits < 0)
            _ballCredits = 0;
        _lastButtonPressed = LocalDateTime.now();
    }

    public boolean debounceTest(){
        if(_lastButtonPressed != null)
            return Duration.between(_lastButtonPressed, LocalDateTime.now()).toMillis() > 2000L;
        else
            return true;
    }

    private boolean processBadgeScan(){
        Integer badgeInt = null;
        if(Util.isInt(txtInput.getText()))
            badgeInt = Integer.parseInt(txtInput.getText());
        if(badgeInt != null) {
            CashierEntity cashier = new HQuery.selectRecord("from CashierEntity where gameCardBarcode=:badge", "hibernate.cfg.xml", new HQuery.HQueryTuple("badge", badgeInt.toString())).query();
            if (cashier != null && cashier.getManager() && cashier.getSeniorSupervisor()) {
                PlayAudioFile.playSound("./audio/golf-start.wav", false, false);
                _ballCredits++;
                addLogEntry("BallCredits:" + _ballCredits);
                return true;
            }
        }
        return false;
    }

    private boolean processTicket(){
        ArrayList<CreditEntity> credits = new ArrayList<>();
        boolean creditFound = false;
        int totalCreditsFound = 0;
        Integer scorecardPlayers = 0;
        String[] redeemTickets = Register.get().getRegister().getEmbedThumb().split(",");
        HQuery.HQueryTuple[] params = new HQuery.HQueryTuple[4];
        params[1] = new HQuery.HQueryTuple("ticketNumber",Long.valueOf(txtInput.getText()));
        params[2] = new HQuery.HQueryTuple("thisMorning",Register.get().getThisMorning());
        for(String redeemTicket : redeemTickets) {
            params[0] = new HQuery.HQueryTuple("redeemTicket",Integer.valueOf(redeemTicket));
            credits.addAll(new HQuery.select("from CreditEntity where redeemTicketId=:redeemTicket and ticketNumber=:ticketNumber and (dateExpires is null or dateExpires>=:thisMorning)", "hibernate.cfg.xml", params).query());
//            addLogEntry("Initial tickets found: " + credits.size());
            if(credits.size() > 0) {
                params[3] = new HQuery.HQueryTuple("trans",credits.get(0).getTransactionId());
                credits.addAll(new HQuery.select("from CreditEntity where redeemTicketId=:redeemTicket and ticketNumber!=:ticketNumber and transactionId=:trans and (dateExpires is null or dateExpires>=:thisMorning)", "hibernate.cfg.xml", params).query());
//                addLogEntry("After additional: " + credits.size());
            }
        }
        if(credits.size() == 0){ //customer scanned order number barcode on bottom of receipt instead of scorecard
            params[1] = new HQuery.HQueryTuple("orderId",Long.valueOf(txtInput.getText()));
            for(String redeemTicket : redeemTickets) {
                params[0] = new HQuery.HQueryTuple("redeemTicket",Integer.valueOf(redeemTicket));
                credits.addAll(new HQuery.select("select c from CreditEntity c left join OrdersEntity o on c.transactionId=o.transactionId where c.redeemTicketId=:redeemTicket and o.orderId=:orderId and (dateExpires is null or dateExpires>=:thisMorning)", "hibernate.cfg.xml", params).query());
//            addLogEntry("Initial tickets found: " + credits.size());
            }
        }
        if(credits.size() > 0) {
            for (CreditEntity tempCredit : credits) {
                TicketTypeEntity ticketTypefound = null;
                for (TicketTypeEntity tt : _ticketTypes) {
                    if (tempCredit.getTicketTypeId().equals(tt.getTicketTypeId()))
                        ticketTypefound = tt;
                }
                if (ticketTypefound == null) {
                    ticketTypefound = new HQuery.selectRecord("from TicketTypeEntity where ticketTypeId=:id", "hibernate.cfg.xml", new HQuery.HQueryTuple("id", tempCredit.getTicketTypeId())).query();
                    if (ticketTypefound != null) {
                        _ticketTypes.add(ticketTypefound);
                    }
                }
                if (ticketTypefound != null && !ticketTypefound.isGolf() && _acceptETabs) {
                    scorecardPlayers += tempCredit.getQuantity();
                    creditFound = true;
                } else if (ticketTypefound != null && ticketTypefound.isGolf()) {
                    creditFound = true;
                }
                if (creditFound) {
                    if (tempCredit.getDateUsed() == null || tempCredit.getDateUsed().plusMinutes(tempCredit.getPlayMinutes()).isAfter(LocalDateTime.now())) {
                        if (tempCredit.getPlayMinutes() != null && tempCredit.getPlayMinutes() > 0) {
                            ArrayList<LastUsedEntity> lastUsed = getLastUsed(tempCredit.getRedeemTicketId(), Long.valueOf(txtInput.getText()));
                            if (lastUsed.size() > 0)
                                creditFound = false;
                        }
                        if (creditFound) {
                            tempCredit.dateUsed = LocalDateTime.now();
                            tempCredit.registerId = Register.get().getRegister().getRegisterId();   // This marks the credit as Scanned and not to be scanned again.
                            new HQuery.update("hibernate.cfg.xml", CreditEntity.class).query(tempCredit);
                            if (ticketTypefound.isGolf()) {
                                LastUsedEntity tempUsed = new LastUsedEntity();
                                tempUsed.setCashierId(_cashierId);
                                tempUsed.setLocationId(Register.get().getLocation().getLocationId());
                                tempUsed.setRedeemTicketId(tempCredit.getRedeemTicketId());
                                tempUsed.setRegisterId(Register.get().getRegister().getRegisterId());
                                tempUsed.setTicketNumber(tempCredit.getTicketNumber());
                                tempUsed.setTimeStamp(LocalDateTime.now());
                                new HQuery.update("hibernate.cfg.xml", LastUsedEntity.class).query(tempUsed);
                            }
                            _ballCredits += tempCredit.getQuantity();
                            totalCreditsFound += tempCredit.getQuantity();
                            if (scorecardPlayers > 0 && scorecardPlayers < 4)
                                PlayAudioFile.playSound("./audio/scanMorePlayers.wav",true,false);
                            if (scorecardPlayers >= 4)
                                GPrint.getInstance().PrintSimple(GolfScoreCards(scorecardPlayers), _printPath, false);
                        }
                    }
                }
            }
            resetRingLights();
            addLogEntry("BallCreditsAdded:" + totalCreditsFound);
            addLogEntry("TotalBallCredits:" + _ballCredits);
            if(totalCreditsFound > 0) {
                if(_ballCredits.equals(getActiveHopperCount())) {
                    dispenseBalls();
                }else{
                    _last10TicketsProcessed.add(new TicketScan(Long.valueOf(txtInput.getText())));
                    _last10TicketsProcessed.sort(new Comparator<TicketScan>() {
                        @Override
                        public int compare(TicketScan ticketScan, TicketScan t1) {
                            return ticketScan.compareTo(t1);
                        }
                    });
                    if (_last10TicketsProcessed.size() > 10)
                        _last10TicketsProcessed.remove(0);
                    if (_last10TicketsProcessed.size() < 10 || getAvgTicketProcessedTime() > 72) {
                        PlayAudioFile.playSound("./audio/golf-start.wav", true, true);
                        _highVolumeMode = false;
                    } else {
                        _highVolumeMode = true;
                        dispenseBalls();
                    }
                }
            }else if(!Long.valueOf(txtInput.getText()).equals(_lastTicketProcessed) && Util.isInt(txtInput.getText())) {
                addLogEntry("Processing badge scan1");
                if (!processBadgeScan())
                    PlayAudioFile.playSound("./audio/tryAgain.wav", true, true);
            }else{
                addLogEntry("Processing badge scan2");
                if (!processBadgeScan())
                    PlayAudioFile.playSound("./audio/golfWindow.wav", false, false);
            }
        }else if(!Long.valueOf(txtInput.getText()).equals(_lastTicketProcessed) && Util.isInt(txtInput.getText())){
            addLogEntry("Processing badge scan3");
            if(!processBadgeScan())
                PlayAudioFile.playSound("./audio/golfWindow.wav",false,false);
        }
        if(totalCreditsFound > 0)
            _lastTicketProcessed = Long.valueOf(txtInput.getText());
        _processingTicket = false;
        return totalCreditsFound > 0;
    }

    public int getActiveHopperCount(){
        int activeHoppers = 0;
        for(Hopper h : _hoppers)
            if(h.isEnabled())
                activeHoppers++;
        return activeHoppers;
    }

    public boolean highVolumeMode(){
        return _highVolumeMode;
    }

    private void dispenseBalls(){
        addLogEntry("High Volume Dispense");
        while(_ballCredits > 0){
            if(_hoppers[_lastRapidFireHopper].isEnabled()){
                _hoppers[_lastRapidFireHopper].dispense(false);
                while(_hoppers[_lastRapidFireHopper].motorRunning()) {
                    try {
                        Thread.sleep(650);
                    } catch (InterruptedException ex) {
                    }
                }

            }
            _lastRapidFireHopper++;
            if(_lastRapidFireHopper > 5)
                _lastRapidFireHopper = 0;
        }
    }

    private Long getAvgTicketProcessedTime(){
        Long average = 0L;
        Duration[] durations = new Duration[_last10TicketsProcessed.size()-1];
        for(int i = 1; i< _last10TicketsProcessed.size(); i++)
            durations[i-1] = Duration.between(_last10TicketsProcessed.get(i-1).getScanTime(), _last10TicketsProcessed.get(i).getScanTime());
        for(int i=0;i<durations.length;i++)
            average+=durations[i].getSeconds();
        return average / durations.length;
    }

    private void resetRingLights(){
        for(Hopper h : _hoppers) {
            h.resetRingLight();
        }
    }

    private String GolfScoreCards(int GQTY) {
        StringBuilder golfcards = new StringBuilder();
        int[] scoreCardTypeQuantity = new int[5];
        int totalCardsSoFar = 0;
        int totalCards = 0;
        int fourPlayer = 0;
        int threePlayer = 0;
        int twoPlayer = 0;
        int onePlayer = 0;
        boolean validEntries = true;

        scoreCardTypeQuantity[4] = GQTY / 4;
        if (scoreCardTypeQuantity[4] > 0)
            fourPlayer = scoreCardTypeQuantity[4];
        else
            fourPlayer = 0;
        totalCardsSoFar = scoreCardTypeQuantity[4] * 4;

        scoreCardTypeQuantity[3] = (GQTY - totalCardsSoFar) / 3;
        if (scoreCardTypeQuantity[3] > 0)
            threePlayer = scoreCardTypeQuantity[3];
        else
            threePlayer = 0;
        totalCardsSoFar += scoreCardTypeQuantity[3] * 3;

        scoreCardTypeQuantity[2] = (GQTY - totalCardsSoFar) / 2;
        if (scoreCardTypeQuantity[2] > 0)
            twoPlayer = scoreCardTypeQuantity[2];
        else
            twoPlayer = 0;
        totalCardsSoFar += scoreCardTypeQuantity[2] * 2;

        scoreCardTypeQuantity[1] = (GQTY - totalCardsSoFar);
        if (scoreCardTypeQuantity[1] > 0)
            onePlayer = scoreCardTypeQuantity[1];
        else
            onePlayer = 0;
        totalCards = fourPlayer + threePlayer + twoPlayer + onePlayer;
        int cardCount = 0;
        for (int i = 0; i < fourPlayer; i++) {
            cardCount++;
            golfcards.append(PrintScoreCard(4, cardCount, totalCards));
        }
        for (int i = 0; i < threePlayer; i++) {
            cardCount++;
            golfcards.append(PrintScoreCard(3, cardCount, totalCards));
        }
        for (int i = 0; i < twoPlayer; i++) {
            cardCount++;
            golfcards.append(PrintScoreCard(2, cardCount, totalCards));
        }
        for (int i = 0; i < onePlayer; i++) {
            cardCount++;
            golfcards.append(PrintScoreCard( 1, cardCount, totalCards));
        }
        return golfcards.toString();
    }

    private class TicketScan implements Comparable<TicketScan>{
        private LocalDateTime scanTime;
        private Long ticketNumber;

        public LocalDateTime getScanTime() {
            return scanTime;
        }

        public Long getTicketNumber() {
            return ticketNumber;
        }

        public TicketScan(Long ticketNum){
            this.ticketNumber = ticketNum;
            scanTime = LocalDateTime.now();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TicketScan that = (TicketScan) o;
            return scanTime.equals(that.scanTime) &&
                    ticketNumber.equals(that.ticketNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(scanTime, ticketNumber);
        }

        @Override
        public int compareTo(TicketScan tkt){
            return this.scanTime.compareTo(tkt.scanTime);
        }
    }

    private String PrintScoreCard(int CardType, int CardCount, int TotalCards) {
        StringBuilder sb = new StringBuilder();
        sb.append("                                        ");//linux temp fix //TODO delete when we get the driver fix
        sb.append("<P2>");
        sb.append("<RC10,10><LT3><HX1090>");
        sb.append("<RC20,20><F3>NAME");
        sb.append("<RC80,10><LT3><HX1090>");
        sb.append("<RC150,10><LT3><HX1090>");
        sb.append("<RC220,10><LT3><HX1090>");
        sb.append("<RC290,10><LT3><HX1090>");
        sb.append("<RC360,10><LT3><HX1090>");
        sb.append("<RC10,10><LT3><VX350>");
        sb.append("<RC10,100><LT3><VX350>");
        sb.append("<RC20,110><F3>1");
        sb.append("<RC10,150><LT3><VX350>");
        sb.append("<RC20,160><F3>2");
        sb.append("<RC10,200><LT3><VX350>");
        sb.append("<RC20,210><F3>3");
        sb.append("<RC10,250><LT3><VX350>");
        sb.append("<RC20,260><F3>4");
        sb.append("<RC10,300><LT3><VX350>");
        sb.append("<RC20,310><F3>5");
        sb.append("<RC10,350><LT3><VX350>");
        sb.append("<RC20,360><F3>6");
        sb.append("<RC10,400><LT3><VX350>");
        sb.append("<RC20,410><F3>7");
        sb.append("<RC10,450><LT3><VX350>");
        sb.append("<RC20,460><F3>8");
        sb.append("<RC10,500><LT3><VX350>");
        sb.append("<RC20,510><F3>9");
        sb.append("<RC10,550><LT3><VX350>");
        sb.append("<RC20,560><F3>10");
        sb.append("<RC10,600><LT3><VX350>");
        sb.append("<RC20,610><F3>11");
        sb.append("<RC10,650><LT3><VX350>");
        sb.append("<RC20,660><F3>12");
        sb.append("<RC10,700><LT3><VX350>");
        sb.append("<RC20,710><F3>13");
        sb.append("<RC10,750><LT3><VX350>");
        sb.append("<RC20,760><F3>14");
        sb.append("<RC10,800><LT3><VX350>");
        sb.append("<RC20,810><F3>15");
        sb.append("<RC10,850><LT3><VX350>");
        sb.append("<RC20,860><F3>16");
        sb.append("<RC10,900><LT3><VX350>");
        sb.append("<RC20,910><F3>17");
        sb.append("<RC10,950><LT3><VX350>");
        sb.append("<RC20,960><F3>18");
        sb.append("<RC10,1000><LT3><VX350>");
        sb.append("<RC20,1002><F3>TOTAL");
        sb.append("<RC10,1100><LT3><VX350>");
        sb.append("<RC440,500><LT3><VX220>");
        sb.append("<RC370,10><F3>THIS IS YOUR TICKET TO PLAY 18 HOLES OF GOLF. PLEASE");
        sb.append("<RC400,10><F3>HOLD ON TO IT. ONLY PLAYERS ARE ALLOWED ON THE COURSE.");

        sb.append("<RC590,880><F2>");
        sb.append(CardCount);           //"P##P");,USE(
        sb.append("<RC590,940>OF ");
        sb.append(TotalCards);           //"P##P");,USE(

        sb.append("<RC500,510><F2>");
        sb.append(LocalDateTime.now().format(Util.dateFormatter));        //Date
        sb.append("<RC500,620><F2>");
        sb.append(Util.dtfLocalTime.format(LocalDateTime.now()));
        sb.append("<RC500,670><F2>");
        sb.append(Register.get().getRegister().getRegisterShortName().trim());
        sb.append("<RC500,777><F2>");
        sb.append("SYSK09");
        sb.append("<RC500,895><F2>");
        sb.append("<RC570,880><F10>");
        //sb.append(@S3);,USE(PTGOLFNUM);       @@@@@@


        if (CardType == 1) {
            sb.append("<RC169,10><LT12><HX1090>");
            sb.append("<RC189,10><LT19><HX1090>");
            sb.append("<RC239,10><LT12><HX1090>");
            sb.append("<RC259,10><LT19><HX1090>");
            sb.append("<RC309,10><LT12><HX1090>");
            sb.append("<RC329,10><LT19><HX1090>");
        }

        if (CardType == 2) {
            sb.append("<RC235,10><LT40><HX1090>");
            sb.append("<RC305,10><LT40><HX1090>");
        }
        if (CardType == 3)
            sb.append("<RC300,10><LT50><HX1090>");

        sb.append("<RC520,525><RR><fP14><X4>:");//barcode start

        int BDATE = LocalDateTime.now().getDayOfYear();

        sb.append(String.format("%02d", CardCount));   // @P##P);,USE(

        sb.append(CardType);
        sb.append(":<NR>");
        sb.append("<p>");

        return sb.toString();
    }

    private ArrayList<LastUsedEntity> getLastUsed(Integer redeemTicketId,Long ticketNumber){
        HQuery.HQueryTuple[] params = new HQuery.HQueryTuple[3];
        params[0] = new HQuery.HQueryTuple("locationId", Register.get().getRegister().getLocationId());
        params[1] = new HQuery.HQueryTuple("ticketType", redeemTicketId);
        params[2] = new HQuery.HQueryTuple("ticketNumber", ticketNumber);
        ArrayList<LastUsedEntity> usedList = new HQuery.select("from LastUsedEntity s where (s.locationId = :locationId)" +
                "and (s.ticketNumber = :ticketNumber) and ((:ticketType = null)or(:ticketType = s.redeemTicketId)) order by timeStamp desc", "com/hibernate.cfg.xml",params).queryWithMax(50);
        //remove used before activitytime of register used at
        for(int i=usedList.size()-1; i>=0; i--) {
            if (usedList.get(i).getTimeStamp().compareTo(LocalDateTime.now().minusMinutes((long) usedList.get(i).getRegisterByRegisterId().getActivityTime())) <= 0)
                usedList.remove(i);
        }
        return usedList;
    }

    private void addUIButtons(){
        _btnTopLeft = new HopperFunctionButton(Settings.getSetting("topLeft.name","error"));
        _btnTopMiddle = new HopperFunctionButton(Settings.getSetting("topMiddle.name","error"));
        _btnTopRight = new HopperFunctionButton(Settings.getSetting("topRight.name","error"));
        _btnBottomLeft = new HopperFunctionButton(Settings.getSetting("bottomLeft.name","error"));
        _btnBottomMiddle = new HopperFunctionButton(Settings.getSetting("bottomMiddle.name","error"));
        _btnBottomRight = new HopperFunctionButton(Settings.getSetting("bottomRight.name","error"));
        _btnTopLeft.setBackground(Color.decode("#" + Settings.getSetting("topLeft.color","FFFAAD")));
        _btnTopMiddle.setBackground(Color.decode("#" + Settings.getSetting("topMiddle.color","FFFAAD")));
        _btnTopRight.setBackground(Color.decode("#" + Settings.getSetting("topRight.color","FFFAAD")));
        _btnBottomLeft.setBackground(Color.decode("#" + Settings.getSetting("bottomLeft.color","FFFAAD")));
        _btnBottomMiddle.setBackground(Color.decode("#" + Settings.getSetting("bottomMiddle.color","FFFAAD")));
        _btnBottomRight.setBackground(Color.decode("#" + Settings.getSetting("bottomRight.color","FFFAAD")));
        radio1.setText(Settings.getSetting("topLeft.name","error"));
        radio2.setText(Settings.getSetting("topMiddle.name","error"));
        radio3.setText(Settings.getSetting("topRight.name","error"));
        radio4.setText(Settings.getSetting("bottomLeft.name","error"));
        radio5.setText(Settings.getSetting("bottomMiddle.name","error"));
        radio6.setText(Settings.getSetting("bottomRight.name","error"));
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnTopLeft,con);
        con = new GridBagConstraints();
        con.gridx = 1;
        con.gridy = 0;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnTopMiddle,con);
        con = new GridBagConstraints();
        con.gridx = 2;
        con.gridy = 0;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnTopRight,con);
        con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 1;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnBottomLeft,con);
        con = new GridBagConstraints();
        con.gridx = 1;
        con.gridy = 1;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnBottomMiddle,con);
        con = new GridBagConstraints();
        con.gridx = 2;
        con.gridy = 1;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(_btnBottomRight,con);
    }

    public void addLogEntry(String log){
        txtOutput.append("\n" + log);
        scrollPane1.getVerticalScrollBar().setValue(scrollPane1.getVerticalScrollBar().getMaximum());
    }

    private void addBtnListener(HopperFunctionButton btn, Hopper hopper) {
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(!toggleSetup.isSelected()) {
                    addLogEntry("Button " + btn.getText() + " is " + (hopper.isEnabled() ? " enabled" : "disabled"));
                    if (hopper.isEnabled()) {
                        addLogEntry("Disabling hopper " + hopper.getHopperNumber());
                        hopper.setEnabled(false,false);
                        btn.setEnabled(false);
                    } else {
                        addLogEntry("Testing hopper " + hopper.getHopperNumber());
                        hopper.test();
                        try {
                            Thread.sleep(3000);
                            btn.setEnabled(hopper.isEnabled());
                        } catch (InterruptedException ex) {

                        }
                    }
                }else{
                    String newName = renameHopperColor("Hopper " + hopper.getHopperNumber().toString(),hopper.getHopperColor());
                    if(!newName.equals(hopper.getHopperColor())){
                        String newColor = chooseColor(newName);
                        if(!newColor.equals(btn.getBackground().toString())) {
                            Settings.setSetting(hopper.getSettingNameXML() + ".name", newName);
                            Settings.setSetting(hopper.getSettingNameXML() + ".color",newColor);
                            btn.setBackground(Color.decode("#" + newColor));
                            btn.setText(newName);
                            btn.revalidate();
                            btn.repaint();
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    private void checkHoppersEnabled(){
        _btnTopLeft.setEnabled(_hopper1.isEnabled());
        _btnTopMiddle.setEnabled(_hopper2.isEnabled());
        _btnTopRight.setEnabled(_hopper3.isEnabled());
        _btnBottomLeft.setEnabled(_hopper4.isEnabled());
        _btnBottomMiddle.setEnabled(_hopper5.isEnabled());
        _btnBottomRight.setEnabled(_hopper6.isEnabled());
    }

    private int countEnabledHoppers(){
        int count = 0;
        if(_hopper1.isEnabled())
            count++;
        if(_hopper2.isEnabled())
            count++;
        if(_hopper3.isEnabled())
            count++;
        if(_hopper4.isEnabled())
            count++;
        if(_hopper5.isEnabled())
            count++;
        if(_hopper6.isEnabled())
            count++;
        return count;
    }

    private String chooseColor(String colorName){
        String colorCode = "";
        do{
            colorCode = toHexString(JColorChooser.showDialog(this,"Choose color for " + colorName, Color.GRAY));
        }while(colorCode == null || colorCode.equals(""));
        return colorCode;
    }

    private String renameHopperColor(String hopperPosition, String currentName){
        CDialog dialog = new CDialog(null);
        dialog.setMessages("Enter color name for " + hopperPosition,"Currently " + currentName);
        dialog.showIt();
        if(dialog.getValidatedText() != null && !dialog.getValidatedText().equals(""))
            return dialog.getValidatedText();
        else
            return currentName;
    }

    private String toHexString(Color colour){
        String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return hexColour;
    }

    private void btnAcceptEtabMouseReleased(MouseEvent e) {
        if(btnAcceptEtab.getText().equals("Clear Credits")){
            _ballCredits = 0;
            addLogEntry("BallCredits:" + _ballCredits);
        }else {
            if (_acceptETabs) {
                Font font = new Font("Segoe UI", Font.PLAIN, 22);
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                btnAcceptEtab.setFont(new Font(attributes));
                btnAcceptEtab.setBackground(Color.RED);
            } else {
                btnAcceptEtab.setBackground(Color.GREEN);
                btnAcceptEtab.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            }
            _acceptETabs = !_acceptETabs;
        }
        txtInput.requestFocus();
    }

    private class HopperFunctionButton extends JButton{
        private boolean enabled;
        private Color background = null;
        @Override
        public Color getBackground() {
            return background;
        }

        public HopperFunctionButton(String buttonText){
            setText(buttonText);
            setForeground(Color.WHITE);
            setFont(new Font("Arial Black",Font.BOLD,45));
            setBorder(new MatteBorder(6, 6, 6, 6, Color.black));
        }
        @Override
        public void setEnabled(boolean enable){
            enabled = enable;
            if(!enable) {
                setForeground(background);
                setBackground(Color.GRAY);
                revalidate();
                repaint();
            }else {
                setBackground(background);
                setForeground(Color.WHITE);
                revalidate();
                repaint();
            }
        }
        @Override
        public boolean isEnabled(){
            return enabled;
        }
        @Override
        public void setBackground(Color color){
            super.setBackground(color);
            if(background == null)
                background = color;
        }
        @Override
        public void addMouseListener(MouseListener listener){
            super.addMouseListener(listener);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        toggleSetup = new JToggleButton();
        txtInput = new JTextField();
        btnAcceptEtab = new JButton();
        scrollPane1 = new JScrollPane();
        txtOutput = new JTextArea();
        panelSensors = new JPanel();
        radio1 = new JRadioButton();
        radio2 = new JRadioButton();
        radio3 = new JRadioButton();
        radio4 = new JRadioButton();
        radio5 = new JRadioButton();
        radio6 = new JRadioButton();

        //======== this ========
        setTitle("Golfland Golfball Machine");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 53, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 1.0, 0.0, 1.0, 1.0E-4};

        //---- toggleSetup ----
        toggleSetup.setText("Setup");
        toggleSetup.setBackground(new Color(0x330000));
        toggleSetup.setForeground(new Color(0xffcc66));
        toggleSetup.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        toggleSetup.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
        contentPane.add(toggleSetup, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- txtInput ----
        txtInput.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtInput.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
        contentPane.add(txtInput, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- btnAcceptEtab ----
        btnAcceptEtab.setText("Accept E-Tabs");
        btnAcceptEtab.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        btnAcceptEtab.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
        btnAcceptEtab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                btnAcceptEtabMouseReleased(e);
            }
        });
        contentPane.add(btnAcceptEtab, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== scrollPane1 ========
        {

            //---- txtOutput ----
            txtOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
            scrollPane1.setViewportView(txtOutput);
        }
        contentPane.add(scrollPane1, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //======== panelSensors ========
        {
            panelSensors.setLayout(new GridBagLayout());
            ((GridBagLayout)panelSensors.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panelSensors.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panelSensors.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panelSensors.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- radio1 ----
            radio1.setText("text");
            radio1.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radio2 ----
            radio2.setText("text");
            radio2.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio2, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radio3 ----
            radio3.setText("text");
            radio3.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio3, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- radio4 ----
            radio4.setText("text");
            radio4.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio4, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- radio5 ----
            radio5.setText("text");
            radio5.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio5, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- radio6 ----
            radio6.setText("text");
            radio6.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            panelSensors.add(radio6, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(panelSensors, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JToggleButton toggleSetup;
    private JTextField txtInput;
    private JButton btnAcceptEtab;
    private JScrollPane scrollPane1;
    private JTextArea txtOutput;
    private JPanel panelSensors;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JRadioButton radio4;
    private JRadioButton radio5;
    private JRadioButton radio6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
