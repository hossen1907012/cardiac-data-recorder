package com.example.cardiac_data_recoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ApplicationUnitTesting {


    String curr_date = "05/07/2023";
    String curr_time = "18 : 53";
    /**
     * testing addData method
     */
    @Test
    public void testAddData() {

        AddNewData dataList = new AddNewData();
        MedicalReportModel data1 = new MedicalReportModel(curr_date, curr_time, "130", "78", "88", "Seems fine");
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        MedicalReportModel data2 = new MedicalReportModel(curr_date, curr_time, "122", "71", "98", "Heart rate is not fine");
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));
    }

    /**
     * testing deleteData method
     */
    @Test
    public void testDeleteData() {


        AddNewData dataList = new AddNewData();
        MedicalReportModel data1 = new MedicalReportModel(curr_date, curr_time, "130", "78", "88", "Seems fine");
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        MedicalReportModel data2 = new MedicalReportModel(curr_date, curr_time, "122", "71", "98", "Heart rate is not fine");
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));

        dataList.deleteData(data1);
        assertEquals(1, dataList.getData().size());
        assertFalse(dataList.getData().contains(data1));

        dataList.deleteData(data2);
        assertEquals(0, dataList.getData().size());
        assertFalse(dataList.getData().contains(data2));
    }

    /**
     * testing addData method for exceptions
     */
    @Test
    public void testAddRecordException() {
        AddNewData dataList = new AddNewData();
        MedicalReportModel data1 = new MedicalReportModel(curr_date, curr_time, "130", "78", "88", "Seems fine");
        dataList.addData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.addData(data1));
    }

    /**
     * testing deleteData method for exceptions
     */
    @Test
    public void testDeleteRecordException() {


        AddNewData dataList = new AddNewData();
        MedicalReportModel data1 = new MedicalReportModel(curr_date, curr_time, "130", "78", "88", "Seems fine");
        dataList.addData(data1);

        dataList.deleteData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.deleteData(data1));
    }
}