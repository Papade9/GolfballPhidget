package com;

import java.util.Hashtable;
import java.util.Map;

public class PrintFileDecoder {
    private static Hashtable<String,String> _controlCharacters = new Hashtable();

    public PrintFileDecoder(){

    }

    public static String decode(String name){
        init();
        if(_controlCharacters.containsKey(name))
            return _controlCharacters.get(name);
        else
            return null;
    }

    public static String encodeCharacter(Character c){
        String returnString = null;
        init();
        if(_controlCharacters.containsValue(c.toString())) {
            for(Map.Entry<String,String> item : _controlCharacters.entrySet())
                if(item.getValue().equals(c.toString())) {
                    returnString = "<" + item.getKey() + ">";
                    break;
                }
        }
        return  returnString;
    }

    public static String encodeString(String s){
        String returnString = null;
        init();
        if(_controlCharacters.containsKey(s)) {
            for(Map.Entry<String,String> item : _controlCharacters.entrySet())
                if(item.getKey().equals(s)) {
                    returnString = "<" + item.getKey() + ">";
                    break;
                }
        }
        return  returnString;
    }

    private static void init(){
        if(_controlCharacters.isEmpty()){
            Character Pexscape = 0x1B;
            Character PlargePrint = 0x0E;
            Character PnormalPrint = 0x14;
            Character PsetLeftMargin = 0x6C; //[Code] ASCII ESC l n Hex. 1B 6C n Decimal 27 108 n [Defined Area] 0≤n≤255
            Character PbarCodeEnd = 0x1E;
            Character PupsideDown = 0x0F;
            Character PupsideDownCancel = 0x12;
            Character PfieldSeparator = 0x1C;
            Character Plogo1 = 0X01;

            _controlCharacters.put("Pexscape",Pexscape.toString());
            _controlCharacters.put("Pformfeed","\n\n\n\n\n\n");
            _controlCharacters.put("PCutter",Pexscape + "d1");
            _controlCharacters.put("PlargePrint",PlargePrint.toString());
            _controlCharacters.put("PnormalPrint",PnormalPrint.toString());
            _controlCharacters.put("PsetLeftMargin",PsetLeftMargin.toString());
            _controlCharacters.put("PbarCodeStart",Pexscape + "b512c");
            _controlCharacters.put("PbarCodeEnd",PbarCodeEnd.toString());
            _controlCharacters.put("SbarCodeStart",Pexscape + "b512!");
            _controlCharacters.put("PInverted", Pexscape + "4");
            _controlCharacters.put("PUpsideDown", PupsideDown.toString());
            _controlCharacters.put("PUpsideDownCancel", PupsideDownCancel.toString());
            _controlCharacters.put("PprintLogo", PfieldSeparator + "p" + Plogo1 + "0");
            _controlCharacters.put("PInvertedCancel", Pexscape + "5");
        }
    }
}
