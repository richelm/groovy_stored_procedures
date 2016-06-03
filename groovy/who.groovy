
import groovy.sql.Sql

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./sybase.properties')
props.load(propsFile.newDataInputStream())

String url = props.getProperty('url')
String driver = props.getProperty('driver')
String login = props.getProperty('login')

println "Testing sp_who"
println "url: ${url}"
println "driver: ${driver}"

def passwd = System.console().readPassword("Password: ")
//String passwd = props.getProperty('passwd')

sql = Sql.newInstance(
	"${url}",
  "${login}",
  "${passwd}",
  "${driver}")

def rowsList = sql.callWithRows("sp_who",[],{})
println rowsList.toString()
