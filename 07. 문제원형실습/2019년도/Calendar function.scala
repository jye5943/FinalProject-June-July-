 
// Functions for week Calculation
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
 
// description: retun currentdate (format yyyy-mm-dd)
// example: currentDate()
def currentDate () : String = {
  var currentcal = Calendar.getInstance();
  var currentdatemiddle = currentcal.getTime();
  var formatCurrent = new SimpleDateFormat("yyyy-MM-dd")
  var currentDate = formatCurrent.format(currentdatemiddle)
  currentDate
}spark.sql
  
// description: convert date to week (same as isoweek)
// constraints the input shell be mathed the format [ YYYY(specific char)MM(specific char)DD ]
// example: dayToWeek("2016-11-02"), dayToWeek("2016/11/02")
def dayToWeek (targetdate : String) : String = {

  // var targetdate = "2014-12-29"
  var ccal = Calendar.getInstance();
  
  // extract divider if that is located in fifth
  var specificChar = targetdate.charAt(4).toString
  
  var refineddate = targetdate.replace(specificChar,"")
  var year = refineddate.substring(0,4).toInt
  var month = refineddate.substring(4).substring(0,2).toInt - 1
  var date = refineddate.substring(6).toInt
  
  ccal.setMinimalDaysInFirstWeek(4);
  
  // Setting if the FirstDayOfWeek Change
  ccal.setFirstDayOfWeek(Calendar.MONDAY)
  ccal.set(year,month,date) 
  var currentyear = ccal.getWeekYear
  var week = ccal.get(Calendar.WEEK_OF_YEAR).toString
  if (week.length ==1)
  {
    week = "0"+week
    }
    var result = currentyear.toString+week
    result
}

// description: get postdate of specific date
// constraints the input shell be mathed the format [ YYYY(specific char)MM(specific char)DD ]
// example: postDate("2016-11-02",2)
def postDate (targetdate : String, day : Int) : String = {
    //var targetdate = "2016-08-17"
    var ecal = Calendar.getInstance();
    
    // extract divider if that is located in fifth
    var specificChar = targetdate.charAt(4).toString
    
    var refineddate = targetdate.replace(specificChar,"")
    var year = refineddate.substring(0,4).toInt
    var month = refineddate.substring(4).substring(0,2).toInt - 1
    var date = refineddate.substring(6).toInt
    
    ecal.set(year,month,date) 
    ecal.setMinimalDaysInFirstWeek(4);
    
    // Setting if the FirstDayOfWeek Change
    ecal.setFirstDayOfWeek(Calendar.MONDAY);
    
    var i = 0
    while (i < day) {
    ecal.add(Calendar.DAY_OF_MONTH,1)
    i=i+1}
    
    var resultyear = ecal.get(Calendar.YEAR).toString
    var resultmonth = (ecal.get(Calendar.MONTH)+1).toString
    var resultdate = ecal.get(Calendar.DAY_OF_MONTH).toString
     if (resultmonth.length ==1)
    {
      resultmonth = "0"+resultmonth
    }     
    
    if (resultdate.length ==1)
    {
      resultdate = "0"+resultdate
    }
    var result = resultyear+"-"+resultmonth+"-"+resultdate
    result
  }
 
// description: get predate of specific date
// constraints the input shell be mathed the format [ YYYY(specific char)MM(specific char)DD ]
// example: preDate("2016-11-02",2)
def preDate (targetdate : String, day : Int) : String = {
    //var targetdate = "2016-08-17"
    var ecal = Calendar.getInstance();
    
    // extract divider if that is located in fifth
    var specificChar = targetdate.charAt(4).toString
    
    var refineddate = targetdate.replace(specificChar,"")
    var year = refineddate.substring(0,4).toInt
    var month = refineddate.substring(4).substring(0,2).toInt - 1
    var date = refineddate.substring(6).toInt
    
    ecal.set(year,month,date) 
    ecal.setMinimalDaysInFirstWeek(4);
    
    // Setting if the FirstDayOfWeek Change
    ecal.setFirstDayOfWeek(Calendar.MONDAY);
    
    var i = 0
    while (i < day) {
    ecal.add(Calendar.DAY_OF_MONTH,-1)
    i=i+1}
    
    var resultyear = ecal.get(Calendar.YEAR).toString
    var resultmonth = (ecal.get(Calendar.MONTH)+1).toString
    var resultdate = ecal.get(Calendar.DAY_OF_MONTH).toString
     if (resultmonth.length ==1)
    {
      resultmonth = "0"+resultmonth
    }     
    
    if (resultdate.length ==1)
    {
      resultdate = "0"+resultdate
    }
    var result = resultyear+"-"+resultmonth+"-"+resultdate
    result
  }
 
// description: get gap of two specific date
// constraints the input shell be mathed the format [ YYYY(specific char)MM(specific char)DD ]
// example: gapDate("2016-11-02","2016-11-04")
def gapDate (startdate : String, enddate : String) : Int = {
  //var targetdate = "2016-08-17"
  var ecal = Calendar.getInstance();
  
  // extract divider if that is located in fifth
  var specificChar = startdate.charAt(4).toString
  var refineddate = startdate.replace(specificChar,"")
  var refineddate2 = enddate.replace(specificChar,"")
  
  var flag = 1
  
  if(refineddate.toDouble==refineddate2.toDouble)
  {
    return 0;
  }
  
  if(refineddate.toDouble > refineddate2.toDouble)
  {
    flag = -1;
  }
      
  var year = refineddate.substring(0,4).toInt
  var month = refineddate.substring(4).substring(0,2).toInt - 1
  var date = refineddate.substring(6).toInt
  
  ecal.set(year,month,date) 
  ecal.setMinimalDaysInFirstWeek(4);
  
  // Setting if the FirstDayOfWeek Change
  ecal.setFirstDayOfWeek(Calendar.MONDAY);
  
  var gapdate = 0
  while (true) {
    
    ecal.add(Calendar.DAY_OF_MONTH,flag)
    
    var resultyear = ecal.get(Calendar.YEAR).toString
    var resultmonth = (ecal.get(Calendar.MONTH)+1).toString
    var resultdate = ecal.get(Calendar.DAY_OF_MONTH).toString
     if (resultmonth.length ==1)
    {
      resultmonth = "0"+resultmonth
    }     
    
    if (resultdate.length ==1)
    {
      resultdate = "0"+resultdate
    }
    var result = resultyear+"-"+resultmonth+"-"+resultdate

    gapdate=gapdate+1
    
    if(result==enddate){
      return gapdate;
    }

  }
  gapdate
}