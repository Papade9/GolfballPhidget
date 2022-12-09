package com;

import javax.print.*;
import java.io.*;

/**
 * Created by jim2 on 5/30/2017.
 */
public class GPrint {

    public final Character _Pexscape = 0x1B;
    public final String _Pformfeed = "\n\n\n\n\n\n"; //0x0C;
    public final String _PCutter = _Pexscape + "d1";
    private static GPrint _instance = null;

    public static GPrint getInstance(){
        if(_instance == null)
            _instance = new GPrint();
        return _instance;
    }

    public void PrintSimple(String testData,String PrintPath, Boolean ToFile) {
        String listOfPrinters="";
        String[] printerChoices;

        try {
            // Open the image file
            if((PrintPath != null)&&(!PrintPath.equals(""))) {
                if(ToFile)
                {
                    String fileName = "\\" + Register.get().getRegister().getRegisterNumber().toString() + ".OUT";

                    BufferedWriter writer = null;
                        try {
                            File f = new File(PrintPath.trim() + fileName);
                            writer = new BufferedWriter(new FileWriter(f));
                            writer.write(testData);
                        } catch (
                                Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                writer.close();
                            } catch (Exception e) {
                            }
                        }
                } else {
                    if(testData.contains("<")) {
                        int placeholder = testData.indexOf("<");
                        String workingString = testData.substring(placeholder);
                        while (workingString.contains("<")) {
                            workingString = testData.substring(placeholder);
                            int start = workingString.indexOf("<");
                            int end = workingString.indexOf(">");
                            String decodeString = PrintFileDecoder.decode(workingString.substring(start + 1, end));
                            if (decodeString != null)
                                testData = testData.replace(testData.substring(placeholder + start, placeholder + end + 1), decodeString);
                            else if(workingString.length() > end)
                                placeholder = placeholder + end + 1;
                            else
                                placeholder = placeholder + end;
                            workingString = testData.substring(placeholder);
                        }
                    }
                    InputStream is = new ByteArrayInputStream(testData.getBytes());
                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

                    // Find the default service
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(
                            flavor, null);
                    int i = 0;
                    int servicesSize = services.length;
                    printerChoices = new String[services.length];
                    if ((i < servicesSize)) {
                        //PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                        // Create the print job
                        PrintService service = services[i];
                        DocPrintJob job = service.createPrintJob();
                        Doc doc = new SimpleDoc(is, flavor, null);

                        // Monitor print job events; for the implementation of PrintJobWatcher,
                        // see e702 Determining When a Print Job Has Finished
                        PrintJobWatcher pjDone = new PrintJobWatcher(job);

                        // Print it
                        job.print(doc, null);

                        // Wait for the print job to be done
                        pjDone.waitForDone();

                        // It is now safe to close the input stream
                        is.close();
                    } else
                        MainScreen.getInstance().addLogEntry("Failed to Find Printer: " + PrintPath.trim());
                }
            }
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
