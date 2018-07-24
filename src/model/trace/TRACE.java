/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

/**
 *
 * @author uidj5418
 */
public class TRACE {
    
    public static final int     TRACE_POINT = 0;
    public static final int     TRACE_COMMAND = 1;
    public static final int     PRIORITY_CLASS = 2;
    
    public static final String  TP_CONFIG_TYPE_STATIC =                      "static";
    public static final String  TP_CONFIG_TYPE_STATIC_LEVEL =                "staticLevel";
    public static final String  TP_CONFIG_TYPE_DYNAMIC =                     "dynamic";
    public static final String  TP_CONFIG_TYPE_DYNAMIC_SWITCH_DEFAULT_ON =   "dynSwitchDefaultOn";
    public static final String  TP_CONFIG_TYPE_DYNAMIC_SWITCH_DEFAULT_OFF =  "dynSwitchDefaultOff";
    
    public static final String  TP_ENCODING_HEX =        "hex";
    public static final String  TP_ENCODING_DEC =        "dec";
    public static final String  TP_ENCODING_BIN =        "bin";
    public static final String  TP_ENCODING_ASCII =      "ascii";
    
    public static final String  TYPE_EVENT =             "EVENT";
    public static final String  TYPE_DATA =              "DATA";
    public static final String  TYPE_DATAFIX =           "DATAFIX";
    public static final String  TYPE_DATA16 =            "DATA16";
    
    public static final String  TP_TYPE_EVENT =          "EVENT";
    public static final String  TP_TYPE_DATA =           "DATA";
    public static final String  TP_TYPE_DATAFIX =        "DATAFIX";
    
    public static final String  TC_TYPE_EVENT =          "CEVENT";
    public static final String  TC_TYPE_DATA =           "CDATA";
    public static final String  TC_TYPE_DATAFIX =        "CDATAFIX";
    public static final String  TC_TYPE_DATA16 =         "CDATA16";
    
    public static final String  TP_PARAM_TYPE_UINT8 =       "PUINT8";
    public static final String  TP_PARAM_TYPE_UINT16 =      "PUINT16";
    public static final String  TP_PARAM_TYPE_UINT32 =      "PUINT32";
    public static final String  TP_PARAM_TYPE_INT8 =        "PINT8";
    public static final String  TP_PARAM_TYPE_INT16 =       "PINT16";
    public static final String  TP_PARAM_TYPE_INT32 =       "PINT32";
    public static final String  TP_PARAM_TYPE_FIX =         "PFIX";         // parameter with fix length (default)
    public static final String  TP_PARAM_TYPE_STRING =      "PSTRING";      // parameter String
    public static final String  TP_PARAM_TYPE_STRINGFIX =   "PSTRINGFIX";   // parameter with fixed string
    public static final String  TP_PARAM_TYPE_VAR =         "PVAR";         // parameter with variable length (allowd only as last parameter)
    
    public static final String  TP_PARAM_8_LENGTH =         "1";
    public static final String  TP_PARAM_16_LENGTH =        "2";
    public static final String  TP_PARAM_32_LENGTH =        "4";
    
    public static final String  TC_PARAM_TYPE_UINT8 =       "CPUINT8";
    public static final String  TC_PARAM_TYPE_UINT16 =      "CPUINT16";
    public static final String  TC_PARAM_TYPE_UINT32 =      "CPUINT32";
    public static final String  TC_PARAM_TYPE_VAR =         "CPVAR";
    public static final String  TC_PARAM_TYPE_STRING =      "CPSTRING";
    
    public static final String  TP_PARAM_DATA_TYPE_VOID_POINTER =   "void*";
    
}
