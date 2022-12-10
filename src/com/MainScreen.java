package com;

import java.awt.event.*;
import javax.swing.border.*;
import com.db.HQuery;
import com.db.tableclass.*;
import com.phidget22.DigitalOutput;
import com.phidget22.Phidget;
import com.phidget22.PhidgetException;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
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
    private final ScheduledExecutorService _service = Executors.newSingleThreadScheduledExecutor();
    private Hopper _hopper1;
    private Hopper _hopper2;
    private Hopper _hopper3;
    private Hopper _hopper4;
    private Hopper _hopper5;
    private Hopper _hopper6;
    private static MainScreen _instance = null;
    private Integer _ballCredits = 0;
    private LocalDateTime _lastButtonPressed = null;
    private ArrayList<TicketTypeEntity> _ticketTypes = new ArrayList<>();
    private boolean _acceptETabs;
    private String _printPath;
    private int _cashierId;
    private Integer _mode = 0;
    public static final Integer PHIDGET_TEST = 1;
    public static final Integer NORMAL = 0;
    public static final Integer TEST = 2;
    private Hopper[] _hoppers = new Hopper[6];
    private int _lastHopperRingFlashed = 0;
    private LocalDateTime _startTime = LocalDateTime.now();
    private LocalDateTime _lastCardScan = LocalDateTime.now();

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
                    System.exit(0);
                    try {
                        Phidget.finalize(0);
                    }catch(PhidgetException ex){

                    }
                }
            }
        });
        Register.get().load();
        _acceptETabs = Register.get().getRegister().getAutoCompletGameSale();
        if(!_acceptETabs)
            btnAcceptEtab.setBackground(Color.GREEN);
        else
            btnAcceptEtab.setBackground(Color.RED);
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
        if(!_mode.equals(PHIDGET_TEST)) {
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
        }else{
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
                    while(!output.getAttached())
                        Thread.sleep(1000);
                    output.setState(true);
                } catch (PhidgetException ex) {
                    addLogEntry("Exception: " + ex);
                }catch(InterruptedException ie){

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
                if(e.getKeyChar() == KeyEvent.VK_ENTER && Util.isLong(txtInput.getText()) && Duration.between(_lastCardScan,LocalDateTime.now()).toMillis() > 2000L){
                    _lastCardScan = LocalDateTime.now();
                    processTicket();
                    txtInput.setText("");
                }else if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    txtInput.setText("");
                    PlayAudioFile.playSound("./audio/tryAgain.wav");
                }
            }
        });
        _service.scheduleWithFixedDelay(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count == 1) {
                    checkHoppersEnabled();
                    txtInput.requestFocus();
                }
                if(_ballCredits < 1 || Duration.between(_lastButtonPressed,LocalDateTime.now()).toMillis() > 10000L)
                    randomizeRingLights();
                count++;
                if(count > 2)
                    count = 0;
            }
        },1000,1000, TimeUnit.MILLISECONDS);
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
                }
            }
        });
    }

    public int getBallCredits(){return _ballCredits;}

    private void randomizeRingLights(){
        Integer hopper;
        do {
            hopper = ThreadLocalRandom.current().nextInt(0, 6);
        }while(hopper.equals(_lastHopperRingFlashed));
        _hoppers[hopper].flashRing();
        _lastHopperRingFlashed = hopper;
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

    private void processTicket(){
        ArrayList<CreditEntity> credits = new ArrayList<>();
        boolean creditFound = false;
        Integer scorecardPlayers = 0;
        String[] redeemTickets = Register.get().getRegister().getEmbedThumb().split(",");
        HQuery.HQueryTuple[] params = new HQuery.HQueryTuple[3];
        params[1] = new HQuery.HQueryTuple("ticketNumber",Long.valueOf(txtInput.getText()));
        params[2] = new HQuery.HQueryTuple("thisMorning",Register.get().getThisMorning());
        for(String redeemTicket : redeemTickets) {
            params[0] = new HQuery.HQueryTuple("redeemTicket",Integer.valueOf(redeemTicket));
            credits.addAll(new HQuery.select("from CreditEntity where redeemTicketId=:redeemTicket and ticketNumber=:ticketNumber and (dateExpires is null or dateExpires>=:thisMorning)", "hibernate.cfg.xml", params).query());
        }
        if(credits.size() > 0) {
            for (CreditEntity tempCredit : credits) {
                if (!creditFound) {
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
                                resetRingLights();
                                if (scorecardPlayers > 0 && scorecardPlayers < 4)
                                    PlayAudioFile.playSound("./audio/scanMorePlayers.wav");
                                else
                                    PlayAudioFile.playSound("./audio/golf-start.wav");
//                                    PlayAudioFile.playSound("./audio/chooseBall.wav");
                                if (scorecardPlayers >= 4)
                                    GPrint.getInstance().PrintSimple(GolfScoreCards(scorecardPlayers), _printPath, false);
                            }
                        }else{
                            PlayAudioFile.playSound("./audio/golfWindow.wav");
                        }
                    } else {
                        PlayAudioFile.playSound("./audio/golfWindow.wav");
                    }
                }
            }
        }else{
            PlayAudioFile.playSound("./audio/golfWindow.wav");
        }
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
        btn.addActionListener(e -> {
            if(!toggleSetup.isSelected()) {
                if (btn.isEnabled()) {
                    btn.setEnabled(false);
                    hopper.setEnabled(false);
                } else {
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
                    }
                }
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
        if(_acceptETabs)
            btnAcceptEtab.setBackground(Color.RED);
        else
            btnAcceptEtab.setBackground(Color.GREEN);
        _acceptETabs = !_acceptETabs;
    }

    private class HopperFunctionButton extends JButton{
        private boolean enabled;
        private Color background;
        @Override
        public Color getBackground() {
            return background;
        }

        public HopperFunctionButton(String buttonText){
            setText(buttonText);
            setForeground(Color.WHITE);
            setFont(new Font("Arial Black",Font.BOLD,55));
            setBorder(new MatteBorder(6, 6, 6, 6, Color.black));
        }
        @Override
        public void setEnabled(boolean enable){
            enabled = enable;
            if(!enable) {
                super.setBackground(Color.GRAY);
                super.setForeground(background);
            }else {
                super.setBackground(background);
                super.setForeground(Color.WHITE);
            }
        }
        @Override
        public boolean isEnabled(){return enabled;}
        @Override
        public void setBackground(Color color){
            super.setBackground(color);
            background = color;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        toggleSetup = new JToggleButton();
        txtInput = new JTextField();
        btnAcceptEtab = new JButton();
        scrollPane1 = new JScrollPane();
        txtOutput = new JTextArea();

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
        toggleSetup.setBackground(new Color(51, 0, 0));
        toggleSetup.setForeground(new Color(255, 204, 102));
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
            scrollPane1.setViewportView(txtOutput);
        }
        contentPane.add(scrollPane1, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
