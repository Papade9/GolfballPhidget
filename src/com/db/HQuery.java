package com.db;

import com.MainScreen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.Register;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dhenderson@golfland.com on 3/25/17.
 */
public class HQuery {
    private static Integer _location = Register.get().getLocation().getLocationId();
    private static File _hibernateFile;
    private HQuery() {}
    private static Hashtable<Integer,String> _databasePasswords = new Hashtable<>();

    private static class HQueryBuilder {
        protected SessionFactory _factory;
        protected Session _session;

        private HQueryBuilder(String hibernateFile) {
            this(hibernateFile, new Class[] {});
        }

        private HQueryBuilder(String hibernateFile, Class[] annotatedClasses) {
            if(hibernateFile.equals("hibernate.cfg.xml"))
                _hibernateFile = getHibernate();
            else
                _hibernateFile = new File(hibernateFile);
            try {
                Configuration config = new Configuration().configure(_hibernateFile);
//                if(hibernateFile.equals("hibernate.cfg.xml") && !_databasePasswords.containsKey(JDBCQuery.JNREG)) {
//                    _databasePasswords.put(JDBCQuery.JNREG, renewPassword(JDBCQuery.JNREG));
//                    if(_databasePasswords.get(JDBCQuery.JNREG).equals("")){
//                        JOptionPane.showMessageDialog(null,"Unable to renew database status. Closing program...","FATAL ERROR",JOptionPane.ERROR_MESSAGE);
//                        System.exit(0);
//                    }
//                }
//                config.getStandardServiceRegistryBuilder().getAggregatedCfgXml().getMappingReferences().clear();
//                ArrayList<MappingReference> mappings = new ArrayList<>();
//                MappingReference reference = new MappingReference(MappingReference.Type.CLASS,"typeFromDB");
//                mappings.add(reference);
//                config.getStandardServiceRegistryBuilder().getAggregatedCfgXml().getMappingReferences().addAll(mappings);
                if(hibernateFile.equals("ecsHibernate.cfg.xml"))
                    config.getProperties().setProperty("hibernate.connection.url",getECSHibernate());
                for(Class annotatedClass : annotatedClasses)
                    config.addAnnotatedClass(annotatedClass);
                _factory = config.buildSessionFactory();
            } catch (Throwable e) {
                MainScreen.getInstance().addLogEntry("Failed to configure query (" + e.getMessage() + ")");
                if(!Register.get().getRegister().getRegisterType().equals(Register.KIOSK2) && !Register.get().getRegister().getRegisterType().equals(Register.TURNSTILE))
                    JOptionPane.showMessageDialog(null,"Could not connect to server!");
                throw new ExceptionInInitializerError(e);
            }

            _session = _factory.getCurrentSession();
            _session.beginTransaction();
        }

        protected void commit() {
            _session.getTransaction().commit();
        }
    }

    public static void setLocation(Integer locationId){
        _location = locationId;
    }

    public static Integer getLocation(){return _location;}

    public static String getECSHibernate(){
        StringBuilder connectionString = new StringBuilder("jdbc:sqlserver://192.168.");
        switch(_location){
            case 2:
                connectionString.append("51.92");
                break;
            case 3:
                connectionString.append("59.185");
                break;
            case 4:
                connectionString.append("55.40");
                break;
            case 5:
                connectionString.append("53.25");
                break;
            case 6:
                connectionString.append("56.29");
                break;
            case 7:
                connectionString.append("54.194");
                break;
            case 8:
                connectionString.append("52.113");
                break;
            case 9:
                connectionString.append("58.155");
                break;
            default:
                connectionString.append("51.92");
        }
        connectionString.append(":1433;databaseName=ECS7");

        return connectionString.toString();
    }

    public static Hashtable<Integer,String> getPasswords(){
        return _databasePasswords;
    }

    public static class DatabasePassword{

    }

    public static File getHibernate(){
        if(!_location.equals(Register.get().getLocation().getLocationId())) {
            switch (_location) {
                case 2:
                    return new File("mglHibernate.cfg.xml");
                case 3:
                    return new File("rosHibernate.cfg.xml");
                case 4:
                    return new File("camHibernate.cfg.xml");
                case 5:
                    return new File("golHibernate.cfg.xml");
                case 6:
                    return new File("milHibernate.cfg.xml");
                case 7:
                    return new File("gusHibernate.cfg.xml");
                case 8:
                    return new File("emeHibernate.cfg.xml");
                case 9:
                    return new File("fglHibernate.cfg.xml");
            }
        }else{
            return new File("hibernate.cfg.xml");
        }
        return null;
    }

    public static class update extends HQueryBuilder {
        public update(String hibernateFile) {
            this(hibernateFile, new Class[] {});
        }

        public update(String hibernateFile, Class annotatedClass) {
            this(hibernateFile, new Class[] {annotatedClass});
        }

        public update(String hibernateFile, Class[] annotatedClasses) {
            super(hibernateFile,annotatedClasses);
        }

        public <E> E query(E object) {
            try {
                _session.saveOrUpdate(object);
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit update query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }

            return object;
        }
    }

    public static class select extends HQueryBuilder {
        private String _queryString = null;
        private HQueryTuple[] _queryParams = null;

        public <E> select(String queryString, String hibernateFile) {
            <E>this(queryString, hibernateFile, new HQueryTuple[] {});
        }

        public <E> select(String queryString, String hibernateFile, HQueryTuple queryParam) {
            <E>this(queryString, hibernateFile, new HQueryTuple[] {queryParam});
        }

        public <E> select(String queryString, String hibernateFile, HQueryTuple[] queryParams) {
            super(hibernateFile);
            this._queryString = queryString;
            this._queryParams = queryParams;
        }

        public <E> ArrayList<E> query() {
            ArrayList<E> list = null;
            try {
                // Create query
                @SuppressWarnings("deprecation")
                org.hibernate.Query query = _session.createQuery(_queryString);

                // Add parameters
                for(HQueryTuple param : _queryParams)
                    if(param != null)
                        query.setParameter(param.id, param.obj);

                // Get the list
                list = new ArrayList<E>(query.list());
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit select query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }

            return list;
        }


        public <E> ArrayList<E> queryWithMax(Integer max) {
            ArrayList<E> list = null;
            try {
                // Create query
                @SuppressWarnings("deprecation")
                org.hibernate.Query query = _session.createQuery(_queryString);

                // Add parameters
                for(HQueryTuple param : _queryParams)
                    if(param != null)
                        query.setParameter(param.id, param.obj);

                //Add max
                query.setMaxResults(max);

                // Get the list
                list = new ArrayList<E>(query.list());
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit select query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }

            return list;
        }
    }

    public static class selectRecord extends HQueryBuilder {
        private String _queryString = null;
        private HQueryTuple[] _queryParams = null;

        public <E> selectRecord(String queryString, String hibernateFile) {
            <E>this(queryString, hibernateFile, new HQueryTuple[] {});
        }

        public <E> selectRecord(String queryString, String hibernateFile, HQueryTuple queryParam) {
            <E>this(queryString, hibernateFile, new HQueryTuple[] {queryParam});
        }

        public <E> selectRecord(String queryString, String hibernateFile, HQueryTuple[] queryParams) {
            super(hibernateFile);
            this._queryString = queryString;
            this._queryParams = queryParams;
        }

        public <E> E query() {
            LocalDateTime start;
            ArrayList<E> list = null;
            E record = null;
            try {
                // Create query
                @SuppressWarnings("deprecation")
                org.hibernate.Query query = _session.createQuery(_queryString);

                // Add parameters
                for(HQueryTuple param : _queryParams)
                    if(param != null)
                        query.setParameter(param.id, param.obj);

                // Get the list
                list = new ArrayList<E>(query.list());
                if(list != null){
                    int listSize = list.size();
                    if(listSize > 0){
                        record = list.get(listSize -1);
                    }
                }
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit select query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }

            return record;
        }


        public <E> E queryWithMax(Integer max) {
            ArrayList<E> list = null;
            E record = null;
            try {
                // Create query
                @SuppressWarnings("deprecation")
                org.hibernate.Query query = _session.createQuery(_queryString);

                // Add parameters
                for(HQueryTuple param : _queryParams)
                    if(param !=  null)
                        query.setParameter(param.id, param.obj);

                //Add max
                query.setMaxResults(max);

                // Get the list
                list = new ArrayList<E>(query.list());
                if(list != null){
                    int listSize = list.size();
                    if(listSize > 0){
                        record = list.get(listSize -1);
                    }
                }
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit select query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }

            return record;
        }
    }

    public static class delete extends HQueryBuilder {
        public delete(String hibernateFile) {
            this(hibernateFile, new Class[] {});
        }

        public delete(String hibernateFile, Class annotatedClass) {
            this(hibernateFile, new Class[] {annotatedClass});
        }

        public delete(String hibernateFile, Class[] annotatedClasses) {
            super(hibernateFile, annotatedClasses);
        }

        public <E> void query(E object) {
            try {
                _session.delete(object);
                this.commit();
            } catch(Exception ex) {
                MainScreen.getInstance().addLogEntry("Failed to commit delete query (" + ex.getMessage() + ")");
                throw ex;
            } finally {
                _factory.close();
            }
        }
    }

    public static class HQueryTuple {
        public String id;
        public Object obj;

        public HQueryTuple(String id, Object obj) {
            this.id = id;
            this.obj = obj;
        }
    }

}