#!/usr/bin/env groovy

import groovy.sql.Sql

// define console for user input
def cons = System.console()

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./sybase.properties')
props.load(propsFile.newDataInputStream())
String url = props.getProperty('url')
String driver = props.getProperty('driver')
String login = props.getProperty('login')

def passwd = cons.readPassword("Password: ")

sql = Sql.newInstance("${url}","${login}","${passwd}","${driver}")

String storedProcedureCall
def params
int returnValue
int colorIndex

// println("\nTesting stored procedure up_raise_error")
// def mynum = cons.readLine("myNum: ")
//
// storedProcedureCall = "{? = call up_raise_error(?)}"
// params = [Sql.INTEGER,mynum.toInteger()]
//
// try {
//   sql.call(storedProcedureCall, params) {rv ->
//     returnValue = rv
//   }
//   println("returnValue: ${returnValue}")
// } catch (Exception e) {
//   String errMessage = e.getMessage()
//   println errMessage
// }


// println("\nTesting stored procedure up_create_employee")
//
// storedProcedureCall = "{? call up_create_employee(?,?,?,?,?,?)}"
// params = [Sql.INTEGER,'Shawn2','Richs','richs2@msu.edu','6/1/2016',10,1.5]
//
// try {
//   sql.call(storedProcedureCall, params) {rv ->
//     returnValue = rv
//   }
//   println("returnValue: ${returnValue}")
// } catch (Exception e) {
//   String errMessage = e.getMessage()
//   println errMessage
// }


// println("\nTesting stored procedure up_multi_params")
// def myColor = cons.readLine("myColor: ")
//
// storedProcedureCall = "{? = call up_multi_params(?,?)}"
// params = [Sql.INTEGER,myColor,Sql.INTEGER]
// sql.call(storedProcedureCall, params) {rv,ci ->
//   returnValue = rv
//   colorIndex = ci
// }
// println("returnValue: ${returnValue}")
// println("colorIndex: ${colorIndex}")

println("\nTesting stored procedure up_retrieve_employee")
def empid = cons.readLine("Employee ID: ")

storedProcedureCall = "execute ? = up_retrieve_employee ?"
params = [Sql.INTEGER, empid.toInteger()]
def rows = sql.execute(storedProcedureCall, params) {rv,rows ->
  returnValue = rv
  println rows
}
//println returnValue
// println rows.join('\n')
//println rows
