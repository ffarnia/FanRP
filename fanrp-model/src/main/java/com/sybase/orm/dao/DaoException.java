/***********************************************************************
 * Module:  DaoException.java
 * Author:  a.gholaminejad
 * Purpose: Defines container class for data access exceptions
 ***********************************************************************/

package com.sybase.orm.dao;

import java.io.PrintStream;
import java.io.PrintWriter;


public class DaoException extends RuntimeException {


   private Throwable cause = null;

   public DaoException() {
      super();
   }


   public DaoException(String msg) {
      super( msg );
   }


   public DaoException(Throwable cause) {
      super();
      this.cause = cause;
   }
   

   public DaoException(String msg, Throwable cause) {
      super( msg );
      this.cause = cause;
   }
   

   public Throwable getCause() {
      return cause;
   }


   public String getMessage() {
      if ( super.getMessage() != null ) {
         return super.getMessage();
      }
      else if ( cause != null ) {
         return cause.toString();
      }
      else {
         return null;
      }
   }
   
   public void printStackTrace(){
      printStackTrace(System.err);        
   }


   public void printStackTrace(PrintStream out){
       synchronized ( out ) {
          PrintWriter pw = new PrintWriter( out, false );
          printStackTrace( pw );
          // Flush the PrintWriter before it's GC'ed.
          pw.flush();
      }  
    }

   public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        if (cause != null)
        {
            s.println("Caused by:");
            cause.printStackTrace(s);
        }
    }

}