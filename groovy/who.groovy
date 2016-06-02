import groovy.sql.Sql

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./application.properties')
props.load(propsFile.newDataInputStream())
String server = props.getProperty('server')
String port = props.getProperty('port')
String database = props.getProperty('database')
String login = props.getProperty('login')
String passwd = props.getProperty('passwd')

// jConnect
String url = "jdbc:sybase:Tds:${server}:${port}/${database}?CHARSET=cp850"
String driver = "com.sybase.jdbc4.jdbc.SybDriver"
// jTDS
//String url = "jdbc:jtds:sybase://${server}:${port}/${database}?CHARSET=cp850"
//String driver = "net.sourceforge.jtds.jdbc.Driver"

sql = Sql.newInstance(
	url,
	"${login}",
	"${passwd}",
  driver)

def rowsList = sql.callWithAllRows("sp_who '32'",[],{})
println rowsList.toString()
