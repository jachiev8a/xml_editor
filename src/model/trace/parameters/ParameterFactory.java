/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace.parameters;

import model.trace.TRACE;

/**
 *
 * @author uidj5418
 */
public class ParameterFactory {
    
    // use getParameter method to get object of type ParameterTracePoint 
    public static ParameterTracePoint getParameter(String parameterType){
        
        if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_FIX))
        {
            return new ParameterFix(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_STRING))
        {
            return new ParameterString(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_VAR))
        {
            return new ParameterVarLen(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_UINT8))
        {
            return new ParameterUint8(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_UINT16))
        {
            return new ParameterUint16(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_UINT32))
        {
            return new ParameterUint32(null);
        }
        else if (parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_INT8))
        {
            return new ParameterInt8(null);
        }
        else if (parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_INT16))
        {
            return new ParameterInt16(null);
        }
        else if (parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_INT32))
        {
            return new ParameterInt32(null);
        }
        else if(parameterType.equalsIgnoreCase(TRACE.TP_PARAM_TYPE_STRINGFIX))
        {
            return new ParameterStringFix(null);
        }
        return null;
    }

}
