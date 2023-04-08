package com;

import com.db.HQuery;
import com.db.tableclass.LocationSettingsEntity;
import com.db.tableclass.RegisterSetupEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by dhenderson@golfland.com on 3/28/17.
 */
public class Register {
    private static transient Register _instance = null;
    private Hashtable<Integer,Boolean> _embedPrefixes = new Hashtable<>();

    public static Register get() {
        if(_instance == null)
            _instance = new Register();

        return _instance;
    }

    private Register() {
        DISK_STARTUP_TOTAL = new File("/").getTotalSpace();
        DISK_STARTUP_USABLE = new File("/").getUsableSpace();
    }

    private String _hibernateFilepath = "hibernate.cfg.xml";
    private File _hibernateFile = null;
    private short _localhostNetId;
    private String _localhostIP;
    private LocationSettingsEntity _location = null;
    private RegisterSetupEntity _register = null;
    private static Long _badgeNumber;
    private static String _lastViewScreen;
    private String _databaseURL = null;
    private long DISK_STARTUP_TOTAL;
    private long DISK_STARTUP_USABLE;
    private BigDecimal _currentTaxRate;
    private Integer _language = 1;
    private Boolean _debug = false;
    public final static Integer REGISTER = 0;
    public final static Integer LAZER_TAG = 2;
    public final static Integer ATTRACTION_ATTENDANT = 3;
    public final static Integer GOLFBALL_MACHINE = 4;
    public final static Integer PRINTSERVER = 20;
    public final static Integer REGSERVER = 21;
    public final static Integer TIMECLOCK = 22;
    public final static Integer ORDERUP = 23;
    public final static Integer BACK_OF_KITCHEN = 25;
    public final static Integer FRYER_ORDERUP = 26;
    public final static Integer KIOSK = 31;
    public final static Integer KIOSK2 = 32;
    public final static Integer TURNSTILE = 41;
    public final static Integer DEV = 98;
    public final static Integer BACKEND = 99;
    private boolean _delaySingleInstanceSocket = false;
    private boolean _anotherInstanceRunning = false;
    private LocationSettingsEntity _actualLocationStore = null;
    private RegisterSetupEntity _actualRegisterStore = null;
    private boolean _loadingComplete = false;
    private boolean _highVolumeGolfRestart = false;

    public boolean attachSingleInstanceSocket(){
        try {
            ServerSocket s = new ServerSocket(12345, 10, InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            // shouldn't happen for localhost
        } catch (IOException e) {
            // port taken, so app is already running
            return true;
        }
        return false;
    }

    public boolean load() {
        if(!_delaySingleInstanceSocket)
            _anotherInstanceRunning = attachSingleInstanceSocket();

        checkDiskRequirements();


        // Load DEFAULT (not location-specific) hibernate file
        _hibernateFile = new File("hibernate.cfg.xml");
        if (!_hibernateFile.exists()) {
            JOptionPane.showMessageDialog(null,"Default hibernate.cfg.xml missing. Exiting.");
            System.exit(1);

        }
        _hibernateFile = new File("hibernate.cfg.xml");

        try {
            InetAddress address = Util.getLocalHostLANAddress();
            _localhostIP = address.getHostAddress().trim();

            try {
                Thread.sleep(1500L);
            } catch(InterruptedException e) {
                MainScreen.getInstance().addLogEntry("Error while delaying 1500ms after loading IP");
            }

            String[] parts = _localhostIP.split("\\.");
            if (Util.isNumeric(parts[2])) {
                _localhostNetId = Short.parseShort(parts[2]);
                if (_localhostNetId == 7 || _localhostNetId == 0 && Short.parseShort(parts[0]) == 10 && Short.parseShort(parts[1]) == 1)    // Change for Jim's HOme and .1 for Mesa
                    _localhostNetId = 51;
                else if(_localhostNetId == 15)//Roseville home network for backend computers
                    _localhostNetId = 59;
                else if(_localhostNetId == 5) //Camelot Home Network for backend computers
                    _localhostNetId = 55;//todo jim testing at camelot
                else if(_localhostNetId == 6) //Milipitas Home Network for backend computers
                    _localhostNetId = 56;
                else if(_localhostNetId == 3) //Golden Tee Home Network for backend computers
                    _localhostNetId = 53;
                else if(_localhostNetId == 4) //Golfland USA Home Network for backend computers
                    _localhostNetId = 54;
                else if(_localhostNetId == 2) //Emerald Hills Home Network for backend computers
                    _localhostNetId = 52;
                else if(_localhostNetId == 10) //Scandia Home Network for backend computers
                    _localhostNetId = 58;

            } else {
                // Exit if unable to parse IP
                throw new IllegalArgumentException("Localhost identifier is not numeric (" + parts[2] + ")");
            }
        } catch (UnknownHostException e) {
            MainScreen.getInstance().addLogEntry("Failed to ascertain localhost IP for register lookup. Exiting. (" + e.getMessage() + ")");
            System.exit(1);
        }

        // Query hibernate for location
        try {
            this.queryForLocationSettings();
        } catch (Exception e) {
            System.out.println("Failed to query for location record. Exiting. (" + e.getMessage() + ")");
            for (StackTraceElement element : e.getStackTrace())
                System.out.println(element.toString());
            System.exit(1);
        }

        // Query hibernate for register-specific init settings
        this.queryForRegister();
        /*if(!_register.getRegisterType().equals(DEV) && !(_register.getRegisterType().equals(BACKEND) && _register.getRegisterId() == 3) && _anotherInstanceRunning) {
            JOptionPane.showMessageDialog(null, "NOT ALLOWED!!!\n\nOnly one copy of JNREG may run on this system!!\n Exiting program!");
            System.exit(0);
        }
        if(!_delaySingleInstanceSocket && !Register.get().getRegister().getRegisterType().equals(Register.DEV) && Register.get().getRegister().getRegisterId() != 3) {
            int javaCount = 0;
            String line;
            Process p;
            try {
                if(System.getProperty("os.name").indexOf("Windows") == -1)
                    p = Runtime.getRuntime().exec("ps -e");
                else
                    p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32" + "\\tasklist.exe");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = reader.readLine()) != null)
                    if (line.contains("java"))
                        javaCount++;
                reader.close();
            } catch (IOException ex) {

            }
            if(javaCount > 1){
                JOptionPane.showMessageDialog(null, "NOT ALLOWED!!!\n\nOnly one copy of JNREG may run on this system!!\n Exiting program!");
                System.exit(0);
            }
        }*/

        return true;
    }

    public RegisterSetupEntity getRegister() {
        return _register;
    }

    public void queryForLocationSettings() throws Exception {

        SessionFactory factory;
        Session session;

        factory = new Configuration()
                .configure(_hibernateFile)
                .addAnnotatedClass(LocationSettingsEntity.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();
        session.beginTransaction();
        ArrayList<LocationSettingsEntity> locationEntities = new ArrayList<>(session.createQuery("from LocationSettingsEntity").list());
        session.getTransaction().commit();
        factory.close();

        // If we didn't get any, throw error
        if(locationEntities.size() < 0) {
            throw new Exception("No Location records were retrieved");
        }

        // Match location entity with the localhost ID (locationNet)
        for(LocationSettingsEntity entity : locationEntities) {
            if(entity.locationNet.equals(_localhostNetId)) {
                _location = entity;
                break;
            }
        }

        if(_location == null)
            throw new Exception("No Location record was matched with this register's IP identifier (set as " + _localhostNetId + ")");

        // If our hibernate filepath isn't the same as the location entity's, set it
        if(!_hibernateFilepath.equals(_location.getServerPath().trim())) {
            _hibernateFile = new File("hibernate.cfg.xml");
        }
    }

    public LocationSettingsEntity getLocation() {return _location;}

    public synchronized void queryForRegister() {
        _register = new HQuery.selectRecord("from RegisterSetupEntity where active=true and ipAddress=:ipAddress", "hibernate.cfg.xml", new HQuery.HQueryTuple("ipAddress", _localhostIP)).query();
        if (_register == null) {
            JOptionPane.showMessageDialog(null, "UNAUTHORIZED COMPUTER!!\n\nNow exiting program...");
            System.exit(1);
        }

        if (_register.getRegisterType().equals(ORDERUP) || _register.getRegisterType().equals(PRINTSERVER))
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    public LocalDateTime getThisMorning(){
        LocalDateTime thisMorning;
        LocalDateTime sevenAM = LocalDateTime.now().withHour(7).withMinute(0).withSecond(0).withNano(0);
        if (LocalDateTime.now().isBefore(sevenAM)) // todo Configure in Location Settings
            thisMorning = sevenAM.minusDays(1);
        else
            thisMorning = sevenAM;
        return thisMorning;
    }

    private void checkDiskRequirements() {
        try {
            File folder = new File("/home/cashier/media/");
            long size = Util.getFolderSize(folder.toPath());

            // If we have less than 60% disk space and the media folder is over 4 GB or over 8 GB
            if ((((double)DISK_STARTUP_TOTAL - DISK_STARTUP_USABLE) / DISK_STARTUP_TOTAL > 0.4 && size > 2147483648L) || size > 4294967296L) {
                File[] listFiles = folder.listFiles();
                long diff;
                int filesDeleted = 0;
                for (File file : listFiles) {
                    File[] images = file.listFiles();
                    for(File f : images) {
                        diff = new Date().getTime() - f.lastModified();
                        if (diff > 120 * 86400000) {
                            f.delete();
                            filesDeleted++;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"Files deleted: " + filesDeleted);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Failed to check disk space:" + e.getMessage());
        }
    }
}
