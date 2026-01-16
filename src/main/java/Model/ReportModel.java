/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ravin
 */
public class ReportModel {
    private Date reportDate;
    private double profit, dailySales, yearSales, yearProfit;
    private int month,year;
    private String week;
    
    public ReportModel(Date reportDate, double profit, double dailySales){
        this.reportDate = reportDate;
        this.profit = profit;
        this.dailySales = dailySales;
        
    }
    public ReportModel(int month, double profit){
//        this.year = year;
        this.month = month;
        this.profit = profit;
    }
    public ReportModel(String week, double profit){
        this.week = week;
        this.profit = profit;     
    }
    public ReportModel(int year, double yearSales, double yearProfit){
        this.year = year;
        this.yearSales = yearSales;
        this.yearProfit = yearProfit;
    }
    
    
    public Date getReportDate(){
        return reportDate;
    }
    public double getProfit(){
        return profit;
    }
    public double getDailySales(){
        return dailySales;
    }
    public int getYear()
    {
        return year;
       
    }
    public int getMonth(){
        return month;
    }
    public String getWeek(){
        return week;
    } 
    public double getYearSales(){
        return yearSales;
    }
    public double getYearProfit(){
        return yearProfit;
    }
}
