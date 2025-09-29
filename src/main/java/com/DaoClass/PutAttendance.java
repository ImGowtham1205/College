package com.DaoClass;

public class PutAttendance {
	
	//Method For Get Table Name For Store The Attendance Details in Correct Table
    public String getAttendanceTable(int dno, int sem) {
        if (dno == 5 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_General_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 10 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_cs_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 15 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_AF_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 20 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_BM_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 25 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_ISM_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 30 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_CA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 35 && sem >= 1 && sem <= 6) 
            return String.format("BBA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 40 && sem >= 1 && sem <= 6) 
            return String.format("Bsc_cs_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
             
        else if (dno == 45 && sem >= 1 && sem <= 6) 
            return String.format("BCA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        return " ";
    }
}